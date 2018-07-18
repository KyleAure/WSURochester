import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import CRC8.CRC8;
import Link.Link;
import Link.SimpleLink;

/**
 * Client GUI - Runs all client activities
 * 
 * @author Kyle Jon Aure
 * @version 1.2
 */
@SuppressWarnings("serial")
public class Server extends JFrame {
	// CONSTANTS for port information
	private static final int senderPort = 3200;
	private static final int receiverPort = 3300;
	// VARIABLES for link
	private static Link myLink;
	private static boolean trace;
	private static String messageToSend;
	private static byte[] sendingBuffer = new byte[512];
	private static byte[] receivingBuffer = new byte[45];
	private static CRC8 crc8 = new CRC8();
	// VARIABLES for GUI
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static JTextArea resultArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Server frame = new Server();
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
	public Server() {
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
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));
		// ### TITLE ###
		JLabel lblServer = new JLabel("Server");
		lblServer.setFont(new Font("Avenir", Font.BOLD, 24));
		contentPane.add(lblServer, "2, 2, 7, 1, center, center");

		// ### TRACE ###
		JRadioButton rdbtnTraceOn = new JRadioButton("Trace On");
		buttonGroup.add(rdbtnTraceOn);
		contentPane.add(rdbtnTraceOn, "4, 4");
		JRadioButton rdbtnTraceOff = new JRadioButton("Trace Off");
		buttonGroup.add(rdbtnTraceOff);
		contentPane.add(rdbtnTraceOff, "6, 4");

		// ### BUTTON: SET SERVER ###
		JButton btnSetServer = new JButton("Set Server");
		btnSetServer.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// TRY set up link THROWS: exception
					myLink = new SimpleLink(receiverPort, senderPort);

					// GET trace option
					if (rdbtnTraceOn.isSelected()) {
						trace = true;
					} else if (rdbtnTraceOff.isSelected()) {
						trace = false;
					}

					// DISABLE buttons we no longer need
					rdbtnTraceOff.setEnabled(false);
					rdbtnTraceOn.setEnabled(false);
					btnSetServer.setEnabled(false);

					// CLEAR result area
					resultArea.setText("");

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Communication Error!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		contentPane.add(btnSetServer, "8, 4");

		// ### RESULT AREA ###
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "4, 6, 3, 3, fill, fill");
		resultArea = new JTextArea();
		scrollPane.setViewportView(resultArea);
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		resultArea.setText("Choose trace option and \nset server to start communication.");

		// ### BUTTON: CLEAR ###
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			@Override
			public void actionPerformed(ActionEvent e) {
				resultArea.setText("");
			}
		});
		contentPane.add(btnClear, "8, 10");
	}

	/**
	 * Wakes up server to receive an incoming message. Test message to see if
	 * there was a transmission error If so send back NAK else send back canned
	 * response.
	 */
	public static void wakeServer() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// GET received message THORWS: Exception
					myLink.receiveFrame(receivingBuffer);

					// INIT parts of message
					String clientID = "";
					int payloadLength;
					String payload = "";
					int checkSum;

					// GET client ID
					for (int i = 0; i < 3; i++) {
						clientID += (char) receivingBuffer[i];
					}

					// GET length
					payloadLength = receivingBuffer[3];

					// GET payload
					for (int i = 4; i < payloadLength + 4; i++) {
						payload += (char) receivingBuffer[i];
					}

					// GET Checksum
					checkSum = receivingBuffer[44];

					// DISPLAY trace data
					if (trace) {
						resultArea.append(
								"Sender: " + clientID + " Length: " + payloadLength + " Checksum: " + checkSum + "\n");
					}

					// TEST check sum
					int checkSumCalc = crc8.checksum(payload.getBytes());

					// NO ERROR
					if (checkSumCalc == checkSum) {
						// DISPLAY received message (payload)
						resultArea.append(": " + payload + "\n");

						// CHECK for termination
						if (payload.toLowerCase().contains("bye")) {
							endTransmission(clientID);
							
						} else {
							// GET response FROM rules engine
							messageToSend = RuleEngine.getResponse(clientID, payload);

							// CONSTRUCT sending buffer
							sendingBuffer = messageToSend.getBytes();

							try {
								// TRY send response THROWS: Exception
								myLink.sendFrame(sendingBuffer, sendingBuffer.length);

								// WAKE Client
								Client.wakeClient();

								// DISPLAY sent message
								resultArea.append("> " + messageToSend + " \n");

							} catch (Exception e) {
								System.out.println("Error sending buffer or waking the server.");
							}
						}
						// WITH ERROR
					} else {
						// DISPLAY error
						if (trace) {
							resultArea.append("Error Recieved: " + payload + "\n" + "Ask for retransmission \n");
						}

						// SEND NAK
						sendNAK();
					}
				} catch (Exception e) {
					System.out.println("Recieved Message Error.");
					System.out.println(e.toString());
				}

			}
		}).start();
	}

	/**
	 * Wakes up client to receive NAK message.
	 */
	private static void sendNAK() {
		// DEFINE message to send
		messageToSend = "1";

		// CONSTRUCT sending buffer
		sendingBuffer = messageToSend.getBytes();

		try {
			// TRY to send NAK THROWS: Exception
			myLink.sendFrame(sendingBuffer, sendingBuffer.length);

			// WAKE client
			Client.wakeClient();

			// DISPLAY NAK
			if (trace) {
				resultArea.append("> NAK \n");
			}
		} catch (Exception e) {
			System.out.println("Error sending buffer or waking the server.");
		}
	}

	/**
	 * Wakes client to send goodbye message.
	 * 
	 * @param client
	 *            Clients name.
	 */
	private static void endTransmission(String client) {
		//CONSTRUCT message to send
		messageToSend = "Goodbye " + client + " !";
		
		//CONSTRUCT sending buffer
		sendingBuffer = messageToSend.getBytes();

		try {
			//TRY send message  THROWS: Exception
			myLink.sendFrame(sendingBuffer, sendingBuffer.length);
			
			//WAKE client
			Client.wakeClient();

			//DISPLAY sent message
			resultArea.append("> " + messageToSend + " \n");
		
		} catch (Exception e) {
			System.out.println("Error sending buffer or waking the server.");
		}

		try {
			//TRY to disconnect  THROWS: Exception
			myLink.disconnect();
			
			//DISPLAY termination notice
			resultArea.append("Connection Terminated.\n");
		
		} catch (Exception e) {
			System.out.println("Error when trying to disconnect");
		}
	}
}
