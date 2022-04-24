package Lab1;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import Frame.Frame;
import Frame.frameButton;
import Frame.frameLabels;
/**
 * @author ravid
 * This is Child Class to change the background color or parent class with a button
 *
 */
@SuppressWarnings("serial")
public class childFrame extends Frame implements ActionListener{
//	Variables
	private parentFrame parentFrame;
	private frameLabels frameLabel = new frameLabels(this);
	private frameButton frameButton = new frameButton(this);
	
	// Constructors
	public childFrame(parentFrame parentFrame) {
//		adding the parent frame of this child frame
		this.parentFrame = parentFrame;
//		upon closing window, only close the frame, but keep the application running.
		setCloseOp(this);
//		Adding Labels to the frame
		frameLabel.addLabels();
//		Adding Button to the frame
		frameButton.addButton(this);
	}
	
//	Function to change the background color of the parent frame
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == frameButton)
			parentFrame.parentFrameBackground();		
	}
}
