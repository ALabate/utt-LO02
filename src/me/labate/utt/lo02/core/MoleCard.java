package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Season;
/**
 * Mole Card from AllyCard
 * @author Benoit
 *
 */
public class MoleCard extends AllyCard {
	
	private static final int[][][] deck = {
		// Mole cards
		{ {1, 1, 1, 1}},
		{ {0, 2, 2, 0}},
		{ {0, 1, 2, 1}}};
	/**
	 * constructor
	 * @param ID
	 */
	public MoleCard(int ID) {
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
		if(methodID != AllyMethod.MOLE)
			return -1;
		else
			return getValue(deck, 0, season);
	}
	@Override
	void useEffect(Player playing, Player target, int method, Game context) {
		if(method != AllyMethod.MOLE.ordinal())
			return;
		int points = this.getValue(AllyMethod.MOLE, context.getSeason());
		if(points >= 0) {
			int menhir = target.getMenhir();
			if(points > menhir) {
				points = menhir;
			}
			// Execute action
			target.setMenhir(menhir - points);
			// log the action to the context
			context.setLastAction(Action.MOLE, playing,
					null,  points,
					target, this, context.getSeason(), context.getYear());
		}
	}
}
