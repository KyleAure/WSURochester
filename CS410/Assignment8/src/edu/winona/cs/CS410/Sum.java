package edu.winona.cs.CS410;

public class Sum implements Summing {

	@Override
	public int sum(int[] input, PossiblyThrow thrower) {
		int result = 0; //Assume input array is empty
		
		if(input == null) {
			result = Integer.MIN_VALUE;
		} else if(input.length != 0) {
			for(int i = 0; i < input.length; i++) {
				try {
					thrower.check(input[i]);
					result += input[i];
				} catch (Exception e) {
					result = Integer.MIN_VALUE;
					break;
				}
			}
		}
		
		
		
		 /*   
		 * Returns Integer.MAX_VALUE if the sum is greater or equal to Integer.MAX_VALUE. 
		 * Returns Integer.MIN_VALUE if the sum is lesser or equal to Integer.MIN_VALUE.  
		 * Returns Integer. 
		 */
		
		
		//Return calculated result
		return result;
	}

	class OverflowException extends Exception {
		public OverflowException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	class UnderflowException extends Exception {
		public UnderflowException(String errorMessage) {
			super(errorMessage);
		}
	}
}
