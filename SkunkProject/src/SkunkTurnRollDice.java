import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnRollDice {
// Option 5: Roll dice, add results to roundRollResult,
	// evaluate consequence (add to running total, or skunk penalty), then check if break due to skunk.
	
	private static boolean isSkunk;
	
	static void completeDieRollEvent(SkunkPlayer parInputPlayer){
		int[] diceResult = Dice.rollingDice();
		SkunkTurnDiceRollsArray.addToRoundRollResult(diceResult[0], diceResult[1], diceResult[2]);
		rollEvaluation(parInputPlayer, diceResult[0], diceResult[1], diceResult[2]);
		isSkunk = SkunkTurnPenaltyEvents.skunkCheckToBreak(diceResult[0], diceResult[1]);
	}
	
	public static boolean getIsSkunk() {
		return isSkunk;
	}

// Imposes result of dice: add to running total, or penalizes for skunk.
	private static void rollEvaluation(SkunkPlayer player, int dice1, int dice2, int diceTotal) {
		StdOut.println("\tPlayer: " + player.getName());
		StdOut.println("\tRolled: " + dice1 + " and " + dice2 + ", for a total of " + diceTotal);
		StdOut.println("");
		
		if (dice1 == 1 && dice2 == 1) {
			SkunkTurnPenaltyEvents.doubleSkunk(player);
		} else if (diceTotal == 3) {
			SkunkTurnPenaltyEvents.singleSkunkDeuce(player);
		} else if (dice1 == 1 || dice2 == 1) {
			SkunkTurnPenaltyEvents.singleSkunk(player);
		} else {
			SkunkTurnMain.setRoundDiceTotal(diceTotal);
			StdOut.println("\tPoints in Current Turn: " + SkunkTurnMain.getRoundDiceTotal());
			StdOut.println("");
			StdOut.println("\t" + player.getName() + " has " + player.getPlayerDiceTotal() + " points.");
			int personalTotalIfQuitNow = player.getPlayerDiceTotal() + SkunkTurnMain.getRoundDiceTotal();
			StdOut.println("\tIf " + player.getName() + " ends their turn now, they will have " + personalTotalIfQuitNow + " points.");
		}
	}
}
