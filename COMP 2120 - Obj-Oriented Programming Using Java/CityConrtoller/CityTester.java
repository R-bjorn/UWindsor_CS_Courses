// Import required Java classes
// ************** (1 mark)
import java.util.ArrayList;
import java.util.Collections;
// **************

// This is a tester class to test the class City
public class CityTester {

    public static void main(String[] args) {

        // Declare an instance object, called c1, of class City using its default constructor.
        // ************** (0.5 mark)
        City c1 = new City();
        // **************

        System.out.println("First city (Code: " + c1.getCode()+ ") added.");

        // Declare an instance object, called c2, of class City, with the name London.
        // Note: You must implicitly use the proper constructor for the initialization
        // ************** (0.5 mark)
        City c2 = new City("London");
        // **************

        // Print out the name of city c2 using the proper method of its corresponding object.
        // You should do this by calling only one println method.
        // ************** (0.5 mark)
        System.out.println("City of " + c2.getName() + " (Code: " + c2.getCode()+ ") added");
        // **************

        // Declare an instance object, called c3, of class City, with the following initial values
        // City name: Toronto, City area: 630.2, City population: 6,197,000, City type: Global
        // City mayor's name: John Tory, City mayor's age: 57
        // Note: You must implicitly use the proper constructor for the initialization
        // ************** (0.5 mark)
        City c3 = new City("Toronto",630.2,6197000,new Person("John Tory",57),CityType.Global);
        // **************

        // Print out the name of the third city using the proper method of its corresponding object.
        // You should do this by calling only one println method.
        // ************** (0.5 mark)
        System.out.println("City of " + c3.getName() + " (Code: " + c3.getCode()+ ") added");
        // **************

        // Set the name of the first city to Windsor
        // Set the area of Windsor to : 146.3
        // Set the population of Windsor to : 336,000
        // Set the Windsor's mayor's name and age to : Drew Dilkens, 42
        // ************** (2 marks)
        c1.setName("Windsor");
        c1.setArea(146.3);
        c1.setPopulation(336000);
        c1.setMayor(new Person("Drew Dilkens",42));
        c1.setCityType(CityType.Port);
        // **************

        System.out.println("First city, " + c1.getName() + ", updated");

        // Add three neighbors, Detroit, Chatham, and Lasalle to the city of Windsor
        // We do not know the values of the other instance variable of these neighbors
        // ************** (1.5 marks)
        c1.addNeighbor(new City("Detroit"));
        c1.addNeighbor(new City("Chatham"));
        c1.addNeighbor(new City("Lasalle"));
        // **************

        // Add three neighbors, Scarborough, Mississauga, and Richmond Hill to the city of Toronto
        // We do not know the values of the other instance variable of these neighbors
        // ************** (1.5 marks)
        c3.addNeighbor(new City("Scarborough"));
        c3.addNeighbor(new City("Mississauga"));
        c3.addNeighbor(new City("Richmond Hill"));
        // **************

        // Create an ArrayList of String, called neighborsNames, and put the sorted list of the
        // city of Windsor's neighbors' names into it. Sort order should be descending.
        // Then print out the sorted list. You should do this by calling only one println method.
        // ************** (1.5 marks)
        ArrayList<String> neighborsNames = c1.neighborsSortedList('D');
        System.out.println("Neighbors of the city of " + c1.getName() + " are " + neighborsNames);
        // **************

        // Use the existing ArrayList neighborsNames, and this time store the sorted list of the
        // city of Windsor's neighbors' names into it. Sort order should be Ascending.
        // Then print out the sorted list. You should do this by calling only one println method.
        // ************** (1.5 marks)
        neighborsNames = c3.neighborsSortedList('A');
        System.out.println("Neighbors of the city of " + c3.getName() + " are " + neighborsNames);
        // **************

        // Remove Richmond Hill from the neighbors of Toronto.
        // Print out a proper message depending on the method's returned value by calling println method.
        // ************** (1.5 marks)
        if (c3.removeNeighbor("Richmond Hill")) {
            System.out.println("City of Richmond Hill has been removed from the city of " + c3.getName() + "'s neighbors");
        }
        else
            System.out.println("Neighbor not found");
        // **************

        // Overwrite the Arraylist neighborsNames by storing the sorted list of the
        // city of Toronto's neighbors' names into it. Sort order should be Ascending.
        // Then print out the sorted list. You should do this by calling only one println method.
        // ************** (1.5 marks)
        neighborsNames = c3.neighborsSortedList('A');
        System.out.println("Neighbors of the city of " + c3.getName() + " are " + neighborsNames);
        // **************

        // Create an ArrayList of String, called cityMayorNames.
        // ************** (0.5 mark)
        ArrayList<String> cityMayorNames = new ArrayList<>();
        // **************

        // Create an ArrayList of city, called cities, and then add the three cities
        // that you have already created into it.
        // ************** (1 mark)
        ArrayList<City> cities = new ArrayList<>();
        cities.add(c1);
        cities.add(c2);
        cities.add(c3);
        // **************

        // Using an enhanced for loop add the name and mayor of each city into the
        // ArrayList cityMayorNames. Separate each city name and its mayor name by a colon.
        // ************** (1 mark)
        for (City c : cities) {
            cityMayorNames.add(c.getName() + ": " + c.cityMayorInfo());
        }
        // **************

        // Sort the ArrayList cityMayorNames, in ascending order.
        // Then print out a title and then the list using an enhanced for loop, one item per line.
        // ************** (1 mark)
        Collections.sort(cityMayorNames);
        System.out.println("List of the cities and their mayors:");
        for (String s : cityMayorNames) {
            System.out.println(s);
        }
        // **************

    }
}