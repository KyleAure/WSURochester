package Test;
import java.util.Scanner;

import LearningTree.LearningTree;
import Utilities.Utilities;

@Deprecated
public class TestClassAnimal {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		boolean gameOn = true;
		String folderLocation = null;

		String startQuestion = "Does it walk on two legs";
		String yesAnswer = "Human";
		String noAnswer = "Dog";

		LearningTree tree = new LearningTree(startQuestion, noAnswer, yesAnswer, "Animal");

		while (gameOn) {
			// Play or Exit Choices
			System.out.print(Utilities.choices());
			int input = console.nextInt();

			switch (input) {
			case 1: // Play
				// Start at root
				int location = 0;

				while (true) {
					System.out.print("\n" + tree.getNode(location).toString());
					String response = console.next();

					if (tree.getNode(location).isQuestion()) {
						if (response.compareToIgnoreCase("n") == 0) {
							location = tree.getLeftChildKey(location);
						} else {
							location = tree.getRightChildKey(location);
						} // end question
					} else {
						if (response.compareToIgnoreCase("n") == 0) {
							System.out.print("\nWhat was the answer? ");
							String answer = console.next();

							System.out.print("\nWhat question can help me tell the difference between " + answer
									+ " and " + tree.getNode(location).getStatement() + "? \n");
							console.nextLine();
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
				System.out.print("\nLoading data will erase all unsaved data.  "
						+ "\nDo you wish to continue? ");
				String load = console.next();
				
				if(load.compareToIgnoreCase("y") == 0){
					folderLocation = Utilities.fileChooser(Utilities.FILE_TYPE);
					
					//TODO remove when testing is complete
					System.out.println(folderLocation);
					
					if(folderLocation != null){
						//Rewrites tree
						tree = new LearningTree(folderLocation);
					}else{
						break;
					}
					
				}else{
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
