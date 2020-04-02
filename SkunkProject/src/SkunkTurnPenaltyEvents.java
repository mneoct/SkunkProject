import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnPenaltyEvents {
	private static final int SINGLE_SKUNK_PENALTY = 1;
	private static final int SINGLE_SKUNK_DEUCE_PENALTY = 1;
	private static final int DBL_SKUNK_PENALTY = 4;
	
	private static void skunkEvent(int penalty, SkunkPlayer inputPlayer) { 
		inputPlayer.setPlayerChipsTotal(-penalty);  
		SkunkKitty.setKitty(penalty);
		SkunkTurnMain.resetRoundDiceTotal();
	}
	
	public static void singleSkunk(SkunkPlayer inputPlayer) { 
		StdOut.println("Single Skunk Detected!");
		skunkEvent(SINGLE_SKUNK_PENALTY, inputPlayer);
	}
	
	public static void singleSkunkDeuce(SkunkPlayer inputPlayer) { 
		StdOut.println("Single Skunk with Deuce Detected!");
		skunkEvent(SINGLE_SKUNK_DEUCE_PENALTY, inputPlayer);
	}
	
	public static void doubleSkunk(SkunkPlayer inputPlayer){
		StdOut.println("Double Skunk Detected!");
		StdOut.println(inputPlayer.getName() + " has lost their own dice points!");
		inputPlayer.resetDice();
		skunkEvent(DBL_SKUNK_PENALTY, inputPlayer);
	}
	
	public static boolean skunkCheckToBreak(int dice1result, int dice2result) {
		return (dice1result == 1 || dice2result == 1) ? true : false;
	}

}
