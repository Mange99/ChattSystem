package Client;

import java.io.IOException;

import Server.AbstractServer;

public class GroupServer extends AbstractServer {
	
	public GroupServer(int port) throws IOException {
		super(port);
		
		new GroupChatClient("localhost", port);
	}
}
