package GUI;

import javax.swing.JOptionPane;

import Client.ChatClient;

public class GruppChattGUI extends AbstractGui{
	
	public GruppChattGUI(ChatClient client, String title) {
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
