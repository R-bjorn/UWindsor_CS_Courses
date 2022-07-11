package state;

public class CastingSpellState implements AnimationState {

	@Override
	public String playAudio() {
		return "psshhhhhooooooooooosh";
	}

	@Override
	public String animate() {
		return "*the wizard casts an ice spell*";
	}

}
