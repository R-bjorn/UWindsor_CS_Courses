package composite;

import java.util.ArrayList;

public class Composite extends Component {

	private ArrayList<Component> components = new ArrayList<Component>();
	
	public Composite(String name) {
		super(name, 0);
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}

	@Override
	public String getName() {
		String compNames = "";
		for (Component c: components) {
			compNames += " " + c.getName();
		}
		return name + " ::: {" + compNames + "}";
	}

	@Override
	public float getCost() {
		float sum = 0;
		for (Component c: components) {
			sum += c.getCost();
		}
		return sum;
	}

	@Override
	public String toString() {
		String compList = "{{{";
		for (Component c: components) {
			compList += " " + c.getName();
		}
		return compList + "}}}";
	}
}
