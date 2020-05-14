package skunk_tests;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SkunkKitty;
import skunk.SkunkPlayer;
import skunk.SkunkTurnAction;
import skunk.SkunkTurnChoice2Roll;
import skunk.SkunkTurnDiceRollsArray;

public class SkunkTurnChoice2RollTest {
	SkunkPlayer testPlayer = new SkunkPlayer("Test Player");
	
	@Test
	public void testDieRollSkunk() {
		StdOut.println("");
		StdOut.println("<<<Testing die rolling can be done and skunk events tested...>>>");
		StdOut.println("<<<Note: Die roll twice per loop, but skunk checks"
				+ " depends only on the 2nd result...>>>");

		for (int i = 0; i < 50; i++) {
			StdOut.println("---");
			StdOut.println("");
			SkunkTurnDiceRollsArray.resetRoundRollResult();
			int beforeChips = 0;
			for (int j = 0; j < 2; j++) {
				SkunkKitty.resetKitty();
				testPlayer.resetPlayerChips();
				testPlayer.addToPlayerChipsTotal(100);
				beforeChips = testPlayer.getPlayerChipsTotal();
				SkunkTurnChoice2Roll.completeDieRollEvent(testPlayer);
			}
			SkunkTurnAction.endOfTurnEvaluation(testPlayer, beforeChips);
			StdOut.println("");
			
			if (SkunkTurnChoice2Roll.skunkTypeGet() == 0) {
				assertTrue ("Kitty was not updated: " + SkunkKitty.getKitty(), SkunkKitty.getKitty() == 4);
				assertTrue ("Player's chips were not updated correctly: " + testPlayer.getPlayerChipsTotal(), testPlayer.getPlayerChipsTotal() == 96);
			}
			else if (SkunkTurnChoice2Roll.skunkTypeGet() == 1) {
				assertTrue ("Kitty was not updated: " + SkunkKitty.getKitty(), SkunkKitty.getKitty() == 2);
				assertTrue ("Player's chips were not updated correctly: " + testPlayer.getPlayerChipsTotal(), testPlayer.getPlayerChipsTotal() == 98);
			}
			else if (SkunkTurnChoice2Roll.skunkTypeGet() == 2) {
				assertTrue ("Kitty was not updated: " + SkunkKitty.getKitty(), SkunkKitty.getKitty() == 1);	
				assertTrue ("Player's chips were not updated correctly: " + testPlayer.getPlayerChipsTotal(), testPlayer.getPlayerChipsTotal() == 99);
			}
			else {
				assertTrue ("Kitty was mistakenly updated: " + SkunkKitty.getKitty(), SkunkKitty.getKitty() == 0);
				assertTrue ("Player's chips were mistakenly updated: " + testPlayer.getPlayerChipsTotal(), testPlayer.getPlayerChipsTotal() == 100);
			}

		}
	}
	
}
