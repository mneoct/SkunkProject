import edu.princeton.cs.introcs.StdOut;

//TODO: Probably can be broken down
public class SkunkPlayerManagement {	
	public static SkunkPlayer[] playersArray;
	
	public static SkunkPlayer[] removePlayers() {
		StdOut.println("Now checking for eliminated players...");
		StdOut.println();
		outerloop:
		while (true) {
			innerloop:
			for (int i = 0; i < playersArray.length + 1; i++) {
				if (i >= playersArray.length) {
					StdOut.println("Done.");
					StdOut.println();
					break outerloop;
				}
				if (playersArray[i].getPlayerChipsTotal() <= 0) {
					StdOut.println("Removing " + playersArray[i].getName());
					playersArray = removePlayerFromArray(playersArray.length, i);			
					StdOut.println();
					break innerloop;
				}
			}
		}
		StdOut.println("Confirmation");
		return playersArray;
	}
	private static SkunkPlayer[] removePlayerFromArray(int currentPlayersCount, int playerRemovedIndex){
	    int i; 
	    int j = 0;
	    SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount - 1]; 
	    for (i = 0; i < currentPlayersCount; i++)
	    	if (i != playerRemovedIndex) {
	    		newSkunkArray[j] = playersArray[i]; 
	    		j++;
	    	}
	    return newSkunkArray; 
	}
	
	
	public static void distributeChips() {
		StdOut.println("Distribution of chips to players initiated...");
		int chipDistributed = SkunkMain.getTotalChips() / playersArray.length;
		for (SkunkPlayer player : playersArray) {
			player.setPlayerChipsTotal(chipDistributed);
			StdOut.println(player.getName() + " has been given " + chipDistributed + " chips.");
		}
		StdOut.println("Distribution of chips to players complete.");
		StdOut.println();
	}
	public static void resetPlayersDice() {
		for (SkunkPlayer player : playersArray)
			player.resetDice();
	}
	
	public static void displayDiceAll(SkunkPlayer[] playersArrayInput) {
		for (SkunkPlayer player: playersArrayInput)
			StdOut.println("\t"+player.getName() + ": " + player.getPlayerDiceTotal());
	}
	
	public static void displayChipsAll(SkunkPlayer[] playersArrayInput){
		for (SkunkPlayer player : playersArrayInput)
			StdOut.println(player.getName()+":"+player.getPlayerChipsTotal());
		StdOut.println();
	}
}