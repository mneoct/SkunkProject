import java.util.Scanner;
//import java.util.Random; 
import edu.princeton.cs.introcs.*;

// TODO:
// Should negative chips (debt) be allowed???
// allow removal of players at beginning (i.e. undo adding players...)
// allow quitting in middle of game??
// BP: resolve accessibilities after figuring out classes, methods.

public class SkunkApp { // main program
	private static final int MAX_PLAYERS = 8;
	private static final int OVERFLOW_SCORE = 50; // = 100, set lower to test games faster...
	private final static int TOTAL_CHIPS = 100; // = 400, set lower for test games faster...
	private static Scanner myObj = new Scanner(System.in);
	private static SkunkPlayer[] playersArray;
	
	public static int getOVERFLOW_SCORE() {
        return OVERFLOW_SCORE;
    }
	public static int getMAX_PLAYERS() {
        return MAX_PLAYERS;
    }
	public static int getTOTAL_CHIPS() {
        return TOTAL_CHIPS;
    }
	
	public static void distributeChips(SkunkPlayer[] inputPlayersArray) {
		StdOut.println("Distribution of chips to players initiated...");
		int chipDistributed = getTOTAL_CHIPS() / inputPlayersArray.length;
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
	private static void startUpTournament() {
		StdOut.println("Tournament has began...");
		playersArray = SkunkPlayerManagement.addPlayers();
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
			playersArray = SkunkPlayerManagement.removePlayers(playersArray);
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