package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.LinkedList;
/*
 * SKIT I DENNA KLASS SÅ LÄNGE ALLT SOM BEHÖVER GÖRAS I SERVER GÖRS DIREKT I CHATTSERVER
 * 
 * public abstract class AbstractServer implements Runnable {
	
	protected static int PORT;
	protected ServerSocket listener;
    protected Thread thread;
    
	protected static HashSet<String> names = new HashSet<String>();
    protected static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    
    //Lists to keep track of which user has what PrintWriter
  	protected static LinkedList<String> ListNames  = new LinkedList<String>();
  	protected static LinkedList<PrintWriter> ListWriters  = new LinkedList<PrintWriter>();
   
    
	public AbstractServer(int port) throws IOException {
		PORT = port;
        listener = new ServerSocket(PORT);
        thread = new Thread(this);
        thread.start();
	}
	
	public void run() {
		System.out.println("The chat server is running.");
		 try {
	            while (true) {
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
		AbstractServer.names = names;
	}    
}
*/
