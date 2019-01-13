package GUI;
	
import javax.swing.JLabel;

import Client.ChatClient;

public class GUI extends AbstractGui{
  
    private JLabel labels[];
    private FriendList friendList;
    
    public GUI(ChatClient c, String t) {
    	super(c, t);

    	friendList = new FriendList(c);
    	
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
	public FriendList getFriendList() {
		return friendList;
	}
}
