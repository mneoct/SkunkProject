package skunk;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnMenuSelection {
	// Selection menu: takes numeric user input; returning 999 renders the result invalid.
	//TODO: Maybe break into smaller bits?
	
	private static String option1 = "View Scoreboard of Game";
	private static String option2 = "Roll dice";
	private static String option3 = "End round";
	private static final String[] optionText = {option1, option2, option3};
	
	public static void optionSelectionTextSkunk() {
		optionSelectionText(optionText);
	} 
	
	private static void optionSelectionText(final String[] textArray) {
		StdOut.println("Select option:");
		for (int i = 0; i < textArray.length; i++) {
			final int j = i + 1;
			StdOut.println("\t" + j + ": " + textArray[i]);
		}
		StdOut.println();
		StdOut.print("Option Selected: ");
	}
		
	public static int optionSelectionChoose() {
		final int optionSelected = getPlayerChoiceInput();
		StdOut.println();
		return optionSelected;
	}

	private static int getPlayerChoiceInput() {
		final String inputOption = StdIn.readLine();
		return UtilityMethods.isNumeric(inputOption) ? Integer.parseInt(inputOption) : 999;
	}
	

}
