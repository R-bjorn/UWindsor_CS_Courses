import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Same as previous example, except now we're using an inner class to define the button event handler

public class MySecondFrameD extends JFrame implements ActionListener{
	JLabel prompt, outputTitle;
	JTextField inputArea;
	JTextArea outputArea;
	JButton myButton;
	public MySecondFrameD(){
		super("Use of buttons");
		setLayout(new FlowLayout());
		prompt = new JLabel( "Type a line" );
		add(prompt); inputArea = new JTextField(10);
		inputArea.addActionListener(this);
		add(inputArea);
		outputTitle = new JLabel( "Output produced by program" );
		add(outputTitle);
		outputArea = new JTextArea(10, 20);
		add(outputArea);
		myButton = new JButton("Clear Output");
		myButton.addActionListener(new ButtonEventHandler());
		add(myButton);
		setSize(250, 300);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		outputArea.append("You typed - " + event. getActionCommand() + " \n");
		inputArea.setText("");
	}
	
	public static void main(String args[]){
		MySecondFrameD aFrame = new MySecondFrameD();
	}
	
	class ButtonEventHandler implements ActionListener{
		public void actionPerformed( ActionEvent e ){
			outputArea.setText(""); //doesn't need reference to frame anymore because this class exists inside of it
		}
	}
}