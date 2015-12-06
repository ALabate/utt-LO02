/**
 * 
 */
package me.labate.utt.lo02.core;

/**
 * @author Benoit
 *
 */
public class StockAllyCard extends StockCard {

	/**
	 * Constructor
	 */
	public StockAllyCard() {
		super();
	}

	/*
	 * @see me.labate.utt.lo02.core.StockCard#reset()
	 */
	@Override
	public void reset() {
		{
		if(!this.isEmpty())
			this.clear();
			for(int cardID = 0; cardID < AllyCard.deckLength() ; cardID ++)
			{
				// add a card to the stock
				stock.add(new AllyCard(cardID));
			}
			this.shuffle();
	}

	}

}
