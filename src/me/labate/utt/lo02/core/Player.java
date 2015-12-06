package me.labate.utt.lo02.core;

import java.util.ArrayList;

import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Choice;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

public abstract class Player {

	public enum Bonus { ALLY, SEEDS };
	
	
	

	//////////////////// Score : Attributes ////////////////////

	/**
	 * Contain the current state of the game
	 */
	private Game context;

	//////////////////// General : Methods ////////////////////
	
	/**
	 * Constructor
	 * @param context Data about the game
	 * @param name Name of the player
	 */
	protected Player(Game context, String name) {
		this.context = context;
		setName(name);
		ingredientCards = new ArrayList<IngredientCard>();
		reset();
	}		

	/**
	 * Reset everything except name and context
	 */
	public void reset() {
		this.allyCard = null;
		this.ingredientCards.clear();
		this.score = 0;
		this.seed = 0;
		this.menhir = 0;
	}

	/**
	 * Tell if its a bot or not
	 * @return true if its a bot
	 */
	public abstract boolean isBot();
	
	//////////////////// Score : Attributes ////////////////////
	/**
	 * Number of seeds the player has
	 * On the real game, it is pebbles next to the field card
	 */
	private int seed = 0;

	/**
	 * Number of menhir the player has
	 * On the real game, it is pebbles on to the field card
	 */
	private int menhir = 0;
	
	/**
	 * After the end of a year, the number of menhir is added to the score
	 */
	private int score = 0;
	
	/**
	 * The name of the player
	 */
	private String name;
	
	//////////////////// Score : Methods ////////////////////

	/**
	 * @return the seed
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * @param seed the seed to set
	 */
	protected void setSeed(int seed) {
		this.seed = seed;
	}

	/**
	 * @return the menhir
	 */
	public int getMenhir() {
		return menhir;
	}

