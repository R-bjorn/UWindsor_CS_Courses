package mvcExampleObserver;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class MyViewB extends JTextField implements Observer{

	private MyModel myModel;
	public MyViewB(MyModel myModel) {
		super(50);
		this.myModel = myModel;
		myModel.addObserver(this);
		setText(myModel.getSomeOtherValue());
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.setText("" + myModel.getSomeOtherValue());
	}
}
