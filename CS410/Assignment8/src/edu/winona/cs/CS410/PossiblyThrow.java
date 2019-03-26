package edu.winona.cs.CS410;

public class PossiblyThrow {
	long runningTotal = 0;
	
	public void check(int value) throws Exception {
	    runningTotal = runningTotal + value;
	    
	    if (runningTotal > Integer.MAX_VALUE) {
	         throw new RuntimeException("Overflow occured");
	    } else if (runningTotal < Integer.MIN_VALUE) {
	         throw new RuntimeException("Underflow occured");
	    }
	}
}
