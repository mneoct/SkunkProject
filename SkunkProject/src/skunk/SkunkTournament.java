package skunk;

import edu.princeton.cs.introcs.StdOut;

public class SkunkTournament {
	private final static String endTournamentPrompt = "Type 'end' to end the tournament, else it will continue";

	public static void skunkTournament() {
		boolean continueTournament = true;
		while (continueTournament) {
			StdOut.println("New Game has been started...");
			StdOut.println("Resetting Individual Dice Totals and Kitty to 0");
			StdOut.println("");
			SkunkGame.oneGame(SkunkPlayerManagement.playersArray);
			
			StdOut.println("<<<>>>");
			StdOut.println("");
			
			StdOut.println("Post-Game Evaluation...");
			SkunkPlayerManagement.playersArray = SPMRemovePlayer.removePlayers(SkunkPlayerManagement.playersArray);
			SkunkTurnChoice1Stats.displayResults(SkunkPlayerManagement.playersArray);
			
			continueTournament = skunkContinueTourney(SkunkPlayerManagement.playersArray);
			if (!continueTournament) {
				break;
			}
		}
	}
	
	// Tested in SkunkTourneyEndTests: one player left/choose continue/choose end.
	public static boolean skunkContinueTourney(final SkunkPlayer[] arrayPlayers) {
		boolean isTrueContinueTourney = true;
		if (arrayPlayers.length <= 1) {
			StdOut.println("We have a grand champion!");
			isTrueContinueTourney = false;
		} 
		
		else  {
			final String tournamentContinueChoice = UtilityMethods.promptReadAndReturn(endTournamentPrompt);
			
			if ("end".equals(tournamentContinueChoice)) {
				StdOut.println("Understood. Tournament is shutting down...");
				isTrueContinueTourney = false;
			}
		}
		return isTrueContinueTourney;
	}
	
}
