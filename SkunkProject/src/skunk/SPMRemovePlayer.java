package skunk;
import edu.princeton.cs.introcs.StdOut;

public class SPMRemovePlayer {
	public static SkunkPlayer[] removePlayers(SkunkPlayer[] passedPlayersArray) {
		SkunkPlayer[] internalPassedPlayersArray = passedPlayersArray;
		StdOut.println("Now checking for eliminated players...");
		StdOut.println();
		
		outerloop:
		while (true) {
			innerloop:
			for (int i = 0; i < internalPassedPlayersArray.length + 1; i++) {
				if (i >= internalPassedPlayersArray.length) {
					StdOut.println("Done.");
					break outerloop;
				}
				if (internalPassedPlayersArray[i].getPlayerChipsTotal() <= 0) {
					StdOut.println("Removing " + internalPassedPlayersArray[i].getName());
					internalPassedPlayersArray = removePlayerFromArray(internalPassedPlayersArray, i);			
					break innerloop;
				}
			}
		}
		return internalPassedPlayersArray;
	}
	
	private static SkunkPlayer[] removePlayerFromArray(SkunkPlayer[] passedPlayersArray, int playerRemovedIndex){
	    int j = 0;
	    int playerCount = passedPlayersArray.length;
	    SkunkPlayer[] newSkunkArray = new SkunkPlayer[playerCount - 1]; 
	    for (int i = 0; i < playerCount; i++) {
			if (i != playerRemovedIndex) {
	    		newSkunkArray[j] = passedPlayersArray[i]; 
	    		j++;
	    	}
		}
	    return newSkunkArray; 
	}
}
