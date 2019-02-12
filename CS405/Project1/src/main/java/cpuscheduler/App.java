package cpuscheduler;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import clock.Time;

public class App {
	private static boolean running = true;

	public static void main(String[] args) {
		//Set up variables
		ScheduleModes mode = null;
		int quantum = 0;
		Scanner reader = new Scanner(System.in);
		
		//Get users input on Scheduling Mode
		while(mode == null) {
			System.out.print("\n(0) Exit");
			System.out.print("\n(1) Shortest Job First");
			System.out.print("\n(2) Round Robin");
			System.out.print("\nChoose Scheduling Mode: ");
			
			int n = reader.nextInt();
			
			switch (n) {
				case 1: mode = ScheduleModes.SJF; break;
				case 2: mode = ScheduleModes.RR; break;
				case 0: System.exit(0); break;
			}
		}
		
		//Get users input on time quantum for RR
		if(mode == ScheduleModes.RR) {
			while(quantum <= 0) {
				System.out.print("\nEnter time quantum in ms: ");
				quantum = reader.nextInt();
				
				if(quantum <= 0) {
				 System.out.println("\nTime quantum cannot be less than or equal to 0.");
				}
			}
		}
		
		//Stop reading user input
		reader.close();
		
		//Initialize Timer
		Time time = new Time();
		
		//REMOVE
		System.out.println("Status of Running: " + running);
		
		//Spawn Threads
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		//Timer Thread: waits until notified and then increments time.
		es.submit(() -> {
			//Get thread name
			String threadName = Thread.currentThread().getName();
			
			//Output thread status
			System.out.println("Timer Thread:" + threadName + " has been initalized.");
			
			//Keep incrementing time until running is set to false.
			synchronized (time) {
				while(running) {
					try {
						//Wait until hold on time is removed
						time.wait();
					} catch (InterruptedException e) {
						System.out.println("Timer thread was interrupted.");
						e.printStackTrace();
					}
					//Increment time
					time.increment();
					
					//Notify process thread that time has been updated
					time.notify();
			}}
		});
		
		//Process Thread: 
		es.submit(() -> {
			//Get thread name
			String threadName = Thread.currentThread().getName();
			
			//Output status
			System.out.println(threadName + " has been initalized. Process Thread.");
			
			
			
			//Keep incrementing until end
			synchronized (time) {
				while(running) {
					
					
					//TODO add process code
			}}
		});	
	}
	
	@SuppressWarnings("unused")
	private List<ProcessControlBlock> ingestJobs(String filePath) {
		ObjectMapper m = new ObjectMapper();
		List<ProcessControlBlock> pcbs = null;
		
		try {
			pcbs = m.readValue(new File(filePath), new TypeReference<List<ProcessControlBlock>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pcbs;
	}

}
