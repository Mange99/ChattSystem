package GUI;
	
import javax.swing.JLabel;

import Client.ChatClient;

public class GUI extends AbstractGUI{
  
    private JLabel labels[];
    private FriendList friendList;
  
    public GUI(ChatClient c, String t) {
    	super(c, t);

    	friendList = new FriendList(c);
    	friendList.add(help);
    	//Frame layout
	   
    	frame.getContentPane().add(friendList.getScrollPane(), "East");
	    

	    frame.revalidate();
	}
    //Getter and setter for Label and getter for FriendList
    public FriendList getFriendList() {
		return friendList;
	}
	public JLabel[] getLabels() {
		return labels;
	}
	public void setLabels(JLabel[] labels) {
		this.labels = labels;
	}
}
