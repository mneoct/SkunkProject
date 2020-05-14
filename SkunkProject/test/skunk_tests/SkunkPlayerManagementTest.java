package skunk_tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SPMAddPlayer;
import skunk.SkunkPlayer;
import skunk.SkunkPlayerManagement;

public class SkunkPlayerManagementTest {
	int chipsTotalTrue = 400;
	@BeforeClass
	public static void executeFirst() {
		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "AAA");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "BBB");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "CCC");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "DDD");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "EEE");
	}
	
	@Test
	public void testDistributionOfChips() {
		StdOut.println("");
		StdOut.println("<<<Testing that all players were given chips equally)>>>");
		SkunkPlayerManagement.distributeChips();
		int chipsTotal = 0;
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			chipsTotal += player.getPlayerChipsTotal();
		}
		
		SkunkPlayerManagement.displayPlayersChipsAndDiceTotal(SkunkPlayerManagement.playersArray);
		assertTrue ("chips not distributed properly", chipsTotal <= chipsTotalTrue);
	}
	
	@Test
	public void testSetDice() {
		StdOut.println("");
		StdOut.println("<<<Testing that players can have their dice set >>>");
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			player.addToPlayerDiceTotal(200);
			StdOut.println(player.getName() + "'s dice: " + player.getPlayerDiceTotal());
			assertEquals ("error setting dice", 200, player.getPlayerDiceTotal());
		}
	}
	
	@Test
	public void testResetDice() {
		StdOut.println("");
		StdOut.println("<<<Testing that resetting all players dice works (all set to 0)>>>");
		SkunkPlayerManagement.resetAllPlayersDice();
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			StdOut.println(player.getName() + "'s dice: " + player.getPlayerDiceTotal());
			assertSame ("Reset dice does not make it zero", 0, player.getPlayerDiceTotal());
		}
	}
	
	@Test
	public void testRandomStartPlayer() {
		StdOut.println("");
		StdOut.println("<<<Testing that randomStartPlayer returns a value less than length of playersArray>>>");
		StdOut.println("<<<And verify that a player is randomly selected>>>");
		for (int i = 0; i < 20; i++) {
			int randomIndex = SkunkPlayerManagement.randomStartPlayer(SkunkPlayerManagement.playersArray.length);
			StdOut.println("Player Chosen: " + SkunkPlayerManagement.playersArray[randomIndex].getName());
			assert(randomIndex <= SkunkPlayerManagement.playersArray.length);
		}
	}
	
	@Test
	public void selectNextPlayerTest() {
		StdOut.println("");
		StdOut.println("<<<Testing that selectNextPlayer selects the index of player next>>>");
		StdOut.println("<<<And verify that the player name is correct>>>");
		StdOut.println("<<<Primarily to show index too high will be reset to zero...");
		
		for (int currentIndex = 0; currentIndex < 8; currentIndex++) {
			if (currentIndex >= SkunkPlayerManagement.playersArray.length) {
				StdOut.println("Current Player is beyond the array's actual length");
			}
			else {
				StdOut.println("Current Player: " + SkunkPlayerManagement.playersArray[currentIndex].getName());
			}
			
			int newIndex = SkunkPlayerManagement.selectNextPlayer(SkunkPlayerManagement.playersArray, currentIndex);
			
			StdOut.println("Next Player is being selected... ");
			StdOut.println("New Player: " + SkunkPlayerManagement.playersArray[newIndex].getName());
		}
	}
}
