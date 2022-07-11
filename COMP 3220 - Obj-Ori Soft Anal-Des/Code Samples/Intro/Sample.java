import java.awt.Point;
import java.util.Date;

public class Sample {

	private int lol = 15;
	
	private static double pi = 3.14159265359; // final
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int lol = 5;
		
		System.out.println(lol + "\n");
		//System.out.println(this.lol + "\n");
		
		Sample aSample = new Sample();
		
		aSample.some_function();

		some_loops(lol, lol);
		//aSample.some_loops(lol, lol);
		
		Point p1 = new Point(7, 3);
		Point p2 = new Point(0, 0);

		System.out.println("\nCalling the passByValue function");
		passByValue(p1, p2);

		System.out.println("\np1: " + p1);
		System.out.println("p2: " + p2 + "\n");
		
		editPi(42.2);
		System.out.println("\npi: " + pi);
		
		Person ryan = new Person("Ryan Scott", new Date(87, 11, 16));
		System.out.println("\nBefore: " + ryan + "\n");
		Date muahahaha = ryan.getBirthDate();
		muahahaha.setYear(77);
		System.out.println("\nAfter: " + ryan + "\n");
		
		Person [] people = new Person [3];
		people[0] = ryan;
		people[1] = new Student("John Bonham", new Date(82, 16, 11), "Biology");
		people[2] = new Student("Timothy Jimothy", new Date(72, 11, 11), "Computer Science");
		
		for (Person p: people) {
			System.out.println(p);
		}
		
		//public Shape someShape = new Shape("red");
		Shape shape1 = new Square("red", 2);
		Shape shape2 = new Triangle("green", 1, 3);
		Shape shape3 = new Triangle("blue", 6, 3);

		System.out.println("\n" + shape1.lessThan(shape2));
		System.out.println(shape1.lessThan(shape3));
		System.out.println(shape3.lessThan(shape2));
		
		GenericStack <Integer> gs = new GenericStack<Integer>();
		gs.push(5);
		gs.push(6);
		gs.push(7);
		gs.push(8);
		System.out.println("\n" + gs.pop());
		System.out.println(gs.pop());
		System.out.println(gs.pop());
		System.out.println(gs.pop());
		System.out.println(gs.pop());
		System.out.println(gs.pop());
		System.out.println(gs.pop());
	}
	
	private void some_function() {
		int lol = 6;
		
		System.out.println(lol);
		System.out.println(this.lol + "\n");
	}
	
	private void some_function(int x) {
		System.out.println(x + "\n");
	}
	
	/*
	private int some_function() {
		return 7;
	}*/
	
	private static void editPi(double newPi) {
		pi = newPi;
	}
	
	private static void some_loops(int ilim, int klim) {
		for (int i=0; i<ilim; i++) {
			System.out.println("Printing i " + i);
		}
		
		System.out.println("");
		
		int [] someIntArray = new int[] {2,4,5,6};
		for (int k: someIntArray){
			if (k <= klim){
				System.out.println("Printing k " + k);
			}
		}
	}

	private static void passByValue(Point a, Point b) {
		//a.x = 14;
		//a.y = 6;
		
		Point temp = a;
		a = b;
		b = temp;

		System.out.println("\na: " + a);
		System.out.println("b: " + b + "\n");
		
		//a = new Point(3, 3);
		//a.x = 1;
		//a.y = 1;
	}
}
