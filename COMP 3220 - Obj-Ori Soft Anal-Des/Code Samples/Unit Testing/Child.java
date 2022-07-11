
public class Child extends Person {

	public Child(String firstName, String lastName, int age) throws Exception {
		super(firstName, lastName, age);
		if (this.getAge() > 18) throw new Exception("Bad age exception");
	}

}
