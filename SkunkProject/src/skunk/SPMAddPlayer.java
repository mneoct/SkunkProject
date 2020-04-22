package skunk;
import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SPMAddPlayer {
	private static final int MAX_PLAYERS = 8;
	
	// Alternative: ask how many players first so internalPlayersArray is initialized
	// with the right number of slots, so it doesn't kill/resurrect itself.
	public static SkunkPlayer[] addPlayers(){	// long, but kind of tricky to break..?
		SkunkPlayer[] internalPlayersArray = null;
		while (true) {
			StdOut.println("Adding New Player...");
			StdOut.println("Enter ... to continue without adding");
			StdOut.print("Enter the new player's name:");
			String userName = StdIn.readLine();
			StdOut.println();
			
			if (userName.equals("...")) {
				if (internalPlayersArray == null || internalPlayersArray.length < 2) {
					StdOut.println("Insufficient players.");
					continue;
				}
				else {
					StdOut.println("Players have been registered.");
					break;
				}
			} else {
				internalPlayersArray = addPlayerToArrayMain(internalPlayersArray, userName);
			}
			
			StdOut.println("Current number of players: " + internalPlayersArray.length);
			for (SkunkPlayer player: internalPlayersArray) {
				StdOut.println(player.getName());
			}
			
			if (internalPlayersArray.length >= MAX_PLAYERS) {
				StdOut.println("Max Players (" + MAX_PLAYERS + ") has been reached.");
				break;
			}
			StdOut.println();
		}
		StdOut.println("Players Registration Complete.");
		StdOut.println("Proceeding to gameplay");
		return internalPlayersArray;
	}
	
	public static SkunkPlayer[] addPlayerToArrayMain(SkunkPlayer[] playersArray, String newName) {
		SkunkPlayer[] newArray = playersArray;
		if (newArray == null) {
			newArray = new SkunkPlayer[1];
			newArray[0] = new SkunkPlayer(newName);
		} else {
			newArray = addPlayerToArray(newArray.length, newArray, new SkunkPlayer(newName));
		}
		return newArray;
	}
	
	private static SkunkPlayer[] addPlayerToArray(int currentPlayersCount,
				SkunkPlayer[] CurrentSkunkPlayerArray, SkunkPlayer newPlayerToBeAdded){
        int i; 
        SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount + 1]; 
        for (i = 0; i < currentPlayersCount; i++) {
			newSkunkArray[i] = CurrentSkunkPlayerArray[i];
		} 
        newSkunkArray[currentPlayersCount] = newPlayerToBeAdded; 
        return newSkunkArray; 
    }
	
}
