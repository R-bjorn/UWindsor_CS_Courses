#include <algorithm>
#include <iterator>
#include <vector>
#include <iostream>

using namespace std;

struct card 
{
	enum class suit { club, spade, diamond, heart };
	enum { ace=1, jack=11, queen=12, king=13 };
	using number = int;

	number num_;
	suit suit_;
};

bool operator ==(card const& a, card const& b)
{
	return a.num_ == b.num_ && a.suit_ == b.suit_;
}

bool operator <(card const& a, card const& b)
{
	return a.num_ <  b.num_;
}

istream& operator >>(istream& is, card& c)
{
	is >> c.num_;

	char ch;
	if(is >> ch)
	{
		switch(ch)
		{
			case 'C': c.suit_ = card::suit::club;break;
			case 'S': c.suit_ = card::suit::spade;break;
			case 'H': c.suit_ = card::suit::heart;break;
			case 'D': c.suit_ = card::suit::diamond;break;
			default:
				is.setstate(ios::failbit);
			  	break;
		}
	}
	else
		is.setstate(ios::badbit);
	return is;
}

ostream& operator <<(ostream& os, card const& c)
{
	os << c.num_;

	switch(c.suit_)
	{
		case card::suit::club:	os<<'C';break;
		case card::suit::spade:	os<<'S';break;
		case card::suit::diamond:	os<<'D';break;
		case card::suit::heart:	os<<'H';break;
	}
	return os;
}

int main()
{
	card mycards[100];
	card in[100];
	int in_count=0;
	int i = 0;
	// Getting all the input
	while(cin >> mycards[i++])
	{
	}

	//Sorting by number
	for(int it = 0 ; it < i-1 ; it++)
	{
		for(int itj = it+1 ; itj < i-1 ; itj++)
		{
			if(mycards[it].num_ > mycards[itj].num_)
			{
				card temp = mycards[it];
				mycards[it] = mycards[itj];
				mycards[itj] = temp;
			}
		}
	}
	
	//Getting the output
	for(int j=0;j<i-1;j++)
	{ 
		int count = 1;
		int it = j;
		int temp = j;
		card current = mycards[j];

		while(mycards[++it] == current)
		{
			count++;
			j++;
		}

		cout << mycards[temp] << " occurs " << count << " times\n";
	}
}








