import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class ButtonEventHandler implements ActionListener{
	MySecondFrameC local_frame;
	public ButtonEventHandler(MySecondFrameC frame_sent){
		local_frame = frame_sent;
	}
	
	public void actionPerformed( ActionEvent e ){
		local_frame.outputArea.setText("");
	}
}