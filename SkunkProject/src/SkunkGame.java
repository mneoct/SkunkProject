import java.util.Random;

import edu.princeton.cs.introcs.StdOut;

public class SkunkGame {
	private static int OVERFLOW_SCORE = SkunkApp.getOVERFLOW_SCORE(); // make getter for overflowscore
	static void playGame(SkunkPlayer[] playersArrayGame) { 	// break into smaller bits...
		SkunkPlayer currentlyPlaying; // playGame()'s ref to current player.		
		int currentPlayerIndex = randomStartPlayer(playersArrayGame.length);

		while(true) {
			// reset dice and display players' current dice.
			PlayRound.resetRoundDiceTotal();
			StdOut.println("Players' Dice Total in Current Game:");
			SkunkApp.displayDiceAll(playersArrayGame);
			StdOut.println();
			
			// get current player, then they roll..
			currentlyPlaying = playersArrayGame[currentPlayerIndex];
			PlayRound.selectMove(currentlyPlaying, playersArrayGame); 
			StdOut.println();

			// Check for overflow (i.e. dice over 100). if so, break.
			// else, select next player.
			if (currentlyPlaying.getPlayerDiceTotal() > OVERFLOW_SCORE){
				StdOut.println("Dice Total of " + currentlyPlaying.getName() + " is over " + OVERFLOW_SCORE);
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
			SkunkPlayer playerPlaying = playersLastStretch[indexPlayerRolling];
			StdOut.println();
			StdOut.println(playerPlaying.getName() + " is now rolling...");
			StdOut.println();
			
			PlayRound.selectMove(playerPlaying, playersLastStretch);
			StdOut.println();
			
			StdOut.println(playerPlaying.getName()
				+ "'s score: " + playerPlaying.getPlayerDiceTotal());
			
			if (playerPlaying.getPlayerDiceTotal() > goalToReach) {
				indexCurrentKingHill = indexPlayerRolling + 0;
				goalToReach = playerPlaying.getPlayerDiceTotal();
				StdOut.println(playerPlaying.getName() + " is now the new top scorer, with " + playerPlaying.getPlayerDiceTotal());
			}
			indexPlayerRolling += 1;
			indexPlayerRolling = SkunkApp.resetIndexOfLoopsArray(indexPlayerRolling, playersLastStretch.length);
		}
		return indexCurrentKingHill;
	}
	private static void tabulateWinnings(SkunkPlayer[] playersLastStretch, int indexOfWinner){
		int currentlyEvaluating = indexOfWinner + 1;
		StdOut.println();

		while (currentlyEvaluating != indexOfWinner){
			currentlyEvaluating = SkunkApp.resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
			SkunkPlayer playerBeingEvaluated = playersLastStretch[currentlyEvaluating];
			StdOut.println(playerBeingEvaluated.getName() + " is being evaluated...");
			StdOut.println("They have a total dice value of " + playerBeingEvaluated.getPlayerDiceTotal());
			
			plunderingDefeated(playerBeingEvaluated);
			
			StdOut.println();
			currentlyEvaluating += 1;
			currentlyEvaluating = SkunkApp.resetIndexOfLoopsArray(currentlyEvaluating, playersLastStretch.length);
		}
		StdOut.println("Total winnings for winner: " + SkunkKitty.getKitty());
		StdOut.println();
		playersLastStretch[indexOfWinner].setPlayerChipsTotal(SkunkKitty.getKitty());
		StdOut.println("End of Game...");
		StdOut.println("Now Showing Players Sheet");
		SkunkApp.printPlayersSheet();
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
}