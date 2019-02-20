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
	
	//Calculated Variables
	private int cpuIndex;
	private int ioIndex;
	private int countCPU;
	private int countIO;
	
	@JsonCreator
	public ProcessControlBlock(
			@JsonProperty("processID") String processID, 
			@JsonProperty("cpuBursts") List<Integer> cpuBursts,
			@JsonProperty("ioBursts") List<Integer> ioBursts) throws IllegalArgumentException {
		
		this.processID = processID;
		this.setCpuBursts(cpuBursts);
		this.setIOBursts(ioBursts);
		
		this.setCpuIndex(0);
		this.setIOIndex(0);
		this.setCountCPU(this.getCpuBursts().get(this.getCpuIndex()));
		this.setCountIO(this.getIOBursts().get(this.getIOIndex()));
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

	public int getCpuIndex() {
		return cpuIndex;
	}

	public void setCpuIndex(int cpuIndex) {
		if(cpuIndex < 0) {throw new ArrayIndexOutOfBoundsException("Index must be zero or greater.");}
		this.cpuIndex = cpuIndex;
	}

	public int getIOIndex() {
		return ioIndex;
	}

	public void setIOIndex(int ioIndex) {
		if(ioIndex < 0) {throw new ArrayIndexOutOfBoundsException("Index must be zero or greater.");}
		this.ioIndex = ioIndex;
	}

	public int getCountCPU() {
		return countCPU;
	}

	public void setCountCPU(int countCPU) {
		if(countCPU < 0) {throw new IllegalArgumentException("CPU Time Remaining must be positive.");}
		this.countCPU = countCPU;
	}

	public int getCountIO() {
		return countIO;
	}

	public void setCountIO(int countIO) {
		if(countIO < 0) {throw new IllegalArgumentException("IO Time Remaining must be positive.");}
		this.countIO = countIO;
	}

	@Override
	public String toString() {
		String result = "";
		result += processID + " -";
		for(int i = 0; i < cpuBursts.size(); i++) {
			for(int j = 0; i < ioBursts.size(); i++) {
				result += " CPU " + cpuBursts.get(i);
				result += " IO " + ioBursts.get(j);
			}
		}
		return result;
	}
	
}
