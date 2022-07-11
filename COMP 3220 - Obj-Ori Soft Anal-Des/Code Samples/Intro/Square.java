
public class Square extends Shape {

	private int size;
	
	public Square(String colour, int size) {
		super(colour);
		this.size = size;
	}

	@Override
	public double getArea() {
		return size * size;
	}

}
