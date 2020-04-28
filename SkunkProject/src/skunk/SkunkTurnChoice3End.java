package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnChoice3End {
// Option 3: Player ends turn, so they get their round's points.
	public static void endTurn(final SkunkPlayer inputPlayer) {
		StdOut.println("\t" + inputPlayer.getName() + " has chosen to end their turn...");
		final int roundDicesTotal = SkunkTurnDiceData.getRoundDiceTotal();
		inputPlayer.addToPlayerDiceTotal(roundDicesTotal); 
	}
}