	/**
	 * @param menhir the menhir to set
	 */
	protected void setMenhir(int menhir) {
		this.menhir = menhir;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	protected void setScore(int score) {
		this.score = score;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public void updateScore(){
		this.score += this.menhir;
		this.menhir = 0;
	}
	
	//////////////////// Actions methods ////////////////////
	

	/**
	 * If the player is a bot, it will do one of the actions
	 * @return true if an action has been done
	 */
	protected abstract boolean doAction();
	
	/**
	 * It will execute the action written on the card
	 * @param cardID The id of the card you want to use
	 * @param method The way you want to use the card
	 * @param target Id of the player you want to attack with leprechaun
	 */
	public void playIngredientCard(IngredientCard card, IngredientMethod method, Player target) {
		// Check if this choice is really needed and if the card belongs to this player
		if(!context.getNeededPlayer().equals(this) 
				|| context.getNeededChoice() != Choice.INGREDIENT
				|| !ingredientCards.contains(card)) {
			return;
		}

		switch(method) {
			case GIANT: // Give a number of seed
			{
				// Get data
				int points = card.getValue(IngredientMethod.GIANT, context.getSeason());
				// Execute action
				seed += points;
				// log the action to the context
				context.setLastAction(Action.GIANT, this, card, points, null, null, context.getSeason(), context.getYear());
				context.clearNeeded();
				break;
			}
			case FERTILIZER: // Convert a number of seed to menhir
			{
				// Get data
				int points = card.getValue(IngredientMethod.FERTILIZER, context.getSeason());
				if(this.seed < points) {
					points = this.seed;
				}
				// Execute action
				seed -= points;
				menhir += points;
				// log the action to the context
				context.setLastAction(Action.FERTILIZER, this, card, points, null, null, context.getSeason(), context.getYear());
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
				context.setLastAction(Action.LEPRECHAUN_REQUEST, this, card, -1, target, null, context.getSeason(), context.getYear());
				context.setNeeded(target, Choice.DEFEND);
				// If target has no ally card or we are in fast game, we can do the attack now
				if(target.getAllyCard() == null) {
					// Execute action
					target.chooseDefend(false);
				}
				break;
			}
		}
		// Remove used card
		ingredientCards.remove(card);
	}

	/**
	 * It will execute the action written on the card
	 * You cannot choose LEPRECHAUN with this method because it 
	 * needs a target but there is an overloaded method for that.
	 * @param cardID The id of the card you want to use
	 * @param method The way you want to use the card
	 */
	public void playIngredientCard(IngredientCard card, IngredientMethod method) {
		this.playIngredientCard(card, method, null);
	}
	
	/**
	 * This let you choose the bonus on the beginning of a year on a
	 * full game
	 * @param bonus
	 */
	public void chooseBonus(Bonus bonus) {
		// Check if this choice is really needed and if the card belongs to this player
		if(!context.getNeededPlayer().equals(this) 
				|| context.getNeededChoice() != Choice.BONUS) {
			return;
		}
		
		switch(bonus) {
			case ALLY:
			{
				// Execute action
				allyCard = (AllyCard)context.getStockAlly().giveACard();
				// log the action to the context
				context.setLastAction(Action.BONUS_ALLY, this, null, -1, null, allyCard, context.getSeason(), context.getYear());
				context.clearNeeded();
				break;
			}
			case SEEDS:
			{
				// Execute action
				seed += 2;
				// log the action to the context
				context.setLastAction(Action.BONUS_SEEDS, this, null, 2, null, null, context.getSeason(), context.getYear());
				context.clearNeeded();
				break;
			}
		}
	}
	

	public void chooseDefend(boolean defend) {
		// Check if this choice is really needed and if the card belongs to this player
		if(!context.getNeededPlayer().equals(this) 
				|| context.getNeededChoice() != Choice.DEFEND) {
			return;
		}

		
		// Get some data
		Player target = this;
		Player player = context.getLastPlayer();
		AllyCard card = null;
		int points = context.getLastIngredientCard().getValue(IngredientMethod.LEPRECHAUN, context.getLastSeason());
		
		// Check if the player can defend
		if(defend) {
			
			// Calculate points with defense
			int defendPoints = target.getAllyCard().getValue(AllyMethod.DOG, context.getLastSeason());
			if(defendPoints >= 0) {
				points -= defendPoints;
				if(points < 0) {
					points = 0;
				}
			}
			// save ally card
			card = target.getAllyCard();
			// Remove ally card
			target.allyCard = null;
			
		}
		// Check if the target has enough seeds
		if(target.getSeed() < points) {
			points = target.getSeed();
		}
			
		// Execute the action
		target.setSeed(target.getSeed() - points);
		player.setSeed(player.getSeed() + points);

		// log the action to the context
		context.setLastAction(Action.LEPRECHAUN, player,
				context.getLastIngredientCard(),  points,
				target, card, context.getLastSeason(), context.getLastYear());
		context.clearNeeded();
	}
	

	/**
	 * Execute a mole attack
	 * @param Player target of the attack
	 */
	public void chooseMoleAttack(Player target) {
		int points = allyCard.getValue(AllyMethod.MOLE, context.getSeason());
		if(points >= 0) {
			int menhir = target.getMenhir();
			if(points > menhir) {
				points = menhir;
			}
			// Execute action
			target.setMenhir(menhir - points);
			// log the action to the context
			context.setLastAction(Action.MOLE, this,
					null,  points,
					target, allyCard, context.getSeason(), context.getYear());
			// Remove ally card
			allyCard = null;
			
		}
	}
	
	
	//////////////////// IngredientCards : Attributes ////////////////////
	
	/**
	 * Contains the four card that the player has in his hand
	 */
	private ArrayList<IngredientCard> ingredientCards;
	
	//////////////////// IngredientCards : Methods ////////////////////

	/**
	 * Put a new card in the hand of the player
	 */
	void drawIngredientCard() {
		if(ingredientCards.size() >= 4) {
			return;
		}
		ingredientCards.add((IngredientCard)context.getStockIngredient().giveACard());
	}
	
	
	/**
	 * Return the list of ingredient cards
	 * @return ArrayList of IngredientCard
	 */
	public ArrayList<IngredientCard> getIngredientCards() {
		// We create another list to be sure no one edit it
		// However IngredientCard will still be editable but it should be protected
		ArrayList<IngredientCard> newList = new ArrayList<IngredientCard>();
		for(int i=0; i < ingredientCards.size() ; i++) {
			newList.add(ingredientCards.get(i));
		}
		return newList;
	}
	
	/**
	 * Get a ingredient card data from its id
	 * @param cardID the id of the card from 0 to getIngredientCardCount()
	 * @return the ingredient card, can be null
	 */
	public IngredientCard getIngredientCard(int cardID) {
		if(cardID >= 0 && cardID < ingredientCards.size()) {
			return ingredientCards.get(cardID);
		}
		return null;
	}
	
	/**
	 * Get the number of ingredient cards
	 * @return the ingredient cards count
	 */
	public int getIngredientCardCount() {
		return ingredientCards.size();
	}
	
	/**
	 * Get ingredient card ID from its object
	 * @param card The ingredient card
	 * @return the card id or -1 if the element is not inside
	 */
	public int getIngredientCardID(IngredientCard card) {
		return ingredientCards.indexOf(card);
	}
	
	//////////////////// AllyCards : Attributes ////////////////////	

	/**
	 * Contain an ally card if the player got one
	 * If not : null
	 */
	private AllyCard allyCard = null;

	//////////////////// AllyCards : Methods ////////////////////
	
	/**
	 * Get the ingredient card of the player
	 * @return the player, can be null
	 */
	public AllyCard getAllyCard() {
		return allyCard;
	}
	
	/**
	 * Tell if the player has an ally card
	 * @return true if the player has an ally card
	 */
	public boolean hasAllyCard() {
		return allyCard != null;
	}
	
	/**
	 * Tell if the player has the given ally card
	 * @param card The ally card
	 * @return true if user has this card
	 */
	public boolean hasAllyCard(AllyCard card) {
		return allyCard.equals(card);
	}	

}