package skunk_tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
	SkunkGameTest.class, 
	SkunkKittyTest.class, 
	SkunkPlayerManagementTest.class,
	SkunkPlayerTest.class,
	SkunkTourneyEndTests.class,
	SkunkTurnChoice2RollTest.class,
	SkunkTurnDiceDataTest.class,
	TabulateWinningsTest.class,
	TestAddRemovePlayer.class,
	TestDie.class,
	TestPlayerTurn.class,
	CallClassFake.class
	})
public class TestSuite {

}