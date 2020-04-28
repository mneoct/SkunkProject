package skunk_tests;

import static org.junit.Assert.assertSame;
import org.junit.Test;
import skunk.SkunkKitty;

public class SkunkKittyTest {
	@Test
	public void testKittyAdd() {
		SkunkKitty.resetKitty();
		SkunkKitty.setKitty(100);
		assertSame ("Failed to set positive values for kitty", 
				100, SkunkKitty.getKitty());
	}
	
	@Test
	public void testKittySubtract() {
		SkunkKitty.resetKitty();
		SkunkKitty.setKitty(-50);
		assertSame ("Failed to set negative values for kitty", 
				-50, SkunkKitty.getKitty());
	}
	
	@Test
	public void testResetKitty() {
		SkunkKitty.resetKitty();
		assertSame ("Failed to reset kitty", 
				0, SkunkKitty.getKitty());
	}
}
