package skunk_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SPMAddPlayer;
import skunk.SkunkKitty;
import skunk.SkunkPlayerManagement;
import skunk.TabulateWinnings;

public class TabulateWinningsTest {
	@Test
	public void testTabulateWinnings() {
		StdOut.println("");
		StdOut.println("<<<Testing that the non-winners are evaluated properly (5 or 10 chips lost accordingly)...>>>");
		StdOut.println("<<<Testing that the winner gets the right amount of winnings ...>>>");


		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "cat");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "dog");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "rab");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "sna");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "eag");
		
		int expectedWinnings = 0;
		SkunkKitty.resetKitty();
		
		for(int i = 1; i < SkunkPlayerManagement.playersArray.length; i++) {
			if ( (i & 1) != 0 ) { 
				SkunkPlayerManagement.playersArray[i].addToPlayerDiceTotal(5);
				expectedWinnings += 5;
			}
			else {
				expectedWinnings += 10;
			}
		}
		
		TabulateWinnings.tabulateWinnings(SkunkPlayerManagement.playersArray, 0);
		
		StdOut.println("Expected Winnings: " + expectedWinnings);
		StdOut.println("Winner's chips: " + SkunkPlayerManagement.playersArray[0].getPlayerChipsTotal());
		
		assertEquals (expectedWinnings, SkunkPlayerManagement.playersArray[0].getPlayerChipsTotal());
	}
}
