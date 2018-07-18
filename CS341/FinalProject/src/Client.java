import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import LearningTree.LearningTree;
import Utilities.PlayerState;
import Utilities.Utilities;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 * Client window which integrates both player and admin functions. Calls upon
 * New Game and New Question windows when more data is necessary.
 * 
 * @author Kyle Jon Aure
 * @version 1.0
 *
 */
public class Client extends JFrame {
	// Serial ID
	private static final long serialVersionUID = 5097179259978586652L;

	// Frame Data Members
	private JPanel contentPane;
	private JTextField playerName;
	private JButton btnYes;
	private JButton btnNo;
	private JButton btnSaveGame;
	private JButton btnNewGame;
	private static JTextArea gameArea;
	private static JTextField subject;
	private static JTextArea gameStatus;

	// High Scores
	private static JList<PlayerState> listHighScores;
	private static DefaultListModel<PlayerState> modelHighScores = new DefaultListModel<>();
	private static PlayerState player;

	// Learning Tree
	private static LearningTree tree;
	private static int location = LearningTree.ROOTLOCATION;

	// User Type
	private static boolean admin;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 *            String[] of arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Create Frame
					Client frame = new Client();

					// Make Frame Visible
					frame.setVisible(true);

					// Load High Scores
					PlayerState.loadHighScores();
					updateHighScores(false);

