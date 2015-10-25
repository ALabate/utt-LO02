/**
 * 
 */
package me.labate.utt.lo02.core;

/**
 * @author alabate
 *
 */
public class BotPlayer extends Player {


	/**
	 * Bot difficulty
	 * from 0 to 10 with 0 the easier
	 */
	private int level = 5;
	
	private Strategy strategy = null;
	
	public BotPlayer(Game context, String name, int level) {
		super(context, name);
		if(level >= 0 && level <= 10) {
			this.level = level;
		}
	}
	
	@Override
	public void doChoice() {
		// Select strategy
		// As we have only one currently we use the random one
		if(strategy == null) {
			strategy = new RandomStrategy(context);
		}
		
		//Use strategy to do choices
		switch(this.getNeededChoice()) {
		case BONUS:
			// TODO
			break;
		case CARD:
			this.chooseCard(strategy.card(), strategy.method(), strategy.target());
			break;
		case DEFENSE:
			// TODO
			break;
		default:
			break;
		}
		
	}

}
