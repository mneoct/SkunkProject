package skunk;
// Keeps track of all rolls in a turn

public class SkunkTurnDiceRollsArray {
	private int dice1; 
	private int dice2;
	private int total_result;
	
	public static SkunkTurnDiceRollsArray[] roundRollResult = new SkunkTurnDiceRollsArray[1];

	public SkunkTurnDiceRollsArray() {
		setDice1Result(0);
		setDice2Result(0);
		setDiceTotalResult(0);
	}
	
	public static void resetRoundRollResult() {
		roundRollResult =  new SkunkTurnDiceRollsArray[1]; 
	}
	
	public int getDice1Result() {
        return this.dice1;
    }
	
    public final void setDice1Result(final int num) {
        this.dice1 = num;
    }

	public int getDice2Result() {
        return this.dice2;
    }
    public final void setDice2Result(final int num) {
        this.dice2 = num;
    }

	public int getDiceTotalResult() {
        return this.total_result;
    }
    public final void setDiceTotalResult(final int num) {
        this.total_result = num;
    }

// takes dice 1, dice 2, and total dice roll result, and add to the array roundRollResult
	public static void addToRoundRollResult(final int d1, final int d2, final int dt) {
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
			final SkunkTurnDiceRollsArray[] arrayOfCurrentTurnDiceRolls, 
			final int currentSizeOfResultsArray, final SkunkTurnDiceRollsArray newResult){
		int i;
		SkunkTurnDiceRollsArray[] roundRollResultInternal;
	    
		if (arrayOfCurrentTurnDiceRolls[0] == null) {
	    	roundRollResultInternal = new SkunkTurnDiceRollsArray[1];
	    	roundRollResultInternal[0] = newResult;
	    }
	    
		else {
		    roundRollResultInternal = new SkunkTurnDiceRollsArray[currentSizeOfResultsArray + 1]; 
		    for (i = 0; i < currentSizeOfResultsArray; i++) {
				roundRollResultInternal[i] = arrayOfCurrentTurnDiceRolls[i];
			} 
		    roundRollResultInternal[currentSizeOfResultsArray] = newResult; 
	    }
		
    	return roundRollResultInternal;
	}
}
