import java.util.Scanner;
import edu.princeton.cs.introcs.*;
// TODO:
// Should negative chips (debt) be allowed???
// allow removal of players at beginning (i.e. undo adding players...)
// allow quitting in middle of game??
// Resolve accessibility after figuring out classes, methods.

public class SkunkApp { // main program
	private static final int MAX_PLAYERS = 8;
	private static final int OVERFLOW_SCORE = 50; // = 100, set lower to test games faster...
	private final static int TOTAL_CHIPS = 100; // = 400, set lower for test games faster...
	private static Scanner myObj = new Scanner(System.in);
	
	public static int getOVERFLOW_SCORE() {
        return OVERFLOW_SCORE;
    }
	public static int getMAX_PLAYERS() {
        return MAX_PLAYERS;
    }
	public static int getTOTAL_CHIPS() {
        return TOTAL_CHIPS;
    }

	public static void SkunkTournament() {
		Scanner exitInput = new Scanner(System.in);	
		while (true) {
			StdOut.println("New Game has been started...");
			StdOut.println("Resetting Individual Dice Totals and Kitty to 0");
			StdOut.println();
			SkunkGame.playGame(SkunkPlayerManagement.playersArray);
			
			StdOut.println("Post-Game Evaluation...");
			SkunkPlayerManagement.removePlayers();
			SkunkPlayerManagement.printPlayersSheet(SkunkPlayerManagement.playersArray);
			
			StdOut.println("Type 'end' to end the tournament, else it will continue");
			String tournamentContinueChoice = exitInput.nextLine();
			
			if (tournamentContinueChoice.toLowerCase().equals("end")) {
				StdOut.println("Understood. Tournament is shutting down...");
				StdOut.println();
				break;
			}
			else if (SkunkPlayerManagement.playersArray.length == 1) {
				StdOut.println("We have a grand champion!");
				break;
			}
		}
		exitInput.close();
	}
	
	public static void main(String[] args){
		
		StdOut.println("Tournament has began...");
		StdOut.println("Now registering players...");
		StdOut.println();
		SkunkPlayerManagement.playersArray = SkunkPlayerManagement.addPlayers();
		StdOut.println();
		// SkunkPlayerManagement.distributeChips();

		SkunkTournament();
		
		StdOut.println("Final Report...");
		SkunkPlayerManagement.printPlayersSheet(SkunkPlayerManagement.playersArray);

		StdOut.println("Tournament has ended...");
		myObj.close();
		
	}
}