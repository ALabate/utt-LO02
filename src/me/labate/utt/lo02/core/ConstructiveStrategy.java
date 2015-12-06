package me.labate.utt.lo02.core;

import java.util.ArrayList;


import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player.Bonus;

public class ConstructiveStrategy implements Strategy {

	/**
	 * Contain the current state of the game
	 */
	Game context;
	/**
	 * Constructor
	 * @param context Context of the game
	 */
	public ConstructiveStrategy(Game context) {
		this.context = context;
	}
	@Override
	public IngredientCard card() {
		ArrayList<IngredientCard> hand = context.getNeededPlayer().getIngredientCards();
		java.util.Iterator<IngredientCard> it = hand.iterator();
		IngredientCard returnCard = hand.get(0);
		int points = 0;
		// for each card
		while(it.hasNext()){
			IngredientCard card = it.next();
			
			switch(context.getSeason()){
			case AUTUMN:
				switch(this.method()){ // depends on the method selected by the strategy.
				// Choose the card which the higher points for the method selected and the season
				case FERTILIZER:
						if(card.getValue(IngredientMethod.FERTILIZER, Season.AUTUMN) > points){
							points = card.getValue(IngredientMethod.FERTILIZER, Season.AUTUMN);
							returnCard = card;
						}
					break;
				case GIANT:
						if(card.getValue(IngredientMethod.GIANT, Season.AUTUMN) > points){
							points = card.getValue(IngredientMethod.GIANT, Season.AUTUMN);
							returnCard = card;
						}
					break;
				case LEPRECHAUN:
							if(card.getValue(IngredientMethod.LEPRECHAUN, Season.AUTUMN) > points){
								points = card.getValue(IngredientMethod.LEPRECHAUN, Season.AUTUMN);
								returnCard = card;
							}
					break;
				}
				break;
			case SPRING:
				switch(this.method()){
				case FERTILIZER:
						if(card.getValue(IngredientMethod.FERTILIZER, Season.SPRING) > points){
							points = card.getValue(IngredientMethod.FERTILIZER, Season.SPRING);
							returnCard = card;
						}
					break;
				case GIANT:
						if(card.getValue(IngredientMethod.GIANT, Season.SPRING) > points){
							points = card.getValue(IngredientMethod.GIANT, Season.SPRING);
							returnCard = card;
						}
					break;
				case LEPRECHAUN:
							if(card.getValue(IngredientMethod.LEPRECHAUN, Season.SPRING) > points){
								points = card.getValue(IngredientMethod.LEPRECHAUN, Season.SPRING);
								returnCard = card;
							}
					break;
				}
				break;
			case SUMMER:
				switch(this.method()){
				case FERTILIZER:
						if(card.getValue(IngredientMethod.FERTILIZER, Season.SUMMER) > points){
							points = card.getValue(IngredientMethod.FERTILIZER, Season.SUMMER);
							returnCard = card;
						}
					break;
				case GIANT:
						if(card.getValue(IngredientMethod.GIANT, Season.SUMMER) > points){
							points = card.getValue(IngredientMethod.GIANT, Season.SUMMER);
							returnCard = card;
						}
					break;
				case LEPRECHAUN:
							if(card.getValue(IngredientMethod.LEPRECHAUN, Season.SUMMER) > points){
								points = card.getValue(IngredientMethod.LEPRECHAUN, Season.SUMMER);
								returnCard = card;
							}
					break;
				}
				break;
			case WINTER:
				switch(this.method()){
				case FERTILIZER:
						if(card.getValue(IngredientMethod.FERTILIZER, Season.WINTER) > points){
							points = card.getValue(IngredientMethod.FERTILIZER, Season.WINTER);
							returnCard = card;
						}
					break;
				case GIANT:
						if(card.getValue(IngredientMethod.GIANT, Season.WINTER) > points){
							points = card.getValue(IngredientMethod.GIANT, Season.WINTER);
							returnCard = card;
						}
					break;
				case LEPRECHAUN:
							if(card.getValue(IngredientMethod.LEPRECHAUN, Season.WINTER) > points){
								points = card.getValue(IngredientMethod.LEPRECHAUN, Season.WINTER);
								returnCard = card;
							}
					break;
				}
				break;
			default: // other cases send null
				returnCard = null;
				break;
			}
		}
		return returnCard;
	}

