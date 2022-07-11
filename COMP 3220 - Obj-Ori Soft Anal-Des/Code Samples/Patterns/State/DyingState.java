package state;

public class DyingState implements AnimationState {

	@Override
	public String playAudio() {
		return "ughghhhghhhhhhhh :(";
	}

	@Override
	public String animate() {
		return "*the wizard falls to the ground*";
	}

}
