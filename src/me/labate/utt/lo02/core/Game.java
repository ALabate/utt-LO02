/**
 * 
 */
package me.labate.utt.lo02.core;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * API to play one game without being able to break the rules
 */
public abstract class Game {
	
	public enum Season { INIT, SPRING, SUMMER, AUTUMN, WINTER };
	public enum Choice { NOTHING, BONUS, INGREDIENT, DEFEND, MOLE };
	public enum Action { NOTHING, GIANT, FERTILIZER, LEPRECHAUN_REQUEST, LEPRECHAUN, MOLE, BONUS_ALLY, BONUS_SEEDS };	

	//////////////////// General : Methods ////////////////////
	
	protected Game() {
		// Init vars
		players = new ArrayList<Player>();
		cardsLeft = new HashMap<String,ArrayList<Integer>>();
	}

	/**
	 * Start the next action in the game and stop when an
	 * user action is required on the user interface
	 * @return false if the game is over
	 */
	public abstract boolean next();
	
	/**
	 * Reset the game and score without deleting players
	 */
	public abstract void reset();	
	
	//////////////////// In game date : Attributes ////////////////////

	/**
	 * Current year starting from one to < yearsEnd
	 */
	private int year;
	
	/**
	 * Number of years in the game.
	 * 
	 * A game has 1 year in fast mode else players.size()
	 */
	private int yearCount;
	
	/**
	 * Current season.
	 * 
	 * A year has 5 season (yes with the initialization turn)
	 */
	private Season season;

	//////////////////// In game date : Methods ////////////////////

	/**
	 * @return the year
	 */
	public int getYear() {
		return this.year;
	}
	
	/**
	 * @return the number of year
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
	 * @param year the year to set
	 */
	protected void setYear(int year) {
		this.year = year;
	}

	/**
	 * @param yearCount the yearCount to set
	 */
	protected void setYearCount(int yearCount) {
		this.yearCount = yearCount;
	}

	/**
	 * @param season the season to set
	 */
	protected void setSeason(Season season) {
		this.season = season;
	}

	//////////////////// Last Action : Attributes ////////////////////

	/**
	 * Last action done by a player
	 */
	private Action lastAction = Action.NOTHING;
	
	/**
	 * the player that have done the last action
	 */
	private Player lastPlayer;
	
	/**
	 * Card used on the last action
	 */
	private IngredientCard lastIngredientCard;

	/**
	 * Seeds or menhir final value of the action
	 */
	private int lastPoints;

	/**
	 * the target player of the last action.
	 * can be null if no interaction with other players
	 */
	private Player lastTarget;

	/**
	 * Ally card used on the last action
	 */
	private AllyCard lastAllyCard;
	
	/**
	 * Season of the last action
	 */
	private Season lastSeason;
	
	/**
	 * Year of the last action
	 */
	private int lastYear;
	

	//////////////////// Last Action : Methods ////////////////////
	
	/**
	 * Set the last action
	 * @param action Last Action
	 * @param player Last player
	 * @param ingredientCard Ingredient card used for the action. Can be null.
	 * @param points Seeds or menhir final value of the action. Can be -1;
	 * @param target Target player of the action. Can be null.
	 * @param allyCard Ally card used for the action. Can be null.
	 * @param season Season of the last action.
	 * @param year Year of the last action.
	 */
	protected void setLastAction(Action action, Player player, IngredientCard ingredientCard,
			int points, Player target, AllyCard allyCard, Season season, int year) {
		
		if(players.contains(player)
				&& (target == null || players.contains(player))
				&& year >= 0 && year < getYearCount()) {
			
			lastAction = action;
			lastPlayer = player;
			lastIngredientCard = ingredientCard;
			lastPoints = points;
			lastTarget = target;
			lastAllyCard = allyCard;
			lastSeason = season;
			lastYear = year;
		}
	}	

	/**
	 * @return the lastAction
	 */
	public Action getLastAction() {
		return lastAction;
	}

	/**
	 * @return the lastPlayer
	 */
	public Player getLastPlayer() {
		return lastPlayer;
	}
	
	/**
	 * @return the lastIngredientCard
	 */
	public IngredientCard getLastIngredientCard() {
		return lastIngredientCard;
	}
	
	/**
	 * @return the lastPoints
	 */
	public int getLastPoints() {
		return lastPoints;
	}
	
	/**
	 * @return the lastTarget
	 */
	public Player getLastTarget() {
		return lastTarget;
	}
	
	/**
	 * @return the lastAllyCard
	 */
	public AllyCard getLastAllyCard() {
		return lastAllyCard;
	}	
	
	/**
	 * @return the lastSeason
	 */
	public Season getLastSeason() {
		return lastSeason;
	}

	/**
	 * @return the lastYear
	 */
	public int getLastYear() {
		return lastYear;
	}
	

	//////////////////// Needed : Attributes ////////////////////
	
	/**
	 * Id of the player that has something to do
	 * If there is none : null
	 */
	private Player neededPlayer;
	
	/**
	 * Choice that the neededPlayer has to do
	 */
	private Choice neededChoice;

	//////////////////// Needed : Methods ////////////////////

	/**
	 * Get needed choice of the neededPlayer
	 * @return the needed choice
	 */
	public Choice getNeededChoice() {
		return neededChoice;
	}
		
	/**
	 * Get the needed player that has to do the needed choice
	 * @return the needed player, can be null
	 */
	public Player getNeededPlayer() {
		return neededPlayer;
	}
	
	/**
	 * Set needed choice and player
	 * @param player Needed player
	 * @param choice Needed choice
	 */
	protected void setNeeded(Player player, Choice choice) {
		if(choice != Choice.NOTHING && players.contains(player)) {
			neededChoice = choice;
			neededPlayer = player;
		}
	}
	
	/**
	 * Set the fact that everything has been done
	 */
	protected void clearNeeded() {
		neededChoice = Choice.NOTHING;
		neededPlayer = null;
	}

	//////////////////// Player : Attributes ////////////////////

	/**
	 * Player list
	 */
	private ArrayList<Player> players;	

	//////////////////// Player : Methods ////////////////////

	/**
	 * Get a player data from its id
	 * @param playerID the id of the player from 0 to getPlayerCount()
	 * @return the player, can be null
	 */
	public Player getPlayer(int playerID) {
		if(playerID >= 0 && playerID < players.size()) {
			return players.get(playerID);
		}
		return null;
	}

	/**
	 * Get the next player
	 * @return the next player, can be null if it's the end
	 */
	public abstract Player getNextPlayer();
	
	/**
	 * Get the number of players
	 * @return the player count
	 */
	public int getPlayerCount() {
		return players.size();
	}
	
	/**
	 * Get player ID from its object
	 * @param player The player
	 * @return the player id or -1 if the element is not inside
	 */
	public int getPlayerID(Player player) {
		return players.indexOf(player);
	}

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
	

	//////////////////// Cards left : Attributes ////////////////////
	/**
	 * List of deck that contain list of card not used
	 */
	private HashMap<String,ArrayList<Integer>> cardsLeft;

	//////////////////// Cards left : Methods ////////////////////

	/**
	 * @return the cardsLeft
	 */
	protected HashMap<String,ArrayList<Integer>> getCardsLeft() {
		return cardsLeft;
	}
}
