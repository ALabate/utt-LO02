package me.labate.utt.lo02.core;


import me.labate.utt.lo02.core.Game.Season;
/**
 * Abtract class of a Card
 * @author Alabate
 *
 */
public abstract class Card {

	/**
	 * Card ID in the card deck
	 */
	protected int cardID;

	/**
	 * create a card by setting the ID
	 * @param carID ID of the card you want to create
	 */

	protected Card(int cardID)
	{
		this.cardID = cardID;
	}
	/**
	 * Convert season enum to season id
	 * @param season the season enum value
	 */
	protected static int seasonToID(Season season) {
		switch(season) {
		case SPRING : return 0;
		case SUMMER : return 1;
		case AUTUMN : return 2;
		case WINTER : return 3;
		default : return -1;
		}
	}
	/**
	 * convert int to season
	 * @param integer
	 * @return season from integer
	 */
	protected static Season intToSeason(int integer){
		Season season;
		switch(integer){
		case 0: season = Season.INIT; break;
		case 1:season = Season.SPRING;break;
		case 2:season = Season.SUMMER;break;
		case 3:season = Season.AUTUMN;break;
		case 4:season = Season.WINTER;break;
		default:season = Season.INIT; 
		}
		return season;
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
	/**
	 * use the effect of the card
	 * @param playing 	: palyer who is playing
	 * @param target 	: player who is targeting
	 * @param method 	: the card's method needed to be played, use the different converter method to convert them to int
	 * @param context	: current game's data
	 */
	abstract void  useEffect(Player playing, Player target, int method, Game context );
}
