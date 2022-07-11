package ClassAdapter;

public class CatAdapter extends Cat implements Adapter{

	public CatAdapter(String name) {
		super(name);
	}

	@Override
	public void makeSound() {
		meow();
	}
}