					// Administrative control
					admin = false;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Learning Twenty Questions");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Client.class.getResource("/assets/favicon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.BUTTON_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.BUTTON_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.BUTTON_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.BUTTON_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						FormSpecs.BUTTON_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.BUTTON_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.PREF_ROWSPEC, }));

		// ### Title ###
		JLabel lblLearningTree = new JLabel("Learning Twenty Questions");
		lblLearningTree.setIcon(new ImageIcon(Client.class.getResource("/assets/favicon.png")));
		lblLearningTree.setForeground(Color.WHITE);
		lblLearningTree.setFont(new Font("Avenir", Font.BOLD, 26));
		contentPane.add(lblLearningTree, "2, 2, 13, 1, center, center");
		JSeparator separator1 = new JSeparator();
		contentPane.add(separator1, "2, 4, 13, 1");

		// ### FIELD: Subject ###
		JLabel lblSubject = new JLabel("Subject:");
		contentPane.add(lblSubject, "2, 6, right, default");
		subject = new JTextField();
		contentPane.add(subject, "4, 6, 7, 1, fill, default");
		subject.setColumns(10);

		// ### BUTTON: New Game ###
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				// Check subject and playerName fields are filled out and start new session.
				if (subject.getText().isEmpty() || playerName.getText().isEmpty()) {
					String incomplete = "Please enter a subject and player name.";
					JOptionPane.showMessageDialog(null, incomplete);
				} else {
					// Get new tree data
					NewGame newGameSession = new NewGame();
					newGameSession.setVisible(true);
					// newGameSession will call startNewGame

					// Create new high score entry
					if (!admin) {
						player = new PlayerState(playerName.getText().trim());
						updateHighScores(true);
					}

					// Disable things
					btnNewGame.setEnabled(false);
					subject.setEnabled(false);
					playerName.setEnabled(false);

					// Enable things
					btnYes.setEnabled(true);
					btnNo.setEnabled(true);
					btnSaveGame.setEnabled(true);
				} // END if/else
			}// END button action
		});// END button definition
		contentPane.add(btnNewGame, "12, 6, 3, 1");

		// ### FIELD: Player Name ###
		JLabel lblPlayerName = new JLabel("Player Name:");
		contentPane.add(lblPlayerName, "2, 8, right, default");
		playerName = new JTextField();
		contentPane.add(playerName, "4, 8, 7, 1, fill, default");
		playerName.setColumns(10);

		// ### BUTTON: Load Game ###
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				// Check player name has been entered.
				if (playerName.getText().isEmpty()) {
					String incomplete = "Please enter a player name.";
					JOptionPane.showMessageDialog(null, incomplete);
				} else {
					// Caution user of override
					String caution = "Loading will override any progress you have made.\nDo you wish to continue?";
					int option = JOptionPane.showConfirmDialog(null, caution);

					// Create new tree from loaded data
					if (option == JOptionPane.YES_OPTION) {
						// Override tree
						tree = new LearningTree(Utilities.fileChooser(Utilities.FILE_TYPE));
						subject.setText(tree.getSubject());

						// Create new high score entry
						if (!admin) {
							player = new PlayerState(playerName.getText().trim());
							updateHighScores(true);
						}

						// Disable new game
						btnNewGame.setEnabled(false);
						subject.setEnabled(false);
						playerName.setEnabled(false);

						// Enable things
						btnYes.setEnabled(true);
						btnNo.setEnabled(true);
						btnSaveGame.setEnabled(true);

						// Start game
						gameStatus.append("Game loaded from: " + tree.getFileLocation() + "\n");
						gameArea.append("\n" + tree.getNode(location).toString());
					}
				}
			}
		});
		contentPane.add(btnLoadGame, "12, 8, 3, 1");
		JSeparator separator2 = new JSeparator();
		contentPane.add(separator2, "2, 10, 13, 1");

		// ### OUTPUT: Game Area ###
		JLabel lblGameArea = new JLabel("Game Area");
		contentPane.add(lblGameArea, "2, 12, 9, 1, center, default");
		JScrollPane scrollGameArea = new JScrollPane();
		contentPane.add(scrollGameArea, "2, 14, 9, 1, fill, fill");
		gameArea = new JTextArea();
		scrollGameArea.setViewportView(gameArea);
		gameArea.setText("Please start or load a game to get started.");

		// ### OUTPUT: High Scores ###
		JLabel lblHighScores = new JLabel("High Scores");
		contentPane.add(lblHighScores, "12, 12, 3, 1, center, default");
		JScrollPane scrollHighScores = new JScrollPane();
		contentPane.add(scrollHighScores, "12, 14, 3, 11, fill, fill");
		listHighScores = new JList<PlayerState>();
		scrollHighScores.setViewportView(listHighScores);
		listHighScores.setModel(modelHighScores);
		JSeparator separator3 = new JSeparator();
		contentPane.add(separator3, "2, 16, 9, 1");

		// ### BUTTON: Yes ###
		btnYes = new JButton("YES");
		btnYes.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnYes.setForeground(Color.BLACK);
		btnYes.setEnabled(false);
		btnYes.setBackground(UIManager.getColor("Button.background"));
		btnYes.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				gameArea.append(" Yes");

				// User was asked a Question
				if (tree.getNode(location).isQuestion()) {
					location = tree.getLeftChildKey(location);
					gameArea.append("\n" + tree.getNode(location).toString());

					// User was asked an Answer
				} else {
					gameArea.setText("");
					gameArea.append("I win! Let's try this again!");
					location = LearningTree.ROOTLOCATION;
					gameArea.append("\n" + tree.getNode(location).toString());
				}
			}
		});
		contentPane.add(btnYes, "2, 18, 3, 1");

		// ### BUTTON: No ###
		btnNo = new JButton("NO");
		btnNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNo.setForeground(Color.BLACK);
		btnNo.setBackground(UIManager.getColor("Button.background"));
		btnNo.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				gameArea.append(" No");

				// User was asked a Question
				if (tree.getNode(location).isQuestion()) {
					location = tree.getRightChildKey(location);
					gameArea.append("\n" + tree.getNode(location).toString());

					// user was asked an Answer
				} else {
					if (!admin) {
						gameArea.setText("You win! Here have 5 points!\n");
						player.setScore(player.getScore() + 5);
						updateHighScores(true);

						// restart
						location = LearningTree.ROOTLOCATION;
						gameArea.append(tree.getNode(location).toString());
					}

					if (admin) {
						NewQuestion newQuestion = new NewQuestion();
						newQuestion.setVisible(true);
					}
				}
			}
		});
		btnNo.setEnabled(false);
		contentPane.add(btnNo, "8, 18, 3, 1");
		JSeparator separator4 = new JSeparator();
		contentPane.add(separator4, "2, 20, 9, 1");

		// ### OUTPUT: Game Status ###
		JLabel lblGameStatus = new JLabel("Game Status");
		contentPane.add(lblGameStatus, "2, 22, 9, 1, center, center");
		gameStatus = new JTextArea();
		gameStatus.setRows(2);
		gameStatus.setEnabled(false);
		gameStatus.setEditable(false);
		contentPane.add(gameStatus, "2, 24, 9, 1, fill, fill");

		// ### BUTTON: Save Game ###
		btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				tree.saveData();
				gameStatus.append("Game saved: " + tree.getFileLocation() + "\n");
			}
		});

		JSeparator separator5 = new JSeparator();
		contentPane.add(separator5, "2, 26, 13, 1");
		btnSaveGame.setEnabled(false);
		contentPane.add(btnSaveGame, "2, 28, 3, 1");

		// ### BUTTON: Quit ###
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				String quitMessage = "Are you sure you want to exit? \nAny unsaved data will be lost.";
				int result = JOptionPane.showConfirmDialog(null, quitMessage);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		contentPane.add(btnQuit, "8, 28, 3, 1");

		// ### BUTTON: Admin ###
		JButton btnAdmin = new JButton("Admin Toggle");
		btnAdmin.addActionListener(new ActionListener() {
			// ### BUTTON: ACTION ###
			public void actionPerformed(ActionEvent e) {
				if (admin) {
					admin = false;
					gameStatus.append("Admin control turned off.\n");
				} else {
					String message = "Enter administrator password";
					String title = "Administrator Password Verification";

					JPanel panel = new JPanel();
					JLabel label = new JLabel(message);
					JPasswordField pass = new JPasswordField(10);
					panel.add(label);
					panel.add(pass);
					String[] options = new String[] { "OK", "Cancel" };
					int option = JOptionPane.showOptionDialog(null, panel, title, JOptionPane.NO_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

					if (option == 0) { // pressing OK button
						char[] password = pass.getPassword();
						String passwordStr = new String(password);
						if (passwordStr.compareTo("admin") == 0) {
							admin = true;
							gameStatus.append("Admin password entered successfully.\n");

							// restart
							location = LearningTree.ROOTLOCATION;
							gameArea.setText("");
							gameArea.append(tree.getNode(location).toString());
						} else {
							gameStatus.append("Admin password incorrect.  Please try again.\n");
						}
					}
				}
			}
		});
		contentPane.add(btnAdmin, "12, 28, 3, 1");
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { subject, playerName, btnNewGame, btnLoadGame, btnYes, btnNo, btnSaveGame, btnQuit }));
	}// END CONSTRUCTOR

	/**
	 * Get user defined subject. Used by support window (NewGame)
	 * 
	 * @require Subject text field data is returned, could be empty.
	 * @ensure String representation that has been trimmed will be returned.
	 * 
	 * @return String trimmed subject.
	 */
	public static String getSubject() {
		return subject.getText().trim();
	}

	/**
	 * Returns statement at current location. Used to get previous answer when
	 * adding a new question.
	 * 
	 * @require Tree must be initialized and contain a location.
	 * @ensure Tree's subject will be returned.
	 * 
	 * @return String statement only
	 */
	public static String getStatement() {
		return tree.getNode(location).getStatement();
	}

	/**
	 * Will create new tree, save it, and start game. Used by support window
	 * (NewGame).
	 * 
	 * @require Tree constructor is used. Ensure params are formatted accordingly.
	 * @ensure New tree will be created, saved, and the game started.
	 * 
	 * @param newQuestion
	 *            String new root question.
	 * @param newYesAns
	 *            String new yes answer from root.
	 * @param newNoAns
	 *            String new no answer from root.
	 */
	public static void startNewGame(String newQuestion, String newYesAns, String newNoAns) {
		// Create and save new tree
		tree = new LearningTree(newQuestion, newNoAns, newYesAns, subject.getText().trim());
		gameStatus.append("New game saved: " + tree.getFileLocation() + "\n");
		tree.saveData();

		// Restart
		gameArea.setText("");
		location = LearningTree.ROOTLOCATION;

		if (admin) {
			gameArea.append(tree.getNode(location).toString());
		} else {
			gameArea.append("New game created and saved.\n"
					+ "Please enter admin state to teach me how to play your new subject.");
		}
	}

	/**
	 * ADMIN FUNCTION ONLY Adds node to tree and restarts game. Used by support
	 * window (NewQuestion).
	 * 
	 * @require Only admin should be able to access this function as new data is
	 *          added to tree.
	 * @ensure New question is added to tree with a new answer.
	 * 
	 * @param newQuestion
	 *            String new question to be added to learning tree
	 * @param newAns
	 *            String new answer to be added to learning tree
	 */
	public static void addNewQuestion(String newQuestion, String newAns) {
		// Add node
		tree.addNode(newQuestion, newAns, location);

		// Restart
		gameArea.setText("");
		location = LearningTree.ROOTLOCATION;
		gameArea.append(tree.getNode(location).toString());
	}

	/**
	 * PRIVATE METHOD Updates HighScores GUI List.
	 * 
	 * @.require high score model and list but be initialized.
	 * @.ensure high score model will be updated and displayed on list.
	 * 
	 * @param save
	 *            boolean True = Save player score first False = Do not save first
	 */
	private static void updateHighScores(boolean save) {
		// First save current high score list
		if (save) {
			player.saveHighScores();
		}

		// Clear old model
		modelHighScores.removeAllElements();

		// Update model and set
		for (PlayerState player : PlayerState.getHighScores()) {
			modelHighScores.addElement(player);
			listHighScores.setModel(modelHighScores);
		}
	}

}
