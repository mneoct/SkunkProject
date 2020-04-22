package skunk;
// TODO:
// Should negative chips (debt) be allowed??? 
// allow removal of players at beginning (i.e. undo adding players...)
// allow quitting in middle of game??
// Continuous: Resolve accessibility after figuring out classes, methods.
// Adjust output: More separators, less verbose but informative.

public class SkunkMain { // main program
	public transient SkunkUI skunkUI;
	public transient UI userInterface;

	public SkunkMain(final SkunkUI ui)
	{
		this.skunkUI = ui;
		this.userInterface = ui; // hide behind the interface UI
	}
	
	//TODO: Check: should be own class? Anyway to further break down?
	void skunkTournament() {
		while (true) {
			userInterface.println("New Game has been started...");
			userInterface.println("Resetting Individual Dice Totals and Kitty to 0");
			userInterface.println("");
			SkunkGame.playGame(SkunkPlayerManagement.playersArray);
			
			userInterface.println("Post-Game Evaluation...");
			SkunkPlayerManagement.playersArray = SPMRemovePlayer.removePlayers(SkunkPlayerManagement.playersArray);
			SkunkPlayerManagement.displayChipsAll(SkunkPlayerManagement.playersArray);
			
			boolean continueTournament = skunkCheckEndTournament(SkunkPlayerManagement.playersArray);
			if (continueTournament) {
				break;
			}
		}
	}
	
	boolean skunkCheckEndTournament(SkunkPlayer[] arrayPlayers) {
		if (arrayPlayers.length == 1) {
			userInterface.println("We have a grand champion!");
			return true;
		} 
		
		String endTournamentPrompt = "Type 'end' to end the tournament, else it will continue";
		String tournamentContinueChoice = userInterface.promptReadAndReturn(endTournamentPrompt);
		
		if (tournamentContinueChoice.equals("end")) {
			userInterface.println("Understood. Tournament is shutting down...");
			return true;
		}
		else {
			return false;
		}
	}
	
	void run(){	
		userInterface.println("Tournament has began...");
		userInterface.println("Now registering players...");
		userInterface.println("");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
		userInterface.println("");
		SkunkPlayerManagement.distributeChips();

		skunkTournament();
		
		userInterface.println("Final Report...");
		SkunkPlayerManagement.displayChipsAll(SkunkPlayerManagement.playersArray);

		userInterface.println("Tournament has ended...");
	}
}