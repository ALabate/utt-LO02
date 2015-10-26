/**
 * 
 */
package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

/**
 * @author alabate
 *
 */
public interface Strategy {
	
	/**
	 * Return the card choosen by the strategy
	 * @return Card choosen by the stategy
	 */
	public abstract int card();
	
	/**
	 * Return the method choosen by the strategy
	 * @return Method choosen by the stategy
	 */
	public abstract IngredientMethod method();
	
	/**
	 * Return the target choosen by the strategy
	 * @return Target choosen by the stategy and -1 if not needed
	 */
	public abstract int target();
	
	// TODO strategy for fullgame
}
