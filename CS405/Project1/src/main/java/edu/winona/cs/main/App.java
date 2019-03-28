package edu.winona.cs.main;

import java.util.List;

import javax.swing.JOptionPane;

import edu.winona.cs.clock.Initiator;
import edu.winona.cs.log.LogLevel;
import edu.winona.cs.log.Output;
import edu.winona.cs.pcb.IngestUtil;
import edu.winona.cs.pcb.ProcessControlBlock;

public class App {
	private static final Output OUTPUT = new Output("OUTPUT");
	private static boolean running = true;

	public static void main(String[] args) {
		// Get scheduling mode from user
		ScheduleModes mode = getSchedulingMode();

		// Get Time Quantum from user, if necessary
		int quantum = 0;
		if(mode == ScheduleModes.RR) {
			quantum = getTimeQuantum();
		}
		
		//InjestJobs from file.
		List<ProcessControlBlock> jobPool = IngestUtil.ingestJobs();

		// Create clock
		Initiator clock = new Initiator();

		// Create scheduler
		Scheduler scheduler = new Scheduler(mode, quantum, jobPool);

		// Add scheduler to clock listeners
		clock.addListener(scheduler);

		// Start running clock
		while (running) {
			clock.tickClock();

			if (scheduler.done()) {
				running = false;
			}
		}
		
		OUTPUT.log(LogLevel.INFO, "Scheduler has finished running Successfully. All jobs completed.");
	}// end Main Method

	private static ScheduleModes getSchedulingMode() {
		
        ScheduleModes[] options = {ScheduleModes.RR, ScheduleModes.SJF};
        int x = -1;
        
        while (x == -1) {
        	x = JOptionPane.showOptionDialog(null, 
            		"Please choose a scheduling mode.",
                    "Scheduling Modes",
                    JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);
        }
        
        OUTPUT.log(LogLevel.INFO, "Scheduling Mode = " + options[x]);
        
        return options[x];

	}// end getSchedulingMode

	private static int getTimeQuantum() {
		int quantum = -1;
		String message = "Enter time quantum for Round Robin.";
		while (quantum <= 0) {
		    String result = JOptionPane.showInputDialog(
		            null, 
		            message, 
		            "Time Quantum", 
		            JOptionPane.QUESTION_MESSAGE);
		    try {
		    	quantum = Integer.parseInt(result);
		    } catch (Exception e) {
		    	message = "Enter time quantum for Round Robin.\n" 
		    			+ "Please enter in an integer greater than 0."; 
		    }
		    
		}
		OUTPUT.log(LogLevel.INFO, "Time Quantum Set: " + quantum);
		return quantum;
	}
}// end App Class
