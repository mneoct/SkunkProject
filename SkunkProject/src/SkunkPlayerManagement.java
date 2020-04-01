import java.util.Scanner;

import edu.princeton.cs.introcs.StdOut;

public class SkunkPlayerManagement {	
	public static Scanner myObj = new Scanner(System.in);
	private static int MAX_PLAYERS = SkunkMain.getMaxPlayers();
	public static SkunkPlayer[] playersArray;
	
	// Alternative: ask how many players first so internalPlayersArray is initialized
	// with the right number of slots, so it doesn't kill/resurrect itself.
	public static SkunkPlayer[] addPlayers(){	// long, but kind of tricky to break..?
		SkunkPlayer[] internalPlayersArray = null;
		while (true) {
			StdOut.println("Adding New Player...");
			StdOut.println("Enter ... to continue without adding");
			StdOut.println("Enter the new player's name:");
			String userName = myObj.nextLine();
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
			}
			
			else {
				try { // playersArray will be null at first, so exception on 1st run.
					internalPlayersArray = addPlayerToArray(internalPlayersArray.length, 
							internalPlayersArray, new SkunkPlayer(userName));
				}
				catch (NullPointerException e) { // should change to catch specific exception 
					internalPlayersArray = new SkunkPlayer[1];
					internalPlayersArray[0] = new SkunkPlayer(userName);
				}
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
	
	private static SkunkPlayer[] addPlayerToArray(int currentPlayersCount,
				SkunkPlayer[] CurrentSkunkPlayerArray, SkunkPlayer newPlayerToBeAdded){
        int i; 
        SkunkPlayer newSkunkArray[] = new SkunkPlayer[currentPlayersCount + 1]; 
        for (i = 0; i < currentPlayersCount; i++) 
            newSkunkArray[i] = CurrentSkunkPlayerArray[i]; 
        newSkunkArray[currentPlayersCount] = newPlayerToBeAdded; 
        return newSkunkArray; 
    }
	
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