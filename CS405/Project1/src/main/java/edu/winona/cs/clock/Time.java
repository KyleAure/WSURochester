package edu.winona.cs.clock;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Default ClockListener that will increment time.
 */
public class Time {
	private static final Log LOG = new Log(Time.class.getName());
	private static int count = 0;
	public static int getTime() {return count;}
	public static void increment() { 
		count++; 
		LOG.log(LogLevel.INFO, "Time Updated: " + count);
	}
}