package GUI;
	
import javax.swing.JLabel;

import Client.ChatClient;

public class GUI extends AbstractGui{
  
    private JLabel labels[];
    private FriendList friendList;
    //The GUI class that run all the methds in AbstarctGUI
    public GUI(ChatClient c) {
    	super(c);
    	System.out.println("maingui k�r");
    	
    	//Creating a friendlist object
    	friendList = new FriendList();
    	
	    //Frame layout
	    frame.getContentPane().add(friendList.getScrollPane(), "East");

	    frame.revalidate();
	  

	}
    //Getter and setter for Label and getter for FriendList
	public JLabel[] getLabels() {
		return labels;
	}
	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}
	public FriendList getFriendList() {
		return friendList;
	}
}
