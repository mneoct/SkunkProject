package skunk;
// TODO:
// Should negative chips (debt) be allowed??? 
// allow removal of players at beginning (i.e. undo adding players...)
// allow quitting in middle of game??
// Continuous: Resolve accessibility after figuring out classes, methods.
// Adjust output: More separators, less verbose but informative.
import edu.princeton.cs.introcs.StdOut;

public class SkunkMain { // main program
	public transient SkunkUI skunkUI;
	public transient UI userInterface;
	
	private final static String endTournamentPrompt = "Type 'end' to end the tournament, else it will continue";

	public SkunkMain(final SkunkUI ui)
	{
		this.skunkUI = ui;
		this.userInterface = ui; // hide behind the interface UI
	}
	
	//TODO: Check: should be own class? Anyway to further break down?
	public static void skunkTournament() {
		while (true) {
			StdOut.println("New Game has been started...");
			StdOut.println("Resetting Individual Dice Totals and Kitty to 0");
			StdOut.println("");
			SkunkGame.oneGame(SkunkPlayerManagement.playersArray);
			
			StdOut.println("<<<>>>");
			StdOut.println("");
			
			StdOut.println("Post-Game Evaluation...");
			SkunkPlayerManagement.playersArray = SPMRemovePlayer.removePlayers(SkunkPlayerManagement.playersArray);
			SkunkTurnChoice1Stats.displayResults(SkunkPlayerManagement.playersArray);
			
			final boolean continueTournament = skunkCheckEndTournament(SkunkPlayerManagement.playersArray);
			if (continueTournament) {
				break;
			}
		}
	}
	
	// Tested in SkunkTourneyEndTests: one player left/choose continue/choose end.
	public static boolean skunkCheckEndTournament(final SkunkPlayer[] arrayPlayers) {
		boolean isTrueEndTourney = false;
		if (arrayPlayers.length <= 1) {
			StdOut.println("We have a grand champion!");
			isTrueEndTourney = true;
		} 
		
		else  {
			final String tournamentContinueChoice = UtilityMethods.promptReadAndReturn(endTournamentPrompt);
			
			if ("end".equals(tournamentContinueChoice)) {
				StdOut.println("Understood. Tournament is shutting down...");
				isTrueEndTourney = true;
			}
		}
		return isTrueEndTourney;
	}
	
	public void run(){	
		StdOut.println("Tournament has began...");
		StdOut.println("Now registering players...");
		StdOut.println("");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
		StdOut.println("");
		StdOut.println("<<<>>>");
		StdOut.println("");
		SkunkPlayerManagement.distributeChips();
		StdOut.println("");
		StdOut.println("<<<>>>");
		StdOut.println("");
		skunkTournament();
		
		StdOut.println("Final Report...");
		SkunkTurnChoice1Stats.displayResults(SkunkPlayerManagement.playersArray);

		StdOut.println("Tournament has ended...");
	}
}