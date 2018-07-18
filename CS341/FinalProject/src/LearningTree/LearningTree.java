package LearningTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;


public class LearningTree implements LearningTreeADT, Serializable {
	public static final int ROOTLOCATION = 0;
	private static final long serialVersionUID = -2910392572128023199L;
	private String fileLocation;
	private String subject;
	private HashMap<Integer, LearningTreeNode> tree;

	/**
	 * Constructor for a new Learning Tree
	 * 
	 * @param startQuestion String First question (root).
	 * @param noAns String If user answers no to first question this is the answer they will receive.
	 * @param yesAns String If user answer yes to first question this is the answer they will receive.
	 * @param subject String subject of learning tree.
	 */
	public LearningTree(String startQuestion, String noAns, String yesAns, String subject) {
		//Initialize tree
		tree = new HashMap<>();
		fileLocation = "savegames/" + subject +".ser";
		this.subject = subject;
		
		//Initialize root, leftChild, and rightChild nodes
		LearningTreeNode root = new LearningTreeNode(startQuestion, true);
		LearningTreeNode leftChild = new LearningTreeNode(noAns, false);
		LearningTreeNode rightChild = new LearningTreeNode(yesAns, false);
		
		//Load first three nodes into tree
		tree.put(ROOTLOCATION, root);
		setLeftChild(leftChild);
		setRightChild(rightChild);
	}
	
	/**
	 * Constructor for a Learning Tree that already exists
	 * 
	 * @require file must contained serialized HashMap data.
	 * 
	 * @ensure this learning tree will be loaded exactly the same as it was saved.
	 * 
	 * @param fileLocation absolute location of file to be loaded.
	 */
	public LearningTree(String fileLocation){
		this.fileLocation = fileLocation;
		loadData();
	}

	@Override
	public void addNode(String question, String answer, int location) {
		//Location = the incorrect answer so it will be the leftChild
		//Create a new question node
		//Create a new answer node to be placed to the right
		LearningTreeNode leftChild = getNode(location);
		LearningTreeNode questionNode = new LearningTreeNode(question, true);
		LearningTreeNode rightChild = new LearningTreeNode(answer, false);
		
		//Reconstruct tree
		tree.put(location, questionNode);
		tree.put(getLeftChildKey(location), leftChild);
		tree.put(getRightChildKey(location), rightChild);
	}

	@Override
	public LearningTreeNode getNode(int location) {
		return tree.get(location);
	}

	@Override
	public int setLeftChild(LearningTreeNode leftChild) {
		int location = getLeftChildKey(ROOTLOCATION);
		tree.put(location, leftChild);
		return location;
	}

	@Override
	public int setRightChild(LearningTreeNode rightChild) {
		int location = getRightChildKey(ROOTLOCATION);
		tree.put(location, rightChild);
		return location;
	}
	
	@Override
	public int getLeftChildKey(int location) {
		return 2 * location + 1;
	}
	
	@Override
	public int getRightChildKey(int location) {
		return 2 * location + 2;
	}
	
	@Override
	public String getFileLocation(){
		return fileLocation;
	}
	
	@Override
	public String getSubject() {
		return subject;
	}
	
	@Override
	public void saveData(){
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void loadData(){
		try {
			FileInputStream fis = new FileInputStream(fileLocation);
			ObjectInputStream ois = new ObjectInputStream(fis);
			LearningTree temp = (LearningTree) ois.readObject();
			
			//Override Data
			this.fileLocation = temp.fileLocation;
			this.subject = temp.subject;
			this.tree = temp.tree;
			
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		} catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString(){
		return traverse();
	}
	
	/**
	 * Helper Method: Traverse through tree structure 
	 * 				  and return a string representation 
	 * 			      to be used for debugging and testing.
	 * 
	 * @return String results of traversing through tree
	 */
	private String traverse(){
		Set<Integer> keySet = tree.keySet();
		if(keySet.isEmpty()){
			return "Tree Empty";
		}
		
		String result = "";
		int numKeys = keySet.size();
		
		for(int i=0; numKeys > 0; i++){
			if(tree.get(i) != null){
				numKeys--;
				result += i + ": " + tree.get(i).toString() + "\n";
			}
		}
		return result;
	}
}
