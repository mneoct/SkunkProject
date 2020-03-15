import java.util.Random;

import edu.princeton.cs.introcs.StdOut;

public class SkunkGame {
	private static int OVERFLOWSCORE = SkunkApp.OVERFLOWSCORE; // make getter for overflowscore
	static void playGame(SkunkPlayer[] playersArrayGame) { 	// break into smaller bits...
		SkunkPlayer currentlyPlaying; // playGame()'s ref to current player.		
		int currentPlayerIndex = randomStartPlayer(playersArrayGame.length);

		while(true) {
			PlayRound.resetRoundDiceTotal();
			StdOut.println("Players' Dice Total in Current Game:");
			SkunkApp.displayDiceAll(playersArrayGame);
			StdOut.println();
			
			currentlyPlaying = playersArrayGame[currentPlayerIndex];
			PlayRound.selectMove(currentlyPlaying, playersArrayGame); 
			StdOut.println();

			// could be a method, but tricky due to the break.
			if (currentlyPlaying.getPlayerDiceTotal() > OVERFLOWSCORE){
				StdOut.println("Dice Total of " + currentlyPlaying.getName() + " is over " + OVERFLOWSCORE);
				StdOut.println();
				break;
			}
			else {
				currentPlayerIndex += 1;
				currentPlayerIndex = SkunkApp.resetIndexOfLoopsArray(currentPlayerIndex, playersArrayGame.length);
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
	private static int lastStretch(SkunkPlayer[] playersLastStretch, int currentGoal, int incomingHillKingIndex){
		int goalToReach = currentGoal;
		int indexCurrentKingHill = incomingHillKingIndex + 0;
		int indexPlayerRolling = incomingHillKingIndex + 1; // start with next player...
		StdOut.println("Last Stretch");

		StdOut.println("Current Top Scorer: " + playersLastStretch[indexCurrentKingHill].getName());
		StdOut.println("Score to Defeat: " + goalToReach);
		
		while (indexPlayerRolling != incomingHillKingIndex+0) {
			indexPlayerRolling = SkunkApp.resetIndexOfLoopsArray(indexPlayerRolling, playersLastStretch.length);
			PlayRound.resetRoundDiceTotal();
			StdOut.println();
			StdOut.println(playersLastStretch[indexPlayerRolling].getName() + " is now rolling...");
			StdOut.println();
			
			PlayRound.selectMove(playersLastStretch[indexPlayerRolling], playersLastStretch);
			StdOut.println();
			
			StdOut.println(playersLastStretch[indexPlayerRolling].getName()
				+ "'s score: " + playersLastStretch[indexPlayerRolling].getPlayerDiceTotal());
			
			if (playersLastStretch[indexPlayerRolling].getPlayerDiceTotal() > goalToReach) {
				indexCurrentKingHill = indexPlayerRolling + 0;
				goalToReach = playersLastStretch[indexPlayerRolling].getPlayerDiceTotal();
				StdOut.println(playersLastStretch[indexPlayerRolling].getName()
				+ " is now the new top scorer, with " + playersLastStretch[indexPlayerRolling].getPlayerDiceTotal());
			}
			indexPlayerRolling += 1;
			indexPlayerRolling = SkunkApp.resetIndexOfLoopsArray(indexPlayerRolling, playersLastStretch.length);
		}
		return indexCurrentKingHill;
	}
	private static void tabulateWinnings(SkunkPlayer[] playersLastStretch, int indexOfWinner){
		int currentlyEvaluating = indexOfWinner + 1;
		int winningsToAdd = SkunkKitty.getKitty();
		StdOut.println();

		while (currentlyEvaluating != indexOfWinner){
			if(currentlyEvaluating >= playersLastStretch.length)
				currentlyEvaluating = 0;
			StdOut.println(playersLastStretch[currentlyEvaluating].getName() + " is being evaluated...");
			StdOut.println("They have a total dice value of " + playersLastStretch[currentlyEvaluating].getPlayerDiceTotal());
			
			if (playersLastStretch[currentlyEvaluating].getPlayerDiceTotal() > 0){
				winningsToAdd = plunderingDefeated(winningsToAdd, 5, playersLastStretch[currentlyEvaluating]);
			}
			else {
				winningsToAdd = plunderingDefeated(winningsToAdd, 10, playersLastStretch[currentlyEvaluating]);
			}
			StdOut.println();
			currentlyEvaluating += 1;
			if(currentlyEvaluating >= playersLastStretch.length){
				currentlyEvaluating = 0;
			}
		}
		StdOut.println("Total winnings for winner: " + winningsToAdd);
		StdOut.println();
		playersLastStretch[indexOfWinner].setPlayerChipsTotal(winningsToAdd);
		StdOut.println("End of Game...");
		StdOut.println("Now Showing Players Sheet");
		SkunkApp.printPlayersSheet();
	}
	private static int plunderingDefeated(int winningsToAddinput, int valueToAdd, SkunkPlayer playerEvaluated) {
		StdOut.println("Therefore, " + valueToAdd +" chips are added to the winnings...");
		StdOut.println("And, " + playerEvaluated.getName() + " has lost " + valueToAdd + " chips.");
		playerEvaluated.setPlayerChipsTotal(-valueToAdd);
		return winningsToAddinput += valueToAdd;
	}
}