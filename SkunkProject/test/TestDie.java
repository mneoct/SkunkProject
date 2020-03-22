import static org.junit.Assert.*;

import org.junit.Test;

public class TestDie {
	@Test
	public void testDieNoZero() {
		// Ensure that die doesn't return 0.
		Die underTest = new Die();
		for (int i = 0; i < 10; i++) {
			underTest.roll();
			assert (underTest.getLastRoll() != 0);
		}
	}
	
	@Test
	public void testDieLTSeven() {
		// Ensure that die result is at most 6.
		Die underTest = new Die();
		for (int i = 0; i < 10; i++) {
			underTest.roll();
			assert (underTest.getLastRoll() <= 6);
		}
	}
}
