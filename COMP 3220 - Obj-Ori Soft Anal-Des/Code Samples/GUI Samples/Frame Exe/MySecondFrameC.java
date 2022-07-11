import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Same as previous example, except now we're using a separate class to handle the button event handler

public class MySecondFrameC extends JFrame implements ActionListener{
	JLabel prompt, outputTitle;
	JTextField inputArea;
	JTextArea outputArea;
	JButton myButton;
	public MySecondFrameC(){
		super("Use of buttons");
		setLayout(new FlowLayout());
		prompt = new JLabel( "Type a line" );
		add(prompt);
		inputArea = new JTextField(10);
		inputArea.addActionListener(this);
		add(inputArea);
		outputTitle = new JLabel( "Output produced by program" );
		add(outputTitle);
		outputArea = new JTextArea(10, 20);
		add(outputArea);
		myButton = new JButton("Clear Output");
		myButton.addActionListener(new ButtonEventHandler(this));
		add(myButton);
		setSize(250, 300);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		outputArea.append("You typed - " + event. getActionCommand() + " \n");
		inputArea.setText("");
	}
	
	public static void main(String args[]){
		MySecondFrameC aFrame = new MySecondFrameC();
	}
}