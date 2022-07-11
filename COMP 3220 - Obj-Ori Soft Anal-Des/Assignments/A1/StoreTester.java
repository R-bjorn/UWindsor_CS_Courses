import java.time.LocalDate;
import java.util.ArrayList;

public class StoreTester {

	public static void main(String[] args) {
		Store store = new Store();
		
		ArrayList<String> authors = new ArrayList<String>();
		authors.add("Jim");
		authors.add("Julie");
		authors.add("Karen");
		
		store.addItem(new Book("Into the Wilds 2: Return of the Wilds", 19.99, LocalDate.of(1999, 11, 22), 10, authors));
		
		store.addItem(new Movie("Into the Wilds", 22.00, LocalDate.of(1999, 11, 22), 10, "Bob", "Joe"));
		
		store.addItem(new CompactDisc("The Tide, The Thief, and River's End", 40.00, LocalDate.of(2013, 10, 4), 100, "Caligula's Horse"));
		
		store.addItem(new CompactDisc("Rise Radiant", 50.00, LocalDate.of(2020, 5, 22), 200, "Caligula's Horse", "Inside Out Music"));
		
		store.addItem(new Game("Halo 7: Even More Infinite - Diamond Edition", 1099.99, LocalDate.of(2026, 1, 17), 3500, "Bungie", false));
		
		store.listInventory();
		
		int idToFind = 9000;
		Item i = store.checkForItem(idToFind);
		
		if (i != null) System.out.println("\nFound item: " + i);
		else System.out.println("\nItem with ID " + idToFind + " not found.");
		
		store.makeSale(idToFind, 3, LocalDate.of(1988, 12, 17));
		
		idToFind = 9100;
		i = store.checkForItem(idToFind);
		
		if (i != null) System.out.println("\nFound item: " + i);
		else System.out.println("\nItem with ID " + idToFind + " not found.");
		
		store.makeSale(idToFind, 24, LocalDate.of(1988, 11, 17));
		
		idToFind = 9200;
		i = store.checkForItem(idToFind);
		
		if (i != null) System.out.println("\nFound item: " + i);
		else System.out.println("\nItem with ID " + idToFind + " not found.");
		
		store.makeSale(idToFind, 24, LocalDate.of(2021, 10, 14));
		
		idToFind = 9300;
		i = store.checkForItem(idToFind);
		
		if (i != null) System.out.println("\nFound item: " + i);
		else System.out.println("\nItem with ID " + idToFind + " not found.");
		
		store.makeSale(idToFind, 26, LocalDate.of(2021, 3, 14));
		
		idToFind = 9350;
		i = store.checkForItem(idToFind);
		
		if (i != null) System.out.println("\nFound item: " + i);
		else System.out.println("\nItem with ID " + idToFind + " not found.");
		
		store.makeSale(idToFind, 24, LocalDate.of(2021, 10, 14));
		
		idToFind = 9400;
		i = store.checkForItem(idToFind);
		
		if (i != null) System.out.println("\nFound item: " + i);
		else System.out.println("\nItem with ID " + idToFind + " not found.");

		store.makeSale(idToFind, 400, LocalDate.of(2027, 2, 18));
		store.makeSale(idToFind, 5, LocalDate.of(2022, 2, 18));
		
		
		System.out.println("\n\nStore information:");
		System.out.println(store.toString());
	}
}
