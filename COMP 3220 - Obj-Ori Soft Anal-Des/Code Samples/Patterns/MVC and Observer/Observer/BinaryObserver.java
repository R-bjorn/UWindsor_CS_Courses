package observerExample;
class BinaryObserver extends Observer {
	public BinaryObserver(Subject s) {
		// Observers register themselves
		subj = s;
		subj.attach(this); 
	} 
	
	public void update() {
		System.out.print( " " +
		Integer.toBinaryString(subj.getState()));
	}
}