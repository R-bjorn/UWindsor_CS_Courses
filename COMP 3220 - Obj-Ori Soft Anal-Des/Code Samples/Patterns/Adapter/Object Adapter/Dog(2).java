package ObjectAdapter;

public class Dog extends Pet{

	public Dog(String name) {
		this.name = name;
	}
	
	public void bark() {
		System.out.println(name + " - \"WOOF!\"");
	}
}
