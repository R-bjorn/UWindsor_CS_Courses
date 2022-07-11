package state;

public class IdleState implements AnimationState {

	@Override
	public String playAudio() {
		return "*crickets*";
	}

	@Override
	public String animate() {
		return "*the wizard is literally just standing there*";
	}

}
