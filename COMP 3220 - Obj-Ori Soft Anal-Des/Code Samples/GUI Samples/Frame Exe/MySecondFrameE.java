import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Same as previous example, except that the way we handle the button events is the correct way to do it

public class MySecondFrameE extends JFrame implements ActionListener{
	JLabel prompt, outputTitle;
	JTextField inputArea;
	JTextArea outputArea;
	JButton myButton;
	
	public MySecondFrameE() {
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
		
		myButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				outputArea.setText("");
			}
		});
		
		/*
		public class myButtonListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				outputArea.setText("");
			}
		}*/
		//myButton.addActionListener(new myButtonListener());
		
		add(myButton);
		setSize(250, 300);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		outputArea.append("You typed - " + event. getActionCommand() + " \n");
		inputArea.setText("");
	}
	
	public static void main(String args[]){
		MySecondFrameE aFrame = new MySecondFrameE();
		}
	}
