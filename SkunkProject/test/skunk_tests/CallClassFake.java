package skunk_tests;

import org.junit.Test;

import skunk.SPMAddPlayer;
import skunk.SPMRemovePlayer;
import skunk.SkunkKitty;
import skunk.SkunkPlayerManagement;
import skunk.SkunkTurnAction;
import skunk.SkunkTurnChoice1Stats;
import skunk.SkunkTurnChoice2Roll;
import skunk.SkunkTurnChoice3End;
import skunk.SkunkTurnDiceData;
import skunk.SkunkTurnMenuSelection;
import skunk.SkunkTurnPenaltyEvents;
import skunk.TabulateWinnings;
import skunk.UtilityMethods;

public class CallClassFake {
	@Test
	public void TestFalseOverflowOrNextPlayer() {
		SkunkKitty skunkKittyX = new SkunkKitty();
		SkunkPlayerManagement SkunkPlayerManagementX = new SkunkPlayerManagement();
		SkunkTurnAction SkunkTurnActionX = new SkunkTurnAction();
		SkunkTurnChoice1Stats SkunkTurnChoice1StatsX = new SkunkTurnChoice1Stats();

		SkunkTurnChoice2Roll SkunkTurnChoice2RollX = new SkunkTurnChoice2Roll();
		SkunkTurnChoice3End SkunkTurnChoice3EndX = new SkunkTurnChoice3End();
		SkunkTurnDiceData SkunkTurnDiceDataX = new SkunkTurnDiceData();
		SkunkTurnMenuSelection SkunkTurnMenuSelectionX = new SkunkTurnMenuSelection();
		SkunkTurnPenaltyEvents SkunkTurnPenaltyEventsX = new SkunkTurnPenaltyEvents();
		SPMAddPlayer SPMAddPlayerX = new SPMAddPlayer();
		SPMRemovePlayer SPMRemovePlayerX = new SPMRemovePlayer();
		TabulateWinnings TabulateWinningsX = new TabulateWinnings();
		UtilityMethods UtilityMethodsX = new UtilityMethods();
	}
}
