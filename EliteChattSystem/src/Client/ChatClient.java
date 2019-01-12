package Client;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import GUI.GUI;

public class ChatClient {
	//Clientens Reader Skrivare JFrame Textfield o TextArea 
    private BufferedReader in;
    private PrintWriter out;
    private GUI gui;
//    Magnus e king superior officer according to himself
    public ChatClient() {
    	gui = new GUI(this);
    	
    	try {
			run();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
   
    }
    //N�r man startar programmet kmr en JOptionPane ruta d�r man skriver in IP address aka lokal aka 127.0.0.1
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
        	gui.getFrame(),
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
    }

    //Samma ruta som innan fast man ska skriva in vad man nickkar in-game 
    private String getName() {
        return JOptionPane.showInputDialog(
            gui.getFrame(),
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
            	String text = ("<html><font color='blue'>" + line.substring(15) + "</font></html> \n");
                gui.getMessageArea().append(text);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
    }
	public PrintWriter getOut() {
		return out;
	}
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}