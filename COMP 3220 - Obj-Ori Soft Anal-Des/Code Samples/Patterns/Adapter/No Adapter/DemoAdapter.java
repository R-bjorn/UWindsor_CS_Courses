package NoAdapter;

import java.util.ArrayList;

public class DemoAdapter {
	
	public static void main(String[] args) {
		Cat c = new Cat("Mr. Lilly");
		Dog d = new Dog("Lola");
		
		c.meow();
		d.bark();
		
		ArrayList<Pet> l = new ArrayList<Pet>();
		l.add(c);
		l.add(d);
		
		System.out.println("\n\n\n");

		for (Pet p: l) {
			if (p instanceof Cat) ((Cat)p).meow();
			else if (p instanceof Dog) ((Dog)p).bark();
		}
	}
}
