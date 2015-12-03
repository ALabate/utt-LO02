/**
 * 
 */
package me.labate.utt.lo02.core;

import java.util.Random;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player.Bonus;


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
	public IngredientCard card() {
		Random rand = new Random();
		return context.getNeededPlayer().getIngredientCard(rand.nextInt(context.getNeededPlayer().getIngredientCardCount()));
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
	public Player target() {
		Random rand = new Random();
		return context.getPlayer(rand.nextInt(context.getPlayerCount()));
	}

	@Override
	public Bonus bonus() {
		Random rand = new Random();
		Bonus[] array = Bonus.values();
		return array[rand.nextInt(array.length)];
	}

	@Override
	public boolean defend() {
		Random rand = new Random();
		return (rand.nextInt(1) == 0);
	}

	@Override
	public boolean moleAttack() {
		Random rand = new Random();
		return (rand.nextInt(1) == 0);
	}

	@Override
	public Player moleAttackTarget() {
		Random rand = new Random();
		return context.getPlayer(rand.nextInt(context.getPlayerCount()));
	}

}