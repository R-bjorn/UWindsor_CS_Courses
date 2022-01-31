public class BaseFormatter implements NumberFormatter
{
   private int base;
   
   public BaseFormatter(int n)
   {
      base = 10;
      if (n >= 2 && n <= 36)
      {
         base = n;
      }
   }
   
   public String format(int n)
   {
      return Integer.toString(n, base);
   }
}
