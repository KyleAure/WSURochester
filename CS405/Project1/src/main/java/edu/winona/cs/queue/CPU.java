package edu.winona.cs.queue;

import java.util.List;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.LogLevel;
import edu.winona.cs.main.ScheduleModes;
import edu.winona.cs.pcb.ProcessControlBlock;

public class CPU implements Queue {
	private static final Log LOG = new Log(CPU.class.getName());
	private ProcessControlBlock job; //Job in CPU
	private ScheduleModes mode; //Scheduling Mode
	private int quantum; //Quantum if set
	private int timeInCPU; //Time job has been in CPU
	private boolean done; 
	private boolean preemptive;
	
	public CPU(ScheduleModes mode, int quantum) {
		// Initial State: No job in CPU.
		job = null;
		this.mode = mode;
		this.quantum = quantum;
		timeInCPU = 0;
		done = true;
		preemptive = false;
	}

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(isEmpty()) {
			job = pcb;
			timeInCPU = 0;
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
			done = true;
			return temp;
		} else {
			LOG.log(LogLevel.SEVERE, "Job is not done.  Scheduler does not have the right.");
			throw new ArrayIndexOutOfBoundsException("Queue access restricted.");
		}
	}

	@Override
	public boolean isFull() {
		return !(job == null);
	}
	
	public boolean isEmpty() {
		return job == null;
	}

	@Override
	public int count() {
		return isFull() ? 1 : 0;
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
			} else if (mode==ScheduleModes.RR && timeInCPU % quantum == 0){
				done = true;
				preemptive = true;
				//Put burst back
				bursts.set(0, burst);
			} else {
				//Put burst back
				bursts.set(0, burst);
			}
			job.setCpuBursts(bursts);
		} else if (isEmpty()){
			LOG.log(LogLevel.INFO, "CPU was notified of time change, but does not have a job.");
		} else {
			LOG.log(LogLevel.SEVERE, "Job has finished but has not moved out of CPU.");
		}

	}
	
	@Override 
	public String toString() {
		return "\n \t CPU:\t\t" + job;
	}

}
