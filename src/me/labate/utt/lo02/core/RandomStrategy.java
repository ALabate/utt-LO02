/**
 * 
 */
package me.labate.utt.lo02.core;

import java.util.ArrayList;
import java.util.Random;

import me.labate.utt.lo02.core.Player.Method;


public class RandomStrategy extends Strategy {

	public RandomStrategy(Game context) {
		super(context);
	}

	@Override
	public int card() {
		ArrayList<Card> cards = context.getPlayers().get(context.currentPlayerID).getCards();
		// If cards are taken randomly so if we take them in the order, it is still random
		for(int c = 0; c < cards.size(); c++) {
			if(cards.get(c) != null) {
				return c;
			}
		}
		return -1;
	}

	@Override
	public Method method() {
		Random rand = new Random();
		int val = rand.nextInt(3);
		
		switch(val) {
		case 0: return Method.GIANT;
		case 1: return Method.FERTILIZER;
		default: return Method.LEPRECHAUN;
		}
	}

	@Override
	public int target() {
		Random rand = new Random();
		return rand.nextInt(context.getPlayers().size());
	}

}
