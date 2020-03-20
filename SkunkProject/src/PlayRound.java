import java.util.Scanner;
import edu.princeton.cs.introcs.*;

public class PlayRound{
	private static int roundDiceTotal = 0;
	private static final int SINGLE_SKUNK_PENALTY = 2; // move to SkunkApp?
	private static final int DBL_SKUNK_PENALTY = 4; // move to SkunkApp?

	private static Scanner playersChoice = new Scanner(System.in);	

	// possible moves
	public static void selectMove(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayRound){
		StdOut.println(inputPlayer.getName() + "'s turn...");
		
		while(true){
			int enteredOption = numericOptionSelection();
			
			if (enteredOption == 1)
				StdOut.println("Current Dice Total: " + getRoundDiceTotal());
			else if (enteredOption == 2)
				StdOut.println("Kitty: " + SkunkKitty.getKitty());
			else if (enteredOption == 3)
				SkunkPlayerManagement.printPlayersSheet();
			else if (enteredOption == 4)
				SkunkPlayerManagement.displayDiceAll();
			else if (enteredOption == 5){
				int[] diceResult = rollingDice();
				rollResult(inputPlayer, diceResult[0], diceResult[1], diceResult[2]);
				if (rollBooleanResult(diceResult[0], diceResult[1], diceResult[2]) == true)
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
	private static void skunkEvent(int penalty, SkunkPlayer inputPlayer) { 
		StdOut.println("Before...");
		StdOut.println("Kitty: " + SkunkKitty.getKitty());
		SkunkPlayerManagement.printPlayersSheet();

		inputPlayer.setPlayerChipsTotal(-penalty);  
		SkunkKitty.setKitty(penalty);
		
		StdOut.println("After...");
		StdOut.println("Kitty: " + SkunkKitty.getKitty());
		SkunkPlayerManagement.printPlayersSheet();
		StdOut.println("Ending Turn...");
	}
	private static void singleSkunk(SkunkPlayer inputPlayer) { 
		StdOut.println("Single Skunk Detected!");
		skunkEvent(SINGLE_SKUNK_PENALTY, inputPlayer);
	}
	private static void doubleSkunk(SkunkPlayer inputPlayer){
		StdOut.println("Double Skunk Detected!");
		StdOut.println(inputPlayer.getName()+" has lost their current dice total!");
		inputPlayer.resetDice();
		skunkEvent(DBL_SKUNK_PENALTY, inputPlayer);
	}
	
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
	private static boolean rollBooleanResult(int dice1result, int dice2result, int totalDiceResult) {
		if (dice1result == 1 || dice2result == 1) {
			return true;
		}
		else
			return false;
	}
	private static void rollResult(SkunkPlayer player, int dice1result, int dice2result, int totalDiceResult) {
		if (dice1result == 1 && dice2result == 1){
			doubleSkunk(player);
		}
		else if (dice1result == 1 || dice2result == 1) {
			singleSkunk(player);
		}
		else
			setRoundDiceTotal(totalDiceResult);
	}
}