package edu.winona.cs.main;

import java.util.ArrayList;
import java.util.List;

import edu.winona.cs.clock.ClockListener;
import edu.winona.cs.clock.Time;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.ProcessControlBlock;
import edu.winona.cs.queue.CPU;
import edu.winona.cs.queue.DiskQueue;
import edu.winona.cs.queue.IOWaitingQueue;
import edu.winona.cs.queue.JobQueue;
import edu.winona.cs.queue.ReadyQueue;

public class Scheduler implements ClockListener {
	private static final Log LOG = new Log(Scheduler.class.getName());
	private ScheduleModes mode;
	private int quantum;
	private List<ProcessControlBlock> completedJobs;
	private CPU cpu;
	private DiskQueue diskQueue;
	private IOWaitingQueue ioQueue;
	private JobQueue jobQueue;
	private ReadyQueue readyQueue;
	
	public Scheduler(ScheduleModes mode, int quantum, List<ProcessControlBlock> jobPool) {
		this.mode = mode;
		this.quantum = quantum;
		cpu = new CPU();
		diskQueue = new DiskQueue();
		ioQueue = new IOWaitingQueue();
		jobQueue = new JobQueue();
		readyQueue = new ReadyQueue(mode);
		completedJobs = new ArrayList<>();
		
		//Load jobs into JobQueue
		for(ProcessControlBlock job : jobPool) {
			jobQueue.addJob(job);
		}
	}

	@Override
	public void timeHasChanged() {
		LOG.log(LogLevel.INFO, "Scheduler heard time change.");
		boolean contextSwitch = false;
		ProcessControlBlock fromCPU = null;
		
		//STEP 1: Load ready queue ONLY IF there is a context switch
		if(cpu.isJobDone()) {
			contextSwitch = true;
			LOG.log(LogLevel.INFO, "Context Switch");
			
			//If CPU is full remove the job
			if(cpu.isFull()) {
				fromCPU = cpu.removeJob();
				LOG.log(LogLevel.INFO, "Scheduler removed job from CPU." + fromCPU);
			}
			
			//STEP 1.1: First take any process from the CPU (RR)
			if(fromCPU != null) {
				if(cpu.isPreemptive()) {
					readyQueue.addJob(fromCPU);
					LOG.log(LogLevel.INFO, "Scheduler put job fromCPU in ReadyQueue");
				} else {
					if(fromCPU.getCpuBursts().isEmpty()) {
						completedJobs.add(fromCPU);
						LOG.log(LogLevel.INFO, "Scheduler put job fromCPU in Completed Jobs");
					}else{
						diskQueue.addJob(fromCPU);
						LOG.log(LogLevel.INFO, "Scheduler put job fromCPU in DiskQueue");
					}	
				}
			}
			
			//STEP 1.2: Second take first process from IOQueue
			while(!readyQueue.isFull() && ioQueue.count() > 0) {
				ProcessControlBlock fromIOQueue = ioQueue.removeJob();
				readyQueue.addJob(fromIOQueue);
				LOG.log(LogLevel.INFO, "Scheduler put job fromIOQueue into ReadyQueue");
			}
			//STEP 1.3: Third take first process in JobQueue
			while(!readyQueue.isFull() && jobQueue.count() > 0) {
				ProcessControlBlock fromJobQueue = jobQueue.removeJob();
				readyQueue.addJob(fromJobQueue);
				LOG.log(LogLevel.INFO, "Scheduler put job fromJobQueue into ReadyQueue");
			}
		}
		
		//STEP 3: Choose which job to load into CPU
		if(contextSwitch && readyQueue.count() >= 1) {
			//Ready queue knows which job to return
			ProcessControlBlock fromReadyQueue = readyQueue.removeJob();
			cpu.addJob(fromReadyQueue);
			LOG.log(LogLevel.INFO, "Scheduler took job fromReadyQueue and put it in CPU." + fromReadyQueue);
		}
		
		//STEP 3: Notify CPU and Disk (decrement bursts)
		cpu.notifyTime();
		LOG.log(LogLevel.INFO, "CPU time slice run: " + cpu.toString());
		
		ArrayList<ProcessControlBlock> fromDiskQueue = diskQueue.notifyTime();
		LOG.log(LogLevel.INFO, "DiskQueue time slice run: " + diskQueue.toString());
		
		if(mode == ScheduleModes.RR && Time.getTime()%quantum == 0) {
			//Job in CPU has reached it's quantum.  It should be removed next click tick
			cpu.setJobDone();
		}
		
		//STEP 4: Move from Disk to IOQueue
		if(fromDiskQueue != null && !fromDiskQueue.isEmpty()) {
			for(ProcessControlBlock job : fromDiskQueue) {
				ioQueue.addJob(job);
			}
		}
		
		//STEP 5: Print State
		//if(contextSwitch) {
			String print = "";
			print += "Time: " + Time.getTime();
			print += cpu.toString();
			print += jobQueue.toString();
			print += readyQueue.toString();
			print += diskQueue.toString();
			print += ioQueue.toString();
			print += "\n \t" + "CompletedJobs: "+ completedJobs + "\n";
			LOG.log(LogLevel.SEVERE, print);
			//System.out.println(print);
		//}
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
		return !cpu.isFull();
	}

}
