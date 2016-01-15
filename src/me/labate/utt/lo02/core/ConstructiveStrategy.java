package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player.Bonus;
/**
 * Strategy which constructs menhir as faster as it can
 * @author Benoit
 *
 */
public class ConstructiveStrategy extends AdvancedStrategy {

	/**
	 * Constructor
	 * @param context Context of the game
	 */
	public ConstructiveStrategy(Game context) {
		super(context);
	}
	@Override
	public IngredientCard card() { 
		// return the most effective card for the current season, the current player and the method chosen by this strategy.
		return CardTheMostEffective(context.getNeededPlayer(), context.getSeason(), this.method());
	}
	
	@Override
	public IngredientMethod method() {
		IngredientMethod method = null;
		switch(context.getSeason()){ // choice depends on season
		case SPRING:
			// first season we want to increase the number of our seeds
				method = IngredientMethod.GIANT;
				if(context.getNeededPlayer().getSeed() > 3){
					method = IngredientMethod.FERTILIZER;
				}
				else{
					method = IngredientMethod.GIANT;
				}
				break;
		case SUMMER:
			//Second season we try to fertilize our seeds
					method = IngredientMethod.FERTILIZER;
					if(context.getNeededPlayer().getSeed() > 2){
						method = IngredientMethod.FERTILIZER;
					}
					else{
						method = IngredientMethod.GIANT;
					}
			break;
		case AUTUMN:
			// Third season get more seed
				method = IngredientMethod.GIANT;
				if(context.getNeededPlayer().getSeed() > 2){
					method= IngredientMethod.FERTILIZER;
				}
				else{
					method = IngredientMethod.GIANT;
				}
			break;
		case WINTER:
			//Last season we try to fertilize our seeds
					method = IngredientMethod.FERTILIZER;
					// check if there is another year left
					if(context.getYear() < context.getYearCount() - 1){
						if(context.getNeededPlayer().getSeed() > 1){
							// player has more than 1 seed
							method = IngredientMethod.FERTILIZER;
						}
						else{ // no seed left
							method = IngredientMethod.GIANT;
						}
					}
					else{ // it's the last year of this game
						method = IngredientMethod.FERTILIZER;
					}
			break;
		default: // other case send null
			method = null;
			break;
		}
		return method;
	}


	@Override
	public Bonus bonus() {
		//  constructive always choose seeds option
			return Bonus.SEEDS;
	}

	@Override
	public boolean defend() {
		// we are not suppose to have an ally card with this method
		return false;
	}

	@Override
	public boolean moleAttack(Player playing) {
		// we are not suppose to have an ally card with this method
		return false;
	}

}
