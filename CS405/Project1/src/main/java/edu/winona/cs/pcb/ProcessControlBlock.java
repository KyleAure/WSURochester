package edu.winona.cs.pcb;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessControlBlock {
	// JSON file properties
	@JsonProperty("processID")
	private String processID;
	@JsonProperty("cpuBursts")
	private List<Integer> cpuBursts;
	@JsonProperty("ioBursts")
	private List<Integer> ioBursts;
	
	@JsonCreator
	public ProcessControlBlock(
			@JsonProperty("processID") String processID, 
			@JsonProperty("cpuBursts") List<Integer> cpuBursts,
			@JsonProperty("ioBursts") List<Integer> ioBursts) throws IllegalArgumentException {
		
		this.processID = processID;
		this.setCpuBursts(cpuBursts);
		this.setIOBursts(ioBursts);
	}
	
	public String getProcessID() {
		return processID;
	}

	public List<Integer> getCpuBursts() {
		return cpuBursts;
	}

	public void setCpuBursts(List<Integer> cpuBursts) throws IllegalArgumentException{
		cpuBursts.forEach(burst -> {
			if(burst <= 0) {
				throw new IllegalArgumentException("Invalid CPU burst size.");
		}});
		
		this.cpuBursts = cpuBursts;
	}

	public List<Integer> getIOBursts() {
		return ioBursts;
	}

	public void setIOBursts(List<Integer> ioBursts) {
		ioBursts.forEach(burst -> {
			if(burst <= 0) {
				throw new IllegalArgumentException("Invalid IO burst size.");
		}});
		this.ioBursts = ioBursts;
	}

	@Override
	public String toString() {
		String result = "";
		result += processID + ":";
		result += "CPU" + cpuBursts + "|";
		result += "IO" + ioBursts;
		return result;
	}
	
}
