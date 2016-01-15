/**
 * 
 */
package me.labate.utt.lo02.core;

/**
 * stock of AllyCard
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

	@Override
	public void reset() {
		{
		if(!this.isEmpty())
			this.clear();
		//fill with DogCard
		for(int cardID = 0; cardID < DogCard.deckLength() ; cardID ++)
		{
			// add a card to the stock
			stock.add(new DogCard(cardID));
		}
		//fill with MoleCard
		for(int cardID = 0; cardID < MoleCard.deckLength() ; cardID ++)
		{
			// add a card to the stock
			stock.add(new MoleCard(cardID));
		}
		this.shuffle();
	}

	}

}
