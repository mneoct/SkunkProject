package skunk_tests;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

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
		assertTrue ("chips not distributed properly", 
				chipsTotal <= 400);
	}
	
	@Test
	public void testSetDice() {
		// Checks that the dice total of players can be set
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			player.setPlayerDiceTotal(200);
			assertSame ("error setting dice", 
					200, player.getPlayerDiceTotal());
		}
	}
	
	@Test
	public void testResetDice() {
		// Checks that the dice total of players can be reset
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			player.resetDice();
			assertSame ("reset dice does not make it zero", 
					0, player.getPlayerDiceTotal());
		}
	}	
	
	@Test
	public void testKittyAdd() {
		SkunkKitty.setKitty(100);
		assertSame ("Failed to set negative values for kitty", 
				100, SkunkKitty.getKitty());
	}
	
	@Test
	public void testKittySubtract() {
		SkunkKitty.setKitty(-50);
		assertSame ("Failed to set positive values for kitty", 
				50, SkunkKitty.getKitty());
	}
	
	@Test
	public void testResetKitty() {
		SkunkKitty.resetKitty();
		assertSame ("Failed to reset kitty", 
				0, SkunkKitty.getKitty());
	}
}
