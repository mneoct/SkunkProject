
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
	// Move to skunkPlayer.java
	// addPlayerToArray() based on https://www.geeksforgeeks.org/how-to-add-an-element-to-an-array-in-java/
	/*
	 * private static void addPlayers(){ // long, but kind of tricky to break..?
	 * while (true) { StdOut.println("Adding New Player...");
	 * StdOut.println("Enter ... to continue without adding");
	 * StdOut.println("Enter the new player's name:"); String userName =
	 * myObj.nextLine(); StdOut.println();
	 * 
	 * if (userName.equals("...")) { if (playersArray == null || playersArray.length
	 * < 2) { StdOut.println("Insufficient players."); continue; } else {
	 * StdOut.println("Players have been registered."); break; } } else { try { //
	 * players_array will be null at first, so exception on 1st run. SkunkPlayer
	 * newPlayer = new SkunkPlayer(userName); playersArray =
	 * addPlayerToArray(playersArray.length, playersArray, newPlayer); } catch
	 * (NullPointerException e) { playersArray = new SkunkPlayer[1]; playersArray[0]
	 * = new SkunkPlayer(userName); } }
	 * 
	 * try { StdOut.println("Current number of players: " + playersArray.length);
	 * for (SkunkPlayer player:playersArray) { StdOut.println(player.getName()); } }
	 * catch (NullPointerException e) { StdOut.println("Current number of players: "
	 * + playersArray.length); for (SkunkPlayer player:playersArray) {
	 * StdOut.println(player); } } if (playersArray.length >= MAX_PLAYERS) {
	 * StdOut.println("Max Players (" + MAX_PLAYERS + ") has been reached."); break;
	 * } StdOut.println(); } StdOut.println("Players Registration Complete.");
	 * StdOut.println("Proceeding to gameplay"); }
	 */
	/*
	 * public static SkunkPlayer[] addPlayerToArray(int currentPlayersCount,
	 * SkunkPlayer[] CurrentSkunkPlayerArray, SkunkPlayer newPlayerToBeAdded){ int
	 * i; SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount + 1];
	 * for (i = 0; i < currentPlayersCount; i++) newSkunkArray[i] =
	 * CurrentSkunkPlayerArray[i]; newSkunkArray[currentPlayersCount] =
	 * newPlayerToBeAdded; return newSkunkArray; } public static SkunkPlayer[]
	 * removePlayerFromArray(int currentPlayersCount, SkunkPlayer[]
	 * CurrentSkunkPlayerArray, int playerRemovedIndex){ int i; int j = 0;
	 * SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount - 1]; for
	 * (i = 0; i < currentPlayersCount; i++) if (i != playerRemovedIndex) {
	 * newSkunkArray[j] = CurrentSkunkPlayerArray[i]; j++; } return newSkunkArray; }
	 * private static SkunkPlayer[] removePlayers(SkunkPlayer[] arrayOfPlayers) {
	 * StdOut.println("Now checking for eliminated players..."); StdOut.println();
	 * outerloop: while (true) { innerloop: for (int i = 0; i <
	 * arrayOfPlayers.length + 1; i++) { if (i >= arrayOfPlayers.length) {
	 * StdOut.println("Done."); break outerloop; } if
	 * (arrayOfPlayers[i].getPlayerChipsTotal() <= 0) { StdOut.println("Removing " +
	 * arrayOfPlayers[i].getName()); arrayOfPlayers =
	 * removePlayerFromArray(arrayOfPlayers.length, arrayOfPlayers, i);
	 * StdOut.println(); break innerloop; } } } StdOut.println("Confirmation");
	 * return arrayOfPlayers; }
	 */
	//set total chips to white,red,blue. Setup possible, but implementation is nightmare
	//nightmare to distribute chips to less than 8 people,
	// nightmare to manage the actual count of each type, how chips are exchanged:
	// e.g. give red chips, expect 2 white ones, what if they don't have any etc. 
	//private static final int blueChipsValue = 10;
	//private static final int redChipsValue = 5;
	//private static final int whiteChipsValue = 1;
	//private int blueChipsTotal = 16;
	//private int redChipsTotal = 32;
	//private int whiteChipsTotal = 80;
}
