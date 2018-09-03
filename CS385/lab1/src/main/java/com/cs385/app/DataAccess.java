package com.cs385.app;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * The data access class provides helper methods to choose a data file 
 * from the system, process it, and manipulate the data.
 * 
 * @author Kyle Jon Aure
 */
public class DataAccess {
	
	/**
	 * Enum list of supported file tips.
	 * TODO Add support for .txt files that are also comma delimited.
	 */
	private enum FILETYPES {
		UNSUPPORTED, CSV;
	}
	
	//Constants
	private final String nl = System.getProperty("line.separator");
	
	//Fields
	private DataAccessException dae;
	private File file;
	private FILETYPES filetype;
	private Student helper;
	
	/* ********************
	 * CONSTRUCTORS
	 * ********************/

	/**
	 * Data Access constructor 
	 * 
	 * Initializes a null file with null filetype and a helper student. 
	 * It is recommended that after initializing a DataAccess object that you use the
	 * chooseFile() method to get the file and filetype.
	 */
	public DataAccess() {
		file = null;
		filetype = null;
		helper = new Student();
	}
	
	/* ********************
	 * HELPER METHODS
	 * ********************/

	/**
	 * Opens a file chooser and gets a file object and type
	 * 
	 * @return File this data access file object
	 */
	public File chooseFile() {
		// Location where file chooser will open too
		File workingDirectory = new File(System.getProperty("user.dir"));

		// File chooser and settings
		final JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(workingDirectory);
		fc.setDialogTitle("Choose a data file to load.");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		// Open dialog
		int returnValue = fc.showOpenDialog(null);

		// Ensure file chosen is in correct format
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();

			filetype = selectedFile.getName().contains(".csv") ? FILETYPES.CSV : FILETYPES.UNSUPPORTED;

			if (filetype == FILETYPES.UNSUPPORTED) {
				dae = new DataAccessException(nl + "Invalid File Type." + nl + "Please choose a .csv file.");
				notifyException(dae);
				file = null;
			} else {
				System.out.println(selectedFile.getAbsolutePath());
				file = selectedFile;
			}
		} else {
			return new File("CANCEL");
		}

		return file;
	}

	/**
	 * Parses data into objects
	 * 
	 * throws IOException
	 */
	public void parseData() throws IOException {
		if (filetype == FILETYPES.CSV) {

			Reader reader = Files.newBufferedReader(file.toPath());
			CSVParser csvParser = new CSVParser(reader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			for (CSVRecord csvRecord : csvParser) {
				new Student(
						csvRecord.get("Firstname"),
						csvRecord.get("Lastname"),
						csvRecord.get("FavoriteColor"),
						csvRecord.get("Pets"),
						csvRecord.get("Hometown"),
						csvRecord.get("Favorite Movie(s)"),
						csvRecord.get("Shoe size"));
			}
			csvParser.close();
		}
	}
	
	/**
	 * Creates a header by using a helper student
	 * to get header information from the Student class.
	 * 
	 * @return String[] header row
	 */
	public String[] createHeader() {
		return helper.createHeader();
	}
	
	/* ********************
	 * GETTERS
	 * ********************/

	public File getFile() {
		if (file == null) {
			throw new NullPointerException("No file has been choosen.  Please use the chooseFile method");
		} else {
			return file;
		}
	}

	public FILETYPES getFileType() {
		if (filetype == null) {
			throw new NullPointerException("No file has been choosen.  Please use the chooseFile method");
		} else {
			return filetype;
		}
	}
	
	public ArrayList<Student> getStudentList() {
		return helper.getStudentList();
	}
	
	public ArrayList<Student> getSortedList() {
		helper.sortAndQuery();
		return helper.getSortedList();
	}
	
	/* ********************
	 * OUTPUT
	 * ********************/

	/**
	 * Notifies user that a non-program breaking exception was thrown.
	 * 
	 * @param e Exception that was thrown
	 */
	private void notifyException(Exception e) {
		JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}

}
