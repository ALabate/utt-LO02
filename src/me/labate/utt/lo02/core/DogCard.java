package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
/**
 * Dog Card from AllyCard
 * @author Benoit
 *
 */
public class DogCard extends AllyCard {

	/**
	 * List of cards that contains methods that contains value for each season
	 */
	private static final int[][][] deck = {
		// Dog cards
		{ {2, 0, 2, 0} },
		{ {1, 2, 0, 1} },
		{ {0, 1, 3, 0} }};
	/**
	 * constructor
	 * @param ID of the card you want to create
	 */
	public DogCard(int ID) {
		super(ID);
	}
	/**
	 * number of cards in the deck
	 * @return max of ID tou can create from the current deck
	 */
	public static int deckLength(){
		return deck.length;
	}
	@Override
	public int getValue(AllyMethod methodID, Season season) {
		if(methodID != AllyMethod.DOG)
			return -1; // return -1 if the method asked is not DOG
		else
			return getValue(deck, 0, season);
	}
	/**
	 * defend with DogCard after a Leprechaun request
	 * @param playing : player who attacks target whit Leprechaun Method
	 * @param target  : player who wants to use his DogCard
	 * @param method  : give int AllyMethod.DOG.ordinal() if you want this method to work, else it would not do anything
	 * @param conext  : context of the game, a Leprechaun request must have been lunched to use this method
	 */
	@Override
	void useEffect(Player playing, Player target, int method, Game context) {
		if( method != AllyMethod.DOG.ordinal())
			return; // make sure the method program wants to play is the right
		// Get some data
		int points = context.getLastIngredientCard().getValue(IngredientMethod.LEPRECHAUN, context.getLastSeason());

		// Calculate points with defense
		int defendPoints = this.getValue(AllyMethod.DOG, context.getLastSeason());
		if(defendPoints >= 0) {
			points -= defendPoints;
			if(points < 0) {
				points = 0;
			}
		}
		// Check if the target has enough seeds
		if(target.getSeed() < points) {
			points = target.getSeed();
		}
				
		// Execute the action
		target.setSeed(target.getSeed() - points);
		playing.setSeed(playing.getSeed() + points);

		// log the action to the context
		context.setLastAction(Action.LEPRECHAUN, playing,
				context.getLastIngredientCard(),  points,
				target, this, context.getLastSeason(), context.getLastYear());
		context.clearNeeded();
	}
}
