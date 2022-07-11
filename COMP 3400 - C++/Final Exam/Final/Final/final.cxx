#include <iostream>
#include <optional>
#include <vector>
#include <map>
#include <cmath>
#include <algorithm>
#include <iterator>
#include <cstdint>
#include <numeric>


using namespace std;

template<typename T>

ostream& operator <<(ostream& os, std::optional<T> const& opt)
{
	return opt ? os << opt.value() : os;
}

template<typename T>

ostream& operator <<(ostream& os, std::vector<T> const& vec)
{
	for (auto item : vec) {
		os << item << " ";
	}
	return os;
}

template<typename Key, typename Value>

ostream& operator <<(ostream& os, std::map<Key, Value> const& mp)
{
	os << "{ ";
	for (auto item : mp)
	{
		if (item.second == 1)
			os << item.first << " ";
		else
			os << item.first << "^" << item.second << " ";
	}
	os << "}";
	return os;
}

std::vector<std::uintmax_t> primes_via_trial_division(std::uintmax_t const& n)
{
	vector< std::uintmax_t> primes;
	for (std::uintmax_t i = 2; i < n; i++)
	{
		if (std::all_of(begin(primes), end(primes),
			[&](auto prime_number)
			{
				if (i % prime_number != 0)
					return true;
				return false;
			}))
			primes.push_back(i);
	}
	return primes;
}

std::optional<std::map<uintmax_t, size_t>> prime_factors_via_trial_division(uintmax_t const& n, std::vector<uintmax_t> const& primes)
{
	std::map<uintmax_t, size_t> factors;
	if (primes.empty() || primes[primes.size()-1] < floor(sqrt(n)))
		return std::nullopt;
	uintmax_t left_over = n;
	auto primesEnd = std::upper_bound(begin(primes), end(primes), floor(sqrt(n)));
	std::for_each(begin(primes), primesEnd, [&](auto prime)
		{
			while (left_over % prime == 0)
			{
				left_over /= prime;
				cout << prime;
				if (factors[prime] != 1)
					factors[prime] = 1;
				else
					factors[prime]++;
			}
		}
	);

	if (left_over != 1) {
			factors[left_over]++;
	}
	return factors;
}

int main()
{

	if (uintmax_t max_number; cin >> max_number)
	{
		auto const primes = primes_via_trial_division(max_number);

		using size_type = decltype(primes)::size_type;

		cout
			<< "There are " << primes.size() << " primes between 0 and "
			<< max_number << '\n'
			<< "The first 15 primes are: "
			;
		// WRITE FIRST STD::COPY CALL HERE!
		std::copy(begin(primes), begin(primes) + min(primes.size(), size_type{ 15 }), std::ostream_iterator<uintmax_t>(std::cout, " "));
		cout << "\nThe last 15 primes are: ";
		// WRITE SECOND STD::COPY CALL HERE!
		std::copy(end(primes)-min(primes.size(), size_type{ 15 }), end(primes), std::ostream_iterator<uintmax_t>(std::cout, " "));
		cout << '\n';

		for (uintmax_t i; cin >> i; )
		{
			auto const prime_factors = prime_factors_via_trial_division(i, primes);
			cout << "factors(" << i << "): ";
			if (prime_factors)
				cout << prime_factors;
			else
				cout << "unable to compute from calculated primes";
			cout << '\n';
		}
	}
}