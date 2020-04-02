import java.util.Random;

import edu.princeton.cs.introcs.StdOut;

public class SkunkGame {
	private final static int OVERFLOW_SCORE = SkunkMain.getOverflowScore();
	
//TODO: Break into smaller bits..	
	protected static void playGame(SkunkPlayer[] playersArrayGame) { 	// break into smaller bits...
		SkunkPlayer currentlyPlaying; 		
		int currentPlayerIndex = randomStartPlayer(playersArrayGame.length);
		SkunkKitty.resetKitty();
		SkunkPlayerManagement.resetAllPlayersDice();
		
		while(true) {
			SkunkTurnMain.resetRoundDiceTotal();
			StdOut.println("Players' Dice Total in Current Game:");
			SkunkPlayerManagement.displayDiceAll(playersArrayGame);
			StdOut.println();
			
			currentlyPlaying = playersArrayGame[currentPlayerIndex];
			printRandomQuotes(currentlyPlaying);
			SkunkTurnAction.playerTurn(currentlyPlaying, playersArrayGame); 
			StdOut.println();

			if (currentlyPlaying.getPlayerDiceTotal() > OVERFLOW_SCORE){
				StdOut.println("Dice Total of " + currentlyPlaying.getName() + " is over " + OVERFLOW_SCORE);
				StdOut.println();
				break;
			}
			else {
				currentPlayerIndex += 1;
				currentPlayerIndex = resetIndexOfLoopsArray(currentPlayerIndex, playersArrayGame.length);
			}
			
			StdOut.println("Next player's turn...");
			StdOut.println();
		}
		
		StdOut.println("Entering last stretch of current game...");
		int winnerOfGameIndex = lastStretch(playersArrayGame, currentlyPlaying.getPlayerDiceTotal(), currentPlayerIndex);
		StdOut.println(playersArrayGame[winnerOfGameIndex].getName() + " is the winner of this game...");
		tabulateWinnings(playersArrayGame, winnerOfGameIndex);
	}
	
	private static int randomStartPlayer(int lengthOfArray) {
		StdOut.println("Choosing random player to start...");
		Random rand = new Random(); 
		return rand.nextInt(lengthOfArray); 
	}
	
//TODO: Break into smaller bits...	
	private static int lastStretch(SkunkPlayer[] playersLastStretch, int currentGoal, int incomingHillKingIndex){
		int goalToReach = currentGoal;
		int indexCurrentKingHill = incomingHillKingIndex + 0; 
		int indexPlayerRolling = incomingHillKingIndex + 1; // start with next player...
		
		StdOut.println("Last Stretch");
		StdOut.println("Current Top Scorer: " + playersLastStretch[indexCurrentKingHill].getName());
		StdOut.println("Score to Defeat: " + goalToReach);
		
		while (indexPlayerRolling != incomingHillKingIndex+0) {
			indexPlayerRolling = resetIndexOfLoopsArray(indexPlayerRolling, playersLastStretch.length);
			SkunkTurnMain.resetRoundDiceTotal();
			SkunkPlayer playerPlaying = playersLastStretch[indexPlayerRolling];
			StdOut.println();
			StdOut.println(playerPlaying.getName() + " is now rolling...");
			StdOut.println();
			
			SkunkTurnAction.playerTurn(playerPlaying, playersLastStretch);
			StdOut.println();
			
			StdOut.println(playerPlaying.getName()
				+ "'s score: " + playerPlaying.getPlayerDiceTotal());
			
			if (playerPlaying.getPlayerDiceTotal() > goalToReach) {
				indexCurrentKingHill = indexPlayerRolling + 0;
				goalToReach = playerPlaying.getPlayerDiceTotal();
				StdOut.println(playerPlaying.getName() + " is now the new top scorer, with " + playerPlaying.getPlayerDiceTotal());
			}
			indexPlayerRolling += 1;
			indexPlayerRolling = resetIndexOfLoopsArray(indexPlayerRolling, playersLastStretch.length);
		}
		return indexCurrentKingHill;
	}
	private static void tabulateWinnings(SkunkPlayer[] playersLastStretch, int indexOfWinner){
		int currentlyEvaluating = indexOfWinner + 1;
		StdOut.println();

		while (currentlyEvaluating != indexOfWinner){
			currentlyEvaluating = resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
			SkunkPlayer playerBeingEvaluated = playersLastStretch[currentlyEvaluating];
			StdOut.println(playerBeingEvaluated.getName() + " is being evaluated...");
			StdOut.println("They have a total dice value of " + playerBeingEvaluated.getPlayerDiceTotal());
			
			plunderingDefeated(playerBeingEvaluated);
			
			StdOut.println();
			currentlyEvaluating += 1;
			currentlyEvaluating = resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
		}
		StdOut.println("Total winnings for winner: " + SkunkKitty.getKitty());
		StdOut.println();
		playersLastStretch[indexOfWinner].setPlayerChipsTotal(SkunkKitty.getKitty());
		StdOut.println("End of Game...");
		StdOut.println("Now Showing Players Sheet");
		SkunkPlayerManagement.displayChipsAll(playersLastStretch);
	}
	private static void plunderingDefeated(SkunkPlayer beingEvaluated) {
		if (beingEvaluated.getPlayerDiceTotal() > 0){
			plunderingDefeatedHelper(5, beingEvaluated);
		}
		else {
			plunderingDefeatedHelper(10, beingEvaluated);
		}
	}
	private static void plunderingDefeatedHelper(int valueToAdd, SkunkPlayer playerEvaluated) {
		StdOut.println("Therefore, " + valueToAdd +" chips are added to the winnings...");
		StdOut.println("And, " + playerEvaluated.getName() + " has lost " + valueToAdd + " chips.");
		playerEvaluated.setPlayerChipsTotal(-valueToAdd);
		SkunkKitty.setKitty(valueToAdd);
	}
	private static int resetIndexOfLoopsArray(int arrayCurrentIndex, int arrayLength) {
		if (arrayCurrentIndex >= arrayLength)
			arrayCurrentIndex = 0;
		return arrayCurrentIndex;
	}
	private static void printRandomQuotes(SkunkPlayer playerRefText) {
		String [] arr = {
        	"Fortune favors the bold. Are you bold, " + playerRefText.getName() + "?",
        	"I wonder what will " + playerRefText.getName() + " do?"};
        Random random = new Random();
        int select = random.nextInt(arr.length); 
        System.out.println(arr[select]); 
	}
}