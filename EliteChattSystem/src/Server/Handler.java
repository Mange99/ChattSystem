package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Handler extends Thread {
	
	private String name;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String login;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// Input and Output
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				out.println("LOGINGUI ");
				login = in.readLine();
				System.out.println("input " + login);
				if(login.startsWith("REGISTER ")) {
					DatabasInsert dbInsert = new DatabasInsert();
					dbInsert.insertPerson(login);
				}else if(login.startsWith("LOGIN ")) {
					String[] usernamePass = login.substring(6).split(":"); 
					RetrieveDatabase rb = new RetrieveDatabase();
					System.out.println("rbchecklogin " + usernamePass[0] + " " + usernamePass[1]);
					if (rb.checkLogin(usernamePass[0], usernamePass[1])) {
						System.out.println("login login login");
					} else {
						System.out.println("false");
						break;
					}
					
				}
				
				out.println("SUBMITNAME ");
				name = in.readLine();
				if (name == null) {
					return;
				}
				synchronized (ChatServer.names) {
					if (!ChatServer.names.contains(name)) {
						ChatServer.ListNames.add(name);
						ChatServer.names.add(name);
						ChatServer.clientList.add(name);
						System.out.println("namn ok");
						if (ChatServer.utloggadeClients.contains(name)) {

						}
						break;
					}
				}
			}

			out.println("NAMEACCEPTED ");
			// Outputting the online users to the new user
			for (String oldName : ChatServer.ListNames) {
				if (!oldName.equals(this.name)) {
					out.println("LOGGEDINUSER " + oldName);
				}
			}

			// Outputting the new user to all online users
			for (PrintWriter writer : ChatServer.writers) {
				writer.println("NEWLOGIN " + name);
			}

			ChatServer.ListWriters.add(out);
			ChatServer.writers.add(out);

			// MESSAGE LOOP
			while (true) {
				String input = in.readLine();
				System.out.println(input);
				if (input == null) {
					return;
				} else if (input.startsWith("!!") && input.matches(".*\\s+.*")) {
					System.out.println(name);
					String namn = input.substring(input.indexOf("!!") + 2, input.indexOf(" "));
					System.out.println("namn: " + namn);

					input = input.substring(input.indexOf(" "));
					int i = 0;
					if (ChatServer.names.contains(namn)) {
						for (String str : ChatServer.ListNames) {
							if (str.trim().contains(namn)) {
								PrintWriter writer = ChatServer.ListWriters.get(i);
								writer.println("PRIVATEMESSAGE " + "[" + getTime() + "] " + "Private Message From "
										+ name + ": " + input);
							} else if (str.trim().contains(name)) {
								PrintWriter writer = ChatServer.ListWriters.get(i);
								writer.println("PRIVATEMESSAGE " + "[" + getTime() + "] " + "Private Message To " + namn
										+ ": " + input);
							}
							i++;
						}
					} else {
						for (String str : ChatServer.ListNames) {
							if (str.trim().contains(name)) {
								PrintWriter writer = ChatServer.ListWriters.get(i);
								writer.println(
										"PRIVATEMESSAGE " + "User: " + namn + ", does not exist, you have no friends");
							}
							i++;
						}
					}
				} else if (input.startsWith("GIF")) {
					for (PrintWriter writer : ChatServer.writers) {
						writer.println("GIF " + input.substring(3));
					}
				} else if (input.startsWith("CREATEGROUP")) {
					// Input "CREATEGROUP IP PORT; name name name"
					// Output "GROUPINVITE IP PORT" to every name
					String[] ipPort = input.split(" ");
					String[] deltagare = input.substring(input.indexOf(";") + 1).split(" ");
					for (int i = 0; i < deltagare.length; i++) {
						System.out.println("deltagare " + deltagare[i]);
						for (int j = 0; j < ChatServer.ListNames.size(); j++) {
							if (ChatServer.ListNames.get(j).equals(deltagare[i])) {
								PrintWriter writer = ChatServer.ListWriters.get(j);
								writer.println("GROUPINVITE " + ipPort[1] + " "
										+ ipPort[2].substring(0, ipPort[2].indexOf(";")));
							}
						}
					}
				} else {
					for (PrintWriter writer : ChatServer.writers) {
						// Global message outputs[hh:mm:ss] name : input
						writer.println("GLOBALMESSAGE " + "[" + getTime() + "] " + name + ": " + input + " ");
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			System.out.println("FINALLY");
			// When the client exits the chat
			if (name != null) {
				// Sending to all the connecting users that a client has logged out
				for (PrintWriter writer : ChatServer.writers) {
					writer.println("LOGOUT " + name);
				}
				ChatServer.utloggadeClients.add(name);
				ChatServer.names.remove(name);
				ChatServer.ListNames.remove(name);
			}
			if (out != null) {
				ChatServer.writers.remove(out);
				ChatServer.ListWriters.remove(out);
			}
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 
	 * @return String [hh:mm:ss]
	 */
	public String getTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		return sdf.format(c.getTime()).toString();
	}
}
