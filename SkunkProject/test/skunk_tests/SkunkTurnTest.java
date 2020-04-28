package skunk_tests;
import org.junit.Test;

import skunk.SkunkTurnDiceData;
import skunk.SkunkTurnMenuSelection;

public class SkunkTurnTest {
	@Test
	public void testResetRoundDice() {
		SkunkTurnDiceData.resetRoundDiceTotal();
		assert (SkunkTurnDiceData.getRoundDiceTotal() == 0);
	}
	
	@Test
	public void testPositiveRoundDice() {
		SkunkTurnDiceData.resetRoundDiceTotal();
		SkunkTurnDiceData.setRoundDiceTotal(15);
		assert (SkunkTurnDiceData.getRoundDiceTotal() == 15);
	}
	
	@Test
	public void testNegativeRoundDice() {
		SkunkTurnDiceData.resetRoundDiceTotal();
		SkunkTurnDiceData.setRoundDiceTotal(-5);
		assert (SkunkTurnDiceData.getRoundDiceTotal() == -5);
	}
	
	@Test
	// kind of an odd test in that we are testing manually.
	public void testSelectionChoice() {
		int[] choicePossible = {1,2,3,4,5,6,999};
		assert(testSelectionChoiceLoop(choicePossible));
	}
	
	public boolean testSelectionChoiceLoop(int[] arrayPossibleInt) {
		boolean isWorking = true;
		for (int x: arrayPossibleInt) {
			SkunkTurnMenuSelection.optionSelectionTextSkunk();
			int choice = SkunkTurnMenuSelection.optionSelectionChoose();
			if (choice != x) {
				isWorking = false;
			}
		}
		return isWorking;
	}
}
