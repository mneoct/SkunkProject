import edu.princeton.cs.introcs.StdOut;

public class TabulateWinnings {
// Move to own classes
	public static void tabulateWinnings(SkunkPlayer[] playersLastStretch, int indexOfWinner){
		int currentlyEvaluating = indexOfWinner + 1;
		StdOut.println();

		while (currentlyEvaluating != indexOfWinner){
			currentlyEvaluating = SkunkGame.resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
			
			SkunkPlayer playerBeingEvaluated = playersLastStretch[currentlyEvaluating];
			String PlayerName = playerBeingEvaluated.getName();
			int PlayerDicePoints = playerBeingEvaluated.getPlayerDiceTotal();
			
			StdOut.println(PlayerName + " is being evaluated...");
			StdOut.println("They have a total dice value of " + PlayerDicePoints);
			StdOut.println();
			
			plunderingDefeated(playerBeingEvaluated);
			currentlyEvaluating += 1;
			currentlyEvaluating = SkunkGame.resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
		}
		
		int kittyChips = SkunkKitty.getKitty();
		
		StdOut.println("Total winnings for winner: " + kittyChips);
		StdOut.println();
		StdOut.println("End of Game...");
		StdOut.println("Now Showing Players Sheet");
		
		playersLastStretch[indexOfWinner].setPlayerChipsTotal(kittyChips);
		SkunkPlayerManagement.displayChipsAll(playersLastStretch);
	}
	
	public static void plunderingDefeated(SkunkPlayer beingEvaluated) {
		int playerEvaluatedDiceTotal = beingEvaluated.getPlayerDiceTotal();
		if (playerEvaluatedDiceTotal > 0){
			plunderingDefeatedHelper(5, beingEvaluated);
		}
		else {
			plunderingDefeatedHelper(10, beingEvaluated);
		}
	}
	
	public static void plunderingDefeatedHelper(int plunderedChips, SkunkPlayer playerEvaluated) {
		String playerName = playerEvaluated.getName();
		StdOut.println("Therefore, " + plunderedChips + " chips are added to the winnings...");
		StdOut.println("And, " + playerName + " has lost " + plunderedChips + " chips.");
		
		playerEvaluated.setPlayerChipsTotal(-plunderedChips);
		SkunkKitty.setKitty(plunderedChips);
	}
}
