import java.util.Date;

public class Person { //final
	private String name;
	private Date born;
	
	public Person(String name, Date born) {
		this.name = name;
		this.born = born;
	}
	
	public Person(Person p) {
		this.name = p.name;
		this.born = p.born;
		//this.born = new Date(p.born.getYear(), born.getMonth(), born.getDay());
	}
	
	public Date getBirthDate() {
		return new Date(born.getYear(), born.getMonth(), born.getDay());
	}

	public String toString() {
		return this.name + " " + born.toString();
	}
}
