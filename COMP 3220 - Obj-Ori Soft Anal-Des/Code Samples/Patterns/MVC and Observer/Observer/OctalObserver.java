package observerExample;
class OctalObserver extends Observer { 
	public OctalObserver(Subject s) { 
		subj = s; 
		subj.attach(this); 
	} 
	
	public void update() {
		System.out.print(" " + Integer.toOctalString(subj.getState()));
	} 
}