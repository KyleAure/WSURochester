package com.wsu.kyleaure.LearningTree;


/**
 * Interface to define available methods, how they should be implemented, 
 * how each method should be used.
 * 
 * @author Kyle Jon Aure
 * @version 0.1
 */
public interface LearningTreeADT{
	
	/**
	 * Use Case: Add a node when the tree has reached a point where it has guessed
	 *           an answer but that answer in marked incorrect.
	 * 
	 * @require use addNode only when you have reached an answerNode (leaf).
	 * 
	 * @ensure previous answer Node will be copied, question node will be inserted at this location
	 *         left child will be set to correct answer, and right child will be set in incorrect answer.
	 * 
	 * @param question Yes or No question that will help the tree distinguish between 
	 * 		  the answer it guessed and the true answer.
	 * 
	 * @param answer True answer that will be added to the tree on the 'yes'
	 *        branch of the new question.
	 *        
	 * @param location where failed answer can be found.
	 */
	public void addNode(String question, String answer, int location);
	
	/**
	 * General Method: Returns node at a specified location.
	 * 
	 * @require Node must exist at the location you specify.
	 * 
	 * @ensure If a node exits at that location it will be returned,
	 * 		   otherwise null will be returned if no node exists.
	 * 
	 * @param location where node exists.  Root = 0;
	 * 
	 * @return LearningTreeNode object is returned.
	 */
	public LearningTreeNode getNode(int location);
	
	/**
	 * Use Case: When first creating a Learning Tree the left
	 * 			 child of the root (first question) must be set.
	 * 
	 * @require leftChild node must be the (NO) answer to root, and this
	 * 			method should not be used once tree has been created.
	 * 
	 * @ensure First time playing this answer will be used for further learning
	 * 
	 * @param leftChild answer node.
	 * 
	 * @return int location of left child.
	 */
	public int setLeftChild(LearningTreeNode leftChild);
	
	/**
	 * Use Case: When first creating a Learning Tree the right
	 * 			 child of the root (first question) must be set.
	 * 
	 * @require rightChild node must be the (YES) answer to root, and this
	 * 			method should not be used once tree has been created.
	 * 
	 * @ensure First time playing this answer will be used for further learning
	 * 
	 * @param rightChild answer node.
	 * 
	 * @return int location of left child.
	 */
	public int setRightChild(LearningTreeNode rightChild);
	
	/**
	 * General Method: Returns location of left child based on 
	 * 				   location of parent.
	 * 
	 * @require parent location must be non-negative
	 * 
	 * @ensure left child location will be returned
	 * 
	 * @param location integer location of parent (p)
	 * 
	 * @return Integer location of left child (lc)
	 * 		   lc = 2p+1
	 */
	public int getLeftChildKey(int location);
	
	/**
	 * General Method: Returns location of right child based on 
	 * 				   location of parent.
	 * 
	 * @require parent location must be non-negative
	 * 
	 * @ensure right child location will be returned
	 * 
	 * @param location integer location of parent (p)
	 * 
	 * @return Integer location of right child (rc)
	 * 		   rc = 2p+2
	 */
	public int getRightChildKey(int location);
	
	/**
	 * Gets file location this Learning Tree
	 * 
	 * @require File location must be set prior to using this method.
	 * 			File location is set by using the saveData method.
	 * 
	 * @ensure File location will be returned and the file will exist at that location.
	 * 
	 * @return String absolute file location.
	 */
	public String getFileLocation();
	
	/**
	 * Gets this learning tree's subject.
	 * 
	 * @require This learning tree must be initialized and contain a subject.
	 * 
	 * @ensure The subject for this learning tree will be returned.
	 * 
	 * @return String subject for this learning tree.
	 */
	public String getSubject();
	
	/**
	 * Use Case: Save data of a newly created LearningTree
	 * 
	 * @require This learning tree must be initialized and contain a fileLocation.
	 * 
	 * @ensure File will be saved in a serialized format to be loaded lated.
	 *          If a file with the same name already exists it will be overriden.
	 */
	public void saveData();
	
	/**
	 * Use Case: Load data file into this LearningTree.  
	 * 
	 * @require Make sure the user wants to perform this action as
	 * 			 any unsaved data will be lost.
	 * 
	 * @ensure All previous unsaved data will be lost and new data will be loaded into this tree.
	 */
	public void loadData();
	


}
