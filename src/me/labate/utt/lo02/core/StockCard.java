package me.labate.utt.lo02.core;

import java.util.LinkedList;
import java.util.Random;
/**
 * abstract stok of cards, as cards exist before they get drawn by a payer
 * @author Benoit
 *
 */
public abstract class StockCard {
	/**
	 * Queue of cards, is the most representative fr a stock.
	 */
	protected LinkedList<Card> stock = new LinkedList<Card>(); 
	/**
	 * Constructor : fill the stock and shuffle it.
	 */
	public StockCard()
	{
		this.reset();
	}
	/**
	 * shuffle the stock of card
	 */
	protected void shuffle(){
				Random rd = new Random();
				Card cardA,cardB;
				int A,B; // card's position in the list
				for(int i = 0; i < 1000000; i++)
				{
					A = rd.nextInt(stock.size());
					cardA = stock.get(A);
					B = rd.nextInt(stock.size());
					cardB = stock.get(B);
					stock.set(B, cardA);
					stock.set(A, cardB);
				}
	}
	/**
	 * refill the the stock by all the card
	 */
	public abstract void reset();
	/**
	 * give the card which is is on the top of the stock
	 * @return the card on the top of the stock or null if empty
	 */
	public Card giveACard() // return null if empty
	{
		return stock.poll();
	}
	/**
	 * check if the stock is empty
	 * @return true if the stock is empty
	 */
	public boolean isEmpty()
	{
		return stock.isEmpty();
	}
	/**
	 * remove all the cards in the stocks
	 */
	public void clear()
	{
		stock.clear();
	}
}
