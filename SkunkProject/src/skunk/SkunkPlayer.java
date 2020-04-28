package skunk;
//TODO: None -- should be fine
public class SkunkPlayer {
	
	private String name;
	private int playerGameScoreDiceTotal; 
	private int playerChipTotal; 
	
	//constructor
	public SkunkPlayer(final String inputName)	{
		setName(inputName);
	}
	
	// getters and setters
	public final String getName() {
        return this.name;
    }
	public final void setName(final String inputName) {
        this.name = inputName;
    }
    
	public final int getPlayerDiceTotal() {
        return this.playerGameScoreDiceTotal;
    }
    public final void addToPlayerDiceTotal(final int num) {
        this.playerGameScoreDiceTotal = playerGameScoreDiceTotal + num;
    }
	public final void resetDice() {
        this.playerGameScoreDiceTotal = 0;
	}
	
	public final int getPlayerChipsTotal() {
        return this.playerChipTotal;
    }
    public final void addToPlayerChipsTotal(final int num){
        this.playerChipTotal = playerChipTotal + num;
    }
}