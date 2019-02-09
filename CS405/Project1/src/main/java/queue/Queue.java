package queue;

import cpuscheduler.ProcessControlBlock;

public interface Queue {
	/**
	 * Adds a job to the queue
	 */
	public void addJob(ProcessControlBlock pcb);
	
	/**
	 * Removes a job from the top of the queue
	 * 
	 * @return pcb object
	 */
	public ProcessControlBlock removeJob();
	

}
