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

public class GUI extends AbstractGui{
  
    private JLabel labels[];
    private FriendList friendList;
    
    public GUI(ChatClient c) {
    	super(c);
    	System.out.println("maingui kör");
    	
    	friendList = new FriendList();
    	
	    //Frame layout
	    frame.getContentPane().add(friendList.getScrollPane(), "East");
	    frame.revalidate();
	  
	}
    
	public JLabel[] getLabels() {
		return labels;
	}
	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}
}
