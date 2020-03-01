import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CrapsSimulationTest
{

	@Test
	void test_static_1()
	{
		Die die = new CrookedDie1();
		
		assertEquals("Die.testStatic()", die.testStatic());
	}

	@Test
	void test_static_2()
	{
		CrookedDie1 die = new CrookedDie1();
		
		assertEquals("CrookedDie1.testStatic()", die.testStatic());
	}
}
