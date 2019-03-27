package edu.winona.cs.queue;

import java.util.ArrayList;
import java.util.List;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.ProcessControlBlock;

public class DiskQueue implements Queue {
	private static final Log LOG = new Log(DiskQueue.class.getName());
	private ArrayList<ProcessControlBlock> diskQueue;
	
	public DiskQueue() {
		diskQueue = new ArrayList<>();
	}

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(this.isFull()) {
			//Do Nothing
		} else {
			diskQueue.add(pcb);
		}
	}

	@Override
	public ProcessControlBlock removeJob() {
		LOG.log(LogLevel.SEVERE, "DiskQueue jobs should be removed via NotifyTime");
		return null;
	}

	@Override
	public boolean isFull() {
		//This queue cannot get full. Always return false.
		return false;
	}

	@Override
	public int count() {
		return diskQueue.size();
	}
	
	/**
	 * If DiskQueue is empty this will return null.
	 * If DiskQueue has jobs in it, every job will be decremented by 1 IO burst.
	 * AND a list of completed jobs will be returned.  
	 * This list should be added to the IOQueue. 
	 */
	public ArrayList<ProcessControlBlock> notifyTime() {
		if(this.count() > 0) {
			ArrayList<ProcessControlBlock> completedJobs = new ArrayList<>();
			for(ProcessControlBlock job : diskQueue){
				//Get IO time
				List<Integer> temp = job.getIOBursts();
				int index = job.getIOIndex();
				int burst = temp.get(index);
				boolean done = false;
				//Decrement it
				burst--;
				//Check if job should be done
				if(burst == 0) {
					done = true;
					job.setCpuIndex(index + 1);
				}
				//Put burst back
				temp.set(index, burst);
				job.setCpuBursts(temp);
				if ( done ) {
					completedJobs.add(job);
					diskQueue.remove(job);
				}
			}
			return completedJobs;
		} else {
			return null;
		}
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
