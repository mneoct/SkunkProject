package skunk_tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SPMAddPlayer;
import skunk.SkunkMain;
import skunk.SkunkPlayerManagement;

public class SkunkTourneyEndTests {

	@Test
	public void skunkCheckEndTournamentTestOnePlayerLeft() {
		StdOut.println("");
		StdOut.println("<<<Testing that Tourney Ends (boolean = true) if 1 player left...>>>");
		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "cat");
		
		StdOut.println("Number of Players: " + SkunkPlayerManagement.playersArray.length);
		boolean x = SkunkMain.skunkCheckEndTournament(SkunkPlayerManagement.playersArray);
		
		assertTrue ("Tournament not ended despite having only one player left!", x == true);
	}
	
	@Test
	public void skunkCheckEndTournamentTestChooseEnd() {
		StdOut.println("");
		StdOut.println("<<<Testing that Tourney Ends (boolean = true) when input is 'end'...>>>");
		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "cat");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "dog");

		StdOut.println("Number of Players: " + SkunkPlayerManagement.playersArray.length);
		boolean x = SkunkMain.skunkCheckEndTournament(SkunkPlayerManagement.playersArray);
		
		assertTrue ("Tournament not ended despite choosing to!", x == true);
	}
	
	@Test
	public void skunkCheckEndTournamentTestChooseContinue() {
		StdOut.println("");
		StdOut.println("<<<Testing that Tourney continues (boolean = false) when input is not 'end'...>>>");
		SkunkPlayerManagement.playersArray = null;
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "cat");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayerToArrayMain(SkunkPlayerManagement.playersArray, "dog");

		StdOut.println("Number of Players: " + SkunkPlayerManagement.playersArray.length);
		boolean x = SkunkMain.skunkCheckEndTournament(SkunkPlayerManagement.playersArray);
		
		assertTrue ("Tournament ended despite choosing not to!", x == false);
	}
}
