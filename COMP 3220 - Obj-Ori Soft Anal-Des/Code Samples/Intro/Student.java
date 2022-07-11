import java.util.Date;

public class Student extends Person {
	String major;
	
	public Student(String name, Date born, String major) {
		super(name, born);
		this.major = major;
	}
	
	public String toString() {
		return super.toString() + " " + this.major;
	}

}
