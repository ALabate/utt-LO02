package me.labate.utt.lo02.core;

import java.util.ArrayList;
import java.util.List;

import me.labate.utt.lo02.core.Game.Action;


public abstract class Player {

	public enum Choice { NOTHING, BONUS, CARD, DEFENSE };
	public enum Method { GIANT, FERTILIZER, LEPRECHAUN };
	
	/**
	 * Contain the current state of the game
	 */
	protected Game context;
	
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
	protected String name;
		
	
	/**
	 * Set the choice that the player has to do so the game continue
	 */
	private Choice neededChoice = Choice.NOTHING;

	/**
	 * Contains the four card that the player has in his hand
	 */
	private ArrayList<Card> cards;

	public Player(Game context, String name) {
		this.context = context;
		this.name = name;
		cards = new ArrayList<Card>();
	}
		

	/**
	 * Put a new card in the hand of the player
	 */
	public void drawCard() {
		if(cards.size() >= 4) {
			return;
		}
		cards.add(new Card(context));
	}
	
	/**
	 * If the player is a bot, it will do the needed choice
	 */
	public abstract void doChoice();
	
	/**
	 * It will execute the action written on the card
	 * @param cardID The id of the card you want to use
	 * @param method The way you want to use the card
	 * @param target Id of the player you want to attack with leprechaun
	 */
	public void chooseCard(int cardID, Method method, int target) {
		if(this.neededChoice != Choice.CARD) {
			// We didn't need to choose a card..
			return;
		}
		// check if card is valid and not removed
		if(cards.get(cardID) == null) {
			// TODO better error..
			return;
		}
		switch(method) {
		case GIANT:
			// Give a number of seed
			this.seed += cards.get(cardID).getGiantValue(context.season);
			// Write the action to the context
			context.lastAction = Game.Action.GIANT;
			context.lastPlayerID = context.getCurrentPlayerID();
			context.lastTargetID = -1;
			context.lastSeason = context.season;
			context.lastYear = context.year;
			context.lastCard = cards.get(cardID);
			context.lastPoints = cards.get(cardID).getGiantValue(context.season);
			break;
		case FERTILIZER:
			// Convert a number of seed to menhir
			int value = cards.get(cardID).getFertilizerValue(context.season);
			if(this.seed < value) {
				value = this.seed;
			}
			seed -= value;
			menhir += value;
			// Write the action to the context
			context.lastAction = Game.Action.FERTILIZER;
			context.lastPlayerID = context.getCurrentPlayerID();
			context.lastTargetID = -1;
			context.lastSeason = context.season;
			context.lastYear = context.year;
			context.lastCard = cards.get(cardID);
			context.lastPoints = value;
			break;
		case LEPRECHAUN:
			// Take seeds from another player
			if(target < 0 || target >= this.context.players.size()) {
		        throw new IllegalArgumentException();
			}
			// Check if we can already realize the action without having to ask
			Player targetPlayer = context.getPlayers().get(target);
			if(targetPlayer.getAllyCard() == null) {
				context.lastPoints = cards.get(cardID).getLeprechaunValue(context.season);
				Player currentPlayer = context.getPlayers().get(context.getCurrentPlayerID());
				// The target has not defense card or game in fast mode
				context.lastAction = Action.LEPRECHAUN;
				if(targetPlayer.getSeed() < context.lastPoints) {
					context.lastPoints = targetPlayer.getSeed();
				}
				targetPlayer.setSeed(targetPlayer.getSeed() - context.lastPoints);
				currentPlayer.setSeed(currentPlayer.getSeed() + context.lastPoints);
				
			}
			else {
				// We have to ask the target if he want to defend
				context.lastAction = Action.LEPRECHAUN_REQUEST;
				context.lastPoints = -1;
			}
			// No mather what we've done, theses informations doen't change
			context.lastPlayerID = context.getCurrentPlayerID();
			context.lastTargetID = target;
			context.lastSeason = context.getSeason();
			context.lastYear = context.getYear();
			context.lastCard = cards.get(cardID);
			break;
		}
		// Remove used card
		cards.set(cardID, null);
		// Confirm the choice has been made
		this.neededChoice = Choice.NOTHING;
		
	}
	private Object getAllyCard() {
		// TODO really return ally card
		return null;
	}


	/**
	 * It will execute the action written on the card
	 * You cannot choose LEPRECHAUN with this method because it 
	 * needs a target but there is an overloaded method for that.
	 * @param cardID The id of the card you want to use
	 * @param method The way you want to use the card
	 */
	public void chooseCard(int cardID, Method method) {
		this.chooseCard(cardID, method, -1);
	}

	/**
	 * neededChoice getter : get the choice that the player has to do so the game continue
	 * @return the choice that the player has to do so the game continue
	 */
	public Choice getNeededChoice() {
		return neededChoice;
	}

	/**
	 * neededChoice setter : set the choice that the player has to do so the game continue
	 * @param neededChoice the choice that the player has to do so the game continue
	 */
	public void setNeededChoice(Choice neededChoice) {
		this.neededChoice = neededChoice;
	}
	
	/**
	 * @return the seed
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * @param seed the seed to set
	 */
	public void setSeed(int seed) {
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
	public void setMenhir(int menhir) {
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
	public void setScore(int score) {
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

	protected ArrayList<Card> getCards() {
		return cards;
	}
}
