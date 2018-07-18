package Utilities;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Utilities {
	public static final int FOLDER_TYPE = 0;
	public static final int FILE_TYPE = 1;

	/**
	 * Pulls up a file chooser used to choose a folder to save in
	 * or a file to read/write from.
	 * 
	 * @param type integer used to restrict choices between folders and files. 
	 *        Use Utilities.FOLDER_TYPE and Utilities.FILE_TYPE
	 * 
	 * @return String absolute path to folder or file or null if user clicks cancel.
	 */
	public static String fileChooser(int type) { 
		String path = null;
		JButton open = new JButton();
		JFileChooser fc = new JFileChooser() {
			private static final long serialVersionUID = -2345634095547055124L;

			@Override
			   protected JDialog createDialog(Component parent) throws HeadlessException {
			       // intercept the dialog created by JFileChooser
			       JDialog dialog = super.createDialog(parent);
			       dialog.setModal(true);  // set modality (or setModalityType)
			       return dialog;
			   }
			};

		fc.setCurrentDirectory(new java.io.File("."));
		
		if(type == FOLDER_TYPE){
			fc.setDialogTitle("Choose a directory location");
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}else if(type == FILE_TYPE){
			fc.setDialogTitle("Choose a file");
			fc.setFileFilter(new FileNameExtensionFilter(".ser", "ser"));
		}

		try {
			if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
				path = fc.getSelectedFile().getAbsolutePath();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * Used for testing to display choices in console environment. 
	 * 
	 * @return String of choices
	 */
	@Deprecated
	public static String choices() {
		String result;
		result = "\nChoose one of the options below: \n";
		result += "1 - Play Again \n";
		result += "2 - Print Tree \n";
		result += "3 - Save Game \n";
		result += "4 - Load Game \n";
		result += "0 - Exit \n";
		result += "-------------------------------\n";
		result += "Choice: ";

		return result;
	}
	
	/**
	 * Starting Questions used for testing.
	 * 
	 * @return String of choices
	 */
	@Deprecated
	public static String startingQuestions() {
		String result;
		result = "\nChoose one of the options below: \n";
		result += "1 - New Game \n";
		result += "2 - Load Game \n";
		result += "0 - Exit \n";
		result += "-------------------------------\n";
		result += "Choice: ";

		return result;
	}
}
