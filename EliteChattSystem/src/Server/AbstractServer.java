package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;

public abstract class AbstractServer {
	
	protected static int PORT;
	
	protected static HashSet<String> names = new HashSet<String>();
    protected static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    protected ServerSocket listener;
   
	public AbstractServer(int port) throws IOException {
		PORT = port;
		
		System.out.println("The chat server is running.");
        listener = new ServerSocket(PORT);
        
        try {
            while (true) {
                new Handler(listener.accept()).start();
            } 
		}finally {
				listener.close();
        }
	}
	
	public static HashSet<String> getNames() {
		return names;
	}

	public static void setNames(HashSet<String> names) {
		ChatServer.names = names;
	}    
}
