// Keeps track of all rolls in a turn

public class SkunkTurnDiceRollsArray {
	private int dice1; 
	private int dice2;
	private int total_result;
	
	public SkunkTurnDiceRollsArray() {
		setDice1Result(0);
		setDice2Result(0);
		setDiceTotalResult(0);
	}
	
	static SkunkTurnDiceRollsArray[] roundRollResult = new SkunkTurnDiceRollsArray[1];
	static void resetRoundRollResult() {
		roundRollResult =  new SkunkTurnDiceRollsArray[1]; 
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
	
// takes dice 1, dice 2, and total dice roll result, and add to the array roundRollResult
	public static void addToRoundRollResult(int d1, int d2, int dt) {
		SkunkTurnDiceRollsArray newRolledResults = new SkunkTurnDiceRollsArray();
		newRolledResults.setDice1Result(d1);
		newRolledResults.setDice2Result(d2);
		newRolledResults.setDiceTotalResult(dt);	
		roundRollResult = addToRoundRollResultHelper(
				roundRollResult, 
				roundRollResult.length, 
				newRolledResults);
	}
	
	private static SkunkTurnDiceRollsArray[] addToRoundRollResultHelper(
	SkunkTurnDiceRollsArray[] arrayOfCurrentTurnDiceRolls, int currentSizeOfResultsArray, SkunkTurnDiceRollsArray newResult){
		int i;

	    if (arrayOfCurrentTurnDiceRolls[0] == null) {
	    	SkunkTurnDiceRollsArray[] roundRollResultInternal = new SkunkTurnDiceRollsArray[1];
	    	roundRollResultInternal[0] = newResult;
	    	return roundRollResultInternal;
	    }
	    else {
		    SkunkTurnDiceRollsArray[] roundRollResultInternal = new SkunkTurnDiceRollsArray[currentSizeOfResultsArray + 1]; 
		    for (i = 0; i < currentSizeOfResultsArray; i++) 
		    	roundRollResultInternal[i] = arrayOfCurrentTurnDiceRolls[i]; 
		    roundRollResultInternal[currentSizeOfResultsArray] = newResult; 
		    return roundRollResultInternal; 
	    }
	}
}
