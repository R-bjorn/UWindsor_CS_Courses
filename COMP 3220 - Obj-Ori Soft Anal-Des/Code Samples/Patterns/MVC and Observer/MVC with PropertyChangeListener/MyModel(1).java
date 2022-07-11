package mvcExamplePropertyChangedListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class MyModel{
	private float someValue;
	private String someOtherValue;
	private PropertyChangeSupport changeListeners = new PropertyChangeSupport(this);
	
	public MyModel(float thisValue, String thatValue) {
		someValue = thisValue;
		someOtherValue = thatValue;
		
	}

	//Doing a pull architecture
	//If I was doing a push architecture, I would pass the new value in notifyObservers
	//Instead, observers get notified and have to query what they want
	public void setSomeValue(float someNewValue) {
		float oldvalue = someValue;
		someValue = someNewValue;
		changeListeners.firePropertyChange("someValue", oldvalue, someValue);
	}
	
	public void setSomeOtherValue(String someOtherNewValue) {
		String oldvalue = someOtherValue;
		someOtherValue = someOtherNewValue;
		changeListeners.firePropertyChange("someOtherValue", oldvalue, someOtherNewValue);
	}
		
	public float getSomeValue() {
		return someValue;
	}
	
	public String getSomeOtherValue() {
		return someOtherValue;
	}

    public void addPropertyChangeListener(PropertyChangeListener l) {
    	changeListeners.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
    	changeListeners.removePropertyChangeListener(l);
    }
}
