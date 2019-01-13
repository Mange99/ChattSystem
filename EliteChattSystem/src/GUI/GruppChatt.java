package GUI;

import javax.swing.JOptionPane;

import Client.ChatClient;

public class GruppChatt extends AbstractGui{

	public GruppChatt(ChatClient client, String title) {
		super(client, title);
		
		if (acceptWindow() == 0) {
			
		}
	}

	public int acceptWindow() {
		return JOptionPane.showConfirmDialog(frame, "Joina");
	}
}
