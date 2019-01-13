package Client;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

import javax.swing.JOptionPane;

import GUI.DisplayGifGUI;
import GUI.GUI;
import GUI.GruppChattGUI;

public class ChatClient {

	
	  private Socket socket;
	//The Clients reader, writer and interface(GUI) 
    private BufferedReader in;
    private PrintWriter out;
    private GUI gui;

    public ChatClient() {
    	gui = new GUI(this, "GLOBALCHAT");
    	
    	try {
			run();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
   
    }
    //When you start the program a JOptionPane will appear on the screen where you have to enter your IP
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
        	gui.getFrame(),
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    //Same as the window before except now you have to enter your nickname for the chat 
    private String getName() {
        return JOptionPane.showInputDialog(
            gui.getFrame(),
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    //Connection to the server after entering IP and name; 
    private void run() throws IOException {
        String serverAddress = getServerAddress();
        socket = new Socket(serverAddress, 9001);
        
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            System.out.println(line);
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                gui.getTextField().setEditable(true);
            } else if (line.startsWith("GLOBALMESSAGE")) {
            	gui.getMessageArea().setForeground(Color.BLACK);
            	String text = (line.substring(14) + "\n");
                gui.getMessageArea().append(text);
            }else if (line.startsWith("PRIVATEMESSAGE")) {
            	String text = (line.substring(15) + "\n");
                gui.getMessageArea().append(text);
            } else if (line.startsWith("NEWLOGIN")) {

            	gui.getFriendList().addUserToList(line.substring(9));
            } else if (line.startsWith("LOGOUT")) {
            	gui.getFriendList().removeUserFromList(line.substring(7));
            } else if (line.startsWith("GROUPINVITE")) {
            	GruppChattGUI gc = new GruppChattGUI(this, "GROUPCHAT");
            }else if (line.startsWith("GIF")) {
            	new DisplayGifGUI(new URL(line.substring(3)), "FunnyGifs", gui);

            }
            
        }
    }
    //Main everytime you start run it a new clint will be created. 
    public static void main(String[] args) throws Exception {
    	new ChatClient();
    }
    public Socket getSocket() {
    	return socket;
    }
    //Getter and setter for the printWriter
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}