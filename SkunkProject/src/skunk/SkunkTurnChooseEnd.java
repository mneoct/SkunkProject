package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnChooseEnd {
// Option 6: Player ends turn, so they get their round's points.
	static void endTurn(SkunkPlayer inputPlayer) {
		StdOut.println("\t" + inputPlayer.getName() + " has chosen to end their turn...");
		int roundDicesTotal = SkunkTurnMain.getRoundDiceTotal();
		inputPlayer.setPlayerDiceTotal(roundDicesTotal); 
	}
}
