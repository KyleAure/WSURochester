package main;

import exceptions.DataAccessException;

import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.opencsv.CSVReader;

public class DataAccess {
	private enum FILETYPES{ UNSUPPORTED, TXT, CSV; }
	private final String nl = System.getProperty("line.separator"); 
	private DataAccessException dae;
	private File file;
	private FILETYPES filetype;
	
	/**
	 * TODO
	 * @return
	 */
	private File chooseFile()  {
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
				return null;
			} else {
				System.out.println(selectedFile.getAbsolutePath());
				file = selectedFile;
				return file;
			}
		} else {
			return null;
		}
		
	}
	
	/**
	 * TODO 
	 * 
	 * @return
	 * @throws IOException
	 */
	public JTable getTable() throws IOException {
		JTable table = null;
		
		if(filetype == FILETYPES.CSV) {
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(file)); 
			
			List<String[]> myEntries = reader.readAll();
			
			String[] columnnames = myEntries.get(0);
			System.out.println("column =" + columnnames);
			DefaultTableModel tableModel = new DefaultTableModel(columnnames, myEntries.size()-1); 
			int rowcount = tableModel.getRowCount();
			for (int x = 0; x<rowcount+1; x++) {
			    int y = 0;
			    if (x>0) {
			        for (String thiscellvalue : myEntries.get(x)) {
			            tableModel.setValueAt(thiscellvalue, x-1, y);
			            y++;
			        }
			    }
			}

			table = new JTable(tableModel);
		}
		
		return table;
		
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
