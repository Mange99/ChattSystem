package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.LinkedList;

public class ChatServer extends Thread implements Runnable {

	protected static int PORT;
	protected ServerSocket listener;
	protected Thread thread;

	protected static HashSet<String> names = new HashSet<String>();
	protected static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

	// Lists to keep track of which user has what PrintWriter
	protected static LinkedList<String> ListNames = new LinkedList<String>();
	protected static LinkedList<PrintWriter> ListWriters = new LinkedList<PrintWriter>();

	// Initialize serverSocket and start the server thread
	public ChatServer(int port) throws IOException {
		PORT = port;
		listener = new ServerSocket(PORT);
		this.start();
	}

	public void run() {
		System.out.println("The chat server is running. " + Thread.currentThread());
		try {
       try {
			new Database();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
			// Creating a Handler and starting a new Thread for each client connecting
			while (true) {
				new Handler(listener.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				listener.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static HashSet<String> getNames() {
		return names;
	}

	public static void setNames(HashSet<String> names) {
		ChatServer.names = names;
	}

	// Lists to keep track on total clients and who is logged in
	protected static LinkedList<String> clientList = new LinkedList<String>();
	protected static LinkedList<String> utloggadeClients = new LinkedList<String>();

	protected static LinkedList<String> globalMessages = new LinkedList<String>();

	// Main method to start server first makes a socket then it trys to run the
	// handlers thread.
	public static void main(String[] args) throws Exception {
		new ChatServer(9001);
	}
}