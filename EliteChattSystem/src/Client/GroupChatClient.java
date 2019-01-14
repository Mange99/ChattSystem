package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import GUI.GroupChatGUI;

public class GroupChatClient extends AbstractClient{
	
	public GroupChatClient(String serverAdress, int port) {
		super(serverAdress, port);
		
		new GroupChatGUI(this, "GROUPCHAT");
	}
	
	public void connectToGroupChat(String ip, int port) {
        try {
			socket = new Socket(ip, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        out = new PrintWriter(socket.getOutputStream(), true);
	        
	        while (true) {
	            String line = in.readLine();
	            System.out.println(line);
	            ChatCommands.inputCommands(out, line, this);
	            
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
