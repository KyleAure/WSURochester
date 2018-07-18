package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Player state keeps player data and high score list.
 * 
 * @author Kyle Jon Aure
 * @version 1.0
 *
 */
public class PlayerState implements Serializable, Comparable<PlayerState>{
	//Serial ID and File location
	private static final long serialVersionUID = -2910392572128023199L;
	private static final String SAVEFILELOC = "savedata/highScores.ser";
	
	//High score list
	private static ArrayList<PlayerState> highScores;
	
	//Data Members
	private String userName;
	private int score;
	
	/**
	 * New player constructor initializes score of 0
	 * 
	 * @param userName Unique userName for this player
	 */
	public PlayerState(String userName) {
		this.userName = userName;
		highScores.add(this);
		this.setScore(0);
	}
	
	/**
	 * Current player constructor who already has a score.
	 * 
	 * @param userName String name of this user.
	 * @param score Integer score of this user.
	 */
	public PlayerState(String userName, int score) {
		this.userName = userName;
		this.setScore(score);
	}

	/**
	 * Return this player's User Name.
	 * 
	 * @require This player must be initialized and have a User Name set.
	 * @ensure User name will be returned as a string.
	 * 
	 * @return String representation of this player's user name.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set this player's user name
	 * @require This player must be initialized. 
	 * @ensure This player's user name will be set/reset.
	 * @param userName String new user name for this player
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Return this player's score.
	 * @require This player must be initialized and have a score set.
	 * @ensure Score will be returned as an integer.
	 * @return Integer this player's score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Set the score for this player and update this player's position in the high score list.
	 * @require this player must be initialized. 
	 * @ensure this player's score will be updated and their position on the high score
	 * 			list will be changed if necessary.
	 * @param score Integer new score for this player
	 */
	public void setScore(int score) {
		this.score = score;
		
		Collections.sort(highScores, new Comparator<PlayerState>() {
		    @Override
		    public int compare(PlayerState lhs, PlayerState rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inverse for descending
		        return lhs.getScore() > rhs.getScore() ? -1 : (lhs.getScore() < rhs.getScore()) ? 1 : 0;
		    }
		});
		
	}
	
	/**
	 * Get high score list.
	 * @require High score list is static and can be null.  Check that scores exist before using.
	 * @ensure Most current high scores list will be returned and be in order.
	 * @return ArrayList high scores returned.
	 */
	public static ArrayList<PlayerState> getHighScores() {
		return highScores;
	}
	
	/**
	 * Save high scores
	 * @require Saving will override data.  
	 * 			 Before calling this method make sure this is what you want to do.
	 * @ensure Current high score list (static) will be saved in the 
	 * 			 default location and override the last save.
	 */
	public void saveHighScores(){
		try {
			FileOutputStream fos = new FileOutputStream(SAVEFILELOC);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(highScores);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * Load high scores list into memory.
	 * @.require If a local high score list exists it will be overridden. 
	 * @.ensure Most recent high score save file will be grabbed and loaded into memory.
	 * 		    If no file exits fake players/scores will be created and saved.
	 */
	public static void loadHighScores(){
		try {
			FileInputStream fis = new FileInputStream(SAVEFILELOC);
			ObjectInputStream ois = new ObjectInputStream(fis);
			highScores = (ArrayList<PlayerState>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			//Initialize new high scores list
			highScores = new ArrayList<>();
			
			//Create new default file
			highScores.add(new PlayerState("Player1", 100));
			highScores.add(new PlayerState("Player2", 50));
			highScores.add(new PlayerState("Player3", 25));
			highScores.add(new PlayerState("Player4", 10));
			highScores.add(new PlayerState("Player5", 5));
			
			//Save default file
			try {
				FileOutputStream fos = new FileOutputStream(SAVEFILELOC);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(highScores);
				oos.close();
				fos.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1){
				e1.printStackTrace();
			}
		} catch (IOException e){
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Username: " + userName + " Score: " + score;
	}

	@Override
	public int compareTo(PlayerState player) {
		if(this.getScore() == player.getScore()) {
			return 0;
		}else if(this.getScore() > player.getScore()) {
			return 1;
		}else {
			return -1;
		}
	}
}
