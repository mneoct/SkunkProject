import java.util.Scanner;
//import java.util.Random; 
import edu.princeton.cs.introcs.*;

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

public class SkunkApp { // main program
	private static final int MAX_PLAYERS = 8;
	static final int OVERFLOWSCORE = 50; // = 100, set lower to test games faster...
	private final static int TOTAL_CHIPS = 100; // = 400, set lower for test games faster...
	public static Scanner myObj = new Scanner(System.in);
	private static SkunkPlayer playersArray[];
	
	// Move to skunkPlayer.java
	// addPlayerToArray() based on https://www.geeksforgeeks.org/how-to-add-an-element-to-an-array-in-java/
	private static void addPlayers(){	// long, but kind of tricky to break..?
		while (true) {
			StdOut.println("Adding New Player...");
			StdOut.println("Enter ... to continue without adding");
			StdOut.println("Enter the new player's name:");
			String userName = myObj.nextLine();
			StdOut.println();
			
			if (userName.equals("...")) {
				if (playersArray == null || playersArray.length < 2) {
					StdOut.println("Insufficient players.");
					continue;
				}
				else {
					StdOut.println("Players have been registered.");
					break;
				}
			}
			else {
				try { // players_array will be null at first, so exception on 1st run.
					SkunkPlayer newPlayer = new SkunkPlayer(userName);
					playersArray = addPlayerToArray(playersArray.length, playersArray, newPlayer);
				}
				catch (NullPointerException e) {
					playersArray = new SkunkPlayer[1];
					playersArray[0] = new SkunkPlayer(userName);
				}
			}
			
			try {
				StdOut.println("Current number of players: " + playersArray.length);
				for (SkunkPlayer player:playersArray) {
					StdOut.println(player.getName());
				}
			}
			catch (NullPointerException e) {
				StdOut.println("Current number of players: " + playersArray.length);
				for (SkunkPlayer player:playersArray) {
					StdOut.println(player);
				}
			}
			if (playersArray.length >= MAX_PLAYERS) {
				StdOut.println("Max Players (" + MAX_PLAYERS + ") has been reached.");
				break;
			}
			StdOut.println();
		}
		StdOut.println("Players Registration Complete.");
		StdOut.println("Proceeding to gameplay");
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
		StdOut.println("Now checking for eliminated players...");
		StdOut.println();
		outerloop:
		while (true) {
			innerloop:
			for (int i = 0; i < arrayOfPlayers.length + 1; i++) {
				if (i >= arrayOfPlayers.length) {
					StdOut.println("Done.");
					break outerloop;
				}
				if (arrayOfPlayers[i].getPlayerChipsTotal() <= 0) {
					StdOut.println("Removing " + arrayOfPlayers[i].getName());
					arrayOfPlayers = removePlayerFromArray(arrayOfPlayers.length, arrayOfPlayers, i);			
					StdOut.println();
					break innerloop;
				}
			}
		}
		StdOut.println("Confirmation");
		return arrayOfPlayers;
	}
	public static void distributeChips(SkunkPlayer[] inputPlayersArray) {
		StdOut.println("Distribution of chips to players initiated...");
		int chipDistributed = TOTAL_CHIPS / inputPlayersArray.length;
		for (SkunkPlayer player : inputPlayersArray) {
			player.setPlayerChipsTotal(chipDistributed);
			StdOut.println(player.getName() + " has been given " + chipDistributed + " chips.");
		}
		StdOut.println("Distribution of chips to players complete.");
		StdOut.println();
	}
	public static void displayDiceAll(SkunkPlayer[] arrayOfSkunkPlayers) {
		for (SkunkPlayer player:arrayOfSkunkPlayers)
			StdOut.println("\t"+player.getName() + ": " + player.getPlayerDiceTotal());
	}
	public static void printPlayersSheet(){
		for (SkunkPlayer player : playersArray)
			StdOut.println(player.getName()+":"+player.getPlayerChipsTotal());
		StdOut.println();
	}

	// Universal
	static int resetIndexOfLoopsArray(int arrayCurrentIndex, int arrayLength) {
		if (arrayCurrentIndex >= arrayLength)
			arrayCurrentIndex = 0;
		return arrayCurrentIndex;
	}			
	public static void startUpTournament() {
		StdOut.println("Tournament has began...");
		addPlayers();
		StdOut.println();
	}
	private static void startUpGameResetValues() {
		for (SkunkPlayer player : playersArray)
			player.resetDice();
		SkunkKitty.resetKitty();
	}
	
	public static void main(String[] args){
		Scanner exitInput = new Scanner(System.in);	

		startUpTournament();
		distributeChips(playersArray);

		while (true) {
			StdOut.println("New Game has been started...");
			StdOut.println("Resetting Individual Dice Totals and Kitty to 0");
			startUpGameResetValues();
			StdOut.println();
			
			StdOut.println("Game being played...");
			SkunkGame.playGame(playersArray);
			
			StdOut.println("Post-Game Evaluation...");
			playersArray = removePlayers(playersArray);
			printPlayersSheet();
			
			StdOut.println("Type 'end' to end the tournament, else it will continue");
			String tournamentContinueChoice = exitInput.nextLine();
			
			if (tournamentContinueChoice.toLowerCase().equals("end")) {
				StdOut.println("Understood. Tournament is shutting down...");
				StdOut.println();
				break;
			}
			else if (playersArray.length == 1) {
				StdOut.println("We have a grand champion!");
				break;
			}
		}
		
		StdOut.println("Final Report...");
		printPlayersSheet();

		StdOut.println("Tournament has ended...");
		myObj.close();
		exitInput.close();
	}
}