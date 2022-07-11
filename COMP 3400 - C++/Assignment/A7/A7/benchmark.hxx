#ifndef comp3400_2021w_benchmark_hxx_
#define comp3400_2021w_benchmark_hxx_

#include <chrono>
  
template <typename Func, typename... Args>
double benchmark(Func&& f, Args&&... args)
{
  using namespace std::chrono;
  auto const start = steady_clock::now(); 
  try 
  {
    //f(  args...  );
    f(  std::forward<Args>(args)...  );
  }
  catch (...)
  {
    // eat any exception
  }
  auto const stop = steady_clock::now(); 
  return 
    duration<double>(
      stop - start
    ).count()
  ;
}

#endif // #ifndef comp3400_2021w_benchmark_hxx_
