package state;

public class Wizard {
	private AnimationState currentState = new IdleState();
	
	public String playAudio() {
		return currentState.playAudio();
	}
	
	public String animate() {
		return currentState.animate();
	}
	
	public void setState(AnimationState state) {
		currentState = state;
	}
	
	public void doStuff() {
		System.out.println("Doing stuff: " + playAudio() + " - " + animate());
	}
}
