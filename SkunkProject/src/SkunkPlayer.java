//TODO: None -- should be fine
public class SkunkPlayer {
	
	private String name;
	private int playerGameScoreDiceTotal = 0; 
	private int playerChipTotal = 50; 
	
	//constructor
	public SkunkPlayer(String inputName)	{
		SetName(inputName);
	}
	
	// getters and setters
	public String getName() {
        return this.name;
    }
	public void SetName(String inputName) {
        this.name = inputName;
    }
    
	public int getPlayerDiceTotal() {
        return this.playerGameScoreDiceTotal;
    }
    public void setPlayerDiceTotal(int num) {
        this.playerGameScoreDiceTotal = playerGameScoreDiceTotal + num;
    }
	public void resetDice() {
        this.playerGameScoreDiceTotal = 0;
	}
	
	public int getPlayerChipsTotal() {
        return this.playerChipTotal;
    }
    public void setPlayerChipsTotal(int num){
        this.playerChipTotal = playerChipTotal + num;
    }
}