package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnChoice1Stats {	
	public static void displayResults(final SkunkPlayer[] playersArrayRound) {
		SkunkPlayerManagement.displayPlayersChipsAndDiceTotal(playersArrayRound);
		SkunkKitty.displayKitty();
	}
	
	public static void displayResults(final SkunkPlayer player, final SkunkPlayer[] playersArrayRound) {
		final String currentPlayerName = player.getName();

		SkunkPlayerManagement.displayPlayersChipsAndDiceTotal(playersArrayRound);
		StdOut.println("");
		StdOut.println("\tCurrent Player: " + currentPlayerName);
		SkunkTurnDiceData.displayRoundDiceTotal();
		SkunkKitty.displayKitty();
	}
}
