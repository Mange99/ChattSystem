package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;

public abstract class AbstractServer implements Runnable {
	
	protected static int PORT;
	protected static HashSet<String> names = new HashSet<String>();
    protected static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    protected ServerSocket listener;
    protected Thread thread;
    
	public AbstractServer(int port) throws IOException {
		PORT = port;
        listener = new ServerSocket(PORT);
        thread = new Thread(this);
        thread.start();
	}
	
	public void run() {
		 try {
	            while (true) {
	            	System.out.println("The chat server is running.");
	                new Handler(listener.accept()).start();
	            } 
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
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
}
