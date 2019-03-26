package edu.winona.cs.CS410;

/**
 * This class checks value to be added to total. 
 * Throws an error if it will overflow or underflow
 * @author Kyle Jon Aure
 */
public class PossiblyThrow {
	long runningTotal = 0;
	Sum s = new Sum();
	
	public void check(int value) throws Exception {
	    runningTotal = runningTotal + value;
	    
	    if (runningTotal > Integer.MAX_VALUE) {
	         throw s.new OverflowException("Overflow occured");
	    } else if (runningTotal < Integer.MIN_VALUE) {
	         throw s.new UnderflowException("Underflow occured");
	    }
	}
}
