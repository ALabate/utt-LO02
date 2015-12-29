package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
/**
 * get the max of seeds and try to build menhirs.
 * @author Benoit
 *
 */
public class DefensiveStrategy extends AdvancedStrategy {

	/**
	 * constructor
	 * @param context
	 */
	public DefensiveStrategy(Game context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IngredientCard card() {
		return CardTheMostEffective(context.getNeededPlayer(), context.getSeason(), this.method());
	}

	@Override
	public IngredientMethod method() {
		Strategy strategy = new ConstructiveStrategy(context);
		return strategy.method();
	}

}
