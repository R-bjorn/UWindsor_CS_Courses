import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

public class MySecondFrameA extends JFrame implements ActionListener{
	JLabel prompt, outputTitle;
	JTextField inputArea; // input value here
	JTextArea outputArea; // output displayed here
	
	public MySecondFrameA(){
		super("An example of a Frame"); //setup the gui components
		setLayout(new FlowLayout()); // initialize variables
		prompt = new JLabel( "Type a line" ); // prompt user to input value
		add(prompt); // attach prompt to frame
		inputArea = new JTextField(10);
		inputArea.addActionListener(this); // we want to listen for an ActionEvent
		add(inputArea); //attach input Area to frame
		outputTitle = new JLabel( "Output produced by program" );
		add(outputTitle); // attach outputTitle to frame
		outputArea = new JTextArea(10, 20); // we initially allow 10 lines each with 20 chars/line
		add(outputArea); // attach outputArea to frame
	}
	
	//process user's action on the input text field
	public void actionPerformed(ActionEvent event){
		String userInput;
		userInput = inputArea.getText(); //extract the input typed by the user
		outputArea.append("You typed - " + userInput + " \n"); // display the output
		inputArea.setText(""); // input on the outputArea and clear the inputArea.
		//inputArea.setEditable(false);
	}
	
	public static void main(String args[]){
		MySecondFrameA aFrame = new MySecondFrameA();
		aFrame.setSize(250, 300);
		aFrame.setVisible(true);
	}
}