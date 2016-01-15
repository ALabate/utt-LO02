/**
 * 
 */
package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
/**
 * A player controlled by the IA
 * @author Benoit, Alabate
 *
 */
public class BotPlayer extends Player {

	/**
	 * Bot difficulty
	 * from 0 to 10 with 0 the easier
	 */
	private int level = 5;
	/**
	 * get the level of this palyer
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Current used strategy
	 */
	private Strategy strategy = null;
	
	/**
	 * Current game context
	 */
	private Game context; // TODO ask @author Alabate why
	
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
		boolean result = false;
		if(!context.getNeededPlayer().equals(this)) {
			return result;
		}
		
		// Select strategy
		if(strategy == null) {
			if(this.level <= 1){
				strategy = new RandomStrategy(context);
			}
			else{
				IngredientMethod method = AdvancedStrategy.methodTheMostEffective(this);
				// look at the most effective method of the player
				switch(method){
				case GIANT:
					strategy = new DefensiveStrategy(context);
					break;
				case FERTILIZER:
					strategy = new ConstructiveStrategy(context);
					break;
				default:
					strategy = new  AgressiveStrategy(context);
					break;
				}
			}
		}
		//Use strategy to do choices
		switch(context.getNeededChoice()) {
			case BONUS:
				Bonus bonus = strategy.bonus();
				if(bonus == null)
					throw new NullPointerException("strategy.bonus() give null pointer");
				this.chooseBonus(bonus);
				result = true;
				break;
			case INGREDIENT:
				Card card = strategy.card();
				IngredientMethod method = strategy.method();
				Player target = strategy.target();
				if(card == null)
					throw new NullPointerException("strategy.card() give null pointer");
				if(method == null)
					throw new NullPointerException("strategy.method() give null pointer");
				if(target == null)
					throw new NullPointerException("strategy.target() give null pointer");
				this.playIngredientCard((IngredientCard)card, method, target);
				result = true;
				break;
			case DEFEND:
				boolean defend = strategy.defend();
				this.chooseDefend(defend);
				result = true;
				break;
			case MOLE:
				if(strategy.moleAttack(this)) {
					this.chooseMoleAttack(strategy.moleAttackTarget(this));
					result = true;
				}
				break;
			default:
				result = false;
		}
		return result;
	}

	@Override
	public boolean isBot() {
		return true;
	}
	@Override
	public void reset(){ // a bot has to reset his strategy
		super.reset();
		strategy = null;
	}
}