package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class DisplayGifGUI {
	
	public DisplayGifGUI(URL url, String text, AbstractGUI GUI) {
			Icon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			text = text.trim();
			JFrame f = new JFrame(text);
			GUI.getTextField().setText("");
			f.getContentPane().add(label);
			f.setResizable(false);
			f.pack();
			f.setLocationRelativeTo(GUI.getFrame());
			f.setVisible(true);
			f.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "EXIT"); 
				    f.getRootPane().getActionMap().put("EXIT", new AbstractAction(){ 
				        public void actionPerformed(ActionEvent e)
				        {
				            f.dispose();
				        }
				    });
	}
}

