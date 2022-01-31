import java.util.ArrayList;
/**
   Provide a static method that reverses all the elements of a
   generic arraylist.
*/
public class ArrayListUtil
{
   public static <T> void reverse(ArrayList<T> a)
   {
      int len = a.size(); // go half way through
      for (int i = 0; i < len / 2; i++)
      {
         T temp = a.get(i);
         a.set(i, a.get(len - 1 - i));
         a.set(len - 1 - i, temp);
      }
   }

   public static <T> ArrayList<T> reverse2(ArrayList<T> a)
   {
      int len = a.size();
      ArrayList<T> result = new ArrayList<>(a.size());
      for (int i = len - 1; i >= 0; i--)
      {
         result.add(a.get(i));
      }

      return result;
   }

}
