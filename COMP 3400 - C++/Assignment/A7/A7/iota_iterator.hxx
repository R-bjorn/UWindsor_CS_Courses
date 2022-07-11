#ifndef comp3400_2021w_iota_iterator_hxx_
#define comp3400_2021w_iota_iterator_hxx_

//===========================================================================

#include <cstdlib>
#include <limits>
#include <type_traits>

//===========================================================================

template <typename T>
class iota_iterator
{
public:
  using value_type = T const;
  using difference_type = std::ptrdiff_t;
  using pointer = value_type*;
  using reference = value_type&;
  using iterator_category = std::random_access_iterator_tag;

private:
  mutable T dist_;

public:
  constexpr iota_iterator() : 
    dist_{} 
  { 
  }

  constexpr iota_iterator(value_type const& v) : 
    dist_(v) 
  { 
  }

  constexpr iota_iterator(iota_iterator const&) = default;
  constexpr iota_iterator& operator =(iota_iterator const&) = default;

  constexpr iota_iterator(iota_iterator&) = default;
  constexpr iota_iterator& operator =(iota_iterator&) = default;

  ~iota_iterator() = default;

  constexpr bool operator ==(iota_iterator const& it) const 
    noexcept(noexcept( dist_ == it.dist_ ))
    { return dist_ == it.dist_; }
  constexpr bool operator !=(iota_iterator const& it) const 
    noexcept(noexcept( dist_ != it.dist_ ))
    { return dist_ != it.dist_; }
  
  constexpr bool operator <(iota_iterator const& it) const 
    noexcept(noexcept( dist_ < it.dist_ ))
    { return dist_ < it.dist_; }
  constexpr bool operator <=(iota_iterator const& it) const 
    noexcept(noexcept( dist_ <= it.dist_ ))
    { return dist_ <= it.dist_; }
  constexpr bool operator >=(iota_iterator const& it) const 
    noexcept(noexcept( dist_ >= it.dist_ ))
    { return dist_ >= it.dist_; }
  constexpr bool operator >(iota_iterator const& it) const 
    noexcept(noexcept( dist_ > it.dist_ ))
    { return dist_ > it.dist_; }

  constexpr reference operator *() const noexcept
    { return dist_; }
  constexpr pointer operator ->() const noexcept
    { return &dist_; }

  constexpr iota_iterator& operator ++() 
    noexcept(noexcept( ++dist_ ))
    { ++dist_; return *this; }
  constexpr iota_iterator operator ++(int) 
    noexcept(
      std::is_nothrow_copy_constructible_v<iota_iterator> &&
      noexcept( ++dist_ )
    )
    { iota_iterator tmp(*this); this->operator ++(); return tmp; }

  constexpr iota_iterator& operator --() 
    noexcept(noexcept( --dist_ ))
    { --dist_; return *this; }
  constexpr iota_iterator operator --(int) 
    noexcept(
      std::is_nothrow_copy_constructible_v<iota_iterator> &&
      noexcept( --dist_ )
    )
    { iota_iterator tmp(*this); this->operator --(); return tmp; }

  constexpr difference_type operator -(iota_iterator const& it) const 
    noexcept(noexcept( dist_ - it.dist_ ))
  { 
    return dist_ - it.dist_; 
  }

  constexpr iota_iterator& operator +=(difference_type const& n) 
    noexcept(noexcept( dist_ += n ))
  {
    dist_ += n;
    return *this; 
  }

  constexpr iota_iterator& operator -=(difference_type const& n) 
    noexcept(noexcept( dist_ -= n ))
  { 
    dist_ -= n;
    return *this;
  }

  constexpr iota_iterator operator +(difference_type const& n)
    noexcept(
      std::is_nothrow_constructible_v<
        iota_iterator, 
        difference_type const&
      > &&
      noexcept(std::declval<iota_iterator>() += n)
    )
  {
    iota_iterator retval(*this);
    retval += n;
    return retval;
  }
  
  constexpr iota_iterator operator -(difference_type const& n)
    noexcept(
      std::is_nothrow_constructible_v<
        iota_iterator, 
        difference_type const&
      > &&
      noexcept(std::declval<iota_iterator>() -= n)
    )
  {
    iota_iterator retval(*this);
    retval -= n;
    return retval;
  }

  class value_proxy final
  {
  private:
    value_type value_;

  public:
    constexpr value_proxy(value_type const& value) noexcept :
      value_{value}
    {
    }

    constexpr operator reference() const noexcept
    {
      return value_;
    }
  };

  constexpr value_proxy operator [](std::size_t i) const 
  {
    using std::numeric_limits;

    iota_iterator result(*this);

    // std::size_t is unsigned and difference_type is signed equivalent so...
    for (; 
      i > numeric_limits<difference_type>::max(); 
      i -= numeric_limits<difference_type>::max()
    )
      result += numeric_limits<difference_type>::max();

    return { *result };
  }

  template <typename Op, typename... Args>
  constexpr decltype(auto) operator()(Op&& op, Args&&... args) const
    noexcept(noexcept(
      std::forward<Op>(op)(
        this->dist_,
        std::forward<Args>(args)...
      )
    ))
  {
    return
      std::forward<Op>(op)(
        this->dist_,
        std::forward<Args>(args)...
      )
    ;
  }
};

template <typename T>
constexpr iota_iterator<T> operator +(
  typename iota_iterator<T>::difference_type const& n, 
  iota_iterator<T> const& i
) noexcept(
  std::is_nothrow_copy_constructible_v<
    iota_iterator<T>
  > &&
  noexcept(std::declval<iota_iterator<T>>() += n)
)
{
  iota_iterator<T> retval(i);
  retval += n;
  return retval;
}
  
template <typename T>
constexpr iota_iterator<T> operator -(
  typename iota_iterator<T>::difference_type const& n, 
  iota_iterator<T> const& i
) noexcept(
  std::is_nothrow_copy_constructible_v<
    iota_iterator<T>
  > &&
  noexcept(std::declval<iota_iterator<T>>() -= n)
)
{
  iota_iterator<T> retval(i);
  retval -= n;
  return retval;
}

//===========================================================================

#endif // #ifndef comp3400_2021w_iota_iterator_hxx_
