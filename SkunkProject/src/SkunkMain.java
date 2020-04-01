// TODO:
// Should negative chips (debt) be allowed??? 
// allow removal of players at beginning (i.e. undo adding players...)
// allow quitting in middle of game??
// Continuous: Resolve accessibility after figuring out classes, methods.
// Adjust output: More separators, less verbose but informative.

public class SkunkMain { // main program
	public SkunkUI skunkUI;
	public UI userInterface;
	
	private static final int MAX_PLAYERS = 8;
	private static final int OVERFLOW_SCORE = 30; // = 100, set lower to test games faster...
	private final static int TOTAL_CHIPS = 30; // = 400, set lower for test games faster...
	
	public static int getOverflowScore() {
        return OVERFLOW_SCORE;
    }
	public static int getMaxPlayers() {
        return MAX_PLAYERS;
    }
	public static int getTotalChips() {
        return TOTAL_CHIPS;
    }

	public SkunkMain(SkunkUI ui)
	{
		this.skunkUI = ui;
		this.userInterface = ui; // hide behind the interface UI
	}
	
	public boolean skunkEndTournament(SkunkPlayer[] arrayPlayers) {
		String endTournamentPrompt = "Type 'end' to end the tournament, else it will continue";
		String tournamentContinueChoice = userInterface.promptReadAndReturn(endTournamentPrompt);
		
		if (tournamentContinueChoice.equals("end")) {
			userInterface.println("Understood. Tournament is shutting down...");
			userInterface.println("");
			return true;
		}
		else if (arrayPlayers.length == 1) {
			userInterface.println("We have a grand champion!");
			return true;
		}
		else
			return false;
	}
	
	//TODO: Check: should be own class? Anyway to further break down?
	public void skunkTournament() {
		while (true) {
			userInterface.println("New Game has been started...");
			userInterface.println("Resetting Individual Dice Totals and Kitty to 0");
			userInterface.println("");
			SkunkGame.playGame(SkunkPlayerManagement.playersArray);
			
			userInterface.println("Post-Game Evaluation...");
			SkunkPlayerManagement.removePlayers();
			SkunkPlayerManagement.displayChipsAll(SkunkPlayerManagement.playersArray);
			
			boolean continueTournament = skunkEndTournament(SkunkPlayerManagement.playersArray);
			if (continueTournament)
				break;
		}
	}
	
	public void run(){	
		userInterface.println("Tournament has began...");
		userInterface.println("Now registering players...");
		userInterface.println("");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
		userInterface.println("");
		// SkunkPlayerManagement.distributeChips();

		skunkTournament();
		
		userInterface.println("Final Report...");
		SkunkPlayerManagement.displayChipsAll(SkunkPlayerManagement.playersArray);

		userInterface.println("Tournament has ended...");
	}
	
	public static void main(String[] args){	
//
	}
}