package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.ChatClient;

public abstract class AbstractGui {

	protected JFrame frame = new JFrame("Chatter");
    protected JTextField textField;
    protected JTextArea messageArea;
    
	public AbstractGui(ChatClient client) {
		
		//TextFielden där man skriver sitt message
    	textField = new JTextField(40);
    	textField.setEditable(false);
    	
    	 //textField actionlistern ifall de finns text i textfield så skriver den ut sedan sätter texten t "" aka tom
	    textField.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	           	if(!textField.getText().equalsIgnoreCase("")) {
	           		client.getOut().println(textField.getText());
	           		textField.setText("");
	           	}
	           }
	        });
	    
    	//Stora rutan där man ser allas meddelanden
    	messageArea = new JTextArea(8, 40);
    	messageArea.setEditable(false);
    	
	    //Frame layout
	    frame.getContentPane().add(textField, "South");
	    frame.getContentPane().add(new JScrollPane(messageArea), "Center");
	    
	    frame.pack();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
	
	
	public JTextField getTextField() {
		return textField;
	}
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	public JTextArea getMessageArea() {
		return messageArea;
	}
	public void setMessageArea(JTextArea messageArea) {
		this.messageArea = messageArea;
	}

	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
