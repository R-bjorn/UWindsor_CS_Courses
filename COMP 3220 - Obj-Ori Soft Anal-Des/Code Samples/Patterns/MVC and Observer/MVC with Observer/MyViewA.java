package mvcExampleObserver;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class MyViewA extends JTextField implements Observer{

	private MyModel myModel;
	public MyViewA(MyModel myModel) {
		super(50);
		this.myModel = myModel;
		myModel.addObserver(this);
		setText("" + myModel.getSomeValue());
	}
	
	@Override
	public void update(Observable o, Object arg) {
		this.setText("" + myModel.getSomeValue());
	}
}
