package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import GUI.AbstractGUI;

public abstract class AbstractClient {
	
	protected AbstractGUI gui;
	protected Socket socket;
	protected BufferedReader in;
	protected PrintWriter out;
	
	public AbstractClient(String serverAddress, int port) {
		try {
	        socket = new Socket(serverAddress, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public AbstractGUI getGUI() {
    	return gui;
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
