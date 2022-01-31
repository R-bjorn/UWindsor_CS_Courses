public class DefaultFormatter implements NumberFormatter
{
   public String format(int n)
   {
      return String.format("%d", n);
   }
}
