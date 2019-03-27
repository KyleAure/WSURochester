package edu.winona.cs.queue;

import java.util.LinkedList;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.ProcessControlBlock;

public class ReadyQueue implements Queue {
	private static final Log LOG = new Log(ReadyQueue.class.getName());
	private static final int MAX = 3; //Max number of jobs.
	private LinkedList<ProcessControlBlock> readyQueue;
	
	public ReadyQueue() {
		readyQueue = new LinkedList<>();
	}

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(this.isFull()) {
			LOG.log(LogLevel.WARNING, "ReadyQueue cannot exceed 3 jobs.");
		} else {
			readyQueue.add(pcb);
		}
	}

	@Override
	public ProcessControlBlock removeJob() {
		if(this.count() > 1) {
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
	public String toString() {
		String result = "Ready Queue:\t";
		for(int i = 0; i < readyQueue.size(); i++) {
			result += readyQueue.get(i).getProcessID() + "\t";
		}
		return result;
	}
}
