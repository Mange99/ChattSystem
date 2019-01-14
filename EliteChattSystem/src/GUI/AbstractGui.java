package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Client.ChatClient;

public abstract class AbstractGui {

  protected JFrame frame;
	protected JTextField textField;
	protected JTextArea messageArea;
	private AbstractGui self;
	protected JButton help;
	
  
	public AbstractGui(ChatClient client, String title) {
		frame = new JFrame(title);
		self = this;
		// Textfield where you enter your messages
		textField = new JTextField(40);
		textField.setEditable(false);

		// Textfield actionListener if there is any specific texts funny things will
		// happend, else just writes message and set the empty the textfield.
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					switch (textField.getText().trim().toLowerCase()) {
						case "/dance":	
						client.getOut().println("GIF " + "https://i.makeagif.com/media/3-27-2016/xHLL7Y.gif");break;
						case"/shutup": 
							client.getOut().println("GIF " + "https://i.imgur.com/HB7qjnW.gif");break;
						case "/gay" : 
							client.getOut().println("GIF " + "https://thumbs.gfycat.com/ImaginativeSecondhandHamadryas-size_restricted.gif");break;
						case "/740" : 
							client.getOut().println("GIF " + "https://thumbs.gfycat.com/GoodSimpleGermanspaniel-max-1mb.gif");break;
						default: break;
					}
				if (!textField.getText().equalsIgnoreCase("")) {

					client.getOut().println(textField.getText());
					textField.setText("");
				}
			}
		});


		// The messageArea a JTextArea where all the messages appears
		messageArea = new JTextArea(8, 40);
		messageArea.setEditable(false);
		
		help = new JButton("?");
		help.setSize(10, 20);
		help.addActionListener(e->{
			JOptionPane.showMessageDialog(frame, "To send Private Message Write: \n!!name whitespace then message\n"
					+ "To send funny gifs wirte /nameOfGif");
		});
		
		// new font
		Font f = new Font("Comic Sans MS", Font.PLAIN, 15);

		//Setting the text in the textfield to black and adding font to both textfield and messageArea
		textField.setForeground(Color.black);
		textField.setFont(f);
		messageArea.setFont(f);
		
		// Frame layout
		frame.getContentPane().add(textField, "South");
		frame.getContentPane().add(new JScrollPane(messageArea), "Center");
		
		//frame settings, pack, visible, and close.
		frame.setSize(700, 350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static String getTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		
		return sdf.format(c.getTime()).toString();
	}
	//Getters and setters for Textfield, MessageArea, JFrame
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

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
