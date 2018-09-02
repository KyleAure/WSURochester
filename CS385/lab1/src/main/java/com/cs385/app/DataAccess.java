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
 * The data access class provides helper methods to choose a data file from the
 * system. and process it.
 * 
 * @author wu7472qj
 *
 */
public class DataAccess {
	private enum FILETYPES {
		UNSUPPORTED, TXT, CSV;
	}

	private final String nl = System.getProperty("line.separator");
	private DataAccessException dae;
	private File file;
	private FILETYPES filetype;

	/**
	 * Data Access constructor initializes a null file with null filetype. It is
	 * recommended that after initializing a DataAccess object that you use the
	 * chooseFile() method.
	 */
	public DataAccess() {
		file = null;
		filetype = null;
	}

	/**
	 * Returns the file object
	 * 
	 * @return File this data access file object
	 */
	public File getFile() {
		if (file == null) {
			throw new NullPointerException("No file has been choosen.  Please use the chooseFile method");
		} else {
			return file;
		}
	}

	/**
	 * Returns the file type
	 * 
	 * @return FILETYPES this data access file's type
	 */
	public FILETYPES getFileType() {
		if (filetype == null) {
			throw new NullPointerException("No file has been choosen.  Please use the chooseFile method");
		} else {
			return filetype;
		}
	}

	/**
	 * Opens a file chooser and gets a file object and type
	 * 
	 * @return File this data access file object
	 */
	public File chooseFile() {
		// Location file chooser will open too
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

			filetype = selectedFile.getName().contains(".txt") ? FILETYPES.TXT
					: selectedFile.getName().contains("csv") ? FILETYPES.CSV : FILETYPES.UNSUPPORTED;

			if (filetype == FILETYPES.UNSUPPORTED) {
				dae = new DataAccessException(nl + "Invalid File Type." + nl + "Please choose a .txt file.");
				notifyException(dae);
				file = null;
			} else {
				System.out.println(selectedFile.getAbsolutePath());
				file = selectedFile;
			}
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
	
	public ArrayList<Student> getStudentList() {
		return Student.getStudentList();
	}
	
	public ArrayList<Student> getSortedList() {
		new Student().sortAndQuery();
		return Student.getSortedList();
	}

	/**
	 * Notifies user that a non-program breaking exception was thrown.
	 * 
	 * @param e Exception that was thrown
	 */
	private void notifyException(Exception e) {
		JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}

}
