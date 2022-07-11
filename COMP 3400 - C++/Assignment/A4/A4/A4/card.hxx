#ifndef card_hxx_
#define card_hxx_

//===========================================================================

#include <algorithm>
#include <iterator>
#include <stdexcept>
#include <optional>
#include <type_traits>
#include <iostream>
  
//===========================================================================

class card
{
public:
  using face_type = char;
  enum { invalid=0, ace=1, jack=11, queen=12, king=13 };

  enum suit_type : char { club, spade, diamond, heart };

private:
  face_type face_;
  suit_type suit_;

public:
  // default construct invalid card value when face_ == 0...
  constexpr card() : face_{} { };

  constexpr card(card const&) = default;
  constexpr card(card&&) = default;
  constexpr card& operator =(card const&) = default;
  constexpr card& operator =(card&&) = default;

  constexpr card(face_type const& f, suit_type const& s) :
    face_{f},
    suit_{s}
  {
    if (!valid())
      throw std::domain_error("Invalid card.");
  }

  constexpr auto operator <=>(card const&) const noexcept = default;

  constexpr operator bool() const noexcept
  {
    return valid();
  }

  constexpr bool valid() const noexcept
  { 
    return face_ <= king; 
  }

  constexpr face_type face() const noexcept { return face_; }
  constexpr suit_type suit() const noexcept { return suit_; }

  constexpr void invalidate() noexcept
  {
    face_ = invalid;
  }

  constexpr void set(face_type const& f, suit_type const& s)
  {
    card tmp(f,s);
    swap(tmp);
  }

  constexpr void swap(card& b) noexcept
  {
    using std::swap;
    swap(face_, b.face_);
    swap(suit_, b.suit_);
  }

  friend std::istream& operator >>(std::istream&, card&);
  friend std::ostream& operator <<(std::ostream&, card const&);
};

inline constexpr auto operator <=>(
  card::suit_type const& a, 
  card::suit_type const& b
) noexcept
{
  return 
    static_cast<std::underlying_type_t<card::suit_type>>(a) 
    <=> static_cast<std::underlying_type_t<card::suit_type>>(b)
  ;
}

inline constexpr void swap(card& a, card& b) noexcept
{
  a.swap(b);
}

//===========================================================================

struct card_faces_begin_t final { };
constexpr card_faces_begin_t card_faces_begin{};

class card_faces
{
private:
  card::face_type face_;

public:
  constexpr card_faces(card_faces_begin_t const&) noexcept :
    face_{card::ace}
  {
  }

  constexpr card_faces(card::face_type const& f = card::king+1) :
    face_{f}
  {
  }

  constexpr card_faces(card_faces const&) 
    noexcept = default;
  constexpr card_faces& operator =(
    card_faces const&) noexcept = default;

  constexpr bool operator ==(card_faces const& b) const 
    noexcept = default;

  constexpr card::face_type const& operator *() const noexcept
  {
    return face_;
  }

  // prefix ++
  constexpr card_faces& operator ++() noexcept
  {
    ++face_;
    return *this;
  }

  // postfix ++
  constexpr card_faces operator ++(int) noexcept
  {
    card_faces retval(*this);
    this->operator ++();
    return retval;
  }
};

//===========================================================================

struct card_suits_begin_t final { };
constexpr card_suits_begin_t card_suits_begin{};

class card_suits
{
private:
  std::optional<card::suit_type> suit_;

public:
  // default constructor constructs invalid suit...
  constexpr card_suits() noexcept :
    suit_{}
  {
  }

  constexpr card_suits(card_suits_begin_t const&) noexcept :
    suit_{card::club}
  {
  }

  constexpr card_suits(card::suit_type const& s) :
    suit_{s}
  {
  }

  constexpr card_suits(card_suits const&) noexcept = default;
  constexpr card_suits& operator =(card_suits const&) noexcept = default;

