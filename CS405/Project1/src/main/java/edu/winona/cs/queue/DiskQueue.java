package edu.winona.cs.queue;

import java.util.LinkedList;

import edu.winona.cs.clock.ClockListener;
import edu.winona.cs.log.Log;
import edu.winona.cs.pcb.ProcessControlBlock;

public class DiskQueue implements Queue, ClockListener {
	private static final Log LOG = new Log(DiskQueue.class.getName());
	private static LinkedList<ProcessControlBlock> diskQueue = new LinkedList<>();

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(isFull()) {
			//Do Nothing
		} else {
			diskQueue.add(pcb);
		}

	}

	@Override
	public ProcessControlBlock removeJob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void timeHasChanged() {
		// TODO Auto-generated method stub
		
	}
	
	@Override 
	public String toString() {
		String result = "Disk Queue:\t";
		
		for(int i = 0; i < diskQueue.size(); i++) {
			result += diskQueue.get(i).getProcessID() + "\t";
		}
		
		return result;
	}

}
