package GUI;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayGifGUI {
	
	public DisplayGifGUI(URL url, String text, AbstractGUI abstractGUI) {
			Icon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			text = text.trim();
			JFrame f = new JFrame(text);
			abstractGUI.getTextField().setText("");
			f.getContentPane().add(label);
			f.setResizable(false);
			f.pack();
			f.setVisible(true);
	}
}

