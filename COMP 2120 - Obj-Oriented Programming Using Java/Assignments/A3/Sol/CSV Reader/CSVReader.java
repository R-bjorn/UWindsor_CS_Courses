import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
   Class to read and process the contents of a standard CSV file
*/
public class CSVReader
{
   private ArrayList<String> rows;
	
   /**
      Constructor opens the input file and stores each line in
      a list of Strings.
      @param fileName the name of the csv file to be processed
   */
   public CSVReader(String fileName)
   {
      rows = new ArrayList<>();
      try (Scanner inFile = new Scanner(new File(fileName)))
      {
         while (inFile.hasNext())
         {
            rows.add(inFile.nextLine());
         }
      }
      catch (FileNotFoundException e)
      {
         System.out.println("File not found!");
      }
   }
	
   /**
      Returns the number of lines in the CSV file
      @return the number of rows (lines) in the file
   */
   public int numberOfRows()
   {
      return rows.size();
   }
	
   /**
      Returns the number of fields in a particular row
      @param row the line number (0 <= row < number of lines)
      @return the number of fields in the given row
   */
   public int numberOfFields(int row)
   {
      if (row < 0 || row >= rows.size())
      {
         throw new IllegalArgumentException("Row: " + row);
      }
      return getFields(row).size();
   }
	
   /**
      Extract fields from a String corresponding to the row number
      given as argument.  Handles fields surrounded by quotes.
      @param row the line number (0 <= row < number of lines)
      @return a list of fields in the given row
   */
   private ArrayList<String> getFields(int row)
   {
      ArrayList<String> fields = new ArrayList<>();
      Scanner line = new Scanner(rows.get(row));
      line.useDelimiter(",");
      while (line.hasNext())
      {
         String field = line.next().trim();
         if (field.startsWith("\""))               
         {
            while (!field.endsWith("\""))    // until closing quotes found
            {
               field = field + "," + line.next();  // append comma-separated fields
            }
            field = field.substring(1,field.length() - 1);    // remove quotes
         }
         field = field.replaceAll("\"\"","\"");
         fields.add(field);
      }
      return fields;
   }
   
   /**
      Returns the field in a particular row and colum
      @param row the line number (0 <= row < number of lines)
      @param column the column number (0 <= row < number of columns in row)
      @return the number of fields in the given row
   */
   public String field(int row, int column)
   {
      if (row < 0 || row >= rows.size())
      {
         throw new IllegalArgumentException("Row: " + row);
      }
      if (column < 0 || column >= numberOfFields(row))
      {
         throw new IllegalArgumentException("Column: " + column);
      }
      return getFields(row).get(column);	
   }
}