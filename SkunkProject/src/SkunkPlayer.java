public class SkunkPlayer {
	private String name;
	private int playerDiceTotal = 0; 
	private int playerChipTotal = 0; 
	
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
        return this.playerDiceTotal;
    }
    public void setPlayerDiceTotal(int num) {
        this.playerDiceTotal = playerDiceTotal + num;
    }
	public void resetDice() {
        this.playerDiceTotal = 0;
	}
	
	public int getPlayerChipsTotal() {
        return this.playerChipTotal;
    }
    public void setPlayerChipsTotal(int num){
        this.playerChipTotal = playerChipTotal + num;
    }
}