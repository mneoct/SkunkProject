package skunk;
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
}
