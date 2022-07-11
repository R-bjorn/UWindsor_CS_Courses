package observerExample;
public class Subject {
	private Observer[] observers = new Observer[9]; // used to keep track
	private int totalObs = 0; // of the observers
	private int state; // The value of this variable is being observed
	
	public void attach(Observer o) {
		observers[totalObs++] = o;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int in) {
		state = in;
		notifyObservers();
	}
	
	private void notifyObservers() {
		for (int i=0; i < totalObs; i++) {
			observers[i].update();
		}
	}
}
