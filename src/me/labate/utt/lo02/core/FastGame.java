package me.labate.utt.lo02.core;


public class FastGame extends Game {
	
	/**
	 * Current player id
	 */
	private int currentPlayerID;

	public FastGame() {
		super();
		reset();
	}


	@Override
	public void reset() {
		// As we are in fast game there is only one year
		setYear(0);
		setYearCount(1);
		
		// The init season is used to give cards to players
		setSeason(Season.INIT);
		
		// for each player, reset points.
		int i = 0;
		while(this.getPlayer(i) != null){
			this.getPlayer(i).reset();
			i++;
		}
		// reset the stock of IngredientCard
		this.getStockIngredient().reset();
		
	}

	@Override
	public boolean next() {
		if(getYear() >= getYearCount()) {
			return false;
		}

		// Initialization turn if needed
		if(getSeason() == Season.INIT) {
			// Give 4 cards
			for(int cardNbr = 0 ; cardNbr < 4 ; cardNbr++) {
				// To each players
				for(int playerID = 0 ; playerID < getPlayerCount(); playerID++) {
					getPlayer(playerID).drawIngredientCard();
				}
			}
			// Give two seeds to each players
			for(int playerID = 0 ; playerID < getPlayerCount(); playerID++) {
				getPlayer(playerID).setSeed(2);
			}
			// At the end of the initialization turn we are in spring (first season)
			setSeason(Season.SPRING);
			currentPlayerID = 0;
		}
		else
		{
			// Finish the last turn
			setLastAction(Action.NOTHING, null, null, -1, null, null, getSeason(), getYear());
			if(getNeededChoice() != Choice.NOTHING) {
				// Cannot continue the needed one didn't choose
				return false;
			}

			// Increment time
			currentPlayerID++;
			if(currentPlayerID >= getPlayerCount() || currentPlayerID < 0) {
				currentPlayerID = 0;
				switch(getSeason()) {
				case INIT : 	setSeason(Season.SPRING); break;
				case SPRING : 	setSeason(Season.SUMMER); break;
				case SUMMER : 	setSeason(Season.AUTUMN); break;
				case AUTUMN : 	setSeason(Season.WINTER); break;
				case WINTER : 
					setSeason(Season.INIT); 
					setYear(getYear() + 1);
					if(getYear() >= getYearCount()) {
						return false;
					}
					break;
				}
			}
		}
				
		// Prepare the next turn
		setNeeded(getPlayer(currentPlayerID), Choice.INGREDIENT);
		
		// Let the bot play
		getPlayer(currentPlayerID).doAction();
		
		return true;
	}

	@Override
	public Player getNextPlayer() {
		int id = currentPlayerID;
		id++;
		if(id >= getPlayerCount() || id < 0) {
			id = 0;
			if(getSeason() == Season.WINTER && getYear() + 1>= getYearCount()) {
				return null;
			}
		}
		return getPlayer(id);
	}


	@Override
	public boolean isFull() {
		// this game is not full
		return false;
	}
		
}