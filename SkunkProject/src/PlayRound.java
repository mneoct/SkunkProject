import java.util.Scanner;
import edu.princeton.cs.introcs.*;

//TODO: MAJOR -- getters and setters ==> implement proper accessibility.
public class PlayRound{
	private static int roundDiceTotal = 0;
	private static final int SINGLE_SKUNK_PENALTY = 2;
	private static final int DBL_SKUNK_PENALTY = 4;

	private static Scanner playersChoice = new Scanner(System.in);	
	
// Keeps track of all rolls in a turn
//TODO: make proper getter, setter, and reset for rollingResults values...
	public static class RollingResults {
		private int dice1; 
		private int dice2;
		private int total_result;
		
		public RollingResults() {
			setDice1Result(0);
			setDice2Result(0);
			setDiceTotalResult(0);
		}
		
		public int getDice1Result() {
	        return this.dice1;
	    }
	    public void setDice1Result(int num) {
	        this.dice1 = num;
	    }
		public void resetDice1() {
	        this.dice1 = 0;
		}
		
		public int getDice2Result() {
	        return this.dice2;
	    }
	    public void setDice2Result(int num) {
	        this.dice2 = num;
	    }
		public void resetDice2() {
	        this.dice2 = 0;
		}
		
		public int getDiceTotalResult() {
	        return this.total_result;
	    }
	    public void setDiceTotalResult(int num) {
	        this.total_result = num;
	    }
		public void resetDiceTotal() {
	        this.total_result = 0;
		}
	}
	
	private static RollingResults[] roundRollResult = new RollingResults[1];
	private static void resetRoundRollResult() {
		roundRollResult =  new RollingResults[1]; 
	}

// roundDiceTotal: getter, setter, reset 
	private static void setRoundDiceTotal(int addDiceToRound) {
		roundDiceTotal += addDiceToRound;
	}
	public static void resetRoundDiceTotal() {
		roundDiceTotal = 0;
	}
	private static int getRoundDiceTotal() {
		return roundDiceTotal;
	}
	
// A Player's Turn: Select option, see result, continue to play until player ends turn or gets skunk.
// TODO: break into smaller bits... ?? 
	public static void playerTurn(SkunkPlayer inputPlayer, SkunkPlayer[] playersArrayRound){
		StdOut.println(inputPlayer.getName() + "'s turn...");
		resetRoundRollResult();
		int chipsBefore = inputPlayer.getPlayerChipsTotal();
		
		while(true){
			StdOut.println();
			int enteredOption = numericOptionSelection();
			
			if (enteredOption == 1)
				StdOut.println("Current Dice Total: " + getRoundDiceTotal());
			else if (enteredOption == 2)
				StdOut.println("Kitty: " + SkunkKitty.getKitty());
			else if (enteredOption == 3)
				SkunkPlayerManagement.printPlayersSheet(playersArrayRound);
			else if (enteredOption == 4)
				SkunkPlayerManagement.displayDiceAll(playersArrayRound);
			else if (enteredOption == 5) {
				if (completeDieRollEvent(inputPlayer, playersArrayRound) == true)
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
		}
		StdOut.println();
		StdOut.println("End Turn confirmed...");
		
		endOfTurnEvaluation(inputPlayer, chipsBefore);
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
	
// Option 5: Roll dice, add results to roundRollResult,
	// evaluate consequence (add to running total, or skunk penalty), then check if break due to skunk.
	private static boolean completeDieRollEvent(SkunkPlayer parInputPlayer, SkunkPlayer[] parPlayersArrayRound){
		int[] diceResult = rollingDice();
		addToRoundRollResult(diceResult[0], diceResult[1], diceResult[2]);
		rollEvaluation(parInputPlayer, parPlayersArrayRound, diceResult[0], diceResult[1], diceResult[2]);
		if (skunkCheckToBreak(diceResult[0], diceResult[1]) == true)
			return true;
		else
			return false;
	}

// dice rolling
	// TODO: new class, or fold into dice?
	private static int[] rollingDice() {
		Dice diceRoll = new Dice();

		int[] returnDiceResults = new int[3];

		returnDiceResults[0] = diceRoll.getLastDie1();
		returnDiceResults[1] = diceRoll.getLastDie2();
		returnDiceResults[2] = diceRoll.getLastRoll();
		
		return returnDiceResults;
	}

// takes dice 1, dice 2, and total dice roll result, and add to the array roundRollResult
	private static void addToRoundRollResult(int d1, int d2, int dt) {
		RollingResults newRolledResults = new RollingResults();
		newRolledResults.setDice1Result(d1);
		newRolledResults.setDice2Result(d2);
		newRolledResults.setDiceTotalResult(dt);	
		roundRollResult = addToRoundRollResultHelper(roundRollResult, roundRollResult.length, newRolledResults);
	}
	private static RollingResults[] addToRoundRollResultHelper(
	RollingResults[] arrayOfCurrentTurnDiceRolls, int currentSizeOfResultsArray, RollingResults newResult){
		int i;

	    if (arrayOfCurrentTurnDiceRolls[0] == null) {
	    	RollingResults[] roundRollResultInternal = new RollingResults[1];
	    	roundRollResultInternal[0] = newResult;
	    	return roundRollResultInternal;
	    }
	    else {
		    RollingResults[] roundRollResultInternal = new RollingResults[currentSizeOfResultsArray + 1]; 
		    for (i = 0; i < currentSizeOfResultsArray; i++) 
		    	roundRollResultInternal[i] = arrayOfCurrentTurnDiceRolls[i]; 
		    roundRollResultInternal[currentSizeOfResultsArray] = newResult; 
		    return roundRollResultInternal; 
	    }
	}

// Imposes result of dice: add to running total, or penalizes for skunk.
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
		if (dice1result == 1 || dice2result == 1)
			return true;
		else
			return false;
	}

// Option 6: Player ends turn, so they get their round's points.
	private static void endTurn(SkunkPlayer inputPlayer) {
		StdOut.println(inputPlayer.getName() + " has chosen to end their turn...");
		inputPlayer.setPlayerDiceTotal(roundDiceTotal); 
	}

// End of Player's Turn Evaluation.
	private static void endOfTurnEvaluation(SkunkPlayer ParInputPlayer, int chipsBeforeInput) {
		StdOut.println("Player: " + ParInputPlayer.getName());
		int i = 0;
		if (roundRollResult[0] != null) {
			for (RollingResults printResults :  roundRollResult) {
				i++;
				StdOut.println("Roll " + i + ": [Dice 1: " + printResults.getDice1Result() + "], [Dice 2: " + printResults.getDice2Result() + "], [Dice Total: " + printResults.getDiceTotalResult() + "]");
			}
		}
		StdOut.println("Points Earned: " + roundDiceTotal);
		int chipsLost = chipsBeforeInput - ParInputPlayer.getPlayerChipsTotal();
		StdOut.println("Chips Lost: " + chipsLost);
	}
	
	public static void main(String[] args){
		SkunkPlayer[] testPlayerArray = new SkunkPlayer[1];
		testPlayerArray[0] = new SkunkPlayer("Test Player");
		playerTurn(testPlayerArray[0], testPlayerArray);
	}
}