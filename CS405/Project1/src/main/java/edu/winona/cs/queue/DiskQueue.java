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
			//List of completed jobs
			ArrayList<ProcessControlBlock> completedJobs = new ArrayList<>();
			
			//Iterate through diskQueue
			for(int i=0; i < diskQueue.size(); i++){
				ProcessControlBlock job = diskQueue.get(i);
				List<Integer> bursts = job.getIOBursts();
				//Get IO time
				int burst = bursts.get(0);
				boolean done = false;
				//Decrement it
				burst--;
				//Check if job should be done
				if(burst == 0) {
					done = true;
					bursts.remove(0);
				} else {
					bursts.set(0, burst);
				}
				job.setIOBursts(bursts);
				
				//If done, add job to completed jobs, and remove it from DiskQueue
				if ( done ) {
					completedJobs.add(job);
					diskQueue.remove(i);
				}
			}
			return completedJobs;
		} else {
			return null;
		}
	}

	@Override 
	public String toString() {
		String result = "\n \t Disk Queue:\t";
		
		for(int i = 0; i < diskQueue.size(); i++) {
			result += diskQueue.get(i).getProcessID() + ":" + diskQueue.get(i).getIOBursts();
		}
		
		return result;
	}
}
