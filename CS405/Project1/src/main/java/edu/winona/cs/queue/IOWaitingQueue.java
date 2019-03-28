package edu.winona.cs.queue;

import java.util.LinkedList;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.ProcessControlBlock;

public class IOWaitingQueue implements Queue {
	private static final Log LOG = new Log(DiskQueue.class.getName());
	private LinkedList<ProcessControlBlock> ioQueue;
	
	public IOWaitingQueue() {
		ioQueue = new LinkedList<>();
	}

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(isFull()) {
			//do nothing
		} else {
			ioQueue.add(pcb);
		}
		
	}

	@Override
	public ProcessControlBlock removeJob() {
		if(this.count() >= 1) {
			return ioQueue.pop();
		} else {
			LOG.log(LogLevel.WARNING, "Job Queue is Empty.  Invalid access.");
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}
	}

	@Override
	public boolean isFull() {
		return false; //This queue does not get full. Always return false;
	}

	@Override
	public int count() {
		return ioQueue.size();
	}


	@Override 
	public String toString() {
		String result = "\n \t IO Queue:\t";
		
		for(int i = 0; i < ioQueue.size(); i++) {
			result += ioQueue.get(i).getProcessID() + " ";
		}
		
		return result;
	}

}
