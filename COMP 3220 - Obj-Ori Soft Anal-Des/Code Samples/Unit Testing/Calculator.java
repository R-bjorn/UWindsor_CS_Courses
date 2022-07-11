
public class Calculator {

	public Calculator() {
		// TODO Auto-generated constructor stub
	}
	
	public int add(int x, int y) {
		return x + y;
	}
	
	public int subtract(int x, int y) {
		return x - y;
	}
	
	public int multiply(int x, int y) {
		return x * y;
	}
	
	public float divide(float x, float y) {
		if (y == 0) throw new ArithmeticException("Divided by zero!");
		return x / y;
	}
	
	@Override
	public boolean equals(Object another) {
		if (another instanceof Calculator) {
			if (this == another) return true;
		}
		return false;
	}

}
