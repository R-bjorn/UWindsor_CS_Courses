package mvcExamplePropertyChangedListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class MyViewB extends JTextField implements PropertyChangeListener{

	private MyModel myModel;
	public MyViewB(MyModel myModel) {
		super(50);
		this.myModel = myModel;
		myModel.addPropertyChangeListener(this);
		setText(myModel.getSomeOtherValue());
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		this.setText("" + myModel.getSomeOtherValue());
	}
}
