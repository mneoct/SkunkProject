import java.util.Random;

import edu.princeton.cs.introcs.StdOut;

public class SkunkGame {
	private static final int OVERFLOW_SCORE = 30; // = 100, set lower to test games faster...
	
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
				currentPlayerIndex = UtilityMethods.resetIndexOfLoopsArray(currentPlayerIndex, playersArrayGame.length);
			}
			
			StdOut.println("Next player's turn...");
			StdOut.println();
		}
		
		StdOut.println("Entering last stretch of current game...");
		int winnerOfGameIndex = lastStretch(playersArrayGame, currentlyPlaying.getPlayerDiceTotal(), currentPlayerIndex);
		
		StdOut.println(playersArrayGame[winnerOfGameIndex].getName() + " is the winner of this game...");
		TabulateWinnings.tabulateWinnings(playersArrayGame, winnerOfGameIndex);
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
			
			indexPlayerRolling = UtilityMethods.resetIndexOfLoopsArray(indexPlayerRolling, playersLastStretch.length);
			SkunkTurnMain.resetRoundDiceTotal();
			SkunkPlayer playerPlaying = playersLastStretch[indexPlayerRolling];
			String playerPlayingName = playerPlaying.getName();
			
			StdOut.println();
			StdOut.println(playerPlayingName + " is now rolling...");
			SkunkTurnAction.playerTurn(playerPlaying, playersLastStretch);
			StdOut.println();
			
			int playerPlayingDiceTotal = playerPlaying.getPlayerDiceTotal();
			
			StdOut.println(playerPlayingName + "'s score: " + playerPlayingDiceTotal);
			
			if (playerPlayingDiceTotal > goalToReach) {
				indexCurrentKingHill = indexPlayerRolling + 0;
				goalToReach = playerPlayingDiceTotal;
				StdOut.println(playerPlayingName + " is now the new top scorer, with " + playerPlayingDiceTotal);
			}
			
			indexPlayerRolling += 1;
			indexPlayerRolling = UtilityMethods.resetIndexOfLoopsArray(indexPlayerRolling, playersLastStretch.length);
			
		}
		return indexCurrentKingHill;
	}
	
	private static void printRandomQuotes(SkunkPlayer playerRefText) {
		String playerName = playerRefText.getName();
		String [] arr = {
        	"Fortune favors the bold. Are you bold, " +playerName + "?",
        	"I wonder what will " + playerName + " do?",
        	"Fortune be with you, " + playerName + "..."
        };
		
        Random random = new Random();
        int select = random.nextInt(arr.length); 
        System.out.println(arr[select]); 
	}

//	public static void main(String[] args){
//		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
//		SkunkGame.playGame(SkunkPlayerManagement.playersArray);
//		SkunkPlayerManagement.playersArray = SPMRemovePlayer.removePlayers(SkunkPlayerManagement.playersArray);
//		StdOut.println("Final Report...");
//		SkunkPlayerManagement.displayChipsAll(SkunkPlayerManagement.playersArray);
//	}
}