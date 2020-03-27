// Keeps track of all rolls in a turn
//TODO: make proper getter, setter, and reset for rollingResults values...

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
	

}
