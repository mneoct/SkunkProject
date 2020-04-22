import org.junit.BeforeClass;
import org.junit.Test;

import skunk.SPMAddPlayer;
import skunk.SkunkKitty;
import skunk.SkunkPlayer;
import skunk.SkunkPlayerManagement;

public class SkunkPlayerManagementTest {
	@BeforeClass
	public static void executeFirst() {
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
	}
	
	@Test
	public void testDistributionOfChips() {
		// checks that chips are distributed, kind of.
		SkunkPlayerManagement.distributeChips();
		int chipsTotal = 0;
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			chipsTotal += player.getPlayerChipsTotal();
		}
		assert (chipsTotal <= 400);
	}
	
	@Test
	public void testResetDice() {
		// Checks that the dice total of players can be set, then reset
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			player.setPlayerDiceTotal(200);
			assert (player.getPlayerDiceTotal() == 200);
		}
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			player.resetDice();
			assert (player.getPlayerDiceTotal() == 0);
		}
	}
	
	@Test
	public void testSkunk() {
		SkunkKitty.setKitty(100);
		assert (SkunkKitty.getKitty() == 100);
		SkunkKitty.setKitty(-50);
		assert (SkunkKitty.getKitty() == 50);
		SkunkKitty.resetKitty();
		assert (SkunkKitty.getKitty() == 0);
	}
}
