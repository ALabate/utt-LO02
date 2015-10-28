package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.AllyCard.AllyMethod;

public class FullGame extends Game {
	//TODO refill deck after a year
	// TODO add constraint 2 to 6 players
	
	/**
	 * Current player id
	 */
	private int currentPlayerID;
	
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
		
		//TODO finish him !
		
		// We start with the 'init' player
		currentPlayerID = -1;		
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
				int score = getPlayer(playerID).getScore() + getPlayer(playerID).getMenhir();
				getPlayer(playerID).reset();
				getPlayer(playerID).setScore(score);
			}
			// Reset card deck
			getCardsLeft().clear();
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
			if(getSeason() != Season.INIT && getLastAction() != Action.LEPRECHAUN_REQUEST) {
				for(int i = 0; i < getPlayerCount() ; i++) {
					if(getPlayer(i).getAllyCard() != null && getPlayer(i).getAllyCard().getValue(AllyMethod.MOLE, getSeason()) >= 0) {
						setNeeded(getPlayer(i), Choice.MOLE);
						if(getPlayer(i).doAction()) {
							clearNeeded();
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

		
}
