package edu.winona.cs.CS410;

public class Sum implements Summing {

	@Override
	public int sum(int[] input, PossiblyThrow thrower) {
		//Assume input array is empty
		long result = 0;
		int intResult = 0;
		
		if(input == null) {
			intResult = Integer.MIN_VALUE;
		} else if(input.length == 0) {
			intResult = 0;
		} else if(input.length != 0){
			for(int i = 0; i < input.length; i++) {
				try {
					thrower.check(input[i]);
					result += input[i];
					System.out.println("input=" + input[i] + " result=" + result);
				} catch (Exception e) {
					intResult = Integer.MIN_VALUE;
					break;
				}
			}//end sum
			
			if(result >= Integer.MAX_VALUE) {
				intResult = Integer.MAX_VALUE;
			} else if(result <= Integer.MIN_VALUE) {
				intResult = Integer.MIN_VALUE;
			} else {
				intResult = (int) result;
			}
		}
		
		//Return calculated result
		return intResult;
	}
}
