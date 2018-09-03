package com.cs385.app;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.io.File;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 * App that launches when program is run.
 * JPanel that contains student data.
 * 
 * @author Kyle Jon Aure
 */
public class App extends JPanel {
	private static final long serialVersionUID = 2835313883254677925L;
	private static DefaultTableModel tableModel = null;
	private static JTable table = new JTable();
	private JScrollPane scrollPane;

	public App() {
		super(new GridLayout(1, 0));

		// Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Student List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		App newContentPane = new App();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		DataAccess da = new DataAccess();

		//Create a table model with just headers
		tableModel = new DefaultTableModel(new Object[][] {}, da.createHeader());
		
		//Create table
		table.setModel(tableModel);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
		table.setFillsViewportHeight(true);
		
		//Get file
		File temp = null;
		while (temp == null) {
			temp = da.chooseFile();
		}
		
		if (temp.getPath() != "CANCEL") {
			//Parse data
			try {
				da.parseData();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//Get student list
			ArrayList<Student> studentList = da.getStudentList();
			
			//Add students to model
			for(Student s : studentList) {
				tableModel.addRow(s.toRow());
			}
			
			//Update table
			table.setModel(tableModel);

			// Schedule a job for the event-dispatching thread:
			// creating and showing this application's GUI.
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();
				}
			});
			
			int response = JOptionPane.showConfirmDialog(null, "Sort and Query?");
			
			if (response == JOptionPane.YES_OPTION) {
				ArrayList<Student> sortedList = da.getSortedList();
				
				tableModel = new DefaultTableModel(new Object[][] {}, da.createHeader());
				
				//Add students to model
				for(Student s : sortedList) {
					tableModel.addRow(s.toRow());
				}
				
				//Update table
				table.setModel(tableModel);
			}
			
		}
		
	}

}
