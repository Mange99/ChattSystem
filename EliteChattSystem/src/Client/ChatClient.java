
package Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import GUI.GUI;

public class ChatClient {
	//Clientens Reader Skrivare JFrame Textfield o TextArea 
    private BufferedReader in;
    private PrintWriter out;
    private GUI gui;
    
    public ChatClient() {
    	gui = new GUI();
    	
    	  gui.getTextField().addActionListener(new ActionListener() {
  	    	public void actionPerformed(ActionEvent e) {
  	           	if(!gui.getTextField().getText().equalsIgnoreCase("")) {
  	           		out.println(gui.getTextField().getText());
  	           		gui.getTextField().setText("");
  	           	}
  	           }
  	        });
    }
    //N�r man startar programmet kmr en JOptionPane ruta d�r man skriver in IP address aka lokal aka 127.0.0.1
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
        	new JFrame(),
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    //Samma ruta som innan fast man ska skriva in vad man nickkar in-game 
    private String getName() {
        return JOptionPane.showInputDialog(
            new JFrame(),
            "Choose a screen name:",
            "Screen name selection",
            JOptionPane.PLAIN_MESSAGE);
    }
    //Connectar till servern efter man  skrivit in IP och namn 
    private void run() throws IOException {
        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server, according to the protocol.
        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                gui.getTextField().setEditable(true);          
            } else if (line.startsWith("MESSAGE")) {
                gui.getMessageArea().append(line.substring(8) + "\n");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.run();
    }
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}