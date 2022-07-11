package ClassAdapter;

import java.util.ArrayList;

public class DemoAdapter {
	
	public static void main(String[] args) {
		CatAdapter c = new CatAdapter("Mr. Lilly");
		DogAdapter d = new DogAdapter("Lola");
		
		c.meow();
		d.bark();
		
		ArrayList<Adapter> l = new ArrayList<Adapter>();
		l.add(c);
		l.add(d);
		
		System.out.println("\n\n\n");
		for (Adapter a: l) {
			a.makeSound();
		}
	}
}
