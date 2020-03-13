import java.util.Scanner;
import java.util.Random; 
// TODO:
// Should negative chips (debt) be allowed???
// allow removal of players at beginning (i.e. undo adding players...)
// allow quitting in middle of game??
// BP: rename items to follow conventions.
// BP: resolve accessibilities after figuring out classes, methods.

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

public class UniversalBoard { // main program
	private static final int MAX_PLAYERS = 8;
	private static final int OVERFLOWSCORE = 100; // = 100, set lower to test games faster...
	private final static int TOTAL_CHIPS = 400; // = 400, set lower for test games faster...
	public static Scanner myObj = new Scanner(System.in);
	private static SkunkPlayer players_array[];
	
	// Move to skunkPlayer.java
	// addPlayerToArray() based on https://www.geeksforgeeks.org/how-to-add-an-element-to-an-array-in-java/
	private static void add_players(){	// long, but kind of tricky to break..?
		while (true) {
			System.out.println("Adding New Player...");
			System.out.println("Enter ... to continue without adding");
			System.out.println("Enter the new player's name:");
			String userName = myObj.nextLine();
			System.out.println();
			
			if (userName.equals("...")) {
				if (players_array == null || players_array.length < 2) {
					System.out.println("Insufficient players.");
					continue;
				}
				else {
					System.out.println("Players have been registered.");
					break;
				}
			}
			else {
				try { // players_array will be null at first, so exception on 1st run.
					SkunkPlayer newPlayer = new SkunkPlayer(userName);
					players_array = addPlayerToArray(players_array.length, players_array, newPlayer);
				}
				catch (NullPointerException e) {
					players_array = new SkunkPlayer[1];
					players_array[0] = new SkunkPlayer(userName);
				}
			}
			
			try {
				System.out.println("Current number of players: " + players_array.length);
				for (SkunkPlayer player:players_array) {
					System.out.println(player.get_name());
				}
			}
			catch (NullPointerException e) {
				System.out.println("Current number of players: " + players_array.length);
				for (SkunkPlayer player:players_array) {
					System.out.println(player);
				}
			}
			if (players_array.length >= MAX_PLAYERS) {
				System.out.println("Max Players (" + MAX_PLAYERS + ") has been reached.");
				break;
			}
			System.out.println();
		}
		System.out.println("Players Registration Complete.");
		System.out.println("Proceeding to gameplay");
	}
	public static SkunkPlayer[] addPlayerToArray(int currentPlayersCount,
				SkunkPlayer[] CurrentSkunkPlayerArray, SkunkPlayer newPlayerToBeAdded){
        int i; 
        SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount + 1]; 
        for (i = 0; i < currentPlayersCount; i++) 
            newSkunkArray[i] = CurrentSkunkPlayerArray[i]; 
        newSkunkArray[currentPlayersCount] = newPlayerToBeAdded; 
        return newSkunkArray; 
    }
	public static SkunkPlayer[] removePlayerFromArray(int currentPlayersCount,
			SkunkPlayer[] CurrentSkunkPlayerArray, int playerRemovedIndex){
	    int i; 
	    int j = 0;
	    SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount - 1]; 
	    for (i = 0; i < currentPlayersCount; i++)
	    	if (i != playerRemovedIndex) {
	    		newSkunkArray[j] = CurrentSkunkPlayerArray[i]; 
	    		j++;
	    	}
	    return newSkunkArray; 
	}
	private static SkunkPlayer[] removePlayers(SkunkPlayer[] arrayOfPlayers) {
		System.out.println("Now checking for eliminated players...");
		System.out.println();
		outerloop:
		while (true) {
			innerloop:
			for (int i = 0; i < arrayOfPlayers.length + 1; i++) {
				if (i >= arrayOfPlayers.length) {
					System.out.println("Done.");
					break outerloop;
				}
				if (arrayOfPlayers[i].get_chips_total() <= 0) {
					System.out.println("Removing " + arrayOfPlayers[i].get_name());
					arrayOfPlayers = removePlayerFromArray(arrayOfPlayers.length, arrayOfPlayers, i);			
					System.out.println();
					break innerloop;
				}
			}
		}
		System.out.println("Confirmation");
		return arrayOfPlayers;
	}
	public static void distributeChips(SkunkPlayer[] inputPlayersArray) {
		System.out.println("Distribution of chips to players initiated...");
		int chips_distributed = TOTAL_CHIPS / inputPlayersArray.length;
		for (SkunkPlayer player : inputPlayersArray) {
			player.set_chips_total(chips_distributed);
			System.out.println(player.get_name() + " has been given " + chips_distributed + " chips.");
		}
		System.out.println("Distribution of chips to players complete.");
		System.out.println();
	}
	public static void displayDiceAll(SkunkPlayer[] arrayOfSkunkPlayers) {
		for (SkunkPlayer player:arrayOfSkunkPlayers)
			System.out.println("\t"+player.get_name() + ": " + player.get_dice_total());
	}
	public static void printPlayersSheet(){
		for (SkunkPlayer player : players_array)
			System.out.println(player.get_name()+":"+player.get_chips_total());
		System.out.println();
	}

	// Move to SkunkGame.java
	private static int randomStartPlayer(int lengthOfArray) {
		System.out.println("Choosing random player to start...");
		Random rand = new Random(); 
		return rand.nextInt(lengthOfArray); 
	}
	private static void turnSetup() {
		PlayRound.reset_round_dice_total();
		System.out.println("Players' Dice Total in Current Game:");
		displayDiceAll(players_array);
	}
	private static void playGame() { 	// break into smaller bits...
		SkunkPlayer currentlyPlaying; // play_game()'s ref to current player.		
		int CurrentPlayerIndex = randomStartPlayer(players_array.length);

		while(true) {
			turnSetup();
			System.out.println();
			
			currentlyPlaying = players_array[CurrentPlayerIndex];
			PlayRound.selectMove(currentlyPlaying, players_array); 
			System.out.println();

			// could be a method, but tricky due to the break.
			if (currentlyPlaying.get_dice_total() > OVERFLOWSCORE){
				System.out.println("Dice Total of " + currentlyPlaying.get_name() + " is over " + OVERFLOWSCORE);
				System.out.println();
				break;
			}
			else {
				CurrentPlayerIndex += 1;
				CurrentPlayerIndex = resetIndexOfLoopsArray(CurrentPlayerIndex, players_array.length);
			}
			
			System.out.println("Next player's turn...");
			System.out.println();
		}
		
		System.out.println("Entering last stretch of current game...");
		int WinnerOfGameIndex = lastStretch(currentlyPlaying.get_dice_total(), CurrentPlayerIndex);
		System.out.println(players_array[WinnerOfGameIndex].get_name() + " is the winner of this game...");
		tabulateWinnings(WinnerOfGameIndex);
	}
	private static int lastStretch(int currentGoal, int incomingHillKingIndex){
		int goalToReach = currentGoal;
		int indexCurrentKingHill = incomingHillKingIndex + 0;
		int IndexPlayerRolling = incomingHillKingIndex + 1; // start with next player...
		
		System.out.println("Last Stretch");
		System.out.println("Current Top Scorer: " + players_array[indexCurrentKingHill].get_name());
		System.out.println("Score to Defeat: " + goalToReach);
		
		while (IndexPlayerRolling != incomingHillKingIndex+0) {
			IndexPlayerRolling = resetIndexOfLoopsArray(IndexPlayerRolling, players_array.length);
			PlayRound.reset_round_dice_total();
			System.out.println();
			System.out.println(players_array[IndexPlayerRolling].get_name() + " is now rolling...");
			System.out.println();
			
			PlayRound.selectMove(players_array[IndexPlayerRolling], players_array);
			System.out.println();
			
			System.out.println(players_array[IndexPlayerRolling].get_name()
				+ "'s score: " + players_array[IndexPlayerRolling].get_dice_total());
			
			if (players_array[IndexPlayerRolling].get_dice_total() > goalToReach) {
				indexCurrentKingHill = IndexPlayerRolling + 0;
				goalToReach = players_array[IndexPlayerRolling].get_dice_total();
				System.out.println(players_array[IndexPlayerRolling].get_name()
				+ " is now the new top scorer, with " + players_array[IndexPlayerRolling].get_dice_total());
			}
			IndexPlayerRolling += 1;
			IndexPlayerRolling = resetIndexOfLoopsArray(IndexPlayerRolling, players_array.length);
		}
		return indexCurrentKingHill;
	}
	private static int plunderingDefeated(int winningsToAddinput, int valueToAdd, SkunkPlayer playerEvaluated) {
		System.out.println("Therefore, " + valueToAdd +" chips are added to the winnings...");
		playerEvaluated.set_chips_total(-valueToAdd);
		return winningsToAddinput += valueToAdd;
	}
	private static void tabulateWinnings(int IndexOfWinner){
		int currentlyEvaluating = IndexOfWinner + 1;
		int winningsToAdd = SkunkKitty.get_kitty();
		System.out.println();

		while (currentlyEvaluating != IndexOfWinner){
			if(currentlyEvaluating >= players_array.length)
				currentlyEvaluating = 0;
			System.out.println(players_array[currentlyEvaluating].get_name() + " is being evaluated...");
			System.out.println("They have a total dice value of " + players_array[currentlyEvaluating].get_dice_total());
			
			if (players_array[currentlyEvaluating].get_dice_total() > 0){
				winningsToAdd = plunderingDefeated(winningsToAdd, 5, players_array[currentlyEvaluating]);
			}
			else {
				winningsToAdd = plunderingDefeated(winningsToAdd, 10, players_array[currentlyEvaluating]);
			}
			System.out.println();
			currentlyEvaluating += 1;
			if(currentlyEvaluating >= players_array.length){
				currentlyEvaluating = 0;
			}
		}
		System.out.println("Total winnings for winner: " + winningsToAdd);
		System.out.println();
		players_array[IndexOfWinner].set_chips_total(winningsToAdd);
		System.out.println("End of Game...");
		System.out.println("Now Showing Players Sheet");
		UniversalBoard.printPlayersSheet();
	}
	
	// Universal
	private static int resetIndexOfLoopsArray(int arrayCurrentIndex, int arrayLength) {
		if (arrayCurrentIndex >= arrayLength)
			arrayCurrentIndex = 0;
		return arrayCurrentIndex;
	}			
	public static void startUpTournament() {
		System.out.println("Tournament has began...");
		add_players();
		System.out.println();
	}
	private static void startUpGameResetValues() {
		for (SkunkPlayer player : players_array)
			player.reset_dice();
		SkunkKitty.reset_kitty();
	}
	
	public static void main(String[] args){
		Scanner exitInput = new Scanner(System.in);	

		startUpTournament();
		distributeChips(players_array);

		while (true) {
			System.out.println("New Game has been started...");
			System.out.println("Resetting Individual Dice Totals and Kitty to 0");
			startUpGameResetValues();
			System.out.println();
			
			System.out.println("Game being played...");
			playGame();
			
			System.out.println("Post-Game Evaluation...");
			players_array = removePlayers(players_array);
			printPlayersSheet();
			
			System.out.println("Type 'end' to end the tournament, else it will continue");
			String tournamentContinueChoice = exitInput.nextLine();
			
			if (tournamentContinueChoice.toLowerCase().equals("end")) {
				System.out.println("Understood. Tournament is shutting down...");
				System.out.println();
				break;
			}
			else if (players_array.length == 1) {
				System.out.println("We have a grand champion!");
				break;
			}
		}
		
		System.out.println("Final Report...");
		printPlayersSheet();

		System.out.println("Tournament has ended...");
		myObj.close();
		exitInput.close();
	}
}