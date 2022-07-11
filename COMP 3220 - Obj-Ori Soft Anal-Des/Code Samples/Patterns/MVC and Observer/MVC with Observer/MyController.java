package mvcExampleObserver;

import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class MyController implements ActionListener{
	//Could use the values of the text boxes to update the model
	//So I have these references here
	//But in this example I'm using a third JTextField to initiate updates
	//Thus these references are unused but feel free to play around
	private MyViewA myViewA;
	private MyViewB myViewB;
	private MyModel myModel;
	
	public MyController(MyModel m, MyViewA a, MyViewB b) {
		myViewA = a;
		myViewB = b;
		myModel = m;
	}
	
	public void updateSomeValue(float newValue) {
		myModel.setSomeValue(newValue);
	}
	
	public void updateSomeOtherValue(String newValue) {
		myModel.setSomeOtherValue(newValue);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newText = ((JTextField)e.getSource()).getText();
		updateSomeValue(Float.parseFloat(newText.split(" ")[0]));
		updateSomeOtherValue(newText.split(" ")[1]);
		((JTextField)e.getSource()).setText("");
	}
	
	
}
