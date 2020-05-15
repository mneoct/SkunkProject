package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnChoice1Stats {	
	public static void displayResults(final SkunkPlayer[] playersArrayRound) {
		SkunkPlayerManagement.displayPlayersChipsAndDiceTotal(playersArrayRound);
		StdOut.println("");
	}
	
	public static void displayResults(final SkunkPlayer player, final SkunkPlayer[] playersArrayRound) {
		final String currentPlayerName = player.getName();
		StdOut.println("\tCurrent Player: " + currentPlayerName);
		SkunkTurnDiceData.displayRoundDiceTotal();
		SkunkKitty.displayKitty();
		StdOut.println("");
		
		displayResults(playersArrayRound);
	}
}
