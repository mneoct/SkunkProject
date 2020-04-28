package skunk_tests;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import skunk.SkunkPlayer;


public class SkunkPlayerTest {
	SkunkPlayer testPlayer = new SkunkPlayer("Test Player");
	
	@Test
	public void setNameTest() {
		String newName = "New Name";

		testPlayer.setName(newName);
		assertSame ("Name is not set", newName, testPlayer.getName());	
	}
	
	@Test
	public void resetDiceTest() {
		testPlayer.resetDice();
		assertSame ("Dice is not reset", 0, testPlayer.getPlayerDiceTotal());	
	}
	
	@Test
	public void setPlayerDiceTotalTest() {
		testPlayer.resetDice();
		testPlayer.addToPlayerDiceTotal(5);
		assertSame ("Dice is not added to correctly", 5, testPlayer.getPlayerDiceTotal());
	}
	
	@Test
	public void addToPlayerChipsTotalTest() {
		testPlayer.addToPlayerChipsTotal(100);
		assertSame ("Chips added incorrectly", 100, testPlayer.getPlayerChipsTotal());
	}
}
