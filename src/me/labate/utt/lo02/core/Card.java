package me.labate.utt.lo02.core;

import java.util.ArrayList;
import java.util.Random;
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
	 * Constructor
	 * @param context Context of the game
	 */
	public Card(Game context, int[][][] deck) {
		this.context = context;
		
		// Create the list of 'not used card' if necessary
		if(!context.cardLeft.containsKey(getClass().getName())) {
			context.cardLeft.put(getClass().getName(), new ArrayList<Integer>());
			for(int i = 0; i < deck.length ; i++) {
				context.cardLeft.get(getClass().getName()).add(i);
			}
		}
		ArrayList<Integer> cardLeft = context.cardLeft.get(getClass().getName());
		
		// Pick a random card ID
		Random rand = new Random();
		int index = rand.nextInt(cardLeft.size());
		this.cardID = cardLeft.get(index);

		// Remove the card from left cards
		cardLeft.remove(index);
		
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
		if(deck[cardID][methodID] == null)
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
