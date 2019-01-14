package Client;

import java.io.IOException;

import Server.AbstractServer;

public class GroupServer extends AbstractServer {
	
	private Thread groupServerThread;
	
	public GroupServer(int port) throws IOException {
		super(port);
		System.out.println("groupserver con");
		groupServerThread = new Thread(this);
		groupServerThread.start();
		System.out.println("efter gruppserver");
		new GroupChatClient("localhost", port);
	}
}
