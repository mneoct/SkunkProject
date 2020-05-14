package skunk_tests;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import edu.princeton.cs.introcs.StdOut;
import skunk.SkunkKitty;

public class SkunkKittyTest {
	@Test
	public void testKittyAdd() {
		StdOut.println("");
		StdOut.println("<<<Testing that kitty was given 100... (add correctly)>>>");
		SkunkKitty.resetKitty();
		SkunkKitty.setKitty(100);
		SkunkKitty.displayKitty();
		assertSame ("Failed to set positive values for kitty", 
				100, SkunkKitty.getKitty());
	}
	
	@Test
	public void testKittySubtract() {
		StdOut.println("");
		StdOut.println("<<<Testing that kitty was subtracted 50... (subtract correctly)>>>");
		SkunkKitty.resetKitty();
		SkunkKitty.setKitty(-50);
		SkunkKitty.displayKitty();
		assertSame ("Failed to set negative values for kitty", 
				-50, SkunkKitty.getKitty());
	}
	
	@Test
	public void testResetKitty() {
		StdOut.println("");
		StdOut.println("<<<Testing that kitty was reset correctly ...>>>");
		SkunkKitty.resetKitty();
		SkunkKitty.displayKitty();
		assertSame ("Failed to reset kitty", 0, SkunkKitty.getKitty());
	}
}