	@Override
	public IngredientMethod method() {
		IngredientMethod method = null;
		switch(context.getSeason()){ // choice depends on season
		case SPRING:
			// first season we want to increase the number of our seeds
			switch(((BotPlayer)context.getNeededPlayer()).getLevel()){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				method = IngredientMethod.GIANT;
				break;
			default:
				if(context.getNeededPlayer().getSeed() > 3){
					method = IngredientMethod.FERTILIZER;
				}
				else{
					method = IngredientMethod.GIANT;
				}
				break;
			}
			break;
		case SUMMER:
			//Second season we try to fertilize our seeds
			switch(((BotPlayer)context.getNeededPlayer()).getLevel()){
				// if level <= 3 choose Fertilizer anyway
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
					method = IngredientMethod.FERTILIZER;
					break;
					// 3 < level < 7 check if our seeds number is not zero and choose giant if it is
				default:
					if(context.getNeededPlayer().getSeed() > 2){
						method = IngredientMethod.FERTILIZER;
					}
					else{
						method = IngredientMethod.GIANT;
					}
					break;
			}
			break;
		case AUTUMN:
			// Third season get more seed
			switch(((BotPlayer)context.getNeededPlayer()).getLevel()){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
				// low levels
				method = IngredientMethod.GIANT;
				break;
			default: // other high level, get seed only if we have not already enough seeds
				if(context.getNeededPlayer().getSeed() > 4){
					method= IngredientMethod.FERTILIZER;
				}
				else{
					method = IngredientMethod.GIANT;
				}
				break;
			}
			
			
			break;
		case WINTER:
			//Last season we try to fertilize our seeds
			switch(((BotPlayer)context.getNeededPlayer()).getLevel()){
				// if level <= 5 choose Fertilizer anyway
				case 0:
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
					method = IngredientMethod.FERTILIZER;
					break;
					//high level
				default:
					// check if there is another year left
					if(context.getYear() < context.getYearCount() - 1){
						if(context.getNeededPlayer().getSeed() > 1){
							// player has more than 1 seed
							method = IngredientMethod.FERTILIZER;
						}
						else{ // no seed left
							method = IngredientMethod.GIANT;
						}
					}
					else{ // it's the last year of this game
						method = IngredientMethod.FERTILIZER;
					}
					break;
			}
			break;
		default: // other case send null
			method = null;
			break;
		}
		return method;
	}

	@Override
	public Player target() {
		// this strategy is not supposed to use Leprechaun method, but if it does, we choose the player who has more seeds
		int targetID = 0,i = 0, maxSeeds = 0;
		while(context.getPlayer(i) != null){
			// compare all players except the one who is playing
			if(context.getPlayer(i).getMenhir() > maxSeeds && context.getPlayer(i)!= context.getNeededPlayer()){
				targetID = i;
				maxSeeds = context.getPlayer(i).getMenhir();
				// select the player who has the higher menhir's points
			}
			i++;
		}
		return context.getPlayer(targetID);
	}

	@Override
	public Bonus bonus() {
		//  constructive always choose seeds option
			return Bonus.SEEDS;
	}

	@Override
	public boolean defend() {
		// we are not suppose to have an ally card with this method
		return false;
	}

	@Override
	public boolean moleAttack() {
		// we are not suppose to have an ally card with this method
		return false;
	}

	@Override
	public Player moleAttackTarget() { // this method is not supposed to be used in this strategy, but if it does, we choose the player who has more menhir.
		int targetID = 0,i = 0, maxMenhirs = 0;
		while(context.getPlayer(i) != null){
			// compare all players except the one who is playing
			if(context.getPlayer(i).getMenhir() > maxMenhirs && context.getPlayer(i)!= context.getNeededPlayer()){
				targetID = i;
				maxMenhirs = context.getPlayer(i).getMenhir();
				// select the player who has the higher menhir's points
			}
			i++;
		}
		return context.getPlayer(targetID);
		// return the player selected
	}

}
