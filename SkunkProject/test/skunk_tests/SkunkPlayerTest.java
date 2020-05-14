package skunk_tests;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SkunkPlayer;


public class SkunkPlayerTest {
	SkunkPlayer testPlayer = new SkunkPlayer("Test Player");
	
	@Test
	public void setNameTest() {
		StdOut.println("");
		StdOut.println("<<<Testing that player is given a new name>>>");
		
		String newName = "New Name";

		testPlayer.setName(newName);
		StdOut.println("New name: " + testPlayer.getName());
		assertSame ("Name is not set", newName, testPlayer.getName());	
	}
	
	@Test
	public void resetDiceTest() {
		StdOut.println("");
		StdOut.println("<<<Testing that player's dice is reset...>>>");
		testPlayer.resetDice();
		assertSame ("Dice is not reset", 0, testPlayer.getPlayerDiceTotal());	
	}
	
	@Test
	public void setPlayerDiceTotalTest() {
		StdOut.println("");
		StdOut.println("<<<Testing that player's dice is increased...>>>");
		testPlayer.resetDice();
		testPlayer.addToPlayerDiceTotal(5);
		StdOut.println("Added 5 dice points to " + testPlayer.getName() + ".");
		StdOut.println("Confirmation: " + testPlayer.getName() + " has " + testPlayer.getPlayerDiceTotal() + " points");
		assertSame ("Dice is not added to correctly", 5, testPlayer.getPlayerDiceTotal());
	}
	
	@Test
	public void addToPlayerChipsTotalTest() {
		StdOut.println("");
		StdOut.println("<<<Testing that player's chips is increased...>>>");
		testPlayer.addToPlayerChipsTotal(100);
		StdOut.println("Added 100 chips to " + testPlayer.getName() + ".");
		StdOut.println("Confirmation: " + testPlayer.getName() + " has " + testPlayer.getPlayerChipsTotal() + " chips");
		assertSame ("Chips added incorrectly", 100, testPlayer.getPlayerChipsTotal());
	}
}
