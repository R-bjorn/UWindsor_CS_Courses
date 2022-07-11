#include <cstddef>		// std::size_t
#include <vector>		// std::vector
#include <limits>		// std::numeric_limits
#include <numeric>		// std::iota
#include <algorithm>	// std::transform
#include <math.h>		// std::exp
#include <iomanip>		// std::setprecision 
#include <iostream>		// std::cout
#include <cmath>		// std::pow
#include <execution>	// std::execution:par_unseq


#include "benchmark.hxx"
#include "iota_iterator.hxx"

template <typename Domain, typename Func, typename ExecutionPolicy>

auto numerical_integration(
	ExecutionPolicy&& ep,
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
	// Declare delta_x to have type Domain const and set its value to be (b − a) ÷ N.
	// Declare half_delta_x to have type Domain const and set its value to be delta_x / 2.
	Domain const delta_x = (b - a) / N;
	Domain const half_delta_x = delta_x / 2;

	return std::transform_reduce(std::forward<ExecutionPolicy>(ep), iota_iterator<size_t>{}, iota_iterator<size_t>{N}, decltype(f(a)){}, [](auto x, auto y) {return x + y; }, [&](auto c) { return f(c) * delta_x; });
}

int main(int args, char**argv) {
	if (args == 0) {
		return EXIT_FAILURE;
	}
	for (std::size_t i = 1; i <= 10000; i *= 10) {
		double estimate{};
		double get = numerical_integration(std::execution::par, i, 0.0, 1.0,
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

		double sec = benchmark(&numerical_integration, argv);
		std::cout << "integration estimate of f over [0,1] using " << i << " intervals taking " << sec << estimate << std::defaultfloat << std::setprecision(std::numeric_limits<double>::digits10 + 1)
#ifndef USE_PI_ESTIMATE
			<< get << '\n';
#else
			<< 4 * get << '\n';
#endif
	}
}