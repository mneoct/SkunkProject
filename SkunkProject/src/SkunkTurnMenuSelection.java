import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnMenuSelection {
	// Selection menu: takes numeric user input; returning 999 renders the result invalid.
	//TODO: Maybe break into smaller bits?
	
	private static String option1 = "View current round's dice total";
	private static String option2 = "View all players' dice points";
	private static String option3 = "View all players' chips";
	private static String option4 = "View kitty";
	private static String option5 = "Roll dice";
	private static String option6 = "End round";
	private static final String[] optionText = {option1, option2, option3, option4, option5, option6};
	
//	public static String[] getOptionText() {
//		return optionText;
//	}
	
	private static void optionSelectionText(String[] textArray) {
		StdOut.println("Select option:");
		for (int i = 0; i < textArray.length; i++) {
			int j = i + 1;
			StdOut.println("\t" + j + ": " + textArray[i] );
		}
		StdOut.println();
		StdOut.print("Option Selected: ");
	}
	
	public static void optionSelectionTextSkunk() {
		optionSelectionText(optionText);
	} 
	
	public static int optionSelectionChoose() {
		int optionSelected = getPlayerChoiceInput();
		StdOut.println();
		return optionSelected;
	}

	private static int getPlayerChoiceInput() {
		String inputOption = StdIn.readLine();
		return (UtilityMethods.isNumeric(inputOption)) ? Integer.parseInt(inputOption) : 999;
	}
	

}
