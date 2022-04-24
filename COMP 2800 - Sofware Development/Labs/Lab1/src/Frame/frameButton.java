package Frame;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Lab1.parentFrame;
import Lab1.childFrame;
@SuppressWarnings("serial")
public class frameButton extends JButton {
	
//	Adding a frame on which the buttons will be added
	JFrame frame;
	JLabel label;
	
//	Constructos that makes an instance of the frame object and adds Action Listener to the button
	public frameButton(JFrame frame) {
		this.addActionListener((ActionListener) frame);
		this.frame = frame;		
	}
	
//	Function to add the button on the frame
	public void addButton(JFrame frame) {
//		Adding Location and size of the button according to the frame  
//		Adding text according to the frame
		if(frame instanceof parentFrame) {
			this.setBounds(70, 50, 150, 50);
			this.setText("Show a new Frame"); }
		else if(frame instanceof childFrame) {
			this.setBounds(70, 50, 200, 50);
			this.setText("Change Parent Frame Color");}
		
		frame.add(this);
	}
	
}
