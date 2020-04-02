import java.util.Scanner;

import edu.princeton.cs.introcs.StdOut;

public class SkunkTurnMenuSelection {
	// Selection menu: takes numeric user input; returning 999 renders the result invalid.
	//TODO: Maybe break into smaller bits?
	private static Scanner playersChoice = new Scanner(System.in);
	
	private static String option1 = "View current round's dice total";
	private static String option2 = "View all players' dice points";
	private static String option3 = "View all players' chips";
	private static String option4 = "View kitty";
	private static String option5 = "Roll dice";
	private static String option6 = "End round";
	private static String[] optionText = {option1, option2, option3, option4, option5, option6};
	
	public static int numericOptionSelection() {
		StdOut.println("Select option:");
		for (int i = 0; i < optionText.length; i++) {
			int j = i + 1;
			StdOut.println("\t" + j + ": " + optionText[i] );
		}
		StdOut.println();
		StdOut.print("Option Selected: ");
		int optionSelected = getPlayerChoiceInput();
		StdOut.println();
		return optionSelected;
	}

	private static int getPlayerChoiceInput() {
		String inputOption = playersChoice.nextLine();
		return (isNumeric(inputOption)) ? Integer.parseInt(inputOption) : 999;
	}
	
	// https://mkyong.com/java/java-how-to-check-if-a-string-is-numeric/ -- 2nd code. modified to use ? :
	public static boolean isNumeric(final String str) {
        return (str == null || str.length() == 0) ? false : str.chars().allMatch(Character::isDigit);
    }
}
