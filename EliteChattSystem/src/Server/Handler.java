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
	private String input;
	private String username;
	
	public Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// Input and Output
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			out.println("LOGINGUI ");
			input = in.readLine();
			System.out.println("input " + input);
			RetrieveDatabase rb = new RetrieveDatabase();
			if(input.startsWith("REGISTER ")) {
				String[] namePass = input.substring(9).split(":");
				//check if the new username and password already exists in the database
				if (!rb.checkIfUserInDatabase(namePass[0], namePass[1])) {
					DatabasInsert dbInsert = new DatabasInsert();
					dbInsert.insertNewPerson(namePass[0], namePass[1]);
					out.println("REGISTERTRUE ");
					//login the new user if not already logged in
					if (!loginUser(rb, namePass[0], namePass[1])){
						return;
					}
				} else {
					System.out.println("användare finns redan i databasen");
					out.println("REGISTERFALSE ");
					return;
				}
			}else if(input.startsWith("LOGIN ")) {
				String[] usernamePass = input.substring(6).split(":"); 
				username = usernamePass[0];
				if (!loginUser(rb, username, usernamePass[1])) {
					return;
				}
			}
				
			while (true) {
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
				
				ChatServer.clientList.remove(username);
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
	 * @param rb
	 * @param username
	 * @param password
	 * @return if the user is logging in
	 * checks if the user exists in the database and checks if that username is already logged in
	 */
	private boolean loginUser(RetrieveDatabase rb, String username, String password) {
		if (rb.checkIfUserInDatabase(username, password) && !ChatServer.clientList.contains(username)) {
			ChatServer.clientList.add(username);
			out.println("LOGINTRUE ");
			return true;
		} else {
			out.println("LOGINFALSE ");
			return false;
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
