package Client;

import javax.swing.*;

public class ClientLogin {

	private JPanel panel;
	private JTextField username;
	private JPasswordField password;
	private String[] options;
	
	public ClientLogin() {
		
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
		
		System.out.println(mainOption);
		switch(mainOption) {
		case 0: 
			
			option = JOptionPane.showConfirmDialog(null, panel, "Log in", JOptionPane.OK_CANCEL_OPTION);
			System.out.println(option + " inloggning");
			if(option == 0) {
				String userName = username.getText();
				char[] pass = password.getPassword();
				System.out.println("inloggad " + option);
				//loginCheck(userName, pass);
			}
			System.out.println("du loggas in");
			break;
		case 1:
			option = JOptionPane.showConfirmDialog(null, panel, "Register", JOptionPane.OK_CANCEL_OPTION);
			System.out.println("Registrering" + option);
			if(option == 0) {
				String userName = username.getText();
				char[] pass = password.getPassword();
				System.out.println("Registreard " + option);
				//registerUser(userName, pass);
				break;
			}
		case 2:
			System.out.println("Cancel");
			break;
		}
	}
}
