package LearningTree;

import java.io.Serializable;

/**
 * Defines the data saved in a node object
 * 
 * @author Kyle Jon Aure
 * @version 1.0
 */
public class LearningTreeNode implements Serializable {
	private static final long serialVersionUID = -9106333754002972876L;
	private String statement;
	private boolean question;
	
	/**
	 * Constructor for a Learning Tree Node that will hold a statement which can either
	 * be a question or an answer. 
	 * 
	 * Statement should not include punctuation so:
	 * When the statement is a question let it be in the form: "Is this a question"
	 * When the statement is an answer let it be in the form: "answer"
	 * 
	 * @require Make sure statement is formatted as specified above. 
	 * 
	 * @ensure Node will be initialized with all params initialize and
	 * 		    toString() method will take care of question and answer grammar/punctuation
	 * 
	 * @param statement string to be kept in node
	 * @param question boolean to determine if statement is a question or an answer
	 * 
	 */
	public LearningTreeNode(String statement, boolean question) {		
		this.statement = statement;
		this.question = question;
	}

	/**
	 * Return statement without toString() formatting
	 * 
	 * @require This node must be initialized and contain a statement.
	 * @ensure Statement will be returned without any of the toString() formatting.
	 * 
	 * @return string statement
	 */
	public String getStatement() {
		return statement;
	}

	/**
	 * Replace current statement with a new statement.
	 * 
	 * @require Statement must follow the formatting restrictions from constructor.
	 * @ensure Statement will be reset.
	 * 
	 * @param statement without formatting or punctuation.
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}

	/**
	 * Determine if this node's statement is a question or answer
	 * 
	 * @require This node must be in initialized and contain a question status.
	 * @ensure Question status will be returned.
	 * 
	 * @return boolean True = question, False = answer
	 */
	public boolean isQuestion() {
		return question;
	}

	@Override
	public String toString() {
		String result = "";
		if(question) {
			result = getStatement() + "?";
		}else {
			result = "Are you thinking of (a) " + getStatement() + "?";
		}
		return result;
	}
}
