
public abstract class Shape implements Sortable{
	protected String colour;
	
	public abstract double getArea();
	
	public String getColour() {
		return this.colour;
	}
	
	protected Shape(String colour) {
		this.colour = colour;
	}
	
	public boolean lessThan(Sortable anObject) {
		if (anObject instanceof Shape) {
			Shape obj = (Shape) anObject;
			if (getArea() <= obj.getArea()) return true;
			return false;
		}
		return false;
	}
}
