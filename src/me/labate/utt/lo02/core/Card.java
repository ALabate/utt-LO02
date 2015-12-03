package me.labate.utt.lo02.core;


import me.labate.utt.lo02.core.Game.Season;

public abstract class Card {

	/**
	 * Contain the current state of the game
	 */
	protected Game context;
	
	/**
	 * Card ID in the card deck
	 */
	protected int cardID;

	/**
	 * create a card by setting the ID
	 * @param carID ID of the card you want to create
	 */
	public Card(int cardID)
	{
		this.cardID = cardID;
		this.context = null;
	}
	/**
	 * Convert season enum to season id
	 * @param season the season enum value
	 */
	protected int seasonToID(Season season) {
		switch(season) {
		case SPRING : return 0;
		case SUMMER : return 1;
		case AUTUMN : return 2;
		case WINTER : return 3;
		default : return -1;
		}
	}
	
	/**
	 * Get the card value for the given method id on the given season
	 * @param deck The deck of cards
	 * @param method The selected method
	 * @param season The selected season
	 * @return the value or -1 if the method is not on the card
	 */
	protected int getValue(int[][][] deck, int methodID, Season season) {
		if(deck[cardID][methodID] == null || seasonToID(season) == -1)
			return -1;
		return deck[cardID][methodID][seasonToID(season)];
	}

	
	/**
	 * Get the card value for the first method on the given season
	 * @param deck The deck of cards
	 * @param season The selected season
	 * @return the value or -1 if the method is not on the card
	 */
	protected int getValue(int[][][] deck, Season season) {
		return getValue(deck, 0, season);	
	}
	
}