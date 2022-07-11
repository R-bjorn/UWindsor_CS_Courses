package composite;

public class Leaf extends Component  {

	public Leaf(String name, float cost) {
		super(name, cost);
	}

	@Override
	public String getName() {
		return "[leaf: " + name + "]";
	}

	@Override
	public float getCost() {
		return cost;
	}
}
