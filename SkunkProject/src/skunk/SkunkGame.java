package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkGame {
	private static final int OVERFLOW_SCORE = 100; // = 100, set lower to test games faster...
	
	public static void oneGame(final SkunkPlayer[] playersArrayGame) { 	// break into smaller bits...
		final int startingPlayerIndex = SkunkPlayerManagement.randomStartPlayer(playersArrayGame.length);
		StdOut.println("New Game has been started...");
		StdOut.println("Resetting Individual Dice Totals and Kitty to 0");
		SkunkKitty.resetKitty();
		SkunkPlayerManagement.resetAllPlayersDice();
		StdOut.println("");
		StdOut.println("<<<>>>");
		StdOut.println("");

		
		final int indexTopPlayer = gamePlay(playersArrayGame, startingPlayerIndex);
		final SkunkPlayer topPlayer = playersArrayGame[indexTopPlayer];
		
		StdOut.println("Entering last stretch of current game...");
		
		StdOut.println("");
		StdOut.println("<<<!!!>>>");
		StdOut.println("");
		
		final int topPlayerDiceTotal = topPlayer.getPlayerDiceTotal();
		final int winnerOfGameIndex = lastStretch(playersArrayGame, topPlayerDiceTotal, indexTopPlayer);
		final SkunkPlayer winningPlayer = playersArrayGame[winnerOfGameIndex];
		final String winnerPlayerName = winningPlayer.getName();
		
		StdOut.println("");
		StdOut.println("<<<>>>");
		StdOut.println("");
		
		StdOut.println(winnerPlayerName + " is the winner of this game...");
		TabulateWinnings.tabulateWinnings(playersArrayGame, winnerOfGameIndex);
	}

	public static int gamePlay(final SkunkPlayer[] playersArrayGame, final int currentPlayerIndex) {
		SkunkPlayer internalCurrentlyPlaying;
		int internalCurrentPlayerIndex = currentPlayerIndex;
		
		while(true) {
			internalCurrentlyPlaying = playersArrayGame[internalCurrentPlayerIndex];
			playATurn(internalCurrentlyPlaying, playersArrayGame);

			final boolean overFlowCheckBreak = overflowOrNextPlayer(internalCurrentlyPlaying);
			if (overFlowCheckBreak) {
				final String currentlyPlayingName = internalCurrentlyPlaying.getName();
				StdOut.println("Dice Total of " + currentlyPlayingName + " is at least " + OVERFLOW_SCORE);
				break;
			}
			internalCurrentPlayerIndex = SkunkPlayerManagement.selectNextPlayer(playersArrayGame, internalCurrentPlayerIndex);
		}
		return internalCurrentPlayerIndex;
	}
	
	public static int lastStretch(final SkunkPlayer[] playersLastStretch, final int currentGoal, final int incomingHillKingIndex){
		int goalToReach = currentGoal;
		int indexCurrentKingHill = incomingHillKingIndex + 0; 
		int indexPlayerRolling = SkunkPlayerManagement.selectNextPlayer(playersLastStretch, indexCurrentKingHill);
		
		SkunkPlayer playerPlaying;
		final String topScorerName = playersLastStretch[indexCurrentKingHill].getName();
		
		StdOut.println("Last Stretch!!");
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
			indexPlayerRolling = SkunkPlayerManagement.selectNextPlayer(playersLastStretch, indexPlayerRolling);
		}
		return indexCurrentKingHill;
	}
	
	public static void playATurn(final SkunkPlayer playerPlaying, final SkunkPlayer[] arrayPlayers) {
		SkunkTurnAction.playerTurn(playerPlaying, arrayPlayers); 
	}
	
	public static boolean overflowOrNextPlayer(final SkunkPlayer currentlyPlaying) {
		boolean isOverflow = false;
		final int currentlyPlayingDice = currentlyPlaying.getPlayerDiceTotal();

		if (currentlyPlayingDice >= OVERFLOW_SCORE){
			StdOut.println();
			isOverflow = true;
		}
		return isOverflow;
	}

//	public static void main(String[] args){
//		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
//		SkunkGame.playGame(SkunkPlayerManagement.playersArray);
//		SkunkPlayerManagement.playersArray = SPMRemovePlayer.removePlayers(SkunkPlayerManagement.playersArray);
//		StdOut.println("Final Report...");
//		SkunkPlayerManagement.displayChipsAll(SkunkPlayerManagement.playersArray);
//	}
}