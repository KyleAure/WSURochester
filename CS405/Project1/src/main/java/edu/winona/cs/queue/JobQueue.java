package edu.winona.cs.queue;

import java.util.LinkedList;
import java.util.List;

import edu.winona.cs.clock.ClockListener;
import edu.winona.cs.clock.Time;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.pcb.IngestUtil;
import edu.winona.cs.pcb.ProcessControlBlock;

/**
 * Initial queue where the jobs are located.
 * @author Kyle Jon Aure
 */
public class JobQueue implements Queue, ClockListener {
	private static final Log LOG = new Log(JobQueue.class.getName());
	private static LinkedList<ProcessControlBlock> jobQueue = new LinkedList<>();

	@Override
	public void addJob(ProcessControlBlock pcb) {
		if(isFull()) {
			//Do nothing
		} else {
			jobQueue.add(pcb);
			LOG.log(LogLevel.INFO, "Job Queue Position: " + jobQueue.size() + "\t" + pcb.toString());
		}
		
	}

	@Override
	public ProcessControlBlock removeJob() {
		if(jobQueue.size() > 1) {
			return jobQueue.pop();
		} else {
			LOG.log(LogLevel.WARNING, "Job Queue is Empty.  Invalid access.");
			throw new ArrayIndexOutOfBoundsException("Queue is empty.");
		}
	}

	@Override
	public boolean isFull() {
		//Job queue can never be full.  Always return false.
		return false;
	}

	@Override
	public int count() {
		return jobQueue.size();
	}

	/**
	 * This method is called when time has changed.
	 */
	@Override
	public void timeHasChanged() {
		//Only respond to time change during time 1 when jobs are ingested. 
		if(Time.getTime() == 1) {
			List<ProcessControlBlock> pcbTemp = IngestUtil.ingestJobs();
			for(int i=0; i<pcbTemp.size(); i++) {
				addJob(pcbTemp.get(i));
			}
		}
	}
	
	@Override 
	public String toString() {
		String result = "Job Queue:\t";
		
		for(int i = 0; i < jobQueue.size(); i++) {
			result += jobQueue.get(i).getProcessID() + "\t";
		}
		
		return result;
	}
}
