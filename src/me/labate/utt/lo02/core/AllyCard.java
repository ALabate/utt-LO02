package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.Game.Season;

public class AllyCard extends Card {

	public enum AllyMethod { MOLE, DOG };

	/**
	 * List of cards that contains methods that contains value for each season
	 */
	private static final int[][][] deck = {
		// Mole cards
		{ {1, 1, 1, 1}, null },
		{ {0, 2, 2, 0}, null },
		{ {0, 1, 2, 1}, null },
		// Dog cards
		{ null, {2, 0, 2, 0} },
		{ null, {1, 2, 0, 1} },
		{ null, {0, 1, 3, 0} }};
	
	/**
	 * Constructor
	 * @param context Context of the game
	 */

	public AllyCard(int ID){
		super(ID);
	}

	/**
	 * Get the card value for the given method on the given season
	 * @param method The selected method
	 * @param season The selected season
	 * @return the value or -1 if the method is not on the card
	 */
	public int getValue(AllyMethod methodID, Season season) {
		return getValue(deck, methodID.ordinal(), season);
	}
	public static int deckLength(){
		return deck.length;
	}

}