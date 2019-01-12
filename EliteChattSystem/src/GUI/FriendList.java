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
	 * Klass f�r friendlistan
	 */
	
	private static final long serialVersionUID = 1L;
	private JButton createGroupButton;
	private JScrollPane scrollPane;
	private LinkedList<JCheckBox> checkBoxes = new LinkedList<JCheckBox>();
	
	public FriendList() {
		
		//skapa grupp knapp som tar dem checkboxes som �r valda och bjud in dem klienterna till gruppchatt
    	createGroupButton = new JButton("Skapa grupp");
    	createGroupButton.addActionListener(e->{
    		for (int i = 0; i < checkBoxes.size(); i++) {
    			JCheckBox temp = checkBoxes.get(i);
    			
    			if (temp.isSelected()) {
    				//skicka ip och l�gg till i nya gruppen
    				System.out.println(" anv�ndare " + temp.getText() + " �r vald");
    			}
    		}
    	});
    	
    	//FriendList som �r p� h�ger sidan
	    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    
	    scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setPreferredSize(new Dimension(150, 200));
	    this.add(createGroupButton);

	}
	
	public void addUserToList(String name) {
		/**
		 * L�gger till en checkbox i friendlistpanel med namnet och i checkBoxes
		 */
		
		JCheckBox checkbox = new JCheckBox(name);
    	this.add(checkbox);
    	checkBoxes.add(checkbox);
    	
    	this.revalidate();
    }
	
	public void removeUserFromList(String name) {
		/**
		 * Tar bort klienten ur checkboxes listan och tar sedan bort den grafiskt
		 */
		checkBoxes.remove(name);
		for (int i = 0; i < checkBoxes.size(); i++) {
			if (checkBoxes.get(i).getText().equals(name)) {
				
				this.remove(checkBoxes.get(i));
			}
		}
		this.repaint();
		this.revalidate();
	}
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
}
