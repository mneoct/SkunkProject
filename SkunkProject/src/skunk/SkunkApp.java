package skunk;
/**
*
* The Main program for our Skunk.
* 
*/

public class SkunkApp 
{
	public SkunkUI skunkUI;
	public SkunkMain skunkMain;

	public SkunkApp()
	{
		skunkUI = new SkunkUI(skunkMain);
		skunkMain = new SkunkMain(skunkUI);
	}
	
	public void run()
	{
		skunkMain.run();
	}

	public static void main(final String[] args)
	{
		new SkunkApp().run();
	}

}
