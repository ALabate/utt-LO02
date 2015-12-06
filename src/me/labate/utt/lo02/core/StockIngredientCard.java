package me.labate.utt.lo02.core;

public class StockIngredientCard extends StockCard {

	public StockIngredientCard() {
		super();
	}

	@Override
	public void reset() {
		{
			if(!this.isEmpty())
				this.clear();
				for(int cardID = 0; cardID < IngredientCard.deckLength() ; cardID ++)
				{
					// add a card to the stock
					stock.add(new IngredientCard(cardID));
				}
				this.shuffle();
		}

	}

}
