package me.labate.utt.lo02.core;

import java.util.Random;
import me.labate.utt.lo02.core.Game.Season;

public class Card {

	/**
	 * Contain the current state of the game
	 */
	private Game context;
	
	/**
	 * Card ID in the card deck
	 */
	private int cardID;
	
	/**
	 * Array of Giant values per card
	 */
	private static final int[][] giantValues = {{1, 1, 1, 1}, {2, 0, 1, 1}, {0, 0, 4, 0}, {1, 3, 1, 0}, {2, 1, 1, 1}, {1, 2, 2, 0}, {2, 1, 1, 2}, {0, 3, 0, 3}, {1, 2, 1, 2}, {1, 3, 1, 2}, {2, 2, 0, 3}, {2, 2, 3, 1}, {2, 2, 3, 1}, {2, 2, 2, 2}, {3, 1, 3, 1}, {4, 1, 1, 1}, {2, 3, 2, 0}, {2, 2, 3, 0}, {3, 1, 4, 1}, {2, 4, 1, 2}, {3, 3, 3, 0}, {1, 2, 2, 1}, {4, 0, 1, 1}, {2, 0, 3, 1}};
	

	/**
	 * Array of Fertilizer values per card
	 */
	private static final int[][] fertilizerValues = {{2, 0, 1, 1}, {1, 3, 0, 0}, {0, 2, 2, 0}, {1, 2, 1, 1}, {1, 0, 2, 2}, {1, 1, 2, 1}, {1, 1, 1, 3}, {2, 1, 3, 0}, {1, 0, 1, 4}, {2, 1, 2, 2}, {1, 1, 4, 1}, {2, 3, 0, 3}, {2, 3, 0, 3}, {0, 4, 4, 0}, {1, 4, 2, 1}, {1, 2, 1, 3}, {0, 4, 3, 0}, {1, 1, 1, 4}, {2, 1, 3, 3}, {2, 2, 2, 3}, {1, 3, 3, 2}, {1, 2, 3, 0}, {1, 1, 3, 1}, {0, 3, 0, 3}};
	

	/**
	 * Array of leprechaun values per card
	 */
	private static final int[][] leprechaunValues = {{2, 0, 2, 0}, {0, 1, 2, 1}, {0, 0, 1, 3}, {1, 0, 4, 0}, {3, 0, 0, 2}, {2, 0, 1, 2}, {2, 0, 2, 2}, {1, 1, 3, 1}, {2, 4, 0, 0}, {0, 0, 3, 4}, {1, 2, 1, 3}, {1, 1, 3, 3}, {1, 1, 3, 3}, {1, 3, 2, 2}, {2, 4, 1, 1}, {1, 2, 2, 2}, {2, 1, 1, 3}, {2, 0, 3, 2}, {2, 3, 2, 2}, {1, 4, 3, 1}, {2, 3, 1, 3}, {0, 2, 2, 2}, {0, 0, 3, 3}, {1, 2, 2, 1}};
	
	/**
	 * Constructor
	 * @param context Context of the game
	 */
	public Card(Game context) {
		this.context = context;

		// TODO Replace this with an algorithm that forbid to 
		// give the same card on the same game by storing in context an array
		Random rand = new Random();
		this.cardID = rand.nextInt(24);
	}
	
	/**
	 * Convert season enum to id
	 * @param season the season enum value
	 */
	private int seasonToID(Season season) {
		switch(season) {
		case SPRING : return 0;
		case SUMMER : return 1;
		case AUTUMN : return 2;
		case WINTER : return 3;
		default : return -1;
		}
	}
	
	/**
	 * Get the card value for giant on the given season
	 * @param season The selected season
	 * @return the value
	 */
	public int getGiantValue(Season season) {
		return giantValues[cardID][seasonToID(season)];		
	}
	
	/**
	 * Get the card value for fertilizer on the given season
	 * @param season The selected season
	 * @return the value
	 */
	public int getFertilizerValue(Season season) {
		return fertilizerValues[cardID][seasonToID(season)];		
	}
	
	/**
	 * Get the card value for leprechaun on the given season
	 * @param season The selected season
	 * @return the value
	 */
	public int getLeprechaunValue(Season season) {
		return leprechaunValues[cardID][seasonToID(season)];		
	}
	
	
}
