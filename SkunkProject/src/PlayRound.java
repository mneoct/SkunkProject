import java.util.Scanner;
import edu.princeton.cs.introcs.*;

public class PlayRound{
	private static int roundDiceTotal = 0;
	private static final int SINGLE_SKUNK_PENALTY = 2;
	private static final int DBL_SKUNK_PENALTY = 4;

	private static Scanner playersChoice = new Scanner(System.in);	
	
	public static class rollingResults{
		public int dice1; 
		public int dice2;
		public int total_result;
		
		public rollingResults() {
			dice1 = 0;
			dice2 = 0;
			total_result = 0;
		}
	}
	
	public static rollingResults[] roundRollResult = new rollingResults[1];
	
	public static void resetRoundRollResult() {
		roundRollResult =  new rollingResults[1]; 
	}
	
	private static rollingResults[] addToRoundRollResult(rollingResults[] roundRollResultInput,
			int sizeOfroundRollResult, rollingResults newResult){
		int i;
	    if (roundRollResultInput[0] == null) {
	    	rollingResults[] roundRollResultInternal = new rollingResults[1];
	    	roundRollResultInternal[0] = newResult;
	    	return roundRollResultInternal;
	    }
	    else {
		    rollingResults[] roundRollResultInternal = new rollingResults[sizeOfroundRollResult + 1]; 
		    for (i = 0; i < sizeOfroundRollResult; i++) 
		    	roundRollResultInternal[i] = roundRollResultInput[i]; 
		    roundRollResultInternal[sizeOfroundRollResult] = newResult; 
		    return roundRollResultInternal; 
	    }
	}
	
	private static void rollingResultsX(int d1, int d2, int dt) {
		rollingResults newRolledResults = new rollingResults();
		newRolledResults.dice1 = d1;
		newRolledResults.dice2 = d2;
		newRolledResults.total_result = dt;	
		roundRollResult = addToRoundRollResult(roundRollResult, roundRollResult.length, newRolledResults);
	}
	
	// possible moves
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
				SkunkPlayerManagement.displayDiceAll();
			else if (enteredOption == 5){
				int[] diceResult = rollingDice();
				rollResult(inputPlayer, diceResult[0], diceResult[1], diceResult[2], playersArrayRound);
				rollingResultsX(diceResult[0], diceResult[1], diceResult[2]);
				if (skunkCheck(diceResult[0], diceResult[1]) == true)
						break;				
				rollEvaluation(inputPlayer);
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
		StdOut.println("End Turn confirmed...");
		
		StdOut.println("Player: " + inputPlayer.getName());
		int i = 0;
		for (rollingResults printResults :  roundRollResult) {
			i++;
			StdOut.println("Round " + i + ": [Dice 1: " + printResults.dice1 + "], [Dice 2: " + printResults.dice2 + "], [Dice Total: " + printResults.total_result + "]");
		}	
		StdOut.println("Points Earned: " + roundDiceTotal);
		int chipsLost = chipsBefore - inputPlayer.getPlayerChipsTotal();
		StdOut.println("Chips Lost: " + chipsLost);
		StdOut.println();
		
	}
	
	private static void setRoundDiceTotal(int addDiceToRound) {
		roundDiceTotal += addDiceToRound;
	}
	static void resetRoundDiceTotal() {
		roundDiceTotal = 0;
	}
	private static int getRoundDiceTotal() {
		return roundDiceTotal;
	}
	
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
	
	// skunk events
	private static void skunkEvent(int penalty, SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayInput) { 
		StdOut.println("Before...");
		StdOut.println("Kitty: " + SkunkKitty.getKitty());
		SkunkPlayerManagement.printPlayersSheet(playersArrayInput);

		inputPlayer.setPlayerChipsTotal(-penalty);  
		SkunkKitty.setKitty(penalty);
		
		StdOut.println("After...");
		StdOut.println("Kitty: " + SkunkKitty.getKitty());
		SkunkPlayerManagement.printPlayersSheet(playersArrayInput);
		StdOut.println("Ending Turn...");
	}
	private static void singleSkunk(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayInput) { 
		StdOut.println("Single Skunk Detected!");
		skunkEvent(SINGLE_SKUNK_PENALTY, inputPlayer, playersArrayInput);
		resetRoundDiceTotal();
	}
	private static void doubleSkunk(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayInput){
		StdOut.println("Double Skunk Detected!");
		StdOut.println(inputPlayer.getName()+" has lost their current dice total!");
		inputPlayer.resetDice();
		skunkEvent(DBL_SKUNK_PENALTY, inputPlayer, playersArrayInput);
		resetRoundDiceTotal();
	}
	
	// dice result stuff -- new class, or fold into dice?
	private static int[] rollingDice() {
		Dice diceRoll = new Dice();
		diceRoll.getLastDie1();
		diceRoll.getLastDie2();

		int[] returnDiceResults = new int[3];

		returnDiceResults[0] = diceRoll.getLastDie1();
		returnDiceResults[1] = diceRoll.getLastDie2();
		returnDiceResults[2] = diceRoll.getLastRoll();
		
		StdOut.println("Dice 1's value: " + returnDiceResults[0]);
		StdOut.println("Dice 2's value: " + returnDiceResults[1]);
		StdOut.println("Total: " + returnDiceResults[2]);
		StdOut.println();
		return returnDiceResults;
	}
	private static void endTurn(SkunkPlayer inputPlayer) {
		StdOut.println(inputPlayer.getName()+" has chosen to end their turn...");
		
		StdOut.println("Before...");
		StdOut.println(inputPlayer.getName() + ": " + inputPlayer.getPlayerDiceTotal());
		
		inputPlayer.setPlayerDiceTotal(roundDiceTotal); 
		
		StdOut.println("After...");
		StdOut.println(inputPlayer.getName() + ": " + inputPlayer.getPlayerDiceTotal());
		
		StdOut.println("Ending Turn...");
	}
	private static void rollEvaluation(SkunkPlayer player){
		StdOut.println("Current Dice in Round: " + roundDiceTotal);
		StdOut.println(player.getName()+" has in their hand a dice value of: " + player.getPlayerDiceTotal());
		int roundTotalIfQuitNow = player.getPlayerDiceTotal() + roundDiceTotal;
		StdOut.println("If " + player.getName()+ " ends their turn now, their dice value in hand will be: " + roundTotalIfQuitNow);
	}
	private static boolean skunkCheck(int dice1result, int dice2result) {
		if (dice1result == 1 || dice2result == 1) {
			return true;
		}
		else
			return false;
	}
	private static void rollResult(SkunkPlayer player, int dice1result, int dice2result, int totalDiceResult, SkunkPlayer[] playersArrayInput) {
		if (dice1result == 1 && dice2result == 1){
			doubleSkunk(player, playersArrayInput);
		}
		else if (dice1result == 1 || dice2result == 1) {
			singleSkunk(player, playersArrayInput);
		}
		else
			setRoundDiceTotal(totalDiceResult);
	}

	public static void main(String[] args){
		SkunkPlayer[] testPlayerArray = new SkunkPlayer[1];
		testPlayerArray[0] = new SkunkPlayer("Test Player");
		playerTurn(testPlayerArray[0], testPlayerArray);
	}
}