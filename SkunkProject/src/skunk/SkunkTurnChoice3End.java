package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnChoice3End {
// Option 3: Player ends turn, so they get their round's points.
	static void endTurn(SkunkPlayer inputPlayer) {
		StdOut.println("\t" + inputPlayer.getName() + " has chosen to end their turn...");
		int roundDicesTotal = SkunkTurnDiceData.getRoundDiceTotal();
		inputPlayer.addToPlayerDiceTotal(roundDicesTotal); 
	}
}
