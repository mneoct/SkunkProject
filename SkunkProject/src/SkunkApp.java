/**
*
* The Main program for our Skunk.
* @author Jon Doe
*/
public class SkunkApp 
{
	public SkunkUI skunkUI;
	public SkunkMain skunkMain;
	private int numberOfPlayers;
	public String[] playerNames;

	public final int getNumberOfPlayers() {
		return numberOfPlayers;
	}
	
	public final void setNumberOfPlayers(final int numOfPlayers) {
		this.numberOfPlayers = numOfPlayers;
	}
	
	public final void createArrayPlayerNames(final int size) {
		this.playerNames = new String[size];
	}
	
	public SkunkApp()
	{
		skunkUI = new SkunkUI(skunkMain);
		skunkMain = new SkunkMain(skunkUI);
	}

	/**
	 * Runs the app within an event loop
	 * 
	 * @return
	 */
	public void run()
	{
		skunkMain.run();
	}

	public static void main(String[] args)
	{
		new SkunkApp().run();
	}

}
