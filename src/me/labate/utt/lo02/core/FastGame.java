package me.labate.utt.lo02.core;

import java.util.ArrayList;

public class FastGame extends Game {

	public FastGame() {
		// Init vars
		players = new ArrayList<Player>();
		
		// As we are in fast game there is only one year
		year = 0;
		yearCount = 1;
		
		// The init season is used to give cards to players
		season = Season.INIT;		
		
	}

	@Override
	public boolean next() {
		if(year >= yearCount) {
			return false;
		}

		// Initialization turn if needed
		if(season == Season.INIT) {
			// Give 4 cards
			for(int cardNbr = 0 ; cardNbr < 4 ; cardNbr++) {
				// To each players
				for(int player = 0 ; player < players.size(); player++) {
					players.get(player).drawCard();
				}
			}
			// At the end of the initialization turn we are in spring (first season)
			season = Season.SPRING;
			currentPlayerID = -1;
		}
		else
		{
			// Finish the last turn
			lastAction = Action.NOTHING;
			if(getPlayers().get(getCurrentPlayerID()).getNeededChoice() != Player.Choice.NOTHING) {
				// Cannot continue if the current user didn't choose
				return false;
			}
		}
		
		// Increment time
		currentPlayerID++;
		if(currentPlayerID >= players.size()) {
			currentPlayerID = 0;
			switch(season) {
			case INIT : season = Season.SPRING; break;
			case SPRING : season = Season.SUMMER; break;
			case SUMMER : season = Season.AUTUMN; break;
			case AUTUMN : season = Season.WINTER; break;
			case WINTER : 
				season = Season.INIT; 
				year++;
				if(year < yearCount) {
					return false;
				}
				break;
			}
		}
		
		// Prepare the next turn
		players.get(currentPlayerID).setNeededChoice(Player.Choice.CARD);
		
		// Let the bot play
		getPlayers().get(getCurrentPlayerID()).doChoice();
		
		return true;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
		
}
