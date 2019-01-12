package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import Client.ChatClient;

public class GUI {
    private JFrame frame = new JFrame("Chatter");
    private JTextField textField;
    private JTextArea messageArea;
    private JLabel labels[];
    private FriendList friendList;
    
    public GUI(ChatClient client) {
    	//TextFielden där man skriver sitt message
    	textField = new JTextField(40);
    	textField.setEditable(false);
    	
    	//Stora rutan där man ser allas meddelanden
    	messageArea = new JTextArea(8, 40);
    	messageArea.setEditable(false);
    	

    	friendList = new FriendList();
    	
	    //Frame layout
	    frame.getContentPane().add(textField, "South");
	    frame.getContentPane().add(new JScrollPane(messageArea), "Center");
	    frame.getContentPane().add(friendList.getScrollPane(), "East");
	    
	    frame.pack();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
<<<<<<< HEAD
	    //textField actionlistern ifall de finns text i textfield sï¿½ skriver den ut sedan sï¿½tter texten t "" aka tom
	  
=======
	    
	    //textField actionlistern ifall de finns text i textfield så skriver den ut sedan sätter texten t "" aka tom
	    textField.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	           	if(!textField.getText().equalsIgnoreCase("")) {
	           		client.getOut().println(textField.getText());
	           		textField.setText("");
	           	}
	           }
	        });
>>>>>>> 999d1bd9378eb716bdcb51e220b3baa5472dba53
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

	public JLabel[] getLabels() {
		return labels;
	}
	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
