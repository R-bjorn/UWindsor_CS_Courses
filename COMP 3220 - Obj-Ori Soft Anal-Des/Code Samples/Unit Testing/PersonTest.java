import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestMethodOrder(MethodOrderer.MethodName.class)
class PersonTest {

	private Person ryan = new Person("Ryan", "Scott", 33);
	private Person notInit;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("\nNote the specific order here");
	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Order(3)
	void testAFewThingsAtOnce() {
		//Any failures herein will be reported together
		//Try messing with the expected results and see what happens
		System.out.println("3 testAFewThingsAtOnce called");
		assertAll("person testing",() -> assertEquals("Ryan", ryan.getFirstName()),
				() -> assertEquals("Scott", ryan.getLastName()),
				() -> assertEquals(33, ryan.getAge()),
				() -> assertTrue(ryan.getFirstName().endsWith("yan")),
				() -> assertTrue(ryan.getFirstName().startsWith("Ry")),
				() -> assertFalse(false));
	}

	//If anything breaks in a code block, subsequent tests in the same block will not get executed
	//Subsequent tests in a block are contingent on the previous ones passing
	//Try messing with this in the first nested assertAll - the assertTrue will not get called
	@Test
	@Order(1)
	void testStuffThatAreDependent() {
		System.out.println("1 testStuffThatAreDependent called");
		assertAll("outer test", 
			() -> {
				assertAll("first nested big test", 
					() -> assertEquals("Ryan", ryan.getFirstName()),
					() -> assertEquals("Scott", ryan.getLastName()),
					() -> assertEquals(33, ryan.getAge()));
				assertTrue(true);
			},
			() -> {
				assertAll("another nested big test", 
					() -> assertTrue(ryan.getFirstName().endsWith("yan")),
					() -> assertTrue(ryan.getFirstName().startsWith("Ry")),
					() -> assertFalse(false));
			}
		);
	}
	
	@Test
	@Order(4)
	void makeSureSomethingIsNotNull() {
		System.out.println("4 makeSureSomethingIsNotNull called");
		assertNotNull(ryan);
	}
	
	@Test
	@Order(2)
	void makeSureSomethingIsNull() {
		System.out.println("2 makeSureSomethingIsNull called");
		assertNull(notInit);
	}
	
	@Test
	@Order(5)
	void showAssumption() {
		System.out.println("5 showAssumption called");
		assumeTrue(false);
		System.out.println("The rest of this test gets aborted, not failed, because of violated assumption");
		assertFalse(true);
	}

}
