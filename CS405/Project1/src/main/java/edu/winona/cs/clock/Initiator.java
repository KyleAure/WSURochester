package edu.winona.cs.clock;

import java.util.ArrayList;
import java.util.List;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.LogLevel;

/**
 * An initiator is someone who wants to make the clock tick.
 * @author kaure
 */
public class Initiator {
	private static final Log LOG = new Log(Initiator.class.getName());
	
	//Keeps a list of those who want to listen.
	private List<ClockListener> listeners = new ArrayList<>();
	
	//Adds someone to the listeners list
	public void addListener(ClockListener toAdd) {
		listeners.add(toAdd);
		LOG.log(LogLevel.INFO, "Added " + toAdd + " to the list of Clock Listeners.");
	}
	
	//Performs the clock tick and notifies the listeners
	public void tickClock() {
		Time.increment();
		
		for (ClockListener h : listeners) {
			h.timeHasChanged();
			LOG.log(LogLevel.INFO, "Listeners have been notified of time change.");
		}
	}
}
