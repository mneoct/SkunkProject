import java.util.Scanner;
import edu.princeton.cs.introcs.*;

public class PlayRound{
	private static int roundDiceTotal = 0;
	private static final int SINGLE_SKUNK_PENALTY = 2;
	private static final int DBL_SKUNK_PENALTY = 4;

	private static Scanner playersChoice = new Scanner(System.in);	
	
// Keeps track of all rolls in a turn
	//TODO: make proper getter, setter, and reset for rollingResults values...
	public static class rollingResults {
		public int dice1; 
		public int dice2;
		public int total_result;
		
		public rollingResults() {
			dice1 = 0;
			dice2 = 0;
			total_result = 0;
		}
	}
	private static rollingResults[] roundRollResult = new rollingResults[1];
	private static void resetRoundRollResult() {
		roundRollResult =  new rollingResults[1]; 
	}

// roundDiceTotal: getter, setter, reset 
	private static void setRoundDiceTotal(int addDiceToRound) {
		roundDiceTotal += addDiceToRound;
	}
	static void resetRoundDiceTotal() {
		roundDiceTotal = 0;
	}
	private static int getRoundDiceTotal() {
		return roundDiceTotal;
	}
	
// A Player's Turn: Select option, see result, continue to play until player ends turn or gets skunk.
// TODO: break into smaller bits...
	public static void playerTurn(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayRound){
		StdOut.println(inputPlayer.getName() + "'s turn...");
		resetRoundRollResult();
		int chipsBefore = inputPlayer.getPlayerChipsTotal();
		
		while(true){
			int enteredOption = numericOptionSelection();
			
			if (enteredOption == 1)
				StdOut.println("Current Dice Total: " + getRoundDiceTotal());
			else if (enteredOption == 2)
				StdOut.println("Kitty: " + SkunkKitty.getKitty());
			else if (enteredOption == 3)
				SkunkPlayerManagement.printPlayersSheet(playersArrayRound);
			else if (enteredOption == 4)
				SkunkPlayerManagement.displayDiceAll(playersArrayRound);
			else if (enteredOption == 5){
				int[] diceResult = rollingDice();

				addToRoundRollResult(diceResult[0], diceResult[1], diceResult[2]);
				rollEvaluation(inputPlayer, playersArrayRound, diceResult[0], diceResult[1], diceResult[2]);
				if (skunkCheckToBreak(diceResult[0], diceResult[1]) == true)
						break;				
			}
			else if (enteredOption == 6) {
				endTurn(inputPlayer);
				break;
			}
			else{
				StdOut.println("Invalid input.");
				StdOut.println("Please select a valid option...");
			}
			StdOut.println();
		}
		StdOut.println();
		StdOut.println("End Turn confirmed...");
		
		StdOut.println("Player: " + inputPlayer.getName());
		int i = 0;
		if (roundRollResult[0] != null) {
			for (rollingResults printResults :  roundRollResult) {
				i++;
				StdOut.println("Roll " + i + ": [Dice 1: " + printResults.dice1 + "], [Dice 2: " + printResults.dice2 + "], [Dice Total: " + printResults.total_result + "]");
			}
		}
		StdOut.println("Points Earned: " + roundDiceTotal);
		int chipsLost = chipsBefore - inputPlayer.getPlayerChipsTotal();
		StdOut.println("Chips Lost: " + chipsLost);
	}

// Selection menu: takes numeric user input; returning 999 renders the result invalid.
	//TODO: Maybe break?
	private static int numericOptionSelection() {
		StdOut.println("select option: \n"
				+ "\t1. View current round's dice total\n"
				+ "\t2. View kitty\n"
				+ "\t3. View all players' chips\n"
				+ "\t4. View all players' Dice Total\n"
				+ "\t5. Roll dice\n"
				+ "\t6. End round");
		StdOut.println();
		
		int optionSelected;
		String inputOption = playersChoice.nextLine();
		try {
			optionSelected = Integer.parseInt(inputOption);
		}
		catch (Exception e){
			optionSelected = 999;
		}
		
		StdOut.println("Option Selected: " + optionSelected);
		StdOut.println();
		return optionSelected;
	}
	
// dice rolling
	// TODO: new class, or fold into dice?
	private static int[] rollingDice() {
		Dice diceRoll = new Dice();
		diceRoll.getLastDie1();
		diceRoll.getLastDie2();

		int[] returnDiceResults = new int[3];

		returnDiceResults[0] = diceRoll.getLastDie1();
		returnDiceResults[1] = diceRoll.getLastDie2();
		returnDiceResults[2] = diceRoll.getLastRoll();
		
		return returnDiceResults;
	}

// takes dice 1, dice 2, and total dice roll result, and add to the array roundRollResult
	private static void addToRoundRollResult(int d1, int d2, int dt) {
		rollingResults newRolledResults = new rollingResults();
		newRolledResults.dice1 = d1;
		newRolledResults.dice2 = d2;
		newRolledResults.total_result = dt;	
		roundRollResult = addToRoundRollResultHelper(roundRollResult, roundRollResult.length, newRolledResults);
	}
	private static rollingResults[] addToRoundRollResultHelper(
	rollingResults[] arrayOfCurrentTurnDiceRolls, int currentSizeOfResultsArray, rollingResults newResult){
		int i;

	    if (arrayOfCurrentTurnDiceRolls[0] == null) {
	    	rollingResults[] roundRollResultInternal = new rollingResults[1];
	    	roundRollResultInternal[0] = newResult;
	    	return roundRollResultInternal;
	    }
	    else {
		    rollingResults[] roundRollResultInternal = new rollingResults[currentSizeOfResultsArray + 1]; 
		    for (i = 0; i < currentSizeOfResultsArray; i++) 
		    	roundRollResultInternal[i] = arrayOfCurrentTurnDiceRolls[i]; 
		    roundRollResultInternal[currentSizeOfResultsArray] = newResult; 
		    return roundRollResultInternal; 
	    }
	}

