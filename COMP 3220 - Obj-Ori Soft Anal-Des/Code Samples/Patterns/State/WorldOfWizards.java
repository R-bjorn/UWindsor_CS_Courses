package state;

public class WorldOfWizards {

	public static void main(String[] args) {
		Wizard w = new Wizard();
		w.doStuff();
		
		w.setState(new RunningState());
		w.doStuff();
		
		w.setState(new IdleState());
		w.doStuff();
		
		w.setState(new RunningState());
		w.doStuff();
		
		w.setState(new CastingSpellState());
		w.doStuff();
		
		w.setState(new DyingState());
		w.doStuff();
		
	}

}
