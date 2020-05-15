package skunk_tests;
import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.Dice;
import skunk.Die;

public class TestDie {
	@Test
	public void testDieNoZero() {
		StdOut.println("");
		StdOut.println("<<<Ensure that die doesn't return 0.>>>");
		Die underTest = new Die();
		for (int i = 0; i < 10; i++) {
			underTest.roll();
			StdOut.println(underTest.toString());
			assert (underTest.getLastRoll() != 0);
		}
	}
	
	@Test
	public void testDieLTSeven() {
		StdOut.println("");
		StdOut.println("<<<Ensure that die result is at most 6.>>>");		
		Die underTest = new Die();
		for (int i = 0; i < 10; i++) {
			underTest.roll();
			StdOut.println(underTest.toString());
			assert (underTest.getLastRoll() <= 6);
		}
	}
	
	@Test
	public void rollingDiceTest() {
		StdOut.println("");
		StdOut.println("<<<Test rollingDice returns array of int: A + B = C rolled dice>>>");	
		int[] arrayReturned = Dice.rollingDice();
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < arrayReturned.length; j++) {
				int k = j+1;
				if (j > arrayReturned.length - 2) {
					StdOut.println("Total Dice: " + arrayReturned[j]);
				}
				else {
					StdOut.println("Dice " + k + ": " + arrayReturned[j]);
				}
			}
			StdOut.println("");
			Dice X = new Dice();
			StdOut.println(X.toString());
		}
		
		
	}
	
//	@Test
//	public void DiceX() {
//		StdOut.println("");
//		StdOut.println("<<<Test rollingDice returns array of int: A + B = C rolled dice>>>");	
//		Dice(d1, d2) 
//		int[] arrayReturned = Dice.rollingDice();
//		
//		for (int i = 0; i < 5; i++) {
//			for (int j = 0; j < arrayReturned.length; j++) {
//				int k = j+1;
//				if (j > arrayReturned.length - 2) {
//					StdOut.println("Total Dice: " + arrayReturned[j]);
//				}
//				else {
//					StdOut.println("Dice " + k + ": " + arrayReturned[j]);
//				}
//			}
//			StdOut.println("");
//		}
//	}
}