/**
 * 
 */
package me.labate.utt.lo02.core;

import java.util.ArrayList;
import java.util.Random;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;


public class RandomStrategy implements Strategy {


	/**
	 * Contain the current state of the game
	 */
	Game context;
	
	/**
	 * Constructor
	 * @param context Context of the game
	 */
	public RandomStrategy(Game context) {
		this.context = context;
	}
	
	@Override
	public int card() {
		ArrayList<IngredientCard> cards = context.getPlayers().get(context.currentPlayerID).getCards();
		// If cards are taken randomly so if we take them in the order, it is still random
		for(int c = 0; c < cards.size(); c++) {
			if(cards.get(c) != null) {
				return c;
			}
		}
		return -1;
	}

	@Override
	public IngredientMethod method() {
		Random rand = new Random();
		int val = rand.nextInt(3);
		
		switch(val) {
		case 0: return IngredientMethod.GIANT;
		case 1: return IngredientMethod.FERTILIZER;
		default: return IngredientMethod.LEPRECHAUN;
		}
	}

	@Override
	public int target() {
		Random rand = new Random();
		return rand.nextInt(context.getPlayers().size());
	}

}
