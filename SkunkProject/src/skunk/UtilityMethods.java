package skunk;

import java.util.Random;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class UtilityMethods {
	// Given current index of an array being looped through, and the length of the array,
	// this resets the index to 0 if index >= length (i.e. attempts to avoid indexoutofbounds).
	public static int resetIndexOfLoopsArray(int arrayCurrentIndex, int arrayLength) {
		int arrayIndex = arrayCurrentIndex;
		if (arrayIndex >= arrayLength) {
			arrayIndex = 0;
		}
		return arrayIndex;
	}
	
	// https://mkyong.com/java/java-how-to-check-if-a-string-is-numeric/ -- 2nd code. modified to use ? :
	public static boolean isNumeric(final String str) {
        return (str == null || str.length() == 0) ? false : str.chars().allMatch(Character::isDigit);
    }
	
	public static void printRandomQuotes(final String playerRefName) {
		final String playerName = playerRefName;
		final String [] arr = {
        	"Fortune favors the bold. Are you bold, " +playerName + "?",
        	"I wonder what will " + playerName + " do?",
        	"Fortune be with you, " + playerName + "..."
        };
		
        final Random random = new Random();
        final int select = random.nextInt(arr.length); 
        final String quoteString = arr[select];
        StdOut.println(quoteString); 
	}
	
	public static String promptReadAndReturn(String question)
	{
		StdOut.print(question + " => ");
		String result = StdIn.readLine();
		return result;
	}
}
