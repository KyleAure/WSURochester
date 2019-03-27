package edu.winona.cs.main;

import java.util.List;

import edu.winona.cs.clock.ClockListener;
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
	private List<ProcessControlBlock> jobPool;
	private CPU cpu;
	private DiskQueue diskQueue;
	private IOWaitingQueue ioQueue;
	private JobQueue jobQueue;
	private ReadyQueue readyQueue;
	
	public Scheduler(ScheduleModes mode, int quantum, List<ProcessControlBlock> jobPool) {
		this.mode = mode;
		this.quantum = quantum;
		this.jobPool = jobPool;
		cpu = new CPU();
		diskQueue = new DiskQueue();
		ioQueue = new IOWaitingQueue();
		jobQueue = new JobQueue();
		readyQueue = new ReadyQueue();
		
		//Load jobs into JobQueue
		for(ProcessControlBlock job : jobPool) {
			jobQueue.addJob(job);
		}
	}

	@Override
	public void timeHasChanged() {
		LOG.log(LogLevel.INFO, "Scheduler heard time change.");
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
