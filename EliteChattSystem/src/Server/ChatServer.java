package Server;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ChatServer extends AbstractServer {

	public ChatServer(int port) throws IOException {
		super(port);
	}
	
	protected static LinkedList<String> ListNames  = new LinkedList<String>();
	protected static LinkedList<PrintWriter> ListWriters  = new LinkedList<PrintWriter>();
	
    public static void main(String[] args) throws Exception {
    	new ChatServer(9001);
    }
}