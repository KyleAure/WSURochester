package edu.winona.cs.queue;

import java.util.LinkedList;

import edu.winona.cs.clock.ClockListener;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.ProcessControlBlock;

public class ReadyQueue implements Queue, ClockListener {
	private static final Log LOG = new Log(ReadyQueue.class.getName());
	private static final int MAX = 3;
	private static LinkedList<ProcessControlBlock> readyQueue = new LinkedList<>();

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(count() == MAX) {
			LOG.log(LogLevel.WARNING, "ReadyQueue cannot exceed 3 jobs.");
		} else {
			readyQueue.add(pcb);
		}
	}

	@Override
	public ProcessControlBlock removeJob() {
		if(readyQueue.size() > 1) {
			return readyQueue.pop();
		} else {
			LOG.log(LogLevel.WARNING, "Ready Queue is Empty.  Invalid access.");
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}
	}

	@Override
	public boolean isFull() {
		return count() == MAX;
	}

	@Override
	public int count() {
		return readyQueue.size();
	}

	@Override
	public void timeHasChanged() {
		//1. Jobs leaving the CPU
		CPU cpu = new CPU();
		
		//2. Jobs from IOWaitQueue
		//3. New process from jobQueue
		
	}
	
	@Override 
	public String toString() {
		String result = "Ready Queue:\t";
		
		for(int i = 0; i < readyQueue.size(); i++) {
			result += readyQueue.get(i).getProcessID() + "\t";
		}
		
		return result;
	}

}
