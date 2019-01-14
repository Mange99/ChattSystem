package Client;
import java.io.IOException;

import javax.swing.JOptionPane;

import GUI.GUI;

public class ChatClient extends AbstractClient {

    public ChatClient(String serverAdress, int port) {
    	super(serverAdress, port);
    	gui = new GUI(this, "GLOBALCHAT");
    	
    	try {
			run();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
   
    }
    //When you start the program a JOptionPane will appear on the screen where you have to enter your IP
    public static String getServerAddress() {
        return JOptionPane.showInputDialog(
        	null,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    //Same as the window before except now you have to enter your nickname for the chat 
    public String getName() {
        return JOptionPane.showInputDialog(
            gui.getFrame(),
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    //Connection to the server after entering IP and name; 
    private void run() throws IOException {
        
        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            System.out.println("line : " + line);
            ChatCommands.inputCommands(out, line, this);
            
        }
    }
    //Main everytime you start run it a new client will be created. 
    public static void main(String[] args) throws Exception {
    	String serverAdress = getServerAddress();
    	if (serverAdress != null) {
    		new ChatClient(serverAdress, 9005);
    	}
    }
}