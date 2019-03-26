package edu.winona.cs.CS410;

public interface Summing {
	
	/** 
	 * Sum returns the sum of the integers in the input array.             
	 * The method must call the thrower.check(value) method for each integer in the input array.   
	 * Returns Integer.MAX_VALUE if the sum is greater or equal to Integer.MAX_VALUE. 
	 * Returns Integer.MIN_VALUE if the sum is lesser or equal to Integer.MIN_VALUE.  
	 * This method should not exit with an error being thrown.  For all error conditions, (i.e. 
	 * null pointer exception), Integer.MIN_VALUE should be returned.  
	 * Note:  For a zero length array, 0 should be returned.   
	 * Returns Integer. 
	 */
	public int sum (int[] input, PossiblyThrow thrower);

}
