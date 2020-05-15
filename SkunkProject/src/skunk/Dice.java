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
	public static final int NUM_TRIALS = 360;

	private int lastRoll;
	private Die die1;
	private Die die2;

	public Dice()
	{
		this.die1 = new Die();
		this.die2 = new Die();
		this.roll();
	}

//	public Dice(final Die die1, final Die die2) 
//	{
//		this.die1 = die1;
//		this.die2 = die2;
//	}

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

	public static int[] rollingDice() {
		final Dice diceRoll = new Dice();

		int[] returnDiceResults = new int[3];

		returnDiceResults[0] = diceRoll.getLastDie1();
		returnDiceResults[1] = diceRoll.getLastDie2();
		returnDiceResults[2] = diceRoll.getLastRoll();
		
		return returnDiceResults;
	}
}
