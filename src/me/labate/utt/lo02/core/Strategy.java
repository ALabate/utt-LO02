/**
 * 
 */
package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.Player.Method;

/**
 * @author alabate
 *
 */
public abstract class Strategy {

	/**
	 * Contain the current state of the game
	 */
	Game context;
	
	/**
	 * Constructor
	 * @param context Context of the game
	 */
	public Strategy(Game context) {
		this.context = context;
	}

	/**
	 * Return the card choosen by the strategy
	 * @return Card choosen by the stategy
	 */
	public abstract int card();
	
	/**
	 * Return the method choosen by the strategy
	 * @return Method choosen by the stategy
	 */
	public abstract Method method();
	
	/**
	 * Return the target choosen by the strategy
	 * @return Target choosen by the stategy and -1 if not needed
	 */
	public abstract int target();
	
	// TODO strategy for fullgame
}
