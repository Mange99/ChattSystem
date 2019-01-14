package GUI;

import javax.swing.JOptionPane;

import Client.AbstractClient;

public class GroupChatGUI extends AbstractGUI{
	
	public GroupChatGUI(AbstractClient client, String title) {
		super(client, title);
	
		if (acceptWindow() != 0) {
			return;
		} else {
			textField.setEditable(true);
		}
		
	}
	
	public int acceptWindow() {
		return JOptionPane.showConfirmDialog(frame, "Joina");
	}
}
