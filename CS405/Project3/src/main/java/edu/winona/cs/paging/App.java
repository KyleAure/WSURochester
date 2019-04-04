package edu.winona.cs.paging;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class App {
	private static Replace algorithm;
	private static int frames;
	private static ArrayList<Integer> processes;
	
    public static void main( String[] args ) {
        //Step 1: get replacement algorithm
    	algorithm = getReplaceAlgo();
    	
    	//Step 2: get number of frames
    	frames = getFrames();
    	
    	//Step 3: parse file
    	while(processes == null) {
        	try {
        		processes = parseFile(getFile());
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}

    	
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
			FileDialog dialog = new FileDialog( (Frame) null, "Select File to Open");
			dialog.setDirectory(".");
		    dialog.setMode(FileDialog.LOAD);
		    dialog.setVisible(true);
		    try {
		    	file = new File(dialog.getDirectory() + dialog.getFile());
		    } catch (Exception e) {
		    	e.printStackTrace();
		    } 
		}
		
		return file;
    }
    
    private static ArrayList<Integer> parseFile(File file) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), Charset.defaultCharset());
		String found = null;
		
		for (String line : lines) {
			if(line == "") {
				//do nothing
			} else if (line.contains(",")) {
				found = line;
				break;
			}
		}
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		for (int i=0; i<found.length(); i++) {
			char isInt = found.charAt(i);
			int intFromChar = Character.getNumericValue(isInt);
			if(intFromChar >= 0) {
				results.add(intFromChar);
			}
		}
		
		System.out.println("Results:" + results);
		
		return results;
		
    }
}
