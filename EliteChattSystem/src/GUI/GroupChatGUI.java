package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Client.AbstractClient;

public class GroupChatGUI extends AbstractGUI {

	public GroupChatGUI(AbstractClient client, String title) {
		super(client, title);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getTextField().setEditable(true);
		frame.getContentPane().remove(help);
		if (acceptWindow() != 0) {
			frame.dispose();
		}

	}

	public int acceptWindow() {
		return JOptionPane.showConfirmDialog(frame, "Joina");
	}
}
