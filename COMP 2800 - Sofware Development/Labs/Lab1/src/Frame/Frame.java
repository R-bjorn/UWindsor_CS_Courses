package Frame;
import java.awt.Color;


//import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Lab1.childFrame;

/**
 * 
 */

/**
 * @author ravid
 *
 */
@SuppressWarnings("serial")
public class Frame extends JFrame{
	//	Variables
	private int frameWidth = 300, frameHeight = 200;
	private String frameTitle = "This is Title";
//	private ImageIcon frameIcon = new ImageIcon(getClass().getResource("logo.png"));
	
	public Frame() {
//		adding an icon Image for frame icon
//		this.setIconImage(frameIcon.getImage());
		this.setLayout(null);
//		adding a title to the frame
		this.setTitle(frameTitle);	
//		set the width and height for the frame 
		this.setSize(frameWidth, frameHeight);
//		make the frame visible 
		this.setVisible(true);
//		add a different background to the frame
		this.getContentPane().setBackground(Color.gray);
//		exit the application
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame is not resizable
		this.setResizable(false);
	}
	
	public void setCloseOp(JFrame frame) {
		if(frame instanceof childFrame)
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
