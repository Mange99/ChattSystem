package Client;

import javax.swing.*;

public class ClientLogin {

	private JPanel panel;
	private JTextField username;
	private JPasswordField password;
	private String[] options;
	
	public ClientLogin(ChatClient client) {
		
		options = new String[3];
		options[0] = "Log in";
		options[1] = "Register";
		options[2] = "Cancel";
		
		panel = new JPanel();
		username = new JTextField(10);
		password = new JPasswordField(10);
		
		panel.add(new JLabel("Username"));
		panel.add(username);
		panel.add(Box.createHorizontalStrut(15));
		panel.add(new JLabel("Password"));
		panel.add(password);
		
		int mainOption = JOptionPane.showOptionDialog(panel, "Log in", "Log in", 0, JOptionPane.INFORMATION_MESSAGE, null,options, null);
		int option;
		
		switch(mainOption) {
		case 0: 
			
			option = JOptionPane.showConfirmDialog(null, panel, "Log in", JOptionPane.OK_CANCEL_OPTION);
			if(option == 0) {
				String userName = username.getText();
				char[] pass = password.getPassword();
				//loginCheck(userName, pass);
				//client.getOut().print();
			}
			break;
		case 1:
			option = JOptionPane.showConfirmDialog(null, panel, "Register", JOptionPane.OK_CANCEL_OPTION);
			System.out.println("Registrering" + option);
			if(option == 0) {
				String userName = username.getText();
				char[] pass = password.getPassword();
				String query = "INSERT INTO users (username , password)" + "VALUES (?, ?)";
				client.getOut().print(query);
				break;
			}
		case 2:
			break;
		}
	}
}
