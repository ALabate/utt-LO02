/**
 * 
 */
package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player.Bonus;

/**
 * @author alabate
 *
 */
public interface Strategy {
	
	/**
	 * Return the card choosen by the strategy
	 * @return Card choosen by the stategy
	 */
	public abstract IngredientCard card();
	
	/**
	 * Return the method choosen by the strategy
	 * @return Method choosen by the stategy
	 */
	public abstract IngredientMethod method();
	
	/**
	 * Return the target choosen by the strategy
	 * @return Target choosen by the stategy and -1 if not needed
	 */
	public abstract Player target();
	
	/**
	 * Return the type of bonus choosen by the strategy
	 * @return type of bonus choosen by the strategy
	 */
	public abstract Bonus bonus();
	
	/**
	 * Return if the strategy want to defend when attacked by leprechaun
	 * @return True if the strategy wants to defend
	 */
	public abstract boolean defend();
	
	/**
	 * Return if the strategy want to attack with mole
	 * @return True if the strategy wants to atack
	 */
	public abstract boolean moleAttack(Player playing);
	
	/**
	 * Return the target id of the mole attack
	 * @return the target id of the mole attack
	 */
	public abstract Player moleAttackTarget(Player playing);
}