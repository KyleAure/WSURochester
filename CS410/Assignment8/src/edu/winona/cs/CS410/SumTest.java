package edu.winona.cs.CS410;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class SumTest {
	Sum s = new Sum();
	PossiblyThrow thrower = new PossiblyThrow();
	
	//Expected Behavior
	@Test
	void expected() {
		int expected = 1345;
		int[] input = {expected - 5, + 5};
		int result = s.sum(input, thrower);
		assertEquals("Result did not match.", expected, result);
	}
	
	//Note:  For a zero length array, 0 should be returned.
	@Test
	void noArray() {
		int[] input = {};
		int result = s.sum(input, thrower);
		assertEquals("Result should be zero", 0, result);
	}

	//Returns Integer.MAX_VALUE if the sum is greater or equal to Integer.MAX_VALUE.
	@Test
	void atMax() {
		int[] input = {Integer.MAX_VALUE - 1, 1};
		int result = s.sum(input, thrower);
		assertEquals("Result should be Integer.MAX_VALUE", Integer.MAX_VALUE, result);
	}
	
	@Test 
	void aboveMax() {
		int[] input = {Integer.MAX_VALUE - 1, 1, 5};
		int result = s.sum(input, thrower);
		assertEquals("Result should be integer.MAX_VALUE", Integer.MAX_VALUE, result);
	}
	
	//Returns Integer.MIN_VALUE if the sum is lesser or equal to Integer.MIN_VALUE.
	@Test
	void atMin() {
		int[] input = {Integer.MIN_VALUE + 1, -1};
		int result = s.sum(input, thrower);
		assertEquals("Result should be Integer.MIN_VALUE", Integer.MIN_VALUE, result);
	}
	
	@Test
	void belowMin() {
		int[] input = {Integer.MIN_VALUE + 1, -1, -5};
		int result = s.sum(input, thrower);
		assertEquals("Result should be integer.MIN_VALUE", Integer.MIN_VALUE, result);
	}
	
	//Null pointer exception handling;
	void nullPtr() {
		int[] input = null;
		int result = s.sum(input, thrower);
		assertEquals("Result should be Integer.MIN_VALUE", Integer.MIN_VALUE, result);
	}

}

