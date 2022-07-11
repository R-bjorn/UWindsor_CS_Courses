package observerExample;
import java.util.Scanner;
	public class DemoObserver {
	public static void main(String[] args) {
		Subject sub = new Subject();
		// Client configures the number and type of Observers
		new HexadecimalObserver(sub);
		new OctalObserver(sub);
		new BinaryObserver(sub);
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.print( "\nEnter a number: " );
			sub.setState(scan.nextInt());
		}
	}
}