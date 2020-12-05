package com.wsu.kyleaure;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Component;
import javax.swing.ImageIcon;

public class NewGame extends JFrame {
	//Serial ID
	private static final long serialVersionUID = 6922855630424405050L;
	
	//Frame members
	private JPanel contentPane;
	private JButton btnSubmit;
	
	//Data Members
	private JTextField startQuestion;
	private JTextField yesAns;
	private JTextField noAns;
	
	//From client
	private String clientSubject = Client.getSubject();

	/**
	 * Launch the application.
	 * 
	 * @param args String[] of arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGame frame = new NewGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewGame() {
		setAlwaysOnTop(true);
		setType(Type.POPUP);
		setTitle("New Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage("favicon.png"));
		setBounds(100, 100, 450, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("1px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.BUTTON_COLSPEC,
				FormSpecs.BUTTON_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("1px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.MIN_ROWSPEC,}));
		
		//### TITLE: New Game Data ###
		JLabel lblNewGameData = new JLabel("New Game Data");
		lblNewGameData.setIcon(new ImageIcon("favicon.png"));
		lblNewGameData.setFont(new Font("Avenir", Font.BOLD, 26));
		contentPane.add(lblNewGameData, "3, 3, 7, 1, center, center");
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "3, 5, 7, 1");
		
		//### LABEL: Subject Chosen ###
		JLabel subject = new JLabel("Subject chosen: " + clientSubject);
		subject.setForeground(Color.RED);
		subject.setFont(new Font("Avenir LT Std 35 Light", Font.BOLD, 16));
		contentPane.add(subject, "3, 7, 7, 1, center, center");
		JSeparator separator_1 = new JSeparator();
		contentPane.add(separator_1, "3, 9, 7, 1");
		
		//### FIELD: Starting Question ###
		JLabel lblStartingQuestion = new JLabel("Starting Question");
		lblStartingQuestion.setFont(new Font("Avenir LT Std 35 Light", Font.PLAIN, 12));
		contentPane.add(lblStartingQuestion, "5, 11, right, default");
		startQuestion = new JTextField();
		startQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!(yesAns.getText().isEmpty() || noAns.getText().isEmpty() || startQuestion.getText().isEmpty())){
					btnSubmit.setEnabled(true);
				}
			}
		});
		contentPane.add(startQuestion, "7, 11, 3, 1, fill, top");
		startQuestion.setColumns(10);
		
		//### FIELD: Yes Answer ###
		JLabel lblYesAnswer = new JLabel("Yes Answer");
		lblYesAnswer.setFont(new Font("Avenir LT Std 35 Light", Font.PLAIN, 12));
		contentPane.add(lblYesAnswer, "5, 13, right, default");
		yesAns = new JTextField();
		yesAns.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!(yesAns.getText().isEmpty() || noAns.getText().isEmpty() || startQuestion.getText().isEmpty())){
					btnSubmit.setEnabled(true);
				}
			}
		});
		contentPane.add(yesAns, "7, 13, 3, 1, fill, default");
		yesAns.setColumns(10);
		
		//### FIELD: No Answer ###
		JLabel lblNoAnswer = new JLabel("No Answer");
		lblNoAnswer.setFont(new Font("Avenir LT Std 35 Light", Font.PLAIN, 12));
		contentPane.add(lblNoAnswer, "5, 15, right, default");
		noAns = new JTextField();
		noAns.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!(yesAns.getText().isEmpty() || noAns.getText().isEmpty() || startQuestion.getText().isEmpty())){
					btnSubmit.setEnabled(true);
				}
			}
		});
		contentPane.add(noAns, "7, 15, 3, 1, fill, default");
		noAns.setColumns(10);
		JSeparator separator_2 = new JSeparator();
		contentPane.add(separator_2, "3, 17, 7, 1");
		
		//### BUTTON: Submit ###
		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(new ActionListener() {
			//### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				Client.startNewGame(
						startQuestion.getText().trim().replace("?", ""),
						noAns.getText().trim().toLowerCase(),
						yesAns.getText().trim().toLowerCase());
				setVisible(false);
			}
		});
		contentPane.add(btnSubmit, "8, 19");
		
		//### BUTTON: Cancel ###
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			//### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnCancel, "9, 19");
	}
}
