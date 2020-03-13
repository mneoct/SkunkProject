import java.util.Scanner;
import edu.princeton.cs.introcs.*;

public class PlayRound{
	private static int round_dice_total = 0;
	private static final int SINGLE_SKUNK_PENALTY = 2;
	private static final int DBL_SKUNK_PENALTY = 4;

	public static Scanner playersChoice = new Scanner(System.in);	

	// possible moves
	public static void selectMove(SkunkPlayer inputPlayer, SkunkPlayer[] players_array){
		StdOut.println(inputPlayer.get_name() + "'s turn...");
		
		while(true){
			int enteredOption = numericOptionSelection();
			
			if (enteredOption == 1)
				StdOut.println("Current Dice Total: " + get_round_dice_total());
			else if (enteredOption == 2)
				StdOut.println("Kitty: " + SkunkKitty.get_kitty());
			else if (enteredOption == 3)
				SkunkApp.printPlayersSheet();
			else if (enteredOption == 4)
				SkunkApp.displayDiceAll(players_array);
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
	private static void set_round_dice_total(int addDiceToRound) {
		round_dice_total += addDiceToRound;
	}
	public static void reset_round_dice_total() {
		round_dice_total = 0;
	}
	private static int get_round_dice_total() {
		return round_dice_total;
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
	private static void skunkEvent(int Penalty, SkunkPlayer inputPlayer) { 
		StdOut.println("Before...");
		StdOut.println("Kitty: " + SkunkKitty.get_kitty());
		SkunkApp.printPlayersSheet();

		inputPlayer.set_chips_total(-Penalty);  
		SkunkKitty.set_kitty(Penalty);
		
		StdOut.println("After...");
		StdOut.println("Kitty: " + SkunkKitty.get_kitty());
		SkunkApp.printPlayersSheet();
		StdOut.println("Ending Turn...");
	}
	private static void singleSkunk(SkunkPlayer inputPlayer) { 
		StdOut.println("Single Skunk Detected!");
		skunkEvent(SINGLE_SKUNK_PENALTY, inputPlayer);
	}
	private static void doubleSkunk(SkunkPlayer inputPlayer){
		StdOut.println("Double Skunk Detected!");
		StdOut.println(inputPlayer.get_name()+" has lost their dice total!");
		inputPlayer.reset_dice();
		skunkEvent(DBL_SKUNK_PENALTY, inputPlayer);
	}
	
	private static int[] rollingDice() {
		int[] returnDiceResults = new int[3];
		returnDiceResults[0] = (int) (Math.random() * 6 + 1);
		returnDiceResults[1] = (int) (Math.random() * 6 + 1);
		returnDiceResults[2] = returnDiceResults[0] + returnDiceResults[1];
		StdOut.println("Dice 1's value: " + returnDiceResults[0]);
		StdOut.println("Dice 2's value: " + returnDiceResults[1]);
		StdOut.println("Total: " + returnDiceResults[2]);
		StdOut.println();
		return returnDiceResults;
	}
	private static void endTurn(SkunkPlayer inputPlayer) {
		StdOut.println(inputPlayer.get_name()+" has chosen to end their turn...");
		
		StdOut.println("Before...");
		StdOut.println(inputPlayer.get_name() + ": " + inputPlayer.get_dice_total());
		
		inputPlayer.set_dice_total(round_dice_total); 
		
		StdOut.println("After...");
		StdOut.println(inputPlayer.get_name() + ": " + inputPlayer.get_dice_total());
		
		StdOut.println("Ending Turn...");
	}
	private static void rollEvaluation(SkunkPlayer player){
		StdOut.println("Current Dice in Round: " + round_dice_total);
		StdOut.println(player.get_name()+" has in their hand a dice value of: " + player.get_dice_total());
		int roundTotalIfQuitNow = player.get_dice_total() + round_dice_total;
		StdOut.println("If " + player.get_name()+ " ends their turn now, their dice value in hand will be: " + roundTotalIfQuitNow);
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
			set_round_dice_total(totalDiceResult);
	}
	
	public static void main(String[] args)	{
		StdOut.println("hello");
	}
}