package mvcExampleObserver;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MyModel extends Observable{
	private float someValue;
	private String someOtherValue;
	
	public MyModel(float thisValue, String thatValue) {
		someValue = thisValue;
		someOtherValue = thatValue;
	}

	//Doing a pull architecture
	//If I was doing a push architecture, I would pass the new value in notifyObservers
	//Instead, observers get notified and have to query what they want
	public void setSomeValue(float someNewValue) {
		someValue = someNewValue;
		setChanged();
		notifyObservers();
	}
	
	public void setSomeOtherValue(String someOtherNewValue) {
		someOtherValue = someOtherNewValue;
		setChanged();
		notifyObservers();
	}
		
	public float getSomeValue() {
		return someValue;
	}
	
	public String getSomeOtherValue() {
		return someOtherValue;
	}
}
