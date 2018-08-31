package main;

import exceptions.DataAccessException;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataAccess {
	private enum FILETYPES{
		UNSUPPORTED, TXT, CSV;
	}
	private static final String nl = System.getProperty("line.separator"); 
	private static DataAccessException dae;
	private static File file;
	private static FILETYPES filetype;

	public static void main(String[] args) throws DataAccessException {
		while(file == null) {
			chooseFile();
		}
		
		try {
			getTable();
		} catch (Exception e) {
			
		}
		

	}
	
	/**
	 * 
	 * @throws IOException 
	 */
	private static void getTable() throws IOException {
		JTable table = null;
		
		if(filetype == FILETYPES.CSV) {
			CSVReader reader = new CSVReader(new FileReader(file)); 
			List myEntries = reader.readAll();
			Object[] columnnames = (String[]) myEntries.get(0);
			DefaultTableModel tableModel = new DefaultTableModel(columnnames, myEntries.size()-1); 
			int rowcount = tableModel.getRowCount();
			for (int x = 0; x<rowcount+1; x++)
			{
			    int y = 0;
			    if (x>0)
			    {
			        for (String thiscellvalue : (String[])myEntries.get(x))
			        {
			            tableModel.setValueAt(thiscellvalue, x-1, y);
			            y++;
			        }
			    }
			}

			table = new JTable(tableModel);
		}
		
		if(table != null) {
			table.setVisible(true);
		}
		
	}
	
	private static void printData() {
		
	}
	
	/**
	 * Opens a JFileChooser and prompts user to choose a data file.
	 * This helper method will set the class variables file and filetype.
	 */
	private static void chooseFile()  {
		//Location file chooser will open too
		File workingDirectory = new File(System.getProperty("user.dir"));
		
		//File chooser and settings
		final JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(workingDirectory);
		fc.setDialogTitle("Choose a data file to load.");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		//Open dialog
		int returnValue = fc.showOpenDialog(null);

		//Ensure file chosen is in correct format
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();
			
			filetype = selectedFile.getName().contains(".txt") ? FILETYPES.TXT 
							: selectedFile.getName().contains("csv") ? FILETYPES.CSV
							: FILETYPES.UNSUPPORTED;

			if (filetype == FILETYPES.UNSUPPORTED) {
				dae = new DataAccessException(nl + "Invalid File Type." + 
						nl + "Please choose a .txt file.");
				notifyException(dae);
				return;
			}
			System.out.println(selectedFile.getAbsolutePath());
			file = selectedFile;
		}
	}
	
	/**
	 * Notifies user that a non-program breaking exception was thrown.
	 * 
	 * @param e Exception that was thrown
	 */
	private static void notifyException(Exception e) {
		JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
	}

}
