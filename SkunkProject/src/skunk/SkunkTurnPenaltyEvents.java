package skunk;
import edu.princeton.cs.introcs.StdOut;

// inevitably tested in SkunkTurnChoice2RollTest
public class SkunkTurnPenaltyEvents {
	private static final int SINGLE_SKUNK_PENALTY = 1;
	private static final int SINGLE_SKUNK_DEUCE_PENALTY = 2;
	private static final int DBL_SKUNK_PENALTY = 4;
	
	private static void skunkEvent(final int penalty, final SkunkPlayer inputPlayer) { 
		inputPlayer.addToPlayerChipsTotal(-penalty);  
		SkunkKitty.setKitty(penalty);
		SkunkTurnDiceData.resetRoundDiceTotal();
	}
	
	public static void singleSkunk(final SkunkPlayer inputPlayer) { 
		StdOut.println("Single Skunk Detected!");
		skunkEvent(SINGLE_SKUNK_PENALTY, inputPlayer);
	}
	
	public static void singleSkunkDeuce(final SkunkPlayer inputPlayer) { 
		StdOut.println("Single Skunk with Deuce Detected!");
		skunkEvent(SINGLE_SKUNK_DEUCE_PENALTY, inputPlayer);
	}
	
	public static void doubleSkunk(final SkunkPlayer inputPlayer){
		StdOut.println("Double Skunk Detected!");
		StdOut.println(inputPlayer.getName() + " has lost their own dice points!");
		inputPlayer.resetDice();
		skunkEvent(DBL_SKUNK_PENALTY, inputPlayer);
	}
	
	public static boolean skunkCheckToBreak(final int dice1result, final int dice2result) {
		return dice1result == 1 || dice2result == 1 ? true : false;
	}

}
