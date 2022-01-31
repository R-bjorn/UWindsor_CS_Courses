/**
   Read all words from a file and add them to a map
   whose keys are the first letters of the words and
   whose values are sets of words that start with
   that same letter. Then print out the word sets in
   alphabetical order. Update the map by modifying
   Worked Example 15.1.
*/

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FirstLetterMap
{
   public static void main(String[] args)
   {
      String filename = "test1.txt"; //SUB "test2.txt";"test3.txt"

      try (Scanner in = new Scanner(new File(filename)))
      {

         Map<Character, Set<String>> words = new TreeMap<>();

         while (in.hasNext())
         {
            String word = clean(in.next());
            Character c = word.charAt(0);
            Set<String> tempTree = words.get(c);
            if (tempTree == null)
            {
               tempTree = new TreeSet<String>();
            }
            tempTree.add(word);
            words.put(c, tempTree);
         }

         for (Character c : words.keySet())
         {
            System.out.println(c + ": " + words.get(c));
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Cannot open: " + filename);
      }
   }

   public static String clean(String s)
   {
      String r = "";
      for (int i = 0; i < s.length(); i++)
      {
         char c = s.charAt(i);
         if (Character.isLetter(c))
         {
            r = r + c;
         }
      }
      return r.toLowerCase();
   }

}
