package me.labate.utt.lo02.core;

import java.util.ArrayList;

public class FullGame extends Game {
	

	public FullGame() {
		// Init vars
		players = new ArrayList<Player>();
		
		// As we are in full game there is the same number of years as the number of players
		year = 0;
		yearCount = -1;
		
		// The init season is used to give cards to players
		season = Season.INIT;
		
		// We start with the first player
		currentPlayerID = year;
		
	}

	@Override
	public boolean next() {
		if(year >= yearCount) {
			return false;
		}

		// Initialization
		if(season == Season.INIT && currentPlayerID == year) {
			// Set the number of years
			yearCount = players.size();
			// Give 4 cards to everyone
			for(int cardNbr = 0 ; cardNbr < 4 ; cardNbr++) {
				// To each players
				for(int player = 0 ; player < players.size(); player++) {
					players.get(player).drawCard();
				}
			}
		}
		else
		{
			// Finish the last turn
			lastAction = Action.NOTHING;
			if(getPlayers().get(getCurrentPlayerID()).getNeededChoice() != Player.Choice.NOTHING) {
				// Cannot continue if the current user didn't choose
				return false;
			}
			
			// Increment time
			currentPlayerID++;
			if(currentPlayerID >= players.size()) {
				currentPlayerID = 0;
			}
			if(currentPlayerID == year) {
				currentPlayerID = 0;
				switch(season) {
				case INIT : season = Season.SPRING; break;
				case SPRING : season = Season.SUMMER; break;
				case SUMMER : season = Season.AUTUMN; break;
				case AUTUMN : season = Season.WINTER; break;
				case WINTER : 
					season = Season.INIT; 
					year++;
					currentPlayerID = year;
					if(year < yearCount) {
						return false;
					}
					break;
				}
			}
		}
		
		
		// Prepare the next turn
		if(season == Season.INIT) {
			players.get(currentPlayerID).setNeededChoice(Player.Choice.BONUS);
		}
		else
		{
			players.get(currentPlayerID).setNeededChoice(Player.Choice.CARD);
		}
		
		// Let the bot play
		getPlayers().get(getCurrentPlayerID()).doChoice();
		
		return true;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
		
}
