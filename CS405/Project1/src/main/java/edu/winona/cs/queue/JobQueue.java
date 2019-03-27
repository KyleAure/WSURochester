package edu.winona.cs.queue;

import java.util.LinkedList;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.ProcessControlBlock;

/**
 * Initial queue where the jobs are located.
 * @author Kyle Jon Aure
 */
public class JobQueue implements Queue {
	private static final Log LOG = new Log(JobQueue.class.getName());
	private LinkedList<ProcessControlBlock> jobQueue = new LinkedList<>();
	
	public JobQueue() {
		jobQueue = new LinkedList<>();
	}

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(this.isFull()) {
			//Do nothing
		} else {
			jobQueue.add(pcb);
			LOG.log(LogLevel.INFO, "Job Queue Position: " + jobQueue.size() + "\t" + pcb.toString());
		}
		
	}

	@Override
	public ProcessControlBlock removeJob() {
		if(this.count() > 1) {
			return jobQueue.pop();
		} else {
			LOG.log(LogLevel.WARNING, "Job Queue is Empty.  Invalid access.");
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}
	}

	@Override
	public boolean isFull() {
		//Job queue can never be full.  Always return false.
		return false;
	}

	@Override
	public int count() {
		return jobQueue.size();
	}

	@Override 
	public String toString() {
		String result = "Job Queue:\t";
		
		for(int i = 0; i < jobQueue.size(); i++) {
			result += jobQueue.get(i).getProcessID() + "\t";
		}
		
		return result;
	}
}
