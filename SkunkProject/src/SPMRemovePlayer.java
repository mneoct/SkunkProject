import edu.princeton.cs.introcs.StdOut;

public class SPMRemovePlayer {
	public static SkunkPlayer[] removePlayers(SkunkPlayer[] passedPlayersArray) {
		StdOut.println("Now checking for eliminated players...");
		StdOut.println();
		outerloop:
		while (true) {
			innerloop:
			for (int i = 0; i < passedPlayersArray.length + 1; i++) {
				if (i >= passedPlayersArray.length) {
					StdOut.println("Done.");
					break outerloop;
				}
				if (passedPlayersArray[i].getPlayerChipsTotal() <= 0) {
					StdOut.println("Removing " + passedPlayersArray[i].getName());
					passedPlayersArray = removePlayerFromArray(passedPlayersArray, i);			
					break innerloop;
				}
			}
		}
		StdOut.println("Confirmation");
		return passedPlayersArray;
	}
	
	private static SkunkPlayer[] removePlayerFromArray(SkunkPlayer[] passedPlayersArray, int playerRemovedIndex){
	    int j = 0;
	    int playerCount = passedPlayersArray.length;
	    SkunkPlayer[] newSkunkArray = new SkunkPlayer[playerCount - 1]; 
	    for (int i = 0; i < playerCount; i++)
	    	if (i != playerRemovedIndex) {
	    		newSkunkArray[j] = passedPlayersArray[i]; 
	    		j++;
	    	}
	    return newSkunkArray; 
	}
}
