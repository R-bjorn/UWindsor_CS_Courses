import java.util.Arrays;
import java.util.Scanner;

/**
   Test class for Comparable Person objects.
*/
public class PersonTester
{
   /**
      Determines first person in a non-empty array of Person objects.
      @param people array of Person objects
      @return first Person by name
   */
   public static Person firstPerson(Person[] people)
   {
      Person first = people[0];
      for (int i = 1; i < people.length; i++)
      {
         if (people[i].compareTo(first) < 0)
         {
            first = people[i];
         }
      }

      return first;
   }

   /**
      Determines last person in a non-empty array of Person objects.
      @param people array of Person objects
      @return last Person by name
   */
   public static Person lastPerson(Person[] people)
   {
      Person last = people[0];
      for (int i = 1; i < people.length; i++)
      {
         if (people[i].compareTo(last) > 0)
         {
            last = people[i];
         }
      }

      return last;
   }

   public static void main(String[] args)
   {
      Person[] people = new Person[10];
      Scanner scanner = new Scanner(System.in);

      System.out.println("Enter 10 names (i.e. firstname and lastname, e.g. John Bull): ");
      for (int i = 0; i < 10 && scanner.hasNext(); i++)
      {
         people[i] = new Person(scanner.next(),scanner.next());
      }

      System.out.println("First: " + Arrays.toString(firstPerson(people).getName()));
      System.out.println("Last: " + Arrays.toString(lastPerson(people).getName()));
   }
}
