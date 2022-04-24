package Frame;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Lab1.parentFrame;
import Lab1.childFrame;
@SuppressWarnings("serial")
public class frameLabels extends JLabel{
	/**
	 * 
	 */
//	Adding a frame on which the labels will be added
	JFrame frame;
	
//	Constructor that just makes an instance of the frame object 
	public frameLabels(JFrame frame) {
		this.frame = frame;
	}
	
//	Function to add labels for text
	public void addLabels() {
//		if the instance of the frame is parent
		if(frame instanceof parentFrame)
			this.setText("This is Parent Frame");
//		if the instance of the frame is child
		else if(frame instanceof childFrame)
			this.setText("This is Child Frame");
//		if the instance of the frame is just JFrame / unknown
		else
			this.setText("This is unknown Frame");
		
//		Make the aligment center in the panel
		this.setVerticalAlignment(JLabel.CENTER);
		this.setHorizontalAlignment(JLabel.CENTER);
		
//		Font manipulation for the frame
		this.setForeground(new Color(0xFFFFFF));
		this.setFont(new Font("Garamond", Font.PLAIN, 20));
		
//		Frame location and size
		this.setBounds(60,0, 200, 50);
		
//		Add all the labels we just created to the frame
		frame.add(this);
	}
}
