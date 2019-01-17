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
		
		
	}
	
	public int getLoginGUIInput() {
		return JOptionPane.showOptionDialog(panel, "Log in", "Log in", 0, JOptionPane.INFORMATION_MESSAGE, null,options, null);
	}
	
	public String loginRegisterGUI(int input) {
		int option;
		String query = "";
		
		switch(input) {
		case 0: 
			option = JOptionPane.showConfirmDialog(null, panel, "Log in", JOptionPane.OK_CANCEL_OPTION);
			if(option == 0) {
				String userName = username.getText();
				char[] pass = password.getPassword();
				String password = "";
				for(int i = 0; i < pass.length; i++) {
					password = password + pass[i];
				}
				query = "LOGIN " + userName + ":" + password;
				System.out.println("output " + query);
			}
			break;
		case 1:
			System.out.println("case 1 clientlogin");
			option = JOptionPane.showConfirmDialog(null, panel, "Register", JOptionPane.OK_CANCEL_OPTION);
			if(option == 0) {
				String userName = username.getText();
				char[] passArr = password.getPassword();
				String password = "";
				for(int i = 0; i < passArr.length; i++) {
					password = password + passArr[i];
				}
				query = "REGISTER " + userName + ":" + password;
				System.out.println(query);
				break;
			}
		case 2:
			break;
		}
		return query;
	}
}
