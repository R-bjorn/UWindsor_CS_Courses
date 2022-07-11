package composite;

public abstract class Component {
	protected String name;
	protected float cost;
	
	public Component(String name, float cost) {
		this.name = name;
		this.cost = cost;
	}
	
	public abstract String getName();
	public abstract float getCost();

	@Override
	public String toString() {
		return name + " " + cost;
	}
}
