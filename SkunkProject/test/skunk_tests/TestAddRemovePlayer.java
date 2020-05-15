package skunk_tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SPMAddPlayer;
import skunk.SPMRemovePlayer;
import skunk.SkunkPlayerManagement;
import skunk.SkunkTurnChoice1Stats;

public class TestAddRemovePlayer {
	@Test
	public void testAddingPlayers() {
		StdOut.println("");
		StdOut.println("<<<Test short names and stop adding early>>>");
		StdOut.println("<<<Manual Testing!...>>>");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
	}
	
	@Test
	public void testRemovingPlayer() {
		StdOut.println("");
		StdOut.println("<<<Manual: adding up to 8 players automatically stops the registration of players...>>>");
		StdOut.println("<<<Then, checks that players are removed by SPMRemovePlayer.removePlayers>>>");
		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
		SkunkPlayerManagement.distributeChips();
		int tempSizeOfArray = SkunkPlayerManagement.playersArray.length;
				
		for(int i = 0; i < SkunkPlayerManagement.playersArray.length; i++) {
			if ( (i & 1) == 0 ) { 
				SkunkPlayerManagement.playersArray[i].addToPlayerChipsTotal(-200);
			} 
		}
		
		SkunkTurnChoice1Stats.displayResults(SkunkPlayerManagement.playersArray);

		SkunkPlayerManagement.playersArray = SPMRemovePlayer.removePlayers(SkunkPlayerManagement.playersArray);
		
		SkunkTurnChoice1Stats.displayResults(SkunkPlayerManagement.playersArray[0], SkunkPlayerManagement.playersArray);

		assertTrue ("Players were not removed", SkunkPlayerManagement.playersArray.length == tempSizeOfArray/2);
	}
}
