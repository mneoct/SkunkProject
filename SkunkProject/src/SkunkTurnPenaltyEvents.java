import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnPenaltyEvents {
	private static final int SINGLE_SKUNK_PENALTY = 2;
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
	
	public static void doubleSkunk(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayInput){
		StdOut.println("Double Skunk Detected!");
		StdOut.println(inputPlayer.getName() + " has lost their own dice points!");
		inputPlayer.resetDice();
		skunkEvent(DBL_SKUNK_PENALTY, inputPlayer);
	}
	
	public static boolean skunkCheckToBreak(int dice1result, int dice2result) {
		if (dice1result == 1 || dice2result == 1)
			return true;
		else
			return false;
	}

}
