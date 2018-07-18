package Link;
/**
 * LINK API provided by instructor
 */
public interface Link {

	// Constructor information
	/*
	 * public Link (int senderPort, int receiverPort) throws Exception;
	 */
	
	// Send message in sendingBuffer of length lengthMessageSent
	public void sendFrame(byte[] sendingBuffer, int lengthMessageSent)
			throws Exception;

	// Receive message and place in receivingBuffer and return its length 
	public int receiveFrame(byte[] receivingBuffer) throws Exception;

	// Close connection
	public void disconnect() throws Exception;

}