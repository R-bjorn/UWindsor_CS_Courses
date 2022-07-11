import java.awt.*; // import the java.awt package

import javax.swing.*;

public class MyFirstFrame extends JFrame{
	JLabel myQuestion, yourResponse;
	public MyFirstFrame(){
		super("Woot we made our first frame we're so awesome");
		//this.setBounds(0, 0, 300, 300);
		this.setBounds(300, 100, 800, 800);
		
		JLabel [] labels = new JLabel[100];
		
		for (int i = 0; i < labels.length ; i++){
			labels[i] = new JLabel("Oh wow so cool :O " + i);
			add(labels[i]);
		}
		
		//Define the label on the titlebar
		setLayout(new FlowLayout());
		//setLayout(new GridLayout(10,10));
		
		// Define what layout manager will be used
		JLabel myQuestion = new JLabel("How are you?");
		// Create a GUI object
		// My mental image - get hold of a picture 
		add(myQuestion); //Add it to the frame
		//Mount the picture in the next available place 
		
		yourResponse = new JLabel("I am fine");
		add(yourResponse);
	}
	
	public static void main(String args[]){
		MyFirstFrame f = new MyFirstFrame();
		f.setVisible(true);
	}
}
