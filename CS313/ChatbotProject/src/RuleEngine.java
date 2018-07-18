/**
 * Rule Engine
 * 
 * Contains method to construct a response for the server
 * 
 * @author Kyle Jon Aure
 * @version 1.2
 */
public class RuleEngine {
	/**
	 * Contains 5 rules for constructing a response for the server.
	 * 
	 * @param client ID Number
	 * @param recievedMessage Message received by the Server
	 * @return String message to be sent back to client
	 */
	public static String getResponse(String client, String recievedMessage) {
		//INIT message to be returned
		String messageToSend = "";
		
		//RULES to construct message
		if(recievedMessage.toLowerCase().contains("hi") | recievedMessage.toLowerCase().contains("hello")) {
			messageToSend = "Hi " + client  + "! " + "How are you?";
		}else if(recievedMessage.length() > 30){
			messageToSend = client + " you really like to talk don't you?";
		}else if(recievedMessage.contains("?")) {
			messageToSend = client + ", no one has ever taken an interest in a robot like me before.  Thank you!";
		}else if(recievedMessage.toLowerCase().contains("computer") | recievedMessage.toLowerCase().contains("program")) {
			messageToSend = client + " I am more than just a computer program you know.";
		}else {
			messageToSend = client + " I really cannot understand what you are saying.  Sorry.";
		}
		
		//RETURN message
		return messageToSend;
	}
}
