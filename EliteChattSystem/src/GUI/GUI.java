package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
    private JFrame frame = new JFrame("Chatter");
    private JTextField textField = new JTextField(40);
    private JTextArea messageArea = new JTextArea(8, 40);
    private JPanel panel = new JPanel();
    private JLabel labels[];

    public GUI() {
    
	    panel.setPreferredSize(new Dimension(130, 200));
	    
	    textField.setEditable(false);
	    messageArea.setEditable(false);
	    //Frame layout
	    frame.getContentPane().add(textField, "South");
	    frame.getContentPane().add(new JScrollPane(messageArea), "Center");
	    frame.getContentPane().add(panel, "East");

	    frame.pack();
	    //textField actionlistern ifall de finns text i textfield så skriver den ut sedan sätter texten t "" aka tom
	    textField.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	           	if(!textField.getText().equalsIgnoreCase("")) {
	           		out.println(textField.getText());
	           		textField.setText("");
	           	}
	           }
	        });
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
	public JPanel getPanel() {
		return panel;
	}
	public void setPanel(JPanel panel) {
		this.panel = panel;
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
