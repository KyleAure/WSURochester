package edu.winona.cs.pcb;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.LogLevel;

public class IngestUtil {
	private static final Log LOG = new Log(IngestUtil.class.getName());
	
	public static List<ProcessControlBlock> ingestJobs() {
		List<ProcessControlBlock> pcbs = null;
		
		//Ask user for File Location
		File file = null;
		
		while(file == null) {
			FileDialog dialog = new FileDialog( (Frame) null, "Select File to Open");
			dialog.setDirectory(".");
		    dialog.setMode(FileDialog.LOAD);
		    dialog.setVisible(true);
		    try {
		    	file = new File(dialog.getFile());
		    } catch (Exception e) {
		    	LOG.log(LogLevel.WARNING, "No file chosen. Try again.");
		    }   
		}
		
		LOG.log(LogLevel.INFO, "File chosen to ingest: " + file.getAbsolutePath());
		
		//Try to ingest JSON data as PCB POJOs
		try {
			ObjectMapper m = new ObjectMapper();
			pcbs = m.readValue(file, new TypeReference<List<ProcessControlBlock>>() {});
			LOG.log(LogLevel.INFO, "Ingest of JSON file successful.\n"
					+ "List of PCBs created:\n" 
					+ pcbs);
		} catch (IOException e) {
			LOG.log(e, LogLevel.SEVERE, e.toString());
		}
		
		return pcbs;
	}
}
