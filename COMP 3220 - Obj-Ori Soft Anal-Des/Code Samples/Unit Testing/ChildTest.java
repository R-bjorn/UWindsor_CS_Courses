import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChildTest {

	private static Child child;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//Move this bad code into each of these setup/teardown methods to see what happens
		//child = new Child("Marty", "McFly", 28);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAge() {
		assertNull(child);
	}
	
	@Test
	void testTrue() {
		assertTrue(true);
	}
	
	@Test
	void testInit() {
		Child c;
		try {
			c = new Child("Ron", "McDon", 14);
			System.out.println("Woot construction of Ron McDon succeeded as it should!");
		} catch (Exception e) {
			fail("Constructor was given legal arguments...");
		}
		
		try {
			c = new Child("Don", "McRon", 22);
			fail("Constructor was given illegal arguments...");
		} catch (Exception e) {
			System.out.println("Woot it should have given us an exception there! :)");
		}
	}

}
