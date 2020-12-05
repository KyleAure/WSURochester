package com.wsu.kyleaure;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import com.wsu.kyleaure.CRC8.CRC8;
import com.wsu.kyleaure.Link.Link;
import com.wsu.kyleaure.Link.SimpleLink;


/**
 * Client GUI - Runs all client activities
 * 
 * @author Kyle Jon Aure
 * @version 1.2
 */
@SuppressWarnings("serial")
public class Client extends JFrame {
	//CONSTANTS for port information
	private static int senderPort = 3200;
	private static int receiverPort = 3300;
	//VARIABLES for link
	private static Link myLink;
	private static boolean trace;
	private static String messageReceived;
	private static int lengthMessageReceived;
	private static byte[] sendingBuffer = new byte[45];
	private static byte[] receivingBuffer = new byte[512];
	private static CRC8 crc8 = new CRC8();
	//VARIABLES for GUI
	private JPanel contentPane;
	private static JTextField clientID;
	private static JTextField payload;
	private JButton btnSendMessage;
	private JButton btnSetClientID;
	private static JTextArea resultArea;
	private JScrollPane scrollPane;
	private JButton btnClear;
	private JLabel lblErrorPercent;
	private static JTextField errorPercent;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	//DATA for end of communication
	private static int numClientChatMessages = 0;
	private static int numClientRequests = 0;
	private static int numOfDamages = 0;
	private static int tempNumOfRetransmissions = 0;
	private static int maxNumOfRetransmissions = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.BUTTON_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.BUTTON_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));
		//### TITLE ###
		JLabel lblTitle = new JLabel("Client");
		lblTitle.setFont(new Font("Avenir", Font.BOLD, 24));
		contentPane.add(lblTitle, "4, 2, 3, 1, center, center");
		
		//### CLIENT ID ###
		JLabel lblClientID = new JLabel("Client ID:");
		contentPane.add(lblClientID, "4, 4, right, center");
		clientID = new JTextField();
		contentPane.add(clientID, "6, 4, fill, default");
		clientID.setColumns(10);

		//### ERROR PERCENT ###
		lblErrorPercent = new JLabel("Error Percent:");
		contentPane.add(lblErrorPercent, "4, 6, right, center");
		errorPercent = new JTextField();
		contentPane.add(errorPercent, "6, 6, fill, default");
		errorPercent.setColumns(10);

		//### TRACE ###
		JRadioButton rdbtnTraceOn = new JRadioButton("Trace On");
		buttonGroup.add(rdbtnTraceOn);
		contentPane.add(rdbtnTraceOn, "4, 8");
		JRadioButton rdbtnTraceOff = new JRadioButton("Trace Off");
		buttonGroup.add(rdbtnTraceOff);
		contentPane.add(rdbtnTraceOff, "6, 8");

		//### RESULT AREA ###
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "4, 10, 3, 1, fill, fill");
		resultArea = new JTextArea();
		scrollPane.setViewportView(resultArea);
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		resultArea.setText(
				"Create a 3 character Client ID, \nchoose trace option, and\nclick set client ID to start chatting.");

		//### BUTTON: SET CLIENT ID ###
		btnSetClientID = new JButton("Set Client ID");
		btnSetClientID.addActionListener(new ActionListener() {
			//### BUTTON: ACTION ###
			@Override
			public void actionPerformed(ActionEvent e) {
				// CHECK make sure client ID is valid
				if (clientID.getText().length() < 4 && !clientID.getText().isEmpty()) {
					try {
						//CHECK if no error percent was entered default to 0
						if(errorPercent.getText().isEmpty()) {
							errorPercent.setText("0");
						}
						
						//TRY make sure user typed an integer and not decimal
						//THROWS: Exception
						Integer.parseInt(errorPercent.getText().trim());
						
						//TRY create link  THROWS: Exception
						myLink = new SimpleLink(senderPort, receiverPort);

						//GET trace option
						if (rdbtnTraceOn.isSelected()) {
							trace = true;
						} else if (rdbtnTraceOff.isSelected()) {
							trace = false;
						}

						// DISABLE buttons we no longer need
						clientID.setEditable(false);
						errorPercent.setEditable(false);
						rdbtnTraceOff.setEnabled(false);
						rdbtnTraceOn.setEnabled(false);
						btnSetClientID.setEnabled(false);

						// ENABLE buttons that we need
						resultArea.setText("");
						payload.setEnabled(true);
						btnSendMessage.setEnabled(true);

					}catch (NumberFormatException e1){
						//Error percentage incorrect
						JOptionPane.showMessageDialog(null,
								"Please enter error as percentage and not a decimal.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} catch (Exception e2) {
						//Communication error
						JOptionPane.showMessageDialog(null,
								"Communication Error! \n" + "Please make sure server is enabled!", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} // end try-catch
				} else {
					JOptionPane.showMessageDialog(null, "Invalid Client ID", "Warning", JOptionPane.WARNING_MESSAGE);
				} // end if-else
			}// end action performed
		});
		contentPane.add(btnSetClientID, "8, 8");

		//### BUTTON: CLEAR ###
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			//### BUTTON: ACTION ###
			@Override
			public void actionPerformed(ActionEvent e) {
				resultArea.setText("");
			}
		});
		contentPane.add(btnClear, "8, 12");

		//### MESSAGE FIELD ###
		JLabel lblMessage = new JLabel("Message:");
		contentPane.add(lblMessage, "2, 14, right, default");
		payload = new JTextField();
		payload.setEnabled(false);
		contentPane.add(payload, "4, 14, 3, 1, fill, default");
		payload.setColumns(10);

		//### BUTTON: SEND MESSAGE ###
		btnSendMessage = new JButton("Send Message");
		btnSendMessage.addActionListener(new ActionListener() {
			//### BUTTON: ACTION###
			@Override
			public void actionPerformed(ActionEvent e) {
				//Increase number of message client sent
				numClientChatMessages++;
				
				//SEND message.  DisplayText = true;
				send(true);
			}
		});
		btnSendMessage.setEnabled(false);
		contentPane.add(btnSendMessage, "8, 14");
	}

	/**
	 * Wakes up client to receive an incoming message. If message is NAK then
	 * retransmit last message else display received message
	 */
	public static void wakeClient() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//GET received message  THROWS: Exception
					lengthMessageReceived = myLink.receiveFrame(receivingBuffer);

					//NAK received
					if (lengthMessageReceived == 1) {
						//CALC Increase number of damages
						numOfDamages++;
						
						//TRACK how many times this has happened for this specific message
						tempNumOfRetransmissions++;
						
						//DISPLAY trace information
						if (trace) {
							resultArea.append("Error, need retansmission \n");
						}
						
						//SEND message.  DisplayText = false;
						send(false);
						
					//RESPONSE received
					} else {
						//CALC maximum number of retransmissions
						if (tempNumOfRetransmissions > maxNumOfRetransmissions) {
							maxNumOfRetransmissions = tempNumOfRetransmissions;
						}
						
						//RESET temporary retransmission count
						tempNumOfRetransmissions = 0;

						//GET received message
						messageReceived = new String(receivingBuffer, 0, lengthMessageReceived);
						
						//DISPLAY trace information
						if (trace) {
							resultArea.append("Response recieved OK \n");
						}
						
						//DISPLAY received message
						resultArea.append(": " + messageReceived + "\n");

						//CHECK for termination message
						if (messageReceived.toLowerCase().contains("bye")) {
							endTransmission();
						}
						
						//RESET payload text to get ready for next message
						payload.setText("");
					}
				} catch (Exception e) {
					System.out.println("Recieved Message Error.");
				}
			}
		}).start();
	}

	/**
	 * Send message to server
	 * 
	 * @param displayText True = Display sent message  False = Do not display sent message
	 */
	private static void send(boolean displayText) {
		//CHECK if payload the correct size
		if (payload.getText().length() <= 40 && !payload.getText().isEmpty()) {
			//TRACK number of unique requests made
			numClientRequests++;
			
			//CONSTRUCT sending buffer (SB)
			//SB: CLIENTID
			for(int i=0; i<clientID.getText().length(); i++) {
				sendingBuffer[i] = (byte) clientID.getText().charAt(i);
			}
			//SB: LENGTH
			sendingBuffer[3] = (byte) payload.getText().trim().length();

			//CREATE payload with error
			StringBuilder payLoadWithError = new StringBuilder(payload.getText().trim());
			payLoadWithError.setCharAt(0,
					error(payLoadWithError.charAt(0), Integer.parseInt(errorPercent.getText().trim())));

			//SB: PAYLOAD (with error)
			for(int i=4; i<payLoadWithError.length() + 4; i++) {
				sendingBuffer[i] = (byte) payLoadWithError.charAt(i-4);
			}
			
			//SB: CHECKSUM (CRC8)
			sendingBuffer[44] = crc8.checksum(payload.getText().trim().getBytes());

			try {
				//SEND sending buffer and length  THROWS: Exception 
				myLink.sendFrame(sendingBuffer, sendingBuffer.length);
				
				//WAKE server to receive message
				Server.wakeServer();

				//DISPLAY text if necessary
				if (displayText) {
					resultArea.append("> " + payload.getText().trim() + "\n");
					resultArea.update(resultArea.getGraphics());
				}
			
			//CATCH any error when sending buffer
			} catch (Exception e) {
				System.out.println("Error sending buffer or waking the server.");
			}
		//WARNING message too long
		} else {
			JOptionPane.showMessageDialog(null, "Message cannot be more than 40 characters", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Method used to create an error for a single character
	 * 
	 * @param c character to be edited
	 * @param percent chance of character being edited [0, 100]
	 * @return char edited character
	 */
	private static char error(char c, int percent) {
		Random rand = new Random();
		char result;
		int test = rand.nextInt(101);
		if (test < percent) {
			result = (char) (c + 1);
		} else {
			result = c;
		}
		return result;
	}

	/**
	 * Method used to end transmission
	 */
	private static void endTransmission() {
		//INIT message to be displayed
		String message = "";
		
		//CALC theoretical number of transmissions
		double theoreticalTransmissions = numClientChatMessages
				/ (1 - (Double.parseDouble(errorPercent.getText().trim()) / 100));

		//CONSTRUCT message to be displayed
		message += "Total number of client chat requests: " + numClientChatMessages + "\n";
		message += "Total number of client request transmission: " + numClientRequests + "\n";
		message += "Theoretical total number of transmissions: " + theoreticalTransmissions + "\n";
		message += "Total number of chat messages damaged: " + numOfDamages + "\n";
		message += "Maximum number of retransmission for any message: " + maxNumOfRetransmissions;

		//DISPLAY end of transmission data
		JOptionPane.showMessageDialog(null, message, "End of Transmission Data", JOptionPane.INFORMATION_MESSAGE);

		//EXIT program
		System.exit(0);
	}
}
