import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnAction {
	

	// A Player's Turn: Select option, see result, continue to play until player ends turn or gets skunk.
	// TODO: break into smaller bits... ?? 
	public static void playerTurn(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayRound){
		StdOut.println(inputPlayer.getName() + "'s turn...");
		SkunkTurnDiceRollsArray.resetRoundRollResult();
		int chipsBefore = inputPlayer.getPlayerChipsTotal();
		
		while(true){
			StdOut.println();
			int enteredOption = SkunkTurnMenuSelection.numericOptionSelection();
			
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
				endTurn(inputPlayer);
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
	

	// Option 6: Player ends turn, so they get their round's points.
		private static void endTurn(SkunkPlayer inputPlayer) {
			StdOut.println("\t" + inputPlayer.getName() + " has chosen to end their turn...");
			inputPlayer.setPlayerDiceTotal(SkunkTurnMain.getRoundDiceTotal()); 
		}

	// End of Player's Turn Evaluation.
		private static void endOfTurnEvaluation(SkunkPlayer ParInputPlayer, int chipsBeforeInput) {
			StdOut.println("\tPlayer: " + ParInputPlayer.getName());
			int i = 0;
			if (SkunkTurnDiceRollsArray.roundRollResult[0] != null) {
				for (SkunkTurnDiceRollsArray printResults : SkunkTurnDiceRollsArray.roundRollResult) {
					i++;
					StdOut.println("\tRoll " + i + ": [Dice 1: " + printResults.getDice1Result() 
					+ "], [Dice 2: " + printResults.getDice2Result() 
					+ "], [Dice Total: " + printResults.getDiceTotalResult() + "]");
				}
			}
			StdOut.println("\tPoints Earned: " + SkunkTurnMain.getRoundDiceTotal());
			int chipsLost = chipsBeforeInput - ParInputPlayer.getPlayerChipsTotal();
			StdOut.println("\tChips Lost: " + chipsLost);
		}
}
