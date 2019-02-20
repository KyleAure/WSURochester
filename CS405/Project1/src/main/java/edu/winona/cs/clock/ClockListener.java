package edu.winona.cs.clock;

/**
 * An interface to to implemented by everyone who wants 
 * to be notified of a clock tick.
 * @author kaure
 */
public interface ClockListener{
	void timeHasChanged();
}
