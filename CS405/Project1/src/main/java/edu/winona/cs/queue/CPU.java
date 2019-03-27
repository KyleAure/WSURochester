package edu.winona.cs.queue;

import java.util.List;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.ProcessControlBlock;

public class CPU implements Queue {
	private static final Log LOG = new Log(CPU.class.getName());
	private ProcessControlBlock job; 
	private boolean haveJob; 
	private boolean done; 
	
	public CPU() {
		// Initial State: No job in CPU.
		job = null;
		haveJob = false;
		done = false;
	}

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(!haveJob) {
			job = pcb;
			haveJob = true;
			done = false;
		} else {
			LOG.log(LogLevel.SEVERE, "CPU already has a job.");
		}
	}

	@Override
	public ProcessControlBlock removeJob() {
		if(done) {
			ProcessControlBlock temp = job;
			job = null;
			haveJob = false;
			return temp;
		} else {
			LOG.log(LogLevel.SEVERE, "CPU does not have a job that can be removed.");
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}
	}

	@Override
	public boolean isFull() {
		return haveJob;
	}

	@Override
	public int count() {
		return haveJob ? 1 : 0;
	}
	
	/**
	 * When executing round robin mark a job done by using this method.
	 */
	public void setJobDone() {
		done = true;
	}

	/**
	 * When executing SJF job stays in CPU until completed.
	 * Check if it is done by using this method.
	 * @return boolean - job status.
	 */
	public boolean isJobDone() {
		return done;
	}
	
	public void notifyTime() {
		if(!done) {
			//Get burst time
			List<Integer> temp = job.getCpuBursts();
			int index = job.getCpuIndex();
			int burst = temp.get(index);
			//Decrement it
			burst--;
			//Check if job should be done
			if(burst == 0) {
				done = true;
				job.setCpuIndex(index + 1);
			}
			//Put burst back
			temp.set(index, burst);
			job.setCpuBursts(temp);
		} else {
			LOG.log(LogLevel.SEVERE, "Job has finished but has not moved out of CPU.");
		}

	}
	
	@Override 
	public String toString() {
		String result = "CPU:\t";
		
		if(job != null) {
			result += job.getProcessID();
		}
		
		return result;
	}

}
