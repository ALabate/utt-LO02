/**
 * 
 */
package me.labate.utt.lo02.core;

public class BotPlayer extends Player {


	/**
	 * Bot difficulty
	 * from 0 to 10 with 0 the easier
	 */
	private int level = 5;
	
	/**
	 * Current used strategy
	 */
	private Strategy strategy = null;
	
	/**
	 * Current game context
	 */
	private Game context;
	
	/**
	 * Constructor
	 * @param context Game context
	 * @param name Player name
	 * @param level Bot level from 0 to 10
	 */
	public BotPlayer(Game context, String name, int level) {
		super(context, name);
		if(level >= 0 && level <= 10) {
			this.level = level;
		}
		this.context = context;
	}

	@Override
	protected boolean doAction() {
		// Exit if it's not our turn to do an action
		if(!context.getNeededPlayer().equals(this)) {
			return false;
		}
		
		// Select strategy
		// As we have only one currently we use the random one
		if(strategy == null) {
			strategy = new RandomStrategy(context);
		}
		
		//Use strategy to do choices
		switch(context.getNeededChoice()) {
			case BONUS:
				this.chooseBonus(strategy.bonus());
				return true;
			case INGREDIENT:
				this.playIngredientCard(strategy.card(), strategy.method(), strategy.target());
				return true;
			case DEFEND:
				this.chooseDefend(strategy.defend());
				return true;
			case MOLE:
				if(strategy.moleAttack()) {
					this.chooseMoleAttack(strategy.moleAttackTarget());
					return true;
				}
				return false;
			default:
				return false;
		}
	}

	@Override
	public boolean isBot() {
		return true;
	}
}
