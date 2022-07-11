package mvcExamplePropertyChangedListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTextField;

public class MyViewA extends JTextField implements PropertyChangeListener{

	private MyModel myModel;
	public MyViewA(MyModel myModel) {
		super(50);
		this.myModel = myModel;
		myModel.addPropertyChangeListener(this);
		setText("" + myModel.getSomeValue());
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.setText("" + myModel.getSomeValue());
	}
}
