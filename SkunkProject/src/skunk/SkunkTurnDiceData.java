package skunk;
import edu.princeton.cs.introcs.StdOut;

// tested in SkunkTurnDiceDataTest
public class SkunkTurnDiceData{
	private static int roundDiceTotal;

// roundDiceTotal: getter, setter, reset 
	public static void setRoundDiceTotal(final int addDiceToRound) {
		roundDiceTotal += addDiceToRound;
	}
	
	public static void resetRoundDiceTotal() {
		roundDiceTotal = 0;
	}
	
	public static int getRoundDiceTotal() {
		return roundDiceTotal;
	}
	
	public static void displayRoundDiceTotal() {
		StdOut.println("\tCurrent Dice Total: " + SkunkTurnDiceData.getRoundDiceTotal());
	}
}