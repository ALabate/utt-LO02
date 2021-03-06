package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.AllyCard.AllyMethod;
/**
 * Advanced Game, whith AllyCards (Dog and Mole)
 * @author Alabate
 *
 */
public class FullGame extends Game {
	
	
	/**
	 * Constructor
	 */
	public FullGame() {
		super();
		reset();		
	}
	

	@Override
	public void reset() {
		// As we are in full game there is the same number of years as the number of players
		setYear(0);
		setYearCount(0);
		
		// The init season is used to give cards to players
		setSeason(Season.INIT);
		
		// We start with the 'init' player
		currentPlayerID = -1;
		int i = 0;
		while(this.getPlayer(i) != null){
			this.getPlayer(i).reset();
			i++;
			}
	}

	@Override
	public boolean next() {
		// Initialization
		if(getSeason() == Season.INIT && currentPlayerID == -1) {
			currentPlayerID = getYear();
			// Set the number of years
			setYearCount(getPlayerCount());
			// Save scores and reset players
			for(int playerID = 0; playerID < getPlayerCount(); playerID++) {
				getPlayer(playerID).updateScore();
				// a player keep his seeds even after one year
				int seeds = getPlayer(playerID).getSeed();
				getPlayer(playerID).reset();
				getPlayer(playerID).setSeed(seeds);
			}
			// Reset card deck (refill and shuffle)
			this.getStockIngredient().reset(); 
			this.getStockAlly().reset();
			// Give 4 cards to everyone
			for(int cardNbr = 0 ; cardNbr < 4 ; cardNbr++) {
				// To each players
				for(int player = 0 ; player < getPlayerCount(); player++) {
					getPlayer(player).drawIngredientCard();
				}
			}
		}
		else
		{
			// Check the end
			if(getYear() >= getYearCount()) {
				return false;
			}
			
			// Finish the last turn
			if(getNeededChoice() != Choice.NOTHING) {
				// Force bot to defend if it's not done yet
				if(getNeededChoice() == Choice.DEFEND) {
					getNeededPlayer().doAction();
				}
				// Cannot continue if the current user didn't choose
				return true;
			}
			
			// Propose to all bot to use their mole attack
			// TODO make it with a thread
			if(getSeason() != Season.INIT && getLastAction() != Action.LEPRECHAUN_REQUEST) {
				for(int i = 0; i < getPlayerCount() ; i++) {
					if(getPlayer(i).getAllyCard() != null && getPlayer(i).getAllyCard().getValue(AllyMethod.MOLE, getSeason()) >= 0) {
						setNeeded(getPlayer(i), Choice.MOLE);
						if(getPlayer(i).doAction()) {
							clearNeeded();
							this.toString();
							return true;
						}
						clearNeeded();
					}
				}
			}
			setLastAction(Action.NOTHING, null, null, -1, null, null, getSeason(), getYear());
						
			// Increment time
			currentPlayerID++;
			if(currentPlayerID >= getPlayerCount()) {
				currentPlayerID = 0;
			}
			if(currentPlayerID == getYear()) {
				switch(getSeason()) {
				case INIT : 	setSeason(Season.SPRING); break;
				case SPRING : 	setSeason(Season.SUMMER); break;
				case SUMMER : 	setSeason(Season.AUTUMN); break;
				case AUTUMN : 	setSeason(Season.WINTER); break;
				case WINTER : 
					setSeason(Season.INIT); 
					setYear(getYear() + 1);
					currentPlayerID = -1;
					return(getYear() < getYearCount());
				}
			}
		}
		
		// Prepare the next turn
		if(getSeason() == Season.INIT) {
			setNeeded(getPlayer(currentPlayerID), Choice.BONUS);
		}
		else {
			setNeeded(getPlayer(currentPlayerID), Choice.INGREDIENT);
		}
		
		// Let the bot play
		getNeededPlayer().doAction();
		if(getNeededChoice() == Choice.DEFEND) {
			getNeededPlayer().doAction();
		}
		return true;
	}


	@Override
	public Player getNextPlayer() {
		int id = currentPlayerID;
		id++;
		if(id >= getPlayerCount()) {
			id = 0;
		}
		if(id == getYear() && getSeason() == Season.WINTER) {
			if(getYear() < getYearCount()) {
				return getPlayer(getYear() + 1);
			}
			return null;
		}
		return getPlayer(id);
	}


	@Override
	public boolean isFull() {
		// this game is full
		return true;
	}

		
}