
import edu.princeton.cs.introcs.StdOut;
import edu.princeton.cs.introcs.StdIn;

public class SkunkUI implements UI
{

	public SkunkMain skunkMain;

	public SkunkUI(SkunkMain skunkMain) 
	{
		this.skunkMain = skunkMain;
	}
	
	public void setDomain(SkunkMain skunkMain)
	{
		this.skunkMain = skunkMain;
	}

	public String promptReadAndReturn(String question)
	{
		StdOut.print(question + " => ");
		String result = StdIn.readLine();
		return result;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void print(String toPrint)
	{
		StdOut.print(toPrint);

	}

	public void println(String toPrint)
	{
		StdOut.println(toPrint);

	}

}
