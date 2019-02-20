package edu.winona.cs.queue;

import edu.winona.cs.pcb.ProcessControlBlock;

public interface Queue {
	
	/**
	 * Adds a job to the queue
	 */
	public void addJob(ProcessControlBlock pcb);
	
	/**
	 * Removes a job from the top of the queue
	 */
	public ProcessControlBlock removeJob();
	
	/**
	 * Utility to determine if queue is full
	 */
	public boolean isFull();
	
	/**
	 * Return queue count
	 */
	public int count();
	

}
