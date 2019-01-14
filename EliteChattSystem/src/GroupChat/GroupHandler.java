package GroupChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GroupHandler extends Thread {
	
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public GroupHandler(Socket socket) {
		this.socket = socket;
		System.out.println("grouphandler cons " + Thread.currentThread());
	
	}
	
	public void run() {
		System.out.println("grouphandles run" + Thread.currentThread());
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
			String name = in.readLine();
			if (name == null) {
				return;
			}
			synchronized (GroupServer.names) {
				if (!GroupServer.names.contains(name)) {
					GroupServer.ListNames.add(name);
					GroupServer.names.add(name);
				}
			}
			
			GroupServer.ListWriters.add(out);
			GroupServer.writers.add(out);
			
			while (true) {
				System.out.println("groupHandler while " + Thread.currentThread());
				String input = in.readLine();
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
					if(GroupServer.names.contains(namn)) {
						for (String str : GroupServer.ListNames) {
							if (str.trim().contains(namn)) {
									PrintWriter writer = GroupServer.ListWriters.get(i);
									writer.println("PRIVATEMESSAGE " + "[" + getTime() +  "] " + "Private Message From " + name + ": " + input);
								} else if (str.trim().contains(name)) {
									PrintWriter writer = GroupServer.ListWriters.get(i);
									writer.println("PRIVATEMESSAGE " + "[" + getTime() + "] " + "Private Message To " +  namn + ": " + input);
								}
							i++;
						}
					}else {
						for (String str : GroupServer.ListNames) {
							if (str.trim().contains(name)) {
								PrintWriter writer = GroupServer.ListWriters.get(i);
								writer.println("PRIVATEMESSAGE " + "User: " + namn + ", does not exist, you have no friends");
								}
							i++;
						}
					}
				} else if (input.startsWith("GIF")) {
					for (PrintWriter writer : GroupServer.writers) {
						writer.println("GIF " + input.substring(3));
					}
				} else {
					for (PrintWriter writer : GroupServer.writers) {
						// Global message writes name : then input
						writer.println("GLOBALMESSAGE " + "[" + getTime() + "] " + name + ": " + input + " ");
					}
				}
			} 
		} catch (IOException e) {
			e.getStackTrace();
		}
	}	
		
	public String getTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		return sdf.format(c.getTime()).toString();
	}
}
