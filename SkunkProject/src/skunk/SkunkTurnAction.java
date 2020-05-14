package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnAction {
	
// Tested manually in testPlayerTurn
	public static void playerTurn(final SkunkPlayer inputPlayer, final SkunkPlayer[] playersArrayRound){
		StdOut.println("");
		StdOut.println("<<<" + inputPlayer.getName() + "'s turn... >>>");
		final String name = inputPlayer.getName();
		UtilityMethods.printRandomQuotes(name);
		SkunkTurnDiceData.resetRoundDiceTotal();
		SkunkTurnDiceRollsArray.resetRoundRollResult();
		final int chipsBefore = inputPlayer.getPlayerChipsTotal();
		
		while(true){
			StdOut.println();
			SkunkTurnMenuSelection.optionSelectionTextSkunk();
			final int enteredOption = SkunkTurnMenuSelection.optionSelectionChoose();
			
			if (enteredOption == 1) {
				SkunkTurnChoice1Stats.displayResults(inputPlayer, playersArrayRound);
			} 
			
			else if (enteredOption == 2) {
				SkunkTurnChoice2Roll.completeDieRollEvent(inputPlayer);
				if (SkunkTurnChoice2Roll.isSkunkGet()){
					break;
				}
			}
			
			else if (enteredOption == 3) {
				SkunkTurnChoice3End.endTurn(inputPlayer);
				break;
			}
			
			else {
				StdOut.println("Invalid input.");
				StdOut.println("Please select a valid option...");
			}
		}
		StdOut.println();
		StdOut.println("End Turn confirmed...");
		
		endOfTurnEvaluation(inputPlayer, chipsBefore);
		
		StdOut.println("");
	}
	
// End of Player's Turn Evaluation.
	public static void endOfTurnEvaluation(final SkunkPlayer ParInputPlayer, final int chipsBeforeInput) {
		int i = 0;
		StdOut.println("\tPlayer: " + ParInputPlayer.getName());
		if (SkunkTurnDiceRollsArray.roundRollResult[0] != null) {
			for (SkunkTurnDiceRollsArray printResults : SkunkTurnDiceRollsArray.roundRollResult) {
				i++;
				StdOut.println("\tRoll " + i + ": [Dice 1: " + printResults.getDice1Result() 
				+ "], [Dice 2: " + printResults.getDice2Result() 
				+ "], [Dice Total: " + printResults.getDiceTotalResult() + "]");
			}
		}
		StdOut.println("\tPoints Earned: " + SkunkTurnDiceData.getRoundDiceTotal());
		final int chipsLost = chipsBeforeInput - ParInputPlayer.getPlayerChipsTotal();
		StdOut.println("\tChips Lost: " + chipsLost);
	}
}