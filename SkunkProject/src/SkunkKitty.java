public class SkunkKitty {
	
	private static int kitty = 0;

	// Move to SkunkKitty.java
	public static int get_kitty(){ 
		return kitty; 
	} 
	public static void set_kitty(int value){
		kitty = kitty + value;
	}
	public static void reset_kitty(){
		kitty = 0;
	}
	public static void main(String[] args)	{
		System.out.println("hello");
	}
}
