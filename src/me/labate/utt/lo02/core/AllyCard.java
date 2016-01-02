package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.Game.Season;
/**
 * AllyCard the abtract method
 * @author Alabate
 *
 */
abstract public class AllyCard extends Card {

	/**
	 * enum for the different kind of AllyCard
	 * @author Alabate
	 * 
	 */
	public enum AllyMethod { MOLE, DOG };
	
	/**
	 * Constructor
	 * @param context Context of the game
	 */

	public AllyCard(int ID){
		super(ID);
	}

	/**
	 * Get the card value for the given method on the given season
	 * @param method The selected method, it's useful for checking which type is the allyCard
	 * @param season The selected season
	 * @return the value or -1 if the method is not on the card
	 */
	abstract public int getValue(AllyMethod methodID, Season season);

}