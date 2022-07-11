
public class Triangle extends Shape {

	private int base;
	private int height;
	
	public Triangle(String colour, int base, int height) {
		super(colour);
		this.base = base;
		this.height = height;
	}

	@Override
	public double getArea() {
		return (base * height) / 2.0;
	}
}
