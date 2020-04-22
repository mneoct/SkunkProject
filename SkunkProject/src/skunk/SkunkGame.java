package skunk;
import java.util.Random;

import edu.princeton.cs.introcs.StdOut;

public class SkunkGame {
	private static final int OVERFLOW_SCORE = 20; // = 100, set lower to test games faster...
	
	protected static void playGame(final SkunkPlayer[] playersArrayGame) { 	// break into smaller bits...
		final int startingPlayerIndex = randomStartPlayer(playersArrayGame.length);
		final SkunkPlayer startingPlayer = playersArrayGame[startingPlayerIndex];
		
		SkunkKitty.resetKitty();
		SkunkPlayerManagement.resetAllPlayersDice();
		
		final int indexTopPlayer = actualPlay(playersArrayGame, startingPlayer, startingPlayerIndex);
		final SkunkPlayer topPlayer = playersArrayGame[indexTopPlayer];
		
		StdOut.println("Entering last stretch of current game...");
		
		final int topPlayerDiceTotal = topPlayer.getPlayerDiceTotal();
		final int winnerOfGameIndex = lastStretch(playersArrayGame, topPlayerDiceTotal, indexTopPlayer);
		final SkunkPlayer winningPlayer = playersArrayGame[winnerOfGameIndex];
		final String winnerPlayerName = winningPlayer.getName(); // violates LoD, but why?
		
		StdOut.println(winnerPlayerName + " is the winner of this game...");
		TabulateWinnings.tabulateWinnings(playersArrayGame, winnerOfGameIndex);
	}
	
	public static boolean overflowOrNextPlayer(final SkunkPlayer currentlyPlaying) {
		boolean isOverflow = false;
		final int currentlyPlayingDice = currentlyPlaying.getPlayerDiceTotal();
		final String currentlyPlayingName = currentlyPlaying.getName();

		if (currentlyPlayingDice > OVERFLOW_SCORE){
			StdOut.println("Dice Total of " + currentlyPlayingName + " is over " + OVERFLOW_SCORE);
			StdOut.println();
			isOverflow = true;
		}
		return isOverflow;
	}
	
	public static int selectNextPlayer(final SkunkPlayer[] playersArrayGame, final int currentPlayerIndex) {
		int internalCurrentPlayerIndex = currentPlayerIndex;
		final SkunkPlayer[] internalPlayersArrayGame = playersArrayGame;
		
		internalCurrentPlayerIndex += 1;
		internalCurrentPlayerIndex = UtilityMethods.resetIndexOfLoopsArray(internalCurrentPlayerIndex, internalPlayersArrayGame.length);
		return internalCurrentPlayerIndex;
	}

	public static int actualPlay(final SkunkPlayer[] playersArrayGame, final SkunkPlayer currentlyPlaying, final int currentPlayerIndex) {
		SkunkPlayer internalCurrentlyPlaying = currentlyPlaying;
		int internalCurrentPlayerIndex = currentPlayerIndex;
		while(true) {
			StdOut.println("Players' Dice Total in Current Game:");
			SkunkPlayerManagement.displayDiceAll(playersArrayGame);
			StdOut.println();
			
			internalCurrentlyPlaying = playersArrayGame[currentPlayerIndex];
			playATurn(internalCurrentlyPlaying, playersArrayGame);

			final boolean overFlowCheckBreak = overflowOrNextPlayer(internalCurrentlyPlaying);
			if (overFlowCheckBreak) {
				break;
			}
			
			internalCurrentPlayerIndex = selectNextPlayer(playersArrayGame, internalCurrentPlayerIndex);
		}
		return internalCurrentPlayerIndex;
	}
	
	private static int lastStretch(final SkunkPlayer[] playersLastStretch, final int currentGoal, final int incomingHillKingIndex){
		int goalToReach = currentGoal;
		int indexCurrentKingHill = incomingHillKingIndex + 0; 
		int indexPlayerRolling = selectNextPlayer(playersLastStretch, indexCurrentKingHill);
		
		SkunkPlayer playerPlaying;
		final String topScorerName = playersLastStretch[indexCurrentKingHill].getName();
		
		StdOut.println("Last Stretch");
		StdOut.println("Current Top Scorer: " + topScorerName);
		StdOut.println("Score to Defeat: " + goalToReach);
		
		while (indexPlayerRolling != incomingHillKingIndex+0) {
			playerPlaying = playersLastStretch[indexPlayerRolling];
			playATurn(playerPlaying, playersLastStretch);
			
			final int playerPlayingDiceTotal = playerPlaying.getPlayerDiceTotal();
			final String playerPlayingName = playerPlaying.getName();
			StdOut.println(playerPlayingName + "'s score: " + playerPlayingDiceTotal);
			
			if (playerPlayingDiceTotal > goalToReach) {
				indexCurrentKingHill = indexPlayerRolling + 0;
				goalToReach = playerPlayingDiceTotal;
				StdOut.println(playerPlayingName + " is now the new top scorer, with " + playerPlayingDiceTotal);
			}
			
			indexPlayerRolling = selectNextPlayer(playersLastStretch, indexPlayerRolling);
		}
		return indexCurrentKingHill;
	}
	
	public static void playATurn(final SkunkPlayer playerPlaying, final SkunkPlayer[] arrayPlayers) {
		SkunkTurnMain.resetRoundDiceTotal();
		printRandomQuotes(playerPlaying);
		SkunkTurnAction.playerTurn(playerPlaying, arrayPlayers); 
	}
	
	private static int randomStartPlayer(final int lengthOfArray) {
		StdOut.println("Choosing random player to start...");
		final Random rand = new Random(); 
		return rand.nextInt(lengthOfArray); 
	}
	
	private static void printRandomQuotes(final SkunkPlayer playerRefText) {
		final String playerName = playerRefText.getName();
		final String [] arr = {
        	"Fortune favors the bold. Are you bold, " +playerName + "?",
        	"I wonder what will " + playerName + " do?",
        	"Fortune be with you, " + playerName + "..."
        };
		
        final Random random = new Random();
        final int select = random.nextInt(arr.length); 
        final String quoteString = arr[select];
        StdOut.println(quoteString); 
	}

//	public static void main(String[] args){
//		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
//		SkunkGame.playGame(SkunkPlayerManagement.playersArray);
//		SkunkPlayerManagement.playersArray = SPMRemovePlayer.removePlayers(SkunkPlayerManagement.playersArray);
//		StdOut.println("Final Report...");
//		SkunkPlayerManagement.displayChipsAll(SkunkPlayerManagement.playersArray);
//	}
}