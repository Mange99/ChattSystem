package GUI;

import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayGifGUI {
	
	public DisplayGifGUI(URL url, String text, GUI gui) {
			Icon icon = new ImageIcon(url);
			JLabel label = new JLabel(icon);
			JFrame f = new JFrame(text);
			gui.getTextField().setText("");
			f.getContentPane().add(label);
			f.setResizable(false);
			f.pack();
			f.setVisible(true);
	}
}

