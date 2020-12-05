package com.wsu.kyleaure;


import java.util.Scanner;

import com.wsu.kyleaure.LearningTree.LearningTree;
import com.wsu.kyleaure.Utilities.Utilities;

@Deprecated
public class ConsoleProgram {
	public static void main(String[] args) {
		//Object initialization
		Scanner console = new Scanner(System.in);
		LearningTree tree = null;
		String folderLocation = null;
		String subject = null;
		
		//primitives
		boolean gameOn = true;
		int input;

		// Starting Choices
		System.out.print(Utilities.startingQuestions());
		int startingInput = console.nextInt();

		if (startingInput == 0) { //exit
			gameOn = false;
		} else if (startingInput == 1) { //ask for subject
			System.out.print("\nWhat subject do you want me to guess about? ");
			subject = console.next();
			
			System.out.print("\nEnter a starting question: ");
			console.nextLine();
			String startQuestion = console.nextLine().replace("?", "");
			
			System.out.print("\nWhat is a good \"YES\" answer? ");
			String yesAns = console.nextLine();
			
			System.out.print("\nWhat is a good \"NO\" answer? ");
			String noAns = console.nextLine();
			
			tree = new LearningTree(startQuestion, noAns, yesAns, subject);
			
			System.out.print("\nNew game created!");
		}
		
		while (gameOn) {
			// Play or Exit Choices
			if (startingInput == 2) {
				input = 4;
				startingInput = -1; // no longer needed
			} else {
				System.out.print(Utilities.choices());
				input = console.nextInt();
				
				if(tree == null && input != 0) {
					System.out.print("Game has not been loaded.  Please try again.");
					input = 4;
				}
			}
			
			switch (input) {
			case 1: // Play
				// Start at root
				int location = 0;

				while (true) {
					System.out.print("\n" + tree.getNode(location).toString());
					String response = console.next();

					if (tree.getNode(location).isQuestion()) { //User was asked a question
						if (response.compareToIgnoreCase("n") == 0) {
							location = tree.getLeftChildKey(location);
						} else if (response.compareToIgnoreCase("y") == 0) {
							location = tree.getRightChildKey(location);
						} // end question
					} else { //User was asked an answer
						if (response.compareToIgnoreCase("n") == 0) {
							System.out.print("\nWhat was the answer? ");
							console.nextLine();
							String answer = console.nextLine();

							System.out.print("\nWhat question can help me tell the difference between " + answer
									+ " and " + tree.getNode(location).getStatement() + "? \n");
							String question = console.nextLine().replace("?", "");

							tree.addNode(question, answer, location);

							break;
						} else {
							System.out.print("\nI won!");
							break;
						}
					} // end answer
				} // end while
				break;

			case 2: // Print tree
				System.out.println(tree.toString());
				break;

			case 3: // Save game
				folderLocation = Utilities.fileChooser(Utilities.FOLDER_TYPE);
				
				if (folderLocation == null) {
					break;
				}

				tree.saveData();
				System.out.print("\nSave successful");

				break;

			case 4: // Load game
				System.out.print("\nLoading data will erase all unsaved data.  " + "\nDo you wish to continue? ");
				String load = console.next();

				if (load.compareToIgnoreCase("y") == 0) {
					folderLocation = Utilities.fileChooser(Utilities.FILE_TYPE);

					if (folderLocation != null) {
						// Rewrites tree
						tree = new LearningTree(folderLocation);
					} else {
						break;
					}

				} else {
					break;
				}

				break;

			case 0: // Exit
				gameOn = false;
				break;

			default: // Try Again
				System.out.println("Please enter a valid command.");
				break;
			}// end switch
		} // end while - controls game-play

		console.close();
	}
}
