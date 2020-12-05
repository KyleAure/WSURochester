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
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class NewQuestion extends JFrame {
	//Serial ID
	private static final long serialVersionUID = 6922855630424405050L;
	
	//Frame members
	private JPanel contentPane;
	private JButton btnSubmit;
	private JTextField correctAns;
	private JLabel instructions1;
	private JLabel instructions2;
	private JTextField newQuestion;
	
	//From client
	private String clientAnswer = Client.getStatement();

	/**
	 * Launch the application.
	 * 
	 * @param args String[] of arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewQuestion frame = new NewQuestion();
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
	public NewQuestion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("favicon.png"));
		setTitle("New Question");
		setType(Type.POPUP);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 272);
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("min:grow"),}));
		
		//### TITLE: New Question Window ###
		JLabel lblNewQuestionTitle = new JLabel("New Question Window");
		lblNewQuestionTitle.setIcon(new ImageIcon("favicon.png"));
		lblNewQuestionTitle.setFont(new Font("Avenir", Font.BOLD, 26));
		contentPane.add(lblNewQuestionTitle, "3, 3, 7, 1, center, center");
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "3, 5, 7, 1");
		
		//### LABEL: Correct Answer ###
		JLabel lblCorrectAnswerTitle = new JLabel("What was the correct answer?");
		contentPane.add(lblCorrectAnswerTitle, "5, 7, 5, 1, center, center");
		
		//### FIELD: Correct Answer ###
		JLabel lblCorrectAnswer = new JLabel("Correct Answer");
		lblCorrectAnswer.setFont(new Font("Avenir LT Std 35 Light", Font.PLAIN, 12));
		contentPane.add(lblCorrectAnswer, "5, 9, right, default");
		correctAns = new JTextField();
		correctAns.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!(correctAns.getText().isEmpty())){
					instructions1.setText("What question can I use to tell the difference between");
					instructions2.setText(clientAnswer + " and " + correctAns.getText().trim() + "?");
					newQuestion.setEnabled(true);
				}else {
					instructions1.setText("");
					instructions2.setText("");
					newQuestion.setEnabled(false);
				}
			}
		});
		contentPane.add(correctAns, "7, 9, 3, 1, fill, default");
		correctAns.setColumns(10);
		JSeparator separator_1 = new JSeparator();
		contentPane.add(separator_1, "3, 11, 7, 1");
		
		//### Instructions ###
		instructions1 = new JLabel("");
		instructions1.setForeground(Color.RED);
		contentPane.add(instructions1, "5, 13, 5, 1, center, center");
		
		instructions2 = new JLabel("");
		instructions2.setForeground(Color.RED);
		contentPane.add(instructions2, "5, 15, 5, 1, center, center");
		
		//### FIELD: Starting Question ###
		JLabel lblNewQuestion = new JLabel("New Question");
		lblNewQuestion.setFont(new Font("Avenir LT Std 35 Light", Font.PLAIN, 12));
		contentPane.add(lblNewQuestion, "5, 17, right, default");
		newQuestion = new JTextField();
		newQuestion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!(newQuestion.getText().isEmpty())) {
					btnSubmit.setEnabled(true);
				}else {
					btnSubmit.setEnabled(false);
				}
			}
		});
		newQuestion.setEnabled(false);
		contentPane.add(newQuestion, "7, 17, 3, 1, fill, default");
		newQuestion.setColumns(10);
		JSeparator separator_2 = new JSeparator();
		contentPane.add(separator_2, "3, 19, 7, 1");
		
		//### BUTTON: Submit ###
		btnSubmit = new JButton("Submit");
		btnSubmit.setEnabled(false);
		btnSubmit.addActionListener(new ActionListener() {
			//### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				Client.addNewQuestion(
						newQuestion.getText().trim().replace("?", ""), 
						correctAns.getText().trim());
				setVisible(false);
			}
		});
		contentPane.add(btnSubmit, "9, 21");
	}
}
