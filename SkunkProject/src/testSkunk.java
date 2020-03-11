import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
// ERRORS ABOUND< MAKE TESTSS>..
public class testSkunk {
	private boolean isFactor(int factor, int number) {
		Method m;
		try {
			m = Classifier6.class.getDeclaredMethod("isFactor", int.class);
			m.setAccessible(true);
			return (Boolean) m.invoke(new Classifier6(number), factor);
		} catch (Throwable t) {
			fail();
		}
		return false;
	}

	@Test
	public void is_factor() { // is_x_a_factor_of_y
		assertTrue(isFactor(1, 10));
		assertTrue(isFactor(5, 25));
		assertFalse(isFactor(6, 25));
	}
	
	@Test
	public void factors_for_1() {
		Set<Integer> expected = new HashSet<Integer>(Arrays.asList(1));
		Classifier6 c = new Classifier6(1);
		assertThat(c.getFactors(), is(expected));
	}

	// helper
	private Set<Integer> expectationSetWith(Integer... numbers) {
	    return new HashSet<Integer>(Arrays.asList(numbers));
	}
	
	@Test
	public void factors_for_6() {
		Set<Integer> expected = expectationSetWith(1, 2, 3, 6);
	    Classifier6 c = new Classifier6(6);
	    c.calculateFactors(); 
	    assertThat(c.getFactors(), is(expected));
	}

	@Test
	public void add_factors() {
		Set<Integer> expected = expectationSetWith(1, 2, 3, 6);
		Classifier6 c = new Classifier6(6);
		c.addFactor(2);
		c.addFactor(3);
		assertThat(c.getFactors(), is(expected));
	}
	
	// Boundary Conditions
	@Test public void factors_for_100() {
	    Classifier6 c = new Classifier6(100);
	    c.calculateFactors(); // 
	    assertThat(c.getFactors(), is(expectationSetWith(1, 100, 2, 50, 4, 25, 5, 20, 10)));
	}
	
	@Test(expected = InvalidNumberException.class)
	public void cannot_classify_negative_numbers() {
	    new Classifier6(-20);
	}
	
	@Test public void factors_for_max_int() {
	    Classifier6 c = new Classifier6(Integer.MAX_VALUE);
	    c.calculateFactors();
	    assertThat(c.getFactors(), is(expectationSetWith(1, 2147483647)));
	}
	
	@Test public void sum() {
	    Classifier6 c = new Classifier6(20);
	    c.calculateFactors();
	    int expected = 1 + 2 + 4 + 5 + 10 + 20;
	    assertThat(c.sumOfFactors(), is(expected));
	}
	
	@Test 
	public void perfection() { // new Classifier6 was classifierFor
    int[] perfectNumbers = new int[] {6, 28, 496, 8128, 33550336};
    for (int number : perfectNumbers)
        assertTrue(new Classifier6(number).isPerfect());
	} 
	
	@Test 
	public void is_deficient() { // new Classifier6 was classifierFor
    int[] perfectNumbers1 = new int[] {8};
    for (int number : perfectNumbers1)
        assertTrue(new Classifier6(number).isDeficient());
    int[] perfectNumbers2 = new int[] {28, 30};
    for (int number : perfectNumbers2)
        assertFalse(new Classifier6(number).isDeficient());
	} 
	
	@Test 
	public void abundant() { // new Classifier6 was classifierFor
	    int[] perfectNumbers1 = new int[] {30};
	    for (int number : perfectNumbers1)
	        assertTrue(new Classifier6(number).isAbundant());
	    int[] perfectNumbers2 = new int[] {28, 8};
	    for (int number : perfectNumbers2)
	        assertFalse(new Classifier6(number).isAbundant());
	} 
//	@Test // Listing 6...
// // suppose to be test negative numbers, but... doesn't? and this takes a long time.
//	public void test_a_bunch_of_numbers() { // new Classifier6 was classifierFor
//	    Set<Integer> expected = new HashSet<Integer>(Arrays.asList(PERFECT_NUMS));
//	    for (int i = 2; i < 33550340; i++) {
//	        if (expected.contains(i))
//	            assertTrue(new Classifier6(i).isPerfect());
//	        else
//	            assertFalse(new Classifier6(i).isPerfect());
//	    }
//	}
	
}