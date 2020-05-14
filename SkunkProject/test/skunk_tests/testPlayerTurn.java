package skunk_tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SPMAddPlayer;
import skunk.SkunkPlayerManagement;
import skunk.SkunkTurnAction;
import skunk.SkunkTurnChoice1Stats;
import skunk.SkunkTurnChoice3End;
import skunk.SkunkTurnDiceData;
import skunk.UtilityMethods;

public class testPlayerTurn {
	
	@Test
	public void testPlayerTurnManual() {
		StdOut.println("");
		StdOut.println("<<<Manual: Test that each option during turn works (try all 3)>>>");
		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "cat");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "dog");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "rab");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "sna");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "eag");
		
		SkunkPlayerManagement.playersArray[0].addToPlayerChipsTotal(50);
		SkunkPlayerManagement.playersArray[1].addToPlayerChipsTotal(45);
		SkunkPlayerManagement.playersArray[2].addToPlayerChipsTotal(40);
		SkunkPlayerManagement.playersArray[3].addToPlayerChipsTotal(30);
		SkunkPlayerManagement.playersArray[4].addToPlayerChipsTotal(20);
		
		for(int i = 0; i < SkunkPlayerManagement.playersArray.length; i++) {
			SkunkTurnAction.playerTurn(SkunkPlayerManagement.playersArray[i], SkunkPlayerManagement.playersArray);
		}
	}
	
	public void testEndTurn() {
		StdOut.println("");
		StdOut.println("<<<Auto shows display results (choice 1)>>>");
		StdOut.println("<<<And then Choice 3 does add to player's running dice total>>>");

		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "cat");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "dog");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "rab");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "sna");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "eag");
		
		SkunkPlayerManagement.playersArray[0].addToPlayerChipsTotal(50);
		SkunkPlayerManagement.playersArray[1].addToPlayerChipsTotal(45);
		SkunkPlayerManagement.playersArray[2].addToPlayerChipsTotal(40);
		SkunkPlayerManagement.playersArray[3].addToPlayerChipsTotal(30);
		SkunkPlayerManagement.playersArray[4].addToPlayerChipsTotal(20);

		SkunkTurnChoice1Stats.displayResults(SkunkPlayerManagement.playersArray[0], SkunkPlayerManagement.playersArray);
		
		SkunkTurnDiceData.resetRoundDiceTotal();
		SkunkPlayerManagement.playersArray[0].resetDice();
		
		SkunkTurnDiceData.setRoundDiceTotal(20);
		SkunkTurnChoice3End.endTurn(SkunkPlayerManagement.playersArray[0]);
		
		assertTrue ("Player did not get dice total", SkunkPlayerManagement.playersArray[0].getPlayerDiceTotal() == 20);
	}
	
	@Test
	public void testRandomQuotes() {
		StdOut.println("");
		StdOut.println("<<<Testing the quotes when it is the next player's turn...>>>");

		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "cat");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "dog");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "rab");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "sna");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "eag");

		for(int i = 0; i < SkunkPlayerManagement.playersArray.length; i++) {
			UtilityMethods.printRandomQuotes(SkunkPlayerManagement.playersArray[i].getName());
		}
	}
}
