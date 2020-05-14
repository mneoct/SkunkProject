package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnChoice2Roll {
	
// tested in SkunkTurnChoice2RollTest... 
// Option 2: Roll dice, add results to roundRollResult,
	// evaluate consequence (add to running total, or skunk penalty), then check if break due to skunk.
	
	private static boolean isSkunk;
	private static int skunkType;
	
	public static void completeDieRollEvent(final SkunkPlayer parInputPlayer){
		final int[] diceResult = Dice.rollingDice();
		SkunkTurnDiceRollsArray.addToRoundRollResult(diceResult[0], diceResult[1], diceResult[2]);
		rollEvaluation(parInputPlayer, diceResult[0], diceResult[1], diceResult[2]);
		isSkunk = SkunkTurnPenaltyEvents.skunkCheckToBreak(diceResult[0], diceResult[1]);
	}
	
	public static boolean isSkunkGet() {
		return isSkunk;
	}
	
	public static int skunkTypeGet() {
		return skunkType;
	}

// Imposes result of dice: add to running total, or penalizes for skunk.
	private static void rollEvaluation(final SkunkPlayer player, final int dice1, final int dice2, final int diceTotal) {
		rollEvaluationStartTextHelper(player,dice1,dice2,diceTotal);
		
		if (dice1 == 1 && dice2 == 1) {
			skunkType = 0;
			SkunkTurnPenaltyEvents.doubleSkunk(player);
		} else if (diceTotal == 3) {
			skunkType = 1;
			SkunkTurnPenaltyEvents.singleSkunkDeuce(player);
		} else if (dice1 == 1 || dice2 == 1) {
			skunkType = 2;
			SkunkTurnPenaltyEvents.singleSkunk(player);
		} else {
			skunkType = 999;
			SkunkTurnDiceData.setRoundDiceTotal(diceTotal);
			final int personalTotalIfQuitNow = player.getPlayerDiceTotal() + SkunkTurnDiceData.getRoundDiceTotal();
			rollEvaluationGoodRollTextHelper(player, personalTotalIfQuitNow);
		}
	}
	
	private static void rollEvaluationStartTextHelper (final SkunkPlayer player, 
			final int dice1, final int dice2, final int diceTotal){
		StdOut.println("\tPlayer: " + player.getName());
		StdOut.println("\tRolled: " + dice1 + " and " + dice2 + ", for a total of " + diceTotal);
		StdOut.println("");
	}
	
	private static void rollEvaluationGoodRollTextHelper(final SkunkPlayer player, final int ifStopDicePoints){
		StdOut.println("\tPoints in Current Turn: " + SkunkTurnDiceData.getRoundDiceTotal());
		StdOut.println("");
		StdOut.println("\t" + player.getName() + " has " + player.getPlayerDiceTotal() + " points.");
		StdOut.println("\tIf " + player.getName() + " ends their turn now, they will have " + ifStopDicePoints + " points.");
	}
}
