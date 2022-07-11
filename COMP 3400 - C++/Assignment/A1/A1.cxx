#include <climits>
#include <string>
#include <iostream>

struct score
{
	std::string first_name_;
	std::string last_name_;
	unsigned int score_;
};

std::istream& operator >>(std::istream& is, score& s)
{
	is >> s.first_name_ >> s.last_name_ >> s.score_;
	return is;	
}

std::ostream& operator <<(std::ostream& os, score& s)
{
	os << s.first_name_ << " " << s.last_name_ << " " << s.score_ << "\n";
	return os;
}

int main()
{
	score student;
	unsigned int i = 0;
	while(std::cin >> student && ++i != UINT_MAX)
		std::cout << student;
}