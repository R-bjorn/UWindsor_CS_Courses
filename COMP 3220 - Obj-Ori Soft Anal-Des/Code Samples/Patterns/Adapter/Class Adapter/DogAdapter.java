package ClassAdapter;

public class DogAdapter extends Dog implements Adapter {

	public DogAdapter(String name) {
		super(name);
	}
	@Override
	public void makeSound() {
		bark();
	}
}
