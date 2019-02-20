package edu.winona.cs.main;

import java.util.Scanner;
import edu.winona.cs.clock.Initiator;
import edu.winona.cs.clock.Time;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.queue.JobQueue;

public class App {
	private static final Log LOG = new Log(App.class.getName());
	private static ScheduleModes mode = null;
	private static int quantum = 0;
	private static Scanner reader = null;
	private static boolean running = true;

	public static void main(String[] args) {
		//Initiate reader to get user input
		reader = new Scanner(System.in);
		
		//Get scheduling mode from user
		getSchedulingMode();
		
		//Get Time Quantum from user, if necessary
		getTimeQuantum();
		
		//Stop reading user input
		reader.close();
		
		//Create clock
		Initiator clock = new Initiator();
		
		//Create queues
		JobQueue jq = new JobQueue();
		
		
		//Add queues to clock listeners
		clock.addListener(jq);
		
		//Start running clock
		while(running) {
			clock.tickClock();
			
			//TODO remove later
			if(Time.getTime() > 5) {
				running = false;
			}
		}
	}//end Main Method
	
	private static void getSchedulingMode() {
		reader = new Scanner(System.in);
		
		//Get users input on Scheduling Mode
		while(mode == null) {
			System.out.println("(0) Exit");
			System.out.println("(1) Shortest Job First");
			System.out.println("(2) Round Robin");
			System.out.println("Choose Scheduling Mode: ");

			int n = reader.nextInt();
			
			switch (n) {
				case 1: 
					mode = ScheduleModes.SJF; 
					LOG.log(LogLevel.INFO, "Scheduling Mode = Shortest Job First");
					break;
				case 2: 
					mode = ScheduleModes.RR; 
					LOG.log(LogLevel.INFO, "Scheduling Mode = Round Robin");
					break;
				case 0: 
					LOG.log(LogLevel.WARNING, "User quite during scheduling mode.");
					System.exit(0); 
					break;
			}
		}
	}// end getSchedulingMode
	
	private static void getTimeQuantum() {
		if(mode == ScheduleModes.RR) {
			while(quantum <= 0) {
				System.out.println("Enter time quantum in ms: ");
				quantum = reader.nextInt();
				
				if(quantum <= 0) {
				 System.out.println("\nTime quantum cannot be less than or equal to 0.");
				}
			}
			LOG.log(LogLevel.INFO, "Time Quantum Set: " + quantum);
		}
	}
	
	public static ScheduleModes getMode() {
		return mode;
	}
	
	public static int getQuantum() {
		return quantum;
	}
}// end App Class
