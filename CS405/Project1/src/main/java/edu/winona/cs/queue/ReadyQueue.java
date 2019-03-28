package edu.winona.cs.queue;

import java.util.List;
import java.util.LinkedList;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.LogLevel;
import edu.winona.cs.main.ScheduleModes;
import edu.winona.cs.pcb.ProcessControlBlock;

public class ReadyQueue implements Queue {
	private static final Log LOG = new Log(ReadyQueue.class.getName());
	private static final int MAX = 3; //Max number of jobs.
	private LinkedList<ProcessControlBlock> readyQueue;
	private ScheduleModes mode; 
	
	public ReadyQueue(ScheduleModes mode) {
		this.mode = mode;
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
		if(this.count() >= 1) {
			if(mode == ScheduleModes.SJF) {
				int shortestJobIndex = 0;
				int shortestTime = Integer.MAX_VALUE;
				for(int i = 0; i < readyQueue.size(); i++) {
					List<Integer> bursts = readyQueue.get(0).getCpuBursts();
					if(bursts.get(0) < shortestTime) {
						shortestTime = bursts.get(0);
						shortestJobIndex = i;
					}
				}
				return readyQueue.remove(shortestJobIndex);
			} else {
				return readyQueue.pop();
			}
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
		return "\n \t Ready Queue:\t" + readyQueue;
	}
}
