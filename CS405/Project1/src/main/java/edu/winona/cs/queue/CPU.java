package edu.winona.cs.queue;

import java.util.List;

import edu.winona.cs.clock.ClockListener;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.main.App;
import edu.winona.cs.pcb.ProcessControlBlock;

public class CPU implements Queue, ClockListener {
	private static final Log LOG = new Log(CPU.class.getName());
	private static ProcessControlBlock job = null; //Currently running job
	private static int count = 0; //Count of jobs in cpu 0 or 1
	private static boolean done = false; //Job is free to be taken out of CPU
	private static int timeInCPU = 0; //Time running on CPU to be checked with Quantum

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(count == 0) {
			job = pcb;
			done = false;
			timeInCPU = 0;
			count++;
		} else {
			LOG.log(LogLevel.SEVERE, "CPU cannot add a job is removed.");
		}
	}

	@Override
	public ProcessControlBlock removeJob() {
		if(count == 1) {
			ProcessControlBlock temp = job;
			job = null;
			count--;
			return temp;
		} else {
			LOG.log(LogLevel.SEVERE, "CPU does not have a job that can be removed.");
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}
	}

	@Override
	public boolean isFull() {
		return count == 1;
	}

	@Override
	public int count() {
		return count;
	}

	@Override
	public void timeHasChanged() {
		if(count == 1) {
			//1. Decrement CPU burst for job in CPU
			List<Integer> tempList = job.getCpuBursts();
			Integer tempCount = tempList.get(job.getCpuIndex());
			tempCount--;
			timeInCPU++;
			tempList.set(job.getCpuIndex(), tempCount);
			
			switch(App.getMode()) {
				case SJF:
					//Job keeps CPU until done.
					if(tempCount == 0)
						done = true;
					break;
				case RR:
					//Job keeps CPU until timeQuantum is reached or job is done.
					if(timeInCPU == App.getQuantum() || tempCount == 0) 
						done = true;
					break;
			}//end switch
		}
	}
	
	public boolean isJobDone() {
		return done;
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
