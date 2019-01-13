package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import GUI.AbstractGui;

public class Handler extends Thread {
	private String name;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				out.println("SUBMITNAME");
				name = in.readLine();
				if (name == null) {
					return;
				}
				synchronized (ChatServer.names) {
					if (!ChatServer.names.contains(name)) {
						ChatServer.ListNames.add(name);
						ChatServer.names.add(name);
						System.out.println("namn ok");
						break;
					}
				}
			}

			out.println("NAMEACCEPTED");
			for (String oldName : ChatServer.ListNames) {
				if (!oldName.equals(this.name)) {
					out.println("NEWLOGIN " + oldName);
				}
			}

			// Skriver ut den ny klientens namn till alla tidigare anslutna klienter
			for (PrintWriter writer : ChatServer.writers) {
				writer.println("NEWLOGIN " + name);
			}

			ChatServer.ListWriters.add(out);
			ChatServer.writers.add(out);

			// MESSAGE LOOPEN
			while (true) {
				String input = in.readLine();
				System.out.println(input);
				System.out.println(input);
				if (input == null) {
					return;
				}  else if (input.startsWith("!!") && input.matches(".*\\s+.*")) {
					System.out.println(name);
					//ITS ALL FUCKED UPP
					String namn = input.substring(input.indexOf("!!") + 2, input.indexOf(" "));
					System.out.println("namn: " + namn);
					
					input = input.substring(input.indexOf(" "));
					int i = 0;
					if(ChatServer.names.contains(namn)) {
						for (String str : ChatServer.ListNames) {
							if (str.trim().contains(namn)) {
									PrintWriter writer = ChatServer.ListWriters.get(i);
									writer.println("PRIVATEMESSAGE " + "[" + AbstractGui.getTime() +  "] " + "Private Message From " + name + ": " + input);
								} else if (str.trim().contains(name)) {
									PrintWriter writer = ChatServer.ListWriters.get(i);
									writer.println("PRIVATEMESSAGE " + "[" + AbstractGui.getTime() + "] " + "Private Message To " +  namn + ": " + input);
								}
							i++;
						}
					}else {
						for (String str : ChatServer.ListNames) {
							if (str.trim().contains(name)) {
								PrintWriter writer = ChatServer.ListWriters.get(i);
								writer.println("PRIVATEMESSAGE " + "User: " + namn + ", doesn�t exist, you have no friends");
								}
							i++;
						}
					}
				} else if (input.startsWith("GIF")) {
					for (PrintWriter writer : ChatServer.writers) {
						writer.println("GIF " + input.substring(3));
					}
				}else if(input.startsWith("CREATEGROUP")) {
					System.out.println("CREATEGROUP FÖRST");
					String ip = input.substring(12, input.indexOf(";"));
					String[] deltagare = input.substring(input.indexOf(";") + 1).split(" ");
					for (int i = 0; i < deltagare.length; i++) {
						System.out.println("deltagare " + deltagare[i]);
						for (int j = 0; j < ChatServer.ListNames.size(); j++) {
							if (ChatServer.ListNames.get(j).equals(deltagare[i])) {
								PrintWriter writer = ChatServer.ListWriters.get(j);
								writer.println("GROUPINVITE " + ip);
							}
						}
					}
				} else {
					for (PrintWriter writer : ChatServer.writers) {
						// Global message writes name : then input
						
						System.out.println(AbstractGui.getTime());
						writer.println("GLOBALMESSAGE " + "[" + AbstractGui.getTime() + "] " + name + ": " + input + " ");
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			System.out.println("FINALLY");
			// When the client exits the chat
			if (name != null) {
				// Sending to all the connectiong users that a client has logged out
				for (PrintWriter writer : ChatServer.writers) {
					writer.println("LOGOUT " + name);
				}
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
}
