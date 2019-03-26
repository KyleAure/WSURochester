package edu.winona.cs.CS410;

public class Sum implements Summing {

	@Override
	public int sum(int[] input, PossiblyThrow thrower) {
		//Assume input array is empty
		int result = 0; 
		
		//If null pointer return Integer.MIN_VALUE
		if(input == null) {
			result = Integer.MIN_VALUE;
		} else if(input.length != 0) {
			for(int i = 0; i < input.length; i++) {
				try {
					thrower.check(input[i]); //Throws exception before overflow/underflow occures.
					result += input[i];
				//Returns Integer.MAX_VALUE if the sum is greater or equal to Integer.MAX_VALUE.
				} catch (OverflowException oe) {
					result = Integer.MAX_VALUE;
					break;
				//Returns Integer.MIN_VALUE if the sum is lesser or equal to Integer.MIN_VALUE.  
				} catch (UnderflowException ue) {
					result = Integer.MIN_VALUE;
				//For all error conditions, (i.e. null pointer exception), Integer.MIN_VALUE should be returned.
				} catch (Exception e) {
					result = Integer.MIN_VALUE;
					break;
				}
			}
		}
		//Return calculated result
		return result;
	}

	//Custom OverflowException
	class OverflowException extends Exception {
		private static final long serialVersionUID = 1L;
		public OverflowException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	//Custom UnderflowException
	class UnderflowException extends Exception {
		private static final long serialVersionUID = 1L;
		public UnderflowException(String errorMessage) {
			super(errorMessage);
		}
	}
}
