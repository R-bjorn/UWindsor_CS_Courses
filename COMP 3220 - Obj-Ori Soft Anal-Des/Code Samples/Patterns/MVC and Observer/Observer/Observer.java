package observerExample;
abstract class Observer {
	protected Subject subj;
	public abstract void update();
}