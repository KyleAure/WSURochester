package edu.winona.cs.paging;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileReader;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.swing.JOptionPane;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.LogLevel;

public class App {
	private static Replace algorithm;
	private static int frames;
	private static final Log log = new Log(App.class.getName()); 
	
    public static void main( String[] args ) {
    	log.log(LogLevel.INFO, "PAGING SIMULATOR");
    	
        //Step 1: get replacement algorithm
    	algorithm = getReplaceAlgo();
    	log.log(LogLevel.INFO, "Selected Paging Algorithm:\n" + algorithm);
    	
    	//Step 2: get number of frames
    	frames = getFrames();
    	log.log(LogLevel.INFO, "Main Store Size:\n" + frames);
    	
    	//Step 3: set main store
    	Mainstore mainstore = new Mainstore(frames);
    	
    	//Step 5: get data
    	PageTable pt = null;
    	Jsonb jsonb = JsonbBuilder.create();
    	while(pt == null) {
        	try {
        		File jsonFile = getFile();
        		log.log(LogLevel.INFO, "File Selected: " + jsonFile.getAbsolutePath());
        		pt = jsonb.fromJson(new FileReader(jsonFile.getAbsolutePath()), PageTable.class);
        		log.log(LogLevel.INFO, "Page Table Created:\n" + pt.toString());
    		} catch (Exception e) {
    			log.log(e, LogLevel.WARNING, "Error thrown during jsonb deserialization.");
    		}
    	}

    	int faults = 0;
    	if(pt.arePagesCreated()) {
        	int nextPage = pt.pop();
        	while(nextPage > 0) {
        		log.log(LogLevel.INFO, "Attempting to get " + nextPage);
        		if(!mainstore.get(nextPage)) {
        			faults++;
        			log.log(LogLevel.INFO, "Fault #" + faults + "\n" + mainstore.toString());
        		}
        		nextPage = pt.pop();
        	}
    	}
    	
    	String endResult = "\nPaging Simulation with " + frames + " page(s) of mainstore\n";
    	endResult += "Using " + algorithm + " algorithm\n";
    	endResult += "Total page faults = " + faults;
    	
    	log.log(LogLevel.INFO, endResult);
    }
    
    public static Replace getAlgorithm() {
    	return algorithm;
    }
    
    private static Replace getReplaceAlgo() {
    	int result = -1;
    	while(result == -1) {
    		result = JOptionPane.showOptionDialog(null, 
        			"Choose Replacement Algorithm to be used", 
        			"Replacement Alogrithm", 
        			JOptionPane.DEFAULT_OPTION, 
        			JOptionPane.QUESTION_MESSAGE, 
        			null, 
        			Replace.values(), 
        			Replace.values()[0]);
    	}
    	return Replace.values()[result];
    }
    
    private static int getFrames() {
    	int frames = -1;
    	String message = "Please input number of frames to generate (1-10)";
    	String additionalMessage = "";
    	while(frames < 1 || frames > 10) {
    		String result = JOptionPane.showInputDialog(null, message + additionalMessage, "Frames", JOptionPane.QUESTION_MESSAGE);
    		try {
    			frames = Integer.parseInt(result);
    		} catch (NumberFormatException e) {
    			System.out.println("Could not parse string.  Please Retry");
    			e.printStackTrace();
    			additionalMessage =  "\nInvalid input, please try again.";
    		}
    	}
    	return frames;
    }
    
    private static File getFile() {
		//Ask user for File Location
		File file = null;
		while(file == null) {
			Frame frame = null;
			FileDialog dialog = new FileDialog(frame, "Select File to Open");
			dialog.setDirectory(".");
		    dialog.setMode(FileDialog.LOAD);
		    dialog.setVisible(true);
		    try {
		    	file = new File(dialog.getDirectory() + dialog.getFile());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    dialog.dispose();
		}
		return file;
    }
}
