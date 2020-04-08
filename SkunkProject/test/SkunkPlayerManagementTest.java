import org.junit.Test;

public class SkunkPlayerManagementTest {
	@Test
	public void testDistributionOfChips() {
		// checks that chips are distributed, kind of.
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
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
		SkunkPlayerManagement.playersArray = SPMAddPlayer.addPlayers();
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			player.setPlayerDiceTotal(200);
			assert (player.getPlayerDiceTotal() == 200);
		}
		for (SkunkPlayer player : SkunkPlayerManagement.playersArray) {
			player.resetDice();
			assert (player.getPlayerDiceTotal() == 0);
		}
	}
}
