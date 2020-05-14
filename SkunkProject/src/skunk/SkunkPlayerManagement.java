package skunk;
import java.util.Random;

import edu.princeton.cs.introcs.StdOut;

// PMD's localvariablecouldbefinal == NOT on for each.
public class SkunkPlayerManagement {	
	public static SkunkPlayer[] playersArray;
	private final static int TOTAL_CHIPS = 4; // = 400, set lower for test games faster...

	public static void distributeChips() {
		StdOut.println("Distribution of chips to players initiated...");
		final int chipDistributed = TOTAL_CHIPS / playersArray.length;
		for (SkunkPlayer player : playersArray) {
			player.addToPlayerChipsTotal(chipDistributed);
			StdOut.println(player.getName() + " has been given " + chipDistributed + " chips.");
		}
		StdOut.println("Distribution of chips to players complete.");
		StdOut.println();
	}
	
	public static void resetAllPlayersDice() {
		for (SkunkPlayer player : playersArray) {
			player.resetDice();
		}
	}
	
	public static void displayPlayersChipsAndDiceTotal(final SkunkPlayer[] playersArrayInput){
		StdOut.println("\tPlayer\tChips\tDice Total");
		for (SkunkPlayer player : playersArrayInput) {
			StdOut.println("\t"+player.getName()+"\t"+player.getPlayerChipsTotal()+"\t"+player.getPlayerDiceTotal());
		}
	}
	
	public static int selectNextPlayer(final SkunkPlayer[] playersArrayGame, final int currentPlayerIndex) {
		int internalCurrentPlayerIndex = currentPlayerIndex;
		final SkunkPlayer[] internalPlayersArrayGame = playersArrayGame;
		
		internalCurrentPlayerIndex += 1;
		internalCurrentPlayerIndex = UtilityMethods.resetIndexOfLoopsArray(internalCurrentPlayerIndex, internalPlayersArrayGame.length);
		return internalCurrentPlayerIndex;
	}
	
	public static int randomStartPlayer(final int lengthOfArray) {
		StdOut.println("Choosing random player to start...");
		final Random rand = new Random(); 
		return rand.nextInt(lengthOfArray); 
	}
}