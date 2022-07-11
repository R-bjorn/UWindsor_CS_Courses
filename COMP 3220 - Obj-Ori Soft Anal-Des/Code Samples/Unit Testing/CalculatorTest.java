import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ooo we can name classes whatever we like")
class CalculatorTest {
	private Calculator calculator = new Calculator();
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("This happened before everything");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("\nThis happened after everything\n\nNote the order and how there isn't a clear pattern here");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("\nThis happened before each test");
		if (true) {
			System.out.println("The true thing happened");
		}
		else {
			System.out.println("The false thing happened");
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("This happened after each test");
	}

	@Test
	void testAddition() {
		System.out.println("testAddition called");
		assertEquals(2, calculator.add(4, -2));
	}

	//Comment out @Test and run the test code to see what happens
	//Or add @Disabled("Some comment as to why you disabled this test") to skip a test
	@Test
	void testSubtraction() {
		System.out.println("testSubtraction called");
		assertEquals(6, calculator.subtract(4, -2));
	}

	@Test
	@DisplayName("ooo we can name methods whatever we like too")
	void testMultiplication() {
		System.out.println("testMultiplication called");
		assertEquals(-8, calculator.multiply(4, -2));
	}

	@Test
	void testDivision() {
		System.out.println("testDivision called");
		assertEquals(-2, calculator.divide(4, -2));
	}

	@Test
	void forcefullyFailATest() {
		System.out.println("forcefullyFailATest called");
		boolean lol = true;
		if (lol) fail("you have failed this test because I said so");
	}
	
	@Test
	void testAnException() {
		System.out.println("testAnException called");
		Exception e = assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
		assertEquals("Divided by zero!", e.getMessage());
	}
	
	@Test
	void testSameness() {
		System.out.println("testSameness called");
		assertSame(calculator, calculator);
	}
	
	@Test
	void testNotSameness() {
		System.out.println("testNotSameness called");
		assertNotSame(calculator, new Calculator());
	}
	
}
