#include <deque>
#include <iterator>
#include <random>
#include <numeric>
#include <iostream>
#include "card.hxx"

using namespace std;
using cards = std::deque<card>;

ostream& operator <<(ostream& os, cards const& c)
{
	ostream_iterator<card> it(cout, " ");
	copy(c.begin(), c.end(), it);
	return os;
}

cards gen_deck_of_cards()
{
	cards retval;
	for (card_faces f(card_faces_begin), fEnd; f != fEnd; ++f)
		for (card_suits s(card_suits_begin), sEnd; s != sEnd; ++s)
			retval.push_back(card{ *f,*s });

	return retval;
}

void shuffle_cards(cards& cs)
{
	random_device rd;
	mt19937 gen(rd());
	shuffle(cs.begin(), cs.end(), gen);
}

cards draw(size_t num, cards& desk)
{
	auto pos = desk.begin();
	if (num <= desk.size()) {
		advance(pos, num);
	}
	else {
		pos = desk.end();
	}

	cards cd(desk.begin(), pos);

	desk.erase(desk.begin(), pos);

	return cd;
}

auto calc_score(cards const& hand)->std::pair<std::size_t, std::size_t>
{
	size_t low = accumulate(hand.begin(), hand.end(), size_t{}, [](size_t const& sum, card const& hand) {
		return sum + hand.face();
		});
	size_t high = accumulate(hand.begin(), hand.end(), size_t{}, [](size_t const& sum, card const& hand) {
		if (hand.face() == card::ace) { return sum + 11; }
		return sum + hand.face();
		});
	return{ low, high };
}

int main() 
{
	cards deck = gen_deck_of_cards();
	shuffle_cards(deck);
	cout << "Deck: " << deck << '\n';
	cout << '\n';
	cout << "Drawing 3 cards...\n";
	
	cards hand = draw(3, deck);
	cout << "Deck: " << deck << '\n';
	cout << "Hand: " << hand << '\n';

	auto result = calc_score(hand);

	if (get<0>(result) == get<1>(result) ) 
	{
		cout << "Score: " << get<0>(result) << '\n';
	}
	else
	{
		cout << "Possible Score: " << get<0>(result) << ' ' << get<1>(result) << '\n';
	}
}