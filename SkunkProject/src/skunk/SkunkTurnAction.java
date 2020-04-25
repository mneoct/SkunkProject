package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnAction {
	
	// A Player's Turn: Select option, see result, continue to play until player ends turn or gets skunk.
	// TODO: break into smaller bits... ?? 
	public static void playerTurn(final SkunkPlayer inputPlayer, final SkunkPlayer[] playersArrayRound){
		StdOut.println(inputPlayer.getName() + "'s turn...");
		SkunkTurnDiceRollsArray.resetRoundRollResult();
		final int chipsBefore = inputPlayer.getPlayerChipsTotal();
		
		while(true){
			StdOut.println();
			SkunkTurnMenuSelection.optionSelectionTextSkunk();
			final int enteredOption = SkunkTurnMenuSelection.optionSelectionChoose();
			
			if (enteredOption == 1) {
				SkunkTurnMain.displayRoundDiceTotal();
			} else if (enteredOption == 2) {
				SkunkPlayerManagement.displayDiceAll(playersArrayRound);
			} else if (enteredOption == 3) {
				SkunkPlayerManagement.displayChipsAll(playersArrayRound);
			} else if (enteredOption == 4) {
				SkunkKitty.displayKitty();
			} else if (enteredOption == 5) {
				SkunkTurnRollDice.completeDieRollEvent(inputPlayer);
				if (SkunkTurnRollDice.getIsSkunk()){
					break;
				}
			}
			else if (enteredOption == 6) {
				SkunkTurnChooseEnd.endTurn(inputPlayer);
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
	}
	
// End of Player's Turn Evaluation.
	private static void endOfTurnEvaluation(final SkunkPlayer ParInputPlayer, final int chipsBeforeInput) {
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
		StdOut.println("\tPoints Earned: " + SkunkTurnMain.getRoundDiceTotal());
		final int chipsLost = chipsBeforeInput - ParInputPlayer.getPlayerChipsTotal();
		StdOut.println("\tChips Lost: " + chipsLost);
	}
}
