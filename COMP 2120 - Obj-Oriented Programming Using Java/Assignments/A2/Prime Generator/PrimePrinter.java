import java.util.Scanner;

/**
   This class prints prime numbers.
*/
public class PrimePrinter
{
   public static void main (String[] args)
   {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter upper limit: ");
      int limit = in.nextInt();

      PrimeGenerator pg = new PrimeGenerator();

      boolean done = false;
      while (!done)
      {
         int prime = pg.nextPrime();
         if (prime > limit)
            done = true;
         else
            System.out.println(prime);
      }
   }
}
