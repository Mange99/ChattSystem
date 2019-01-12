package GUI;

import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class FriendList extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton createGroupButton;
	private JScrollPane scrollPane;
	private LinkedList<JCheckBox> checkBoxes = new LinkedList<JCheckBox>();
	
	public FriendList() {
		
		//skapa grupp knapp som tar dem checkboxes som är valda och bjud in dem klienterna till gruppchatt
    	createGroupButton = new JButton("Skapa grupp");
    	createGroupButton.addActionListener(e->{
    		for (int i = 0; i < checkBoxes.size(); i++) {
    			JCheckBox temp = checkBoxes.get(i);
    			
    			if (temp.isSelected()) {
    				//skicka ip och lägg till i nya gruppen
    				System.out.println(" användare " + temp.getText() + " är vald");
    			}
    		}
    	});
    	
    	//FriendList som är på höger sidan
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    
	    scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setPreferredSize(new Dimension(150, 200));
	    this.add(createGroupButton);

	    for (int i = 0; i < 4; i++) {
	    	JCheckBox cb = new JCheckBox("test" + i);
		    this.add(cb);
		    checkBoxes.add(cb);
	    }
	    
	    this.revalidate();

	}
	
	//lägger till användare 
	public void addUsersToList(String name) {
		JCheckBox checkbox = new JCheckBox(name);
    	this.add(checkbox);
    	checkBoxes.add(checkbox);
    	
    	this.revalidate();
    }
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
