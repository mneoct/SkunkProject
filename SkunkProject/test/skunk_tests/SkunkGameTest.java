package skunk_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import skunk.SkunkGame;
import skunk.SkunkPlayer;

public class SkunkGameTest {
	@Test
	public void TestFalseOverflowOrNextPlayer() {
		SkunkPlayer testPlayer = new SkunkPlayer("Test Player");
		testPlayer.addToPlayerDiceTotal(10);
		boolean isOverflowFalse = SkunkGame.overflowOrNextPlayer(testPlayer);
		assertFalse ("Should not be overflow!", isOverflowFalse);
	}
	
	@Test
	public void TestTrueOverflowOrNextPlayer() {
		SkunkPlayer testPlayer = new SkunkPlayer("Test Player");
		testPlayer.addToPlayerDiceTotal(30);
		boolean isOverflowTrue = SkunkGame.overflowOrNextPlayer(testPlayer);
		assertTrue ("Should be overflow!", isOverflowTrue);
	}
}
