package skunk_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SkunkGame;
import skunk.SkunkPlayer;

public class SkunkGameTest {
	@Test
	public void TestFalseOverflowOrNextPlayer() {
		StdOut.println("");
		StdOut.println("<<<Testing that given player has NOT surpassed dice threshold to start last round>>>");
		SkunkPlayer testPlayer = new SkunkPlayer("Test Player");
		testPlayer.addToPlayerDiceTotal(10);
		boolean isOverflowFalse = SkunkGame.overflowOrNextPlayer(testPlayer);
		assertFalse ("Should not be overflow!", isOverflowFalse);
	}
	
	@Test
	public void TestTrueOverflowOrNextPlayer() {
		StdOut.println("");
		StdOut.println("<<<Testing that given player has surpassed dice threshold to start last round>>>");
		SkunkPlayer testPlayer = new SkunkPlayer("Test Player");
		testPlayer.addToPlayerDiceTotal(30);
		boolean isOverflowTrue = SkunkGame.overflowOrNextPlayer(testPlayer);
		assertTrue ("Should be overflow!", isOverflowTrue);
	}
}
