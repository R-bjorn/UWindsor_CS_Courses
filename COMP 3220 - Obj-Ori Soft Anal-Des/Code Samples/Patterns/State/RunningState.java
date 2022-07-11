package state;

public class RunningState implements AnimationState {

	@Override
	public String playAudio() {
		return "swoosh swoosh swoosh";
	}

	@Override
	public String animate() {
		return "*the majestic wizard gracefully runs in the wilderness, with robe swooshing in the wind*";
	}

}
