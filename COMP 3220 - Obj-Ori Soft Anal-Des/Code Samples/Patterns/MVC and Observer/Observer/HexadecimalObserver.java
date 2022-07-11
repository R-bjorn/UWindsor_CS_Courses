package observerExample;
class HexadecimalObserver extends Observer {
	public HexadecimalObserver(Subject s) { 
		subj = s; 
		subj.attach(this); 
	} 
	
	public void update() {
		// Observers "pull" information 
		System.out.print(" " + Integer.toHexString(subj.getState())); 
	}
}