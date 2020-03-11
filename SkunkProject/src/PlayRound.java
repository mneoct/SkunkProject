import java.util.Scanner;

public class PlayRound{
	private static int round_dice_total = 0;
	private static final int singleSkunkPenalty = 2;
	private static final int doubleSkunkPenalty = 4;

	public static Scanner playersChoice = new Scanner(System.in);	

	// possible moves
	public static void selectMove(SkunkPlayer inputPlayer, SkunkPlayer[] players_array){
		System.out.println(inputPlayer.get_name() + "'s turn...");
		
		while(true){
			int enteredOption = numericOptionSelection();
			
			if (enteredOption == 1)
				System.out.println("Current Dice Total: " + get_round_dice_total());
			else if (enteredOption == 2)
				System.out.println("Kitty: " + UniversalBoard.get_kitty());
			else if (enteredOption == 3)
				UniversalBoard.printPlayersSheet();
			else if (enteredOption == 4)
				UniversalBoard.displayDiceAll(players_array);
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
				System.out.println("Invalid input.");
				System.out.println("Please select a valid option...");
			}
			System.out.println();
		}
		System.out.println("End Turn confirmed...");
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
		System.out.println("select option: \n"
				+ "\t1. View current round's dice total\n"
				+ "\t2. View kitty\n"
				+ "\t3. View all players' chips\n"
				+ "\t4. View all players' Dice Total\n"
				+ "\t5. Roll dice\n"
				+ "\t6. End round");
		System.out.println();
		
		int optionSelected;
		String inputOption = playersChoice.nextLine();
		try {
			optionSelected = Integer.parseInt(inputOption);
		}
		catch (Exception e){
			optionSelected = 999;
		}
		
		System.out.println("Option Selected: " + optionSelected);
		System.out.println();
		return optionSelected;
	}
	
	// skunk events
	private static void skunkEvent(int Penalty, SkunkPlayer inputPlayer) { 
		System.out.println("Before...");
		System.out.println("Kitty: " + UniversalBoard.get_kitty());
		UniversalBoard.printPlayersSheet();

		inputPlayer.set_chips_total(-Penalty);  
		UniversalBoard.set_kitty(Penalty);
		
		System.out.println("After...");
		System.out.println("Kitty: " + UniversalBoard.get_kitty());
		UniversalBoard.printPlayersSheet();
		System.out.println("Ending Turn...");
	}
	private static void singleSkunk(SkunkPlayer inputPlayer) { 
		System.out.println("Single Skunk Detected!");
		skunkEvent(singleSkunkPenalty, inputPlayer);
	}
	private static void doubleSkunk(SkunkPlayer inputPlayer){
		System.out.println("Double Skunk Detected!");
		System.out.println(inputPlayer.get_name()+" has lost their dice total!");
		inputPlayer.reset_dice();
		skunkEvent(doubleSkunkPenalty, inputPlayer);
	}
	
	private static int[] rollingDice() {
		int[] returnDiceResults = new int[3];
		returnDiceResults[0] = (int) (Math.random() * 6 + 1);
		returnDiceResults[1] = (int) (Math.random() * 6 + 1);
		returnDiceResults[2] = returnDiceResults[0] + returnDiceResults[1];
		System.out.println("Dice 1's value: " + returnDiceResults[0]);
		System.out.println("Dice 2's value: " + returnDiceResults[1]);
		System.out.println("Total: " + returnDiceResults[2]);
		System.out.println();
		return returnDiceResults;
	}
	private static void endTurn(SkunkPlayer inputPlayer) {
		System.out.println(inputPlayer.get_name()+" has chosen to end their turn...");
		
		System.out.println("Before...");
		System.out.println(inputPlayer.get_name() + ": " + inputPlayer.get_dice_total());
		
		inputPlayer.set_dice_total(round_dice_total); 
		
		System.out.println("After...");
		System.out.println(inputPlayer.get_name() + ": " + inputPlayer.get_dice_total());
		
		System.out.println("Ending Turn...");
	}
	private static void rollEvaluation(SkunkPlayer player){
		System.out.println("Current Dice in Round: " + round_dice_total);
		System.out.println(player.get_name()+" has in their hand a dice value of: " + player.get_dice_total());
		int roundTotalIfQuitNow = player.get_dice_total() + round_dice_total;
		System.out.println("If " + player.get_name()+ " ends their turn now, their dice value in hand will be: " + roundTotalIfQuitNow);
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
		System.out.println("hello");
	}
}