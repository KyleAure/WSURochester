package edu.winona.cs.main;

import java.util.ArrayList;
import java.util.List;

import edu.winona.cs.clock.ClockListener;
import edu.winona.cs.clock.Time;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.LogLevel;
import edu.winona.cs.log.Output;
import edu.winona.cs.pcb.ProcessControlBlock;
import edu.winona.cs.queue.CPU;
import edu.winona.cs.queue.DiskQueue;
import edu.winona.cs.queue.IOWaitingQueue;
import edu.winona.cs.queue.JobQueue;
import edu.winona.cs.queue.ReadyQueue;

public class Scheduler implements ClockListener {
	private static final Log LOG = new Log(Scheduler.class.getName());
	private static final Output OUTPUT = new Output("OUTPUT");
	private List<ProcessControlBlock> completedJobs;
	private CPU cpu;
	private DiskQueue diskQueue;
	private IOWaitingQueue ioQueue;
	private JobQueue jobQueue;
	private ReadyQueue readyQueue;
	private Boolean contextSwitch;
	
	public Scheduler(ScheduleModes mode, int quantum, List<ProcessControlBlock> jobPool) {
		cpu = new CPU(mode, quantum);
		diskQueue = new DiskQueue();
		ioQueue = new IOWaitingQueue();
		jobQueue = new JobQueue();
		readyQueue = new ReadyQueue(mode);
		completedJobs = new ArrayList<>();
		contextSwitch = false;
		
		//Load jobs into JobQueue
		for(ProcessControlBlock job : jobPool) {
			jobQueue.addJob(job);
		}
	}
	
	public boolean isContextSwitch() {
		return contextSwitch;
	}

	@Override
	public void timeHasChanged() {
		LOG.log(LogLevel.INFO, "Scheduler heard time change.");
		contextSwitch = cpu.isJobDone();
		String status1, status2;
		
		//STEP 1: Notify Disk, do IO, and move from Disk to IOQueue
		ArrayList<ProcessControlBlock> fromDiskQueue = diskQueue.notifyTime();
		if(fromDiskQueue != null && !fromDiskQueue.isEmpty()) {
			for(ProcessControlBlock job : fromDiskQueue) {
				ioQueue.addJob(job);
				LOG.log(LogLevel.INFO, "Scheduler moved "+ job.getProcessID() +" to IOWaiting Queue.");
			}
		}
		
		//STEP 2: Load ready queue ONLY IF there is a context switch
		if(contextSwitch) {
			contextSwitch = true;
			ProcessControlBlock fromCPU = null;
			LOG.log(LogLevel.INFO, "Context Switch");
			
			//Remove job from CPU
			if(cpu.isFull()) {
				fromCPU = cpu.removeJob();
				LOG.log(LogLevel.INFO, "Scheduler removed " + fromCPU.getProcessID() + " job from CPU.");
			}
			
			//STEP 2.1: First take any process from the CPU (RR)
			if(fromCPU != null) {
				if(cpu.isPreemptive()) {
					readyQueue.addJob(fromCPU);
					LOG.log(LogLevel.INFO, "Scheduler put "+ fromCPU.getProcessID() +" in ReadyQueue");
				} else {
					if(fromCPU.getCpuBursts().isEmpty()) {
						completedJobs.add(fromCPU);
						LOG.log(LogLevel.INFO, "Scheduler put "+ fromCPU.getProcessID() +" in Completed Jobs");
					}else{
						diskQueue.addJob(fromCPU);
						LOG.log(LogLevel.INFO, "Scheduler put "+ fromCPU.getProcessID() +" in DiskQueue");
					}	
				}
			}
			
			
			//STEP 2.2: Second take first process from IOQueue
			while(!readyQueue.isFull() && ioQueue.count() > 0) {
				ProcessControlBlock fromIOQueue = ioQueue.removeJob();
				readyQueue.addJob(fromIOQueue);
				LOG.log(LogLevel.INFO, "Scheduler put "+ fromIOQueue.getProcessID() +" into ReadyQueue");
			}
			//STEP 2.3: Third take first process in JobQueue
			while(!readyQueue.isFull() && jobQueue.count() > 0) {
				ProcessControlBlock fromJobQueue = jobQueue.removeJob();
				readyQueue.addJob(fromJobQueue);
				LOG.log(LogLevel.INFO, "Scheduler put "+ fromJobQueue.getProcessID() +" into ReadyQueue");
			}
		}
		
		//STEP 3: Choose which job to load into CPU
		if(contextSwitch && readyQueue.count() >= 1) {
			//Ready queue knows which job to return
			ProcessControlBlock fromReadyQueue = readyQueue.removeJob();
			cpu.addJob(fromReadyQueue);
			LOG.log(LogLevel.INFO, "Scheduler took "+ fromReadyQueue.getProcessID() +" and put it in CPU.");
		}
		
		//STEP 4: Print queue status
		if(contextSwitch) {
			OUTPUT.log(LogLevel.INFO, printState());
		}

		//STEP 3: Notify CPU (decrement bursts)
		status1 = cpu.toString();
		cpu.notifyTime();
		status2 = cpu.toString();
		LOG.log(LogLevel.INFO, "\nCPU time slice run:\n" 
				+ "\nBefore: "+ status1 
				+ "\nAfter: " + status2);

	}
	
	/**
	 * Use this method to check if we are entirely done processing jobs.
	 * @return boolean: true - we are done, false - we are not done.
	 */
	public boolean done() {
		/*
		 * If scheduler has done it's job correctly at the end of each time slice either
		 * 1. A job will be in the CPU
		 * 2. No job will be in the CPU because there are no jobs left to run.
		 */
		return cpu.isEmpty() && diskQueue.isEmpty();
	}
	
	public String printState() {
		String print = "";
		print += "\nTime: " + Time.getTime();
		print += cpu.toString();
		print += jobQueue.toString();
		print += readyQueue.toString();
		print += diskQueue.toString();
		print += ioQueue.toString();
		print += "\n \t" + "CompletedJobs: "+ completedJobs + "\n";
		return print;
	}

}
