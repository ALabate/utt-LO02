package me.labate.utt.lo02.core;

public class HumanPlayer extends Player {

	public HumanPlayer(Game context, String name) {
		super(context, name);
	}

	@Override
	public void doChoice() {
		// An human cannot be controlled by the computer
		// So we let the UI tell use what the human do
	}
	
	
}
