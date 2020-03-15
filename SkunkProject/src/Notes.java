
public class Notes {
//For backups?
//	// Move to SkunkGame.java
//	private static int randomStartPlayer(int lengthOfArray) {
//		StdOut.println("Choosing random player to start...");
//		Random rand = new Random(); 
//		return rand.nextInt(lengthOfArray); 
//	}
//	private static void turnSetup() {
//		PlayRound.reset_round_dice_total();
//		StdOut.println("Players' Dice Total in Current Game:");
//		displayDiceAll(players_array);
//	}
//	private static void playGame() { 	// break into smaller bits...
//		SkunkPlayer currentlyPlaying; // play_game()'s ref to current player.		
//		int CurrentPlayerIndex = randomStartPlayer(players_array.length);
//
//		while(true) {
//			turnSetup();
//			StdOut.println();
//			
//			currentlyPlaying = players_array[CurrentPlayerIndex];
//			PlayRound.selectMove(currentlyPlaying, players_array); 
//			StdOut.println();
//
//			// could be a method, but tricky due to the break.
//			if (currentlyPlaying.get_dice_total() > OVERFLOWSCORE){
//				StdOut.println("Dice Total of " + currentlyPlaying.get_name() + " is over " + OVERFLOWSCORE);
//				StdOut.println();
//				break;
//			}
//			else {
//				CurrentPlayerIndex += 1;
//				CurrentPlayerIndex = resetIndexOfLoopsArray(CurrentPlayerIndex, players_array.length);
//			}
//			
//			StdOut.println("Next player's turn...");
//			StdOut.println();
//		}
//		
//		StdOut.println("Entering last stretch of current game...");
//		int WinnerOfGameIndex = lastStretch(currentlyPlaying.get_dice_total(), CurrentPlayerIndex);
//		StdOut.println(players_array[WinnerOfGameIndex].get_name() + " is the winner of this game...");
//		tabulateWinnings(WinnerOfGameIndex);
//	}
//	private static int lastStretch(int currentGoal, int incomingHillKingIndex){
//		int goalToReach = currentGoal;
//		int indexCurrentKingHill = incomingHillKingIndex + 0;
//		int IndexPlayerRolling = incomingHillKingIndex + 1; // start with next player...
//		
//		StdOut.println("Last Stretch");
//		StdOut.println("Current Top Scorer: " + players_array[indexCurrentKingHill].get_name());
//		StdOut.println("Score to Defeat: " + goalToReach);
//		
//		while (IndexPlayerRolling != incomingHillKingIndex+0) {
//			IndexPlayerRolling = resetIndexOfLoopsArray(IndexPlayerRolling, players_array.length);
//			PlayRound.reset_round_dice_total();
//			StdOut.println();
//			StdOut.println(players_array[IndexPlayerRolling].get_name() + " is now rolling...");
//			StdOut.println();
//			
//			PlayRound.selectMove(players_array[IndexPlayerRolling], players_array);
//			StdOut.println();
//			
//			StdOut.println(players_array[IndexPlayerRolling].get_name()
//				+ "'s score: " + players_array[IndexPlayerRolling].get_dice_total());
//			
//			if (players_array[IndexPlayerRolling].get_dice_total() > goalToReach) {
//				indexCurrentKingHill = IndexPlayerRolling + 0;
//				goalToReach = players_array[IndexPlayerRolling].get_dice_total();
//				StdOut.println(players_array[IndexPlayerRolling].get_name()
//				+ " is now the new top scorer, with " + players_array[IndexPlayerRolling].get_dice_total());
//			}
//			IndexPlayerRolling += 1;
//			IndexPlayerRolling = resetIndexOfLoopsArray(IndexPlayerRolling, players_array.length);
//		}
//		return indexCurrentKingHill;
//	}
//	private static int plunderingDefeated(int winningsToAddinput, int valueToAdd, SkunkPlayer playerEvaluated) {
//		StdOut.println("Therefore, " + valueToAdd +" chips are added to the winnings...");
//		playerEvaluated.set_chips_total(-valueToAdd);
//		return winningsToAddinput += valueToAdd;
//	}
//	private static void tabulateWinnings(int IndexOfWinner){
//		int currentlyEvaluating = IndexOfWinner + 1;
//		int winningsToAdd = SkunkKitty.get_kitty();
//		StdOut.println();
//
//		while (currentlyEvaluating != IndexOfWinner){
//			if(currentlyEvaluating >= players_array.length)
//				currentlyEvaluating = 0;
//			StdOut.println(players_array[currentlyEvaluating].get_name() + " is being evaluated...");
//			StdOut.println("They have a total dice value of " + players_array[currentlyEvaluating].get_dice_total());
//			
//			if (players_array[currentlyEvaluating].get_dice_total() > 0){
//				winningsToAdd = plunderingDefeated(winningsToAdd, 5, players_array[currentlyEvaluating]);
//			}
//			else {
//				winningsToAdd = plunderingDefeated(winningsToAdd, 10, players_array[currentlyEvaluating]);
//			}
//			StdOut.println();
//			currentlyEvaluating += 1;
//			if(currentlyEvaluating >= players_array.length){
//				currentlyEvaluating = 0;
//			}
//		}
//		StdOut.println("Total winnings for winner: " + winningsToAdd);
//		StdOut.println();
//		players_array[IndexOfWinner].set_chips_total(winningsToAdd);
//		StdOut.println("End of Game...");
//		StdOut.println("Now Showing Players Sheet");
//		SkunkApp.printPlayersSheet();
//	}
//	
}