	private static void rollEvaluation(SkunkPlayer player, SkunkPlayer[] playerArrayInput, int dice1, int dice2, int diceTotal){
		StdOut.println("Player: " + player.getName());
		StdOut.println("Rolled: " + dice1 + " and " + dice2 + ", for a total of " + diceTotal);
		if (dice1 == 1 && dice2 == 1)
			doubleSkunk(player, playerArrayInput);
		else if (dice1 == 1 || dice2 == 1)
			singleSkunk(player);
		else {
			setRoundDiceTotal(diceTotal);
			StdOut.println("Points in Current Turn: " + roundDiceTotal);
			StdOut.println(player.getName() + " has " + player.getPlayerDiceTotal() + " points.");
			int personalTotalIfQuitNow = player.getPlayerDiceTotal() + roundDiceTotal;
			StdOut.println("If " + player.getName()+ " ends their turn now, they will have " + personalTotalIfQuitNow + " points.");
		}
	}

// skunk events
	private static void skunkEvent(int penalty, SkunkPlayer inputPlayer) { 
		inputPlayer.setPlayerChipsTotal(-penalty);  
		SkunkKitty.setKitty(penalty);
	}
	private static void singleSkunk(SkunkPlayer inputPlayer) { 
		StdOut.println("Single Skunk Detected!");
		skunkEvent(SINGLE_SKUNK_PENALTY, inputPlayer);
		resetRoundDiceTotal();
	}
	private static void doubleSkunk(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayInput){
		StdOut.println("Double Skunk Detected!");
		StdOut.println(inputPlayer.getName() + " has lost their own dice points!");
		inputPlayer.resetDice();
		skunkEvent(DBL_SKUNK_PENALTY, inputPlayer);
		resetRoundDiceTotal();
	}
	private static boolean skunkCheckToBreak(int dice1result, int dice2result) {
		if (dice1result == 1 || dice2result == 1) {
			return true;
		}
		else
			return false;
	}

// Player ends turn, so they get their round's points.
	private static void endTurn(SkunkPlayer inputPlayer) {
		StdOut.println(inputPlayer.getName() + " has chosen to end their turn...");
		inputPlayer.setPlayerDiceTotal(roundDiceTotal); 
	}
	
	public static void main(String[] args){
		SkunkPlayer[] testPlayerArray = new SkunkPlayer[1];
		testPlayerArray[0] = new SkunkPlayer("Test Player");
		playerTurn(testPlayerArray[0], testPlayerArray);
	}
}