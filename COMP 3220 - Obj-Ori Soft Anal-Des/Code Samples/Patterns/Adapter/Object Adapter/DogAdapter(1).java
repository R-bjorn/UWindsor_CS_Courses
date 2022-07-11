package ObjectAdapter;

public class DogAdapter implements Adapter {

	private Dog instance;
	public DogAdapter(Dog d) {
		instance = d;
	}
	
	@Override
	public void makeSound() {
		instance.bark();
	}
}