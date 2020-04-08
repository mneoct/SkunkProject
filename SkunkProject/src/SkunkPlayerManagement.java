import edu.princeton.cs.introcs.StdOut;

//TODO: Probably can be broken down
public class SkunkPlayerManagement {	
	public static SkunkPlayer[] playersArray;
	private final static int TOTAL_CHIPS = 400; // = 400, set lower for test games faster...

	public static void distributeChips() {
		StdOut.println("Distribution of chips to players initiated...");
		int chipDistributed = TOTAL_CHIPS / playersArray.length;
		for (SkunkPlayer player : playersArray) {
			player.setPlayerChipsTotal(chipDistributed);
			StdOut.println(player.getName() + " has been given " + chipDistributed + " chips.");
		}
		StdOut.println("Distribution of chips to players complete.");
		StdOut.println();
	}
	
	public static void resetAllPlayersDice() {
		for (SkunkPlayer player : playersArray) {
			player.resetDice();
		}
	}
	
	public static void displayDiceAll(SkunkPlayer[] playersArrayInput) {
		for (SkunkPlayer player: playersArrayInput) {
			StdOut.println("\t"+player.getName() + ": " + player.getPlayerDiceTotal());
		}
	}
	
	public static void displayChipsAll(SkunkPlayer[] playersArrayInput){
		for (SkunkPlayer player : playersArrayInput) {
			StdOut.println("\t"+player.getName()+":"+player.getPlayerChipsTotal());
		}
	}
}