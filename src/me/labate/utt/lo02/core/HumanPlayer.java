package me.labate.utt.lo02.core;

/**
 * Represent the User as a Player
 * @author Alabate
 *
 */
public class HumanPlayer extends Player {

	/**
	 * Constructor
	 * @param context
	 * @param name
	 */
	public HumanPlayer(Game context, String name) {
		super(context, name);
	}

	@Override
	protected boolean doAction() {
		// An human cannot be controlled by the computer
		// So we let the UI tell use what the human do
		return false;
	}

	@Override
	public boolean isBot() {
		return false;
	}
	
	
}