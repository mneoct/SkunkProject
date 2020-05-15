package skunk;

public class SkunkMain { // main program
	public transient SkunkUI skunkUI;
	public transient UI userInterface;
	

	public SkunkMain(final SkunkUI ui)
	{
		this.skunkUI = ui;
		this.userInterface = ui; // hide behind the interface UI
	}
	

	public void run(){	
		userInterface.println("Tournament has began...");
		userInterface.println("Now registering players...");
		userInterface.println("");
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
		userInterface.println("");
		userInterface.println("<<<>>>");
		userInterface.println("");
		
		SkunkPlayerManagement.distributeChips();
		userInterface.println("");
		userInterface.println("<<<>>>");
		userInterface.println("");
		
		SkunkTournament.skunkTournament();
		
		userInterface.println("Final Report...");
		SkunkTurnChoice1Stats.displayResults(SkunkPlayerManagement.playersArray);

		userInterface.println("Tournament has ended...");
	}
}