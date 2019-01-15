package Client;

import java.awt.Color;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import GUI.DisplayGifGUI;
import GroupChat.GroupChatClient;

public class ChatCommands {

	public static void inputCommandsGlobal(PrintWriter out, String line, ChatClient client) {
		String text = "";
		String command = line.substring(0, line.indexOf(" "));
		switch (command) {
		case "SUBMITNAME":
			String name = client.getName();
			client.setUsername(name);
			out.println(name);
			break;
		case "NAMEACCEPTED":
			client.getGUI().getTextField().setEditable(true);
			client.getGUI().getTextField().requestFocus();
			break;
		case "GLOBALMESSAGE":
			client.getGUI().getMessageArea().setForeground(Color.BLACK);
			text = (line.substring(14) + "\n");
			client.getGUI().getMessageArea().append(text);
			break;
		case "PRIVATEMESSAGE":
			text = (line.substring(15) + "\n");
			client.getGUI().getMessageArea().append(text);
			break;
		case "NEWLOGIN":
			client.getGUI().getFriendList().addUserToList(line.substring(9));
			client.getGUI().getMessageArea().append(line.substring(9) + " has joined the cult \n");
			break;
		case "LOGGEDINUSER":
			client.getGUI().getFriendList().addUserToList(line.substring(13));
			break;
		case "LOGOUT":
			client.getGUI().getFriendList().removeUserFromList(line.substring(7));
			break;
		case "GROUPINVITE":
			// input "GROUPINVITE IP PORT
			String[] ipPort = line.split(" ");
			String ip = ipPort[1].substring(1);
			String port = ipPort[2];
			System.out.println("ip port " + ip + " " + port);
			new GroupChatClient(ip, Integer.parseInt(port));
			break;
		case "GIF":
			try {
				new DisplayGifGUI(new URL(line.substring(3)), "FunnyGifs", client.getGUI());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	public static void inputCommandsGroup(PrintWriter out, String line, GroupChatClient client) {
		String text = "";
		String command = line.substring(0, line.indexOf(" "));
		switch (command) {
		case "GLOBALMESSAGE":
			client.getGUI().getMessageArea().setForeground(Color.BLACK);
			text = (line.substring(14) + "\n");
			client.getGUI().getMessageArea().append(text);
			break;
		case "PRIVATEMESSAGE":
			text = (line.substring(15) + "\n");
			client.getGUI().getMessageArea().append(text);
			break;
		case "GIF":
			try {
				new DisplayGifGUI(new URL(line.substring(3)), "FunnyGifs", client.getGUI());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
