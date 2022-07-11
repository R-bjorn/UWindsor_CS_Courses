package ObjectAdapter;

public class CatAdapter implements Adapter{

	private Cat instance;
	public CatAdapter(Cat c) {
		instance = c;
	}
	
	@Override
	public void makeSound() {
		instance.meow();
	}
}
