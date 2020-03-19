import java.util.Scanner;

import edu.princeton.cs.introcs.StdOut;

public class SkunkPlayerManagement {
	public static Scanner myObj = new Scanner(System.in);
	private static int MAX_PLAYERS = SkunkApp.getMAX_PLAYERS();
	public static SkunkPlayer[] addPlayers(){	// long, but kind of tricky to break..?
		SkunkPlayer[] playersArray = null;
		while (true) {
			StdOut.println("Adding New Player...");
			StdOut.println("Enter ... to continue without adding");
			StdOut.println("Enter the new player's name:");
			String userName = myObj.nextLine();
			StdOut.println();
			
			if (userName.equals("...")) {
				if (playersArray == null || playersArray.length < 2) {
					StdOut.println("Insufficient players.");
					continue;
				}
				else {
					StdOut.println("Players have been registered.");
					break;
				}
			}
			else {
				try { // players_array will be null at first, so exception on 1st run.
					SkunkPlayer newPlayer = new SkunkPlayer(userName);
					playersArray = addPlayerToArray(playersArray.length, playersArray, newPlayer);
				}
				catch (NullPointerException e) {
					playersArray = new SkunkPlayer[1];
					playersArray[0] = new SkunkPlayer(userName);
				}
			}
			
			try {
				StdOut.println("Current number of players: " + playersArray.length);
				for (SkunkPlayer player:playersArray) {
					StdOut.println(player.getName());
				}
			}
			catch (NullPointerException e) {
				StdOut.println("Current number of players: " + playersArray.length);
				for (SkunkPlayer player:playersArray) {
					StdOut.println(player);
				}
			}
			if (playersArray.length >= MAX_PLAYERS) {
				StdOut.println("Max Players (" + MAX_PLAYERS + ") has been reached.");
				break;
			}
			StdOut.println();
		}
		StdOut.println("Players Registration Complete.");
		StdOut.println("Proceeding to gameplay");
		return playersArray;
	}
	private static SkunkPlayer[] addPlayerToArray(int currentPlayersCount,
				SkunkPlayer[] CurrentSkunkPlayerArray, SkunkPlayer newPlayerToBeAdded){
        int i; 
        SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount + 1]; 
        for (i = 0; i < currentPlayersCount; i++) 
            newSkunkArray[i] = CurrentSkunkPlayerArray[i]; 
        newSkunkArray[currentPlayersCount] = newPlayerToBeAdded; 
        return newSkunkArray; 
    }
	public static SkunkPlayer[] removePlayers(SkunkPlayer[] arrayOfPlayers) {
		StdOut.println("Now checking for eliminated players...");
		StdOut.println();
		outerloop:
		while (true) {
			innerloop:
			for (int i = 0; i < arrayOfPlayers.length + 1; i++) {
				if (i >= arrayOfPlayers.length) {
					StdOut.println("Done.");
					break outerloop;
				}
				if (arrayOfPlayers[i].getPlayerChipsTotal() <= 0) {
					StdOut.println("Removing " + arrayOfPlayers[i].getName());
					arrayOfPlayers = removePlayerFromArray(arrayOfPlayers.length, arrayOfPlayers, i);			
					StdOut.println();
					break innerloop;
				}
			}
		}
		StdOut.println("Confirmation");
		return arrayOfPlayers;
	}
	private static SkunkPlayer[] removePlayerFromArray(int currentPlayersCount,
			SkunkPlayer[] CurrentSkunkPlayerArray, int playerRemovedIndex){
	    int i; 
	    int j = 0;
	    SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount - 1]; 
	    for (i = 0; i < currentPlayersCount; i++)
	    	if (i != playerRemovedIndex) {
	    		newSkunkArray[j] = CurrentSkunkPlayerArray[i]; 
	    		j++;
	    	}
	    return newSkunkArray; 
	}
}
