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
	private boolean preemptive;
	
	public CPU() {
		// Initial State: No job in CPU.
		job = null;
		haveJob = false;
		done = true;
		preemptive = false;
	}

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(!haveJob) {
			job = pcb;
			haveJob = true;
			done = false;
			preemptive = false;
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
			LOG.log(LogLevel.SEVERE, "Job is not done.  Scheduler does not have the right.");
			throw new ArrayIndexOutOfBoundsException("Queue access restricted.");
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
		preemptive = true;
	}

	/**
	 * When executing SJF job stays in CPU until completed.
	 * Check if it is done by using this method.
	 * When using RR, scheduler can set job to done by using setJobDone();
	 * @return boolean - job status.
	 */
	public boolean isJobDone() {
		return done;
	}
	
	/**
	 * If the job was marked done preemptively use this method to check.
	 * @return boolean - preemptive status
	 */
	public boolean isPreemptive() {
		return preemptive;
	}
	
	public void notifyTime() {
		if(!done) {
			//Get burst time
			List<Integer> bursts = job.getCpuBursts();
			int burst = bursts.get(0);
			//Decrement it
			burst--;
			//Check if job should be done
			if(burst == 0) {
				done = true;
				preemptive = false;
				bursts.remove(0);
			} else {
				//Put burst back
				bursts.set(0, burst);
			}
			job.setCpuBursts(bursts);
		} else {
			LOG.log(LogLevel.SEVERE, "Job has finished but has not moved out of CPU.");
		}

	}
	
	@Override 
	public String toString() {
		String result = "\n \t CPU:\t";
		
		if(job != null) {
			result += job.getProcessID() + ":";
			result += job.getCpuBursts();
		}
		
		return result;
	}

}
