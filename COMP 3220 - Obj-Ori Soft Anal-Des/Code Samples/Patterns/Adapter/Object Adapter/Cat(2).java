package ObjectAdapter;

public class Cat extends Pet {

	public Cat(String name) {
		this.name = name;
	}
	
	public void meow() {
		System.out.println(name + " - \"Mew\"");
	}
}
