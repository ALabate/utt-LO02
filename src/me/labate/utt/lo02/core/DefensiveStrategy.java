package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

public class DefensiveStrategy extends AdvancedStrategy {

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
