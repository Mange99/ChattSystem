package GUI;

import java.awt.Dimension;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


import Client.ChatClient;
import GroupChat.GroupServer;

/**
 * @author alejyb1
 *
 */
public class FriendList extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JButton createGroupButton;
	private JScrollPane scrollPane;
	private LinkedList<JCheckBox> checkBoxes = new LinkedList<JCheckBox>();
	

	public FriendList(ChatClient client) {
		//A "Create Gruop Button" That will create a new groupchat with the people chosen form the checkboxes.

    	createGroupButton = new JButton("Skapa grupp");
    	createGroupButton.addActionListener(e->{
    		System.out.println("friendlist thread " + Thread.currentThread());
    		String port = JOptionPane.showInputDialog(null, "Skriv in vilken port du vill starta servern på:");
    		try {
				new GroupServer(Integer.parseInt(port));
			} catch (NumberFormatException | IOException e1) {
				e1.printStackTrace();
			}
    		System.out.println("Skickar creategrpu");
    		String createGroupMessage = "CREATEGROUP " + client.getSocket().getLocalAddress() + " " + port + ";";
    		for (int i = 0; i < checkBoxes.size(); i++) {
    			JCheckBox temp = checkBoxes.get(i);
    			
    			if (temp.isSelected()) {
    				createGroupMessage += " " + temp.getText();
    				System.out.println(createGroupMessage);
    			}
    		}
    		client.getOut().println(createGroupMessage);
    	});
    	
    	//The friendlist to the right in the screen

	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    
	    scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setPreferredSize(new Dimension(150, 200));
	    this.add(createGroupButton);

	}
	//Adding users to the frinedlist when they come online
	public void addUserToList(String name) {
		JCheckBox checkbox = new JCheckBox(name);
    	this.add(checkbox);
    	checkBoxes.add(checkbox);
    	
    	this.revalidate();
    }
	//Removes users from the friendlist when they leaves. Then repain and revalidate the Frame for the users sill online
	public void removeUserFromList(String name) {
		for (int i = 0; i < checkBoxes.size(); i++) {
			if (checkBoxes.get(i).getText().equals(name)) {
				this.remove(checkBoxes.get(i));
				checkBoxes.remove(i);
			}
		}
		this.repaint();
		this.revalidate();
	}
	//Jscrollpane for the users if there are many people online
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
