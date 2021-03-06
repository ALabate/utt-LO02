package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
/**
 * Strategy which use more Leprechaun methods and do not hesitate to use mole
 * @author Benoit
 *
 */
public class AgressiveStrategy extends AdvancedStrategy {
	/**
	 * Constructor
	 * @param context
	 */
	public AgressiveStrategy(Game context) {
		super(context);
	}
	@Override
	public IngredientMethod method(){
		IngredientMethod method;
		Player player = context.getNeededPlayer();
		if(player.getSeed() > 0)
			method = IngredientMethod.FERTILIZER;
		else
			method = IngredientMethod.LEPRECHAUN;
		return method;
		
	}
	@Override
	public IngredientCard card() { 
		// return the most effective card for the current season, the current player and the method chosen by this strategy.
		return CardTheMostEffective(context.getNeededPlayer(), context.getSeason(), this.method());
	}
	@Override
	public boolean moleAttack(Player playing) {
		if(context.getNeededPlayer().hasAllyCard()){
			AllyCard ally = context.getNeededPlayer().getAllyCard();
			// check if ally is a dog
			int points = ally.getValue(AllyMethod.DOG, context.getSeason());
			if(points > 0) {
				// check if someone has more menhir than this player
				Player player = whoHasMoreMenhir(playing);
				if(player.getMenhir() > 0)
					return true;
			}
		}
		return false;
	}
}
