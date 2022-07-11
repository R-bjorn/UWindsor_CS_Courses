package ObjectAdapter;

import java.util.ArrayList;

public class DemoAdapter {
	
	public static void main(String[] args) {
		Cat c = new Cat("Mr. Lilly");
		Dog d = new Dog("Lola");
		
		CatAdapter ca = new CatAdapter(c);
		DogAdapter da = new DogAdapter(d);
		
		ca.makeSound();
		da.makeSound();
		
		ArrayList<Adapter> l = new ArrayList<Adapter>();
		l.add(ca);
		l.add(da);
		
		System.out.println("\n\n\n");
		for (Adapter a: l) {
			a.makeSound();
		}
	}
}
