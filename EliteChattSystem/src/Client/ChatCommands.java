package Client;

import java.awt.Color;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import GUI.DisplayGifGUI;
import GroupChat.GroupChatClient;

public class ChatCommands {
	
	public static void inputCommandsGlobal(PrintWriter out, String line, ChatClient client) {
    	if (line.startsWith("SUBMITNAME")) {
    		String name = client.getName();
    		client.setUsername(name);
            out.println(name);
        } else if (line.startsWith("NAMEACCEPTED")) {
           client.getGUI().getTextField().setEditable(true);
            client.getGUI().getTextField().requestFocus();
        } else if (line.startsWith("GLOBALMESSAGE")) {
        	client.getGUI().getMessageArea().setForeground(Color.BLACK);
        	String text = (line.substring(14) + "\n");
        	client.getGUI().getMessageArea().append(text);
        }else if (line.startsWith("PRIVATEMESSAGE")) {
        	String text = (line.substring(15) + "\n");
        	client.getGUI().getMessageArea().append(text);
        } else if (line.startsWith("NEWLOGIN")) {
        	client.getGUI().getFriendList().addUserToList(line.substring(9));
        	client.getGUI().getMessageArea().append(line.substring(9) + " has joined the cult \n");
        }else if (line.startsWith("LOGGEDINUSER")) {
        	client.getGUI().getFriendList().addUserToList(line.substring(13));
        }  else if (line.startsWith("LOGOUT")) {
        	client.getGUI().getFriendList().removeUserFromList(line.substring(7));
        } else if (line.startsWith("GROUPINVITE")) {
        	//input "GROUPINVITE IP PORT 
        	String[] ipPort = line.split(" ");
        	String ip = ipPort[1].substring(1);
        	String port = ipPort[2];
        	System.out.println("ip port " + ip + " " + port);
        	new GroupChatClient(ip, Integer.parseInt(port));
        }else if (line.startsWith("GIF")) {
        	try {
				new DisplayGifGUI(new URL(line.substring(3)), "FunnyGifs", client.getGUI());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
        }
	}

	public static void inputCommandsGroup(PrintWriter out, String line, GroupChatClient client) {
		if (line.startsWith("GLOBALMESSAGE")) {
	    	client.getGUI().getMessageArea().setForeground(Color.BLACK);
	    	String text = (line.substring(14) + "\n");
	    	client.getGUI().getMessageArea().append(text);
	    }else if (line.startsWith("PRIVATEMESSAGE")) {
	    	String text = (line.substring(15) + "\n");
	    	client.getGUI().getMessageArea().append(text);
	    } else if (line.startsWith("GIF")) {
	    	try {
				new DisplayGifGUI(new URL(line.substring(3)), "FunnyGifs", client.getGUI());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
}
	
