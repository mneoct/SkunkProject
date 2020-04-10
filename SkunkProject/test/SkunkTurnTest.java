import org.junit.BeforeClass;
import org.junit.Test;

public class SkunkTurnTest {
	@BeforeClass
	public static void executeFirst() {
		SkunkPlayer[] testPlayerArray = new SkunkPlayer[1];
		testPlayerArray[0] = new SkunkPlayer("Cat");
		SPMAddPlayer.addPlayerToArrayMain(testPlayerArray, "Dog");
	}
	
	@Test
	public void testDistributionOfChips() {
		// checks that chips are distributed, kind of.
		SkunkTurnMain.setRoundDiceTotal(15);
		assert (SkunkTurnMain.getRoundDiceTotal() == 15);
		
		SkunkTurnMain.setRoundDiceTotal(-5);
		assert (SkunkTurnMain.getRoundDiceTotal() == 10);
		
		SkunkTurnMain.resetRoundDiceTotal();
		assert (SkunkTurnMain.getRoundDiceTotal() == 0);
	}
	
	public boolean testNumberLoop(int[] arrayPossibleInt, int testedInt) {
		for (int x: arrayPossibleInt) {
			if (testedInt == x)
					return true;
		}
		return false;
	}
	
	@Test
	public void testSelectionChoice() {
		SkunkTurnMenuSelection.optionSelectionTextSkunk();
		int choice = SkunkTurnMenuSelection.optionSelectionChoose();
		int[] choicePossible = {1,2,3,4,5,6,999};
		assert(testNumberLoop(choicePossible, choice));
	}
}
