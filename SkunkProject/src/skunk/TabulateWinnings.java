package skunk;
import edu.princeton.cs.introcs.StdOut;

// tested in TabulateWinningsTest.java
public final class TabulateWinnings {
	public static void tabulateWinnings(SkunkPlayer[] playersLastStretch, int indexOfWinner){
		int currentlyEvaluating = indexOfWinner + 1;
		StdOut.println();

		while (currentlyEvaluating != indexOfWinner){
			currentlyEvaluating = UtilityMethods.resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
			
			SkunkPlayer playerBeingEvaluated = playersLastStretch[currentlyEvaluating];
			String PlayerName = playerBeingEvaluated.getName();
			int PlayerDicePoints = playerBeingEvaluated.getPlayerDiceTotal();
			
			StdOut.println(PlayerName + " is being evaluated...");
			StdOut.println("They have a total dice value of " + PlayerDicePoints);
			StdOut.println();
			
			plunderingDefeated(playerBeingEvaluated);
			currentlyEvaluating += 1;
			currentlyEvaluating = UtilityMethods.resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
		}
		
		int kittyChips = SkunkKitty.getKitty();
		
		StdOut.println("Total winnings for winner: " + kittyChips);
		StdOut.println();
		StdOut.println("End of Game...");
		StdOut.println("Now Showing Players Sheet");
		
		playersLastStretch[indexOfWinner].addToPlayerChipsTotal(kittyChips);
		SkunkTurnChoice1Stats.displayResults(playersLastStretch);
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
		
		playerEvaluated.addToPlayerChipsTotal(-plunderedChips);
		SkunkKitty.setKitty(plunderedChips);
	}
}