/**
 * 
 */
package me.labate.utt.lo02.core;
import java.util.ArrayList;

import me.labate.utt.lo02.core.Player.Choice;
import me.labate.utt.lo02.core.Player.Method;

/**
 * API to play one game without being able to break the rules
 */
public abstract class Game {
	
	public enum Season { INIT, SPRING, SUMMER, AUTUMN, WINTER };
	public enum Action { NOTHING, GIANT, FERTILIZER, LEPRECHAUN_REQUEST, LEPRECHAUN, MOLE };
	
	// TODO add constraint 2 to 6 players
	
	/**
	 * Player list
	 */
	protected ArrayList<Player> players;
	
	/**
	 * Current year starting from one to < yearsEnd
	 */
	protected int year;
	
	/**
	 * Number of years in the game.
	 * 
	 * A game has 1 year in fast mode else players.size()
	 */
	protected int yearCount;
	
	/**
	 * Current season.
	 * 
	 * A year has 5 season (yes with the initialization turn)
	 */
	protected Season season;
	
	/**
	 * Id of the current playing player
	 */
	protected int currentPlayerID;
	
	/**
	 * Last action done by a player
	 */
	protected Action lastAction = Action.NOTHING;
	
	/**
	 * Year of the last action
	 */
	protected int lastYear;
	
	/**
	 * Season of the last action
	 */
	protected Season lastSeason;
	
	/**
	 * the target player id of the last action.
	 * 
	 * If there is none : -1
	 */
	protected int lastTargetID;
	
	/**
	 * the player id that have done the last action
	 */
	protected int lastPlayerID;
	
	/**
	 * Seeds or menhir value of the action.
	 * 
	 * Generally it will be what's written on the card but
	 * with leprechaun, you can have less if the target hasn't 
	 * enough seeds
	 */
	protected int lastPoints;
	/**
	 * Card used on the last action
	 */
	protected Card lastCard;

	// TODO Add Card part
	/**
	 * Ally card used on the last action
	 */
//	protected AllyCard lastAllyCard;
	
	/**
	 * Reset the game and score without deleting players
	 */
	public abstract void reset();
	
	/**
	 * Start the next action in the game and stop when an
	 * user action is required on the user interface
	 * @return false if the game is over
	 */
	public abstract boolean next();
	

	/**
	 * @return the year
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * @return the yearCount
	 */
	public int getYearCount() {
		return this.yearCount;
	}

	/**
	 * @return the season
	 */
	public Season getSeason() {
		return season;
	}

	/**
	 * @return the currentPlayer ID
	 */
	public int getCurrentPlayerID() {
		return currentPlayerID;
	}
	
	/**
	 * @return the Player list
	 */
	protected ArrayList<Player> getPlayers() {
		return players;
	}
	
	
	//////////////////// Last action API ///////////////

	/**
	 * @return the lastAction
	 */
	public Action getLastAction() {
		return lastAction;
	}
	
	/**
	 * @return the lastYear
	 */
	public int getLastYear() {
		return lastYear;
	}
	
	/**
	 * @return the lastSeason
	 */
	public Season getLastSeason() {
		return lastSeason;
	}
	
	/**
	 * @return the lastTarget
	 */
	public int getLastTargetID() {
		return lastTargetID;
	}
	
	/**
	 * @return the lastPlayer
	 */
	public int getLastPlayerID() {
		return lastPlayerID;
	}
	
	/**
	 * @return the lastPoints
	 */
	public int getLastPoints() {
		return lastPoints;
	}
	
	/**
	 * @return the lastCard
	 */
	public Card getLastCard() {
		return lastCard;
	}
	
	/**
	 * @return the lastAllyCard
	 */
//	public AllyCard getLastAllyCard() {
//		return lastAllyCard;
//	}

	
	//////////////////// Player API ////////////////////
	
	/**
	 * Add an human player to the player list
	 * @param name The player name
	 */
	public void addHuman(String name) {
		this.players.add(new HumanPlayer(this, name));
	}
	
	/**
	 * Add a computer player to the player list
	 * @param name The player name
	 * @param level The level of the bot from 0 to 10 with 0 the easier
	 */
	public void addBot(String name, int level) {
		this.players.add(new BotPlayer(this, name, level));
	}
	
	/**
	 * It will execute the action written on the card for the current user
	 * @param cardID The id of the card you want to use
	 * @param method The way you want to use the card
	 * @param target Id of the player you want to attack with leprechaun
	 */
	public void chooseCard(int cardID, Method method, int target) {
		players.get(currentPlayerID).chooseCard(cardID, method, target);
	}
	
	/**
	 * It will execute the action written on the card for the current user
	 * You cannot choose LEPRECHAUN with this method because it 
	 * needs a target but there is an overloaded method for that.
	 * @param cardID The id of the card you want to use
	 * @param method The way you want to use the card
	 */
	public void chooseCard(int cardID, Method method) {
		players.get(currentPlayerID).chooseCard(cardID, method);
	}
	

	/**
	 * Get number of seeds of a player
	 * @param playerID the player ID
	 * @return the player seeds count
	 */
	public int getPlayerSeed(int playerID) {
		return players.get(playerID).getSeed();
	}	

	/**
	 * Get number of menhir of a player
	 * @param playerID the player ID
	 * @return the player menhir count
	 */
	public int getPlayerMenhir(int playerID) {
		return players.get(playerID).getMenhir();
	}

	/**
	 * Get score of a player
	 * @param playerID the player ID
	 * @return the player score
	 */
	public int getPlayerScore(int playerID) {
		return players.get(playerID).getScore();
	}
	
	/**
	 * Get needed choice of the current player
	 * @return the needed choice
	 */
	public Choice getPlayerNeededChoice() {
		return players.get(currentPlayerID).getNeededChoice();
	}

	
	/**
	 * Get name of a player
	 * @param playerID the player ID
	 * @return the player name
	 */
	public String getPlayerName(int playerID) {
		return players.get(playerID).getName();
	}
	
	/**
	 * Get the number of players
	 * @return the player count
	 */
	public int getPlayerCount() {
		return players.size();
	}
	
	/**
	 * Get current player cards
	 */
	public ArrayList<Card> getPlayerCards() {
		return players.get(currentPlayerID).getCards();
	}
	
}
