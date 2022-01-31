/**
   This class generates all prime numbers.
*/
public class PrimeGenerator
{
   private int current;

   public PrimeGenerator()
   {
      current = 1;
   }

   /**
      Calculates the next prime number.
      @return the next prime number
   */
   public int nextPrime()
   {
      do
      {
         current++;
      }
      while (!isPrime(current));

      return current;
   }

   public static boolean isPrime(int n)
   {
      if (n <= 1)
         return false;
      if (n > 2 && n % 2 == 0)
         return false;
      
      for (int i = 3; i * i <= n; i = i + 2)
         if (n % i == 0)
            return false;

      return true;
   }
}
