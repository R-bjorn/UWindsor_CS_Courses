#include <cstddef>		// std::size_t
#include <vector>		// std::vector
#include <limits>		// std::numeric_limits
#include <numeric>		// std::iota
#include <algorithm>	// std::transform
#include <math.h>		// std::exp
#include <iomanip>		// std::setprecision 
#include <iostream>		// std::cout
#include <cmath>		// std::pow


template <typename Domain, typename Func>
auto numerical_integration_serial(
	std::size_t N,
	Domain const& a,
	Domain const& b,
	Func&& f
)
{
	// If N is zero, set it to one so that it does not have to deal with division by zero.
	if (N == 0) {
		N = 1;
	}
	// Declaring a vector and Call std::iota() to populate the vector with values starting at 0.
	std::vector<std::size_t> indices(N);
	// Calling std::iota
	std::iota(indices.begin(), indices.end(), 0);

	// Declare delta_x to have type Domain const and set its value to be (b − a) ÷ N.
	// Declare half_delta_x to have type Domain const and set its value to be delta_x / 2.
	Domain const delta_x = (b - a) / N;
	Domain const half_delta_x = delta_x / 2;
	// Declare a vector containing type Domain values. The vector has N elements.
	std::vector<Domain> MidPointvalues(N);

	// Calling std::transform to map each indices element to that interval's midpoint (i.e., "x") value as follows:
	std::transform(indices.begin(), indices.end(), MidPointvalues.begin(),
		// lambda function as forth arg
		[&](std::size_t x) {
			return (x * delta_x) + half_delta_x;
		}
	);

	return std::transform_reduce(MidPointvalues.begin(), MidPointvalues.end(), decltype(f(a)){}, [](auto x, auto y) {return x + y; }, [&](auto c) { return f(c) * delta_x; });
}

int main() {
	for (std::size_t i = 1; i <= 10000; i *= 10) {
		auto get = numerical_integration_serial(i, 0.0, 1.0,
#ifdef USE_EXP_FUNC
			// lambda function
			[](double const& x) {
				return exp((-1) * (x * x));
			}  // end lambda function
#elif defined(USE_PI_ESTIMATE)
			[](double const& x) {
				return 1.0 / (1.0 + (x * x));
			}
#else 
#error "USE_EXP_FUNC or USE_PI_ESTIMATE must be #defined."
#endif
		);
		std::cout << "integration estimate of f over [0,1] using " << i << " intervals: " << std::scientific << std::setprecision(std::numeric_limits<double>::digits10 + 1)
#ifndef USE_PI_ESTIMATE
			<< get << '\n';
#else
			<< 4 * get << '\n';
#endif
	}
}