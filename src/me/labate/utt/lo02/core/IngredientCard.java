package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Choice;
import me.labate.utt.lo02.core.Game.Season;
/**
 * Ingredient Card of the game
 * @author Alabate,Benoit
 *
 */
public class IngredientCard extends Card {

	/**
	 * Enumeration of method which can be played by an Ingredient card
	 * @author Alabate,Benoit
	 *
	 */
	public enum IngredientMethod { GIANT, FERTILIZER, LEPRECHAUN };
	/**
	 * convert int to IngredientMethod
	 * @param integer
	 * @return Ingredient method from int
	 */
	public static IngredientMethod intToIngredientMethod(int integer){
		IngredientMethod method;
		switch(integer){
		case 0:method = IngredientMethod.GIANT;break;
		case 1:method = IngredientMethod.FERTILIZER;break;
		default:method = IngredientMethod.LEPRECHAUN;break;
		}
		return method;
	}
	/**
	 * convert IngredientCard to int
	 * @param method Enumeration of Ingredient Method
	 * @return value of the Enumeration of Ingredient Method
	 */
	public static int IngredientMethodToInt(IngredientMethod method){
		int value;
		switch(method){
		case GIANT:		value = 0;break;
		case FERTILIZER:value = 1;break;
		default:		value = 2;break;
		}
		return value;
	}

	/**
	 * List of cards that contains methods that contains value for each season
	 */
	private static final int[][][] deck = {
		{{1, 1, 1, 1}, {2, 0, 1, 1}, {2, 0, 2, 0}},
		{{2, 0, 1, 1}, {1, 3, 0, 0}, {0, 1, 2, 1}},
		{{0, 0, 4, 0}, {0, 2, 2, 0}, {0, 0, 1, 3}},
		{{1, 3, 1, 0}, {1, 2, 1, 1}, {0, 1, 4, 0}},
		{{2, 1, 1, 1}, {1, 0, 2, 2}, {3, 0, 0, 2}},
		{{1, 2, 2, 0}, {1, 1, 2, 1}, {2, 0, 1, 2}},
		{{2, 1, 1, 2}, {1, 1, 1, 3}, {2, 0, 2, 2}},
		{{0, 3, 0, 3}, {2, 1, 3, 0}, {1, 1, 3, 1}},
		{{1, 2, 1, 2}, {1, 0, 1, 4}, {2, 4, 0, 0}},
		{{1, 3, 1, 2}, {2, 1, 2, 2}, {0, 0, 3, 4}},
		{{2, 2, 0, 3}, {1, 1, 4, 1}, {1, 2, 1, 3}},
		{{2, 2, 3, 1}, {2, 3, 0, 3}, {1, 1, 3, 3}},
		{{2, 2, 3, 1}, {2, 3, 0, 3}, {1, 1, 3, 3}},
		{{2, 2, 2, 2}, {0, 4, 4, 0}, {1, 3, 2, 2}},
		{{3, 1, 3, 1}, {1, 4, 2, 1}, {2, 4, 1, 1}},
		{{4, 1, 1, 1}, {1, 2, 1, 3}, {1, 2, 2, 2}},
		{{2, 3, 2, 0}, {0, 4, 3, 0}, {2, 1, 1, 3}},
		{{2, 2, 3, 0}, {1, 1, 1, 4}, {2, 0, 3, 2}},
		{{3, 1, 4, 1}, {2, 1, 3, 3}, {2, 3, 2, 2}},
		{{2, 4, 1, 2}, {2, 2, 2, 3}, {1, 4, 3, 1}},
		{{3, 3, 3, 0}, {1, 3, 3, 2}, {2, 3, 1, 3}},
		{{1, 2, 2, 1}, {1, 2, 3, 0}, {0, 2, 2, 2}},
		{{4, 0, 1, 1}, {1, 1, 3, 1}, {0, 0, 3, 3}},
		{{2, 0, 1, 3}, {0, 3, 0, 3}, {1, 2, 2, 1}}}; 


	/**
	 * Constructor setting the ID
	 * @param ID
	 */
	public IngredientCard(int ID)
	{
		super(ID);
	}
	/**
	 * Get the card value for the given method on the given season
	 * @param method The selected method
	 * @param season The selected season
	 * @return the value or -1 if the method is not on the card
	 */
	public int getValue(IngredientMethod methodID, Season season) {
		return getValue(deck, methodID.ordinal(), season);
	}
	/**
	 * number of cards in the deck
	 * @return max of ID tou can create from the current deck
	 */
	public static int deckLength(){
		return deck.length;
	}
	
	@Override
	void useEffect(Player playing, Player target, int method, Game context ){
		switch(intToIngredientMethod(method)) {
			case GIANT: // Give a number of seed
			{
				// Get data
				int points = this.getValue(IngredientMethod.GIANT, context.getSeason());
				// Execute action
				playing.setSeed(playing.getSeed() + points);
				// log the action to the context
				context.setLastAction(Action.GIANT, playing, this, points, null, null, context.getSeason(), context.getYear());
				context.clearNeeded();
				break;
			}
			case FERTILIZER: // Convert a number of seed to menhir
			{
				// Get data
				int points = this.getValue(IngredientMethod.FERTILIZER, context.getSeason());
				if(playing.getSeed() < points) {
					points = playing.getSeed();
				}
				// Execute action
				playing.setSeed(playing.getSeed() - points);
				playing.setMenhir(playing.getMenhir() + points);
				// log the action to the context
				context.setLastAction(Action.FERTILIZER, playing, this, points, null, null, context.getSeason(), context.getYear());
				context.clearNeeded();
				break;
			}
			case LEPRECHAUN:
			{
				// Check if the target is a member of the game
				if(context.getPlayerID(target) < 0) {
					return;
				}
				// log the action to the context
				context.setLastAction(Action.LEPRECHAUN_REQUEST, playing, this, -1, target, null, context.getSeason(), context.getYear());
				context.setNeeded(target, Choice.DEFEND);
				// If target has no ally card or we are in fast game, we can do the attack now
				if(target.getAllyCard() == null) {
					// Execute action
					target.chooseDefend(false);
				}
				break;
			}
		}
	}
	/**
	 * execute the action of the Leprechaun Method of an IngredientCard
	 * @param playing : player using LeprechaunMethod
	 * @param target  : player who is stolen by player playing
	 * @param context : context of the game
	 */
	void finishLeprechaun(Player playing, Player target, Game context){
		
		int points = this.getValue(IngredientMethod.LEPRECHAUN, context.getLastSeason());
		// Check if the target has enough seeds
				if(target.getSeed() < points) {
					points = target.getSeed();
				}
					
				// Execute the action
				target.setSeed(target.getSeed() - points);
				playing.setSeed(playing.getSeed() + points);

				// log the action to the context
				context.setLastAction(Action.LEPRECHAUN, playing,
						this,  points,
						target, null, context.getLastSeason(), context.getLastYear());
				context.clearNeeded();
	}
}