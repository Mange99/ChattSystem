package GroupChat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.LinkedList;

public class GroupServer extends Thread implements Runnable {
	
//	private Thread groupServerThread;
	private static int PORT;
	private ServerSocket listener;
    
	protected static HashSet<String> names = new HashSet<String>();
    protected static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    
    //Lists to keep track of which user has what PrintWriter
  	protected static LinkedList<String> ListNames  = new LinkedList<String>();
  	protected static LinkedList<PrintWriter> ListWriters  = new LinkedList<PrintWriter>();
   
  	public GroupServer(int port) throws IOException {
  		PORT = port;
        listener = new ServerSocket(PORT);
        this.start();
	}
	
	public void run() {
		System.out.println("The chat server is running. " + Thread.currentThread());
		new GroupChatClient("localhost", PORT);
		 try {
	            while (true) {
	            	System.out.println("run " + Thread.currentThread());
	                new GroupHandler(listener.accept()).start();
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
		GroupServer.names = names;
	}    
	
}