  constexpr bool operator ==(card_suits const& b) const 
    noexcept = default;

  constexpr card::suit_type const& operator *() const noexcept
  {
    return *suit_;
  }

  // prefix ++
  constexpr card_suits& operator ++() noexcept
  {
    if (suit_)
    {
      switch (*suit_)
      {
        case card::club:    suit_ = card::spade; break;
        case card::spade:   suit_ = card::diamond; break;
        case card::diamond: suit_ = card::heart; break;
        default:
        case card::heart:   suit_.reset(); break;
      }
    }
    return *this;
  }

  // postfix ++
  constexpr card_suits operator ++(int) noexcept
  {
    card_suits retval(*this);
    this->operator ++();
    return retval;
  }
};

//===========================================================================

//
// For I/O purposes:
//   * a "0" face is an invalid card.
//   * a "1" face is a 10.
//   * a "A", "J", "Q", or "K" face is the same.
//   * otherwise the face is a number between 2 and 9.
//   * the suit is one of "H", "D", "C", or "S" representing
//     hearts, diamonds, clubs, and spades respectively
//   * when read in, the suit or face can be in lower case
//   * when written out, the suit or face will always be upper case
//
inline std::istream& operator >>(std::istream& is, card& c)
{
  std::istream::sentry s(is);
  if (s)
  {
    // read in card "number"...
    char ch;
    if (is >> ch) // this can skip whitespace
    {
      switch (ch)
      {
        case '0': c.face_ = card::invalid; break;
        case 'a':
        case 'A': c.face_ = card::ace; break;
        case '2': c.face_ = 2; break;
        case '3': c.face_ = 3; break;
        case '4': c.face_ = 4; break;
        case '5': c.face_ = 5; break;
        case '6': c.face_ = 6; break;
        case '7': c.face_ = 7; break;
        case '8': c.face_ = 8; break;
        case '9': c.face_ = 9; break;
        case '1': c.face_ = 10; break;
        case 'j':
        case 'J': c.face_ = card::jack; break;
        case 'q':
        case 'Q': c.face_ = card::queen; break;
        case 'k':
        case 'K': c.face_ = card::king; break;

        default:
          is.setstate(std::ios::failbit);
          is.unget();
          return is;
      }
    }

    // read in card suit...
    auto ch2 = is.get(); 
      // i.e., unformatted call to get char so no whitespace is skipped
    if (ch2 == std::istream::traits_type::eof())
    {
      // NOTE: unformatted calls return traits_type::eof() to indicate
      //       an error. This is like checking for a EOF or -1 error when
      //       doing file I/O in C.
      is.setstate(std::ios::badbit);
      return is;
    }

    switch (static_cast<char>(ch2)) // all is okay... cast to char
    {
      case 'C': c.suit_ = card::club; break;
      case 'S': c.suit_ = card::spade; break;
      case 'H': c.suit_ = card::heart; break;
      case 'D': c.suit_ = card::diamond; break;

      default:
        is.setstate(std::ios::badbit); // invalid suit
        break;
    }
  }
  return is;
}

inline std::ostream& operator <<(std::ostream& os, card const& c)
{
  std::ostream::sentry s(os);
  if (s)
  {
    // output number/face...
    switch (c.face_)
    {
      case card::invalid:   os << '0'; break;
      case card::ace:       os << 'A'; break;
      case card::jack:      os << 'J'; break;
      case card::queen:     os << 'Q'; break;
      case card::king:      os << 'K'; break;
      case 10:              os << '1'; break;
      default:              os << static_cast<short>(c.face_); break;
    }

    // output suit...
    switch (c.suit_)
    {
      case card::club:      os << 'C'; break;
      case card::spade:     os << 'S'; break;
      case card::diamond:   os << 'D'; break;
      case card::heart:     os << 'H'; break;
    }
  }
  return os;
}

//===========================================================================

#endif // #ifndef card_hxx_ 
