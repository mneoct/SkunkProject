package skunk;
/**
 * Dice represents a single pair of rollable Die objects, randomly generating
 * sums of their two values
 * 
 * This is a Javadoc comment: add more to your finished class below
 * 
 * @author eric
 *
 */

public class Dice
{

	private int lastRoll;
	private Die die1;
	private Die die2;

	public Dice()
	{
		this.die1 = new Die();
		this.die2 = new Die();
		this.roll();
	}

	public Dice(Die die1, Die die2) 
	{
		this.die1 = die1;
		this.die2 = die2;
	}

	public int getLastDie1()
	{
		return this.die1.getLastRoll();
	}
	public int getLastDie2()
	{
		return this.die2.getLastRoll();
	}
	public int getLastRoll()
	{
		return this.lastRoll;
	}

	public final void roll()
	{
		die1.roll();
		die2.roll();
		this.lastRoll = die1.getLastRoll() + die2.getLastRoll();
	}

	@Override
	public String toString()
	{
		return "Dice with last roll: " + getLastRoll() + " => " + die1.getLastRoll() + " + " + die2.getLastRoll();
	}

	public static final int NUM_TRIALS = 360;
	
	public static int[] rollingDice() {
		Dice diceRoll = new Dice();

		int[] returnDiceResults = new int[3];

		returnDiceResults[0] = diceRoll.getLastDie1();
		returnDiceResults[1] = diceRoll.getLastDie2();
		returnDiceResults[2] = diceRoll.getLastRoll();
		
		return returnDiceResults;
	}
	
//	public static void main(String[] args)
//	{
//		// simulate repeated rolls of Dice, counting the many double skunks
//		
//		Dice dice = new Dice();
//		int doubleSkunkCount = 0;
//
//		for (int i = 0; i < NUM_TRIALS; i++)
//		{
//			dice.roll();
//			StdOut.println(dice);
//
//			if (dice.getLastRoll() == 2)
//				doubleSkunkCount++;
//		}
//
//		StdOut.println("Actual count: " + doubleSkunkCount);
//		StdOut.println("Expected count: " + (NUM_TRIALS / 36.0));
//	}
}
