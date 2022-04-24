package Lab1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Random;

import Frame.Frame;
import Frame.frameButton;
import Frame.frameLabels;

/**
 * @author ravid
 *
 */
@SuppressWarnings("serial")
public class parentFrame extends Frame implements ActionListener{
		
	//	Variables
	private frameLabels frameLabel = new frameLabels(this);
	private frameButton frameButton = new frameButton(this);

	// Constructor
	public parentFrame(){
//		add a different background to the frame
		parentFrameBackground();
		frameLabel.addLabels();
//		Adding Button to the frame
		frameButton.addButton(this);
	}
	
//	adding a function to randomly generate different colors.
	public void parentFrameBackground() {
//		Java random function
		Random random = new Random();
//		RGB values
		int red = random.nextInt(255), 
				green = random.nextInt(255), 
				blue = random.nextInt(255);
//		frameBackground = new Color(red, green, blue);
		this.getContentPane().setBackground(new Color(red, green, blue));
	}
	
//	Action Listener Function when the button is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
//		looking for the button source		
		if(e.getSource() == frameButton) {
//			making a child frame with a button to change parent frame background
			new childFrame(this); 	
		}
		
	}
	
}
