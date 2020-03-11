public class SkunkPlayer {
	private String name;
	private int dice_total = 0; 
	private int chips_total = 0; 
	
	//constructor
	public SkunkPlayer(String input_name)	{
		set_name(input_name);
	}
	// getters and setters
	public String get_name() {
        return this.name;
    }
	public void set_name(String inputName) {
        this.name = inputName;
    }
    
	public int get_dice_total() {
        return this.dice_total;
    }
    public void set_dice_total(int num) {
        this.dice_total = dice_total + num;
    }
	public void reset_dice() {
        this.dice_total = 0;
	}
	
	public int get_chips_total() {
        return this.chips_total;
    }
    public void set_chips_total(int num){
        this.chips_total = chips_total + num;
    }
}