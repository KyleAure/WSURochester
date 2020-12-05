package com.wsu.kyleaure;

/**
 * Launches both Client and Server JFrame Instances
 * 
 * @author Kyle Jon Aure
 * @version 1.2
 */
public class Main {
	public static void main(String[] args) {
		Client client = new Client();
		Server server = new Server();
		
		client.setVisible(true);
		client.setLocation(0, 0);
		server.setVisible(true);
		server.setLocation(550, 0);
	}
}
