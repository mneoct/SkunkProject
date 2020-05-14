package skunk_tests;
import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SkunkTurnDiceData;

public class SkunkTurnDiceDataTest {
	@Test
	public void testResetRoundDice() {
		StdOut.println("");
		StdOut.println("<<<Testing that Round's Dice is reset correctly...>>>");

		SkunkTurnDiceData.resetRoundDiceTotal();
		assert (SkunkTurnDiceData.getRoundDiceTotal() == 0);
	}
	
	@Test
	public void testPositiveRoundDice() {
		StdOut.println("");
		StdOut.println("<<<Testing that Round's Dice is increased correctly...>>>");
		SkunkTurnDiceData.resetRoundDiceTotal();
		SkunkTurnDiceData.setRoundDiceTotal(15);
		assert (SkunkTurnDiceData.getRoundDiceTotal() == 15);
	}
	
	@Test
	public void testNegativeRoundDice() {
		StdOut.println("");
		StdOut.println("<<<Testing that Round's Dice is subtracted from correctly...>>>");
		SkunkTurnDiceData.resetRoundDiceTotal();
		SkunkTurnDiceData.setRoundDiceTotal(-5);
		assert (SkunkTurnDiceData.getRoundDiceTotal() == -5);
	}
}
