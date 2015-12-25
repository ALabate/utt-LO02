package me.labate.utt.lo02.core;

import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player.Bonus;
/**
 * Simple Strategy which is looking for options with the more points.
 * @author Benoit
 * 
 */
public class AdvancedStrategy implements Strategy {

	protected Game context;
	
	public AdvancedStrategy(Game context) {
		this.context = context;
	}
	
	@Override
	public IngredientCard card() { 
		// return the most effective card for the current season, the current player and the method chosen by this strategy.
		return CardTheMostEffective(context.getNeededPlayer(), context.getSeason(), this.method());
	}


	@Override
	public IngredientMethod method() {
		return methodTheMostEffective(context.getNeededPlayer(), context.getSeason());
	}

	@Override
	public Player target() {
		return whoHasMoreSeed();
	}

	@Override
	public Bonus bonus() {
		// always choose ally by default.
		return Bonus.ALLY;
	}

	@Override
	public boolean defend() {
		if(context.getNeededPlayer().hasAllyCard()){
			AllyCard ally = context.getNeededPlayer().getAllyCard();
			// check if ally is a dog
			int points = ally.getValue(AllyMethod.DOG, context.getSeason());
			if(points > 0) 
				return true;
			else
				return false;
		}
		System.out.println("defend");
		return false;
	}

	@Override
	public boolean moleAttack() {
		if(context.getNeededPlayer().hasAllyCard()){
			AllyCard ally = context.getNeededPlayer().getAllyCard();
			// check if ally is a dog
			int points = ally.getValue(AllyMethod.DOG, context.getSeason());
			if(points > 0) {
				// check if someone has more menhir than this player
				Player player = whoHasMoreMenhir();
				if(player.getMenhir() > context.getNeededPlayer().getMenhir())
					return true;
			}
		}
		return false;
	}

	@Override
	public Player moleAttackTarget() {
		return whoHasMoreMenhir();
		// return the player selected
	}
	/*///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*
	***************************************************************************************************************************************************************	/
	Functions which get some stats useful for strategy, don't hesitate to use them if you want to create a better strategy											/
	***************************************************************************************************************************************************************	/
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// */
	/**
	 * 
	 * @return player whose his number of menhirs is the highest except player who is playing
	 */
	public Player whoHasMoreMenhir(){
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
	}
	/**
	 * 
	 * @return player whose his number of Seeds is the highest except player who is playing
	 */
	public Player whoHasMoreSeed(){
			int targetID = 0, i = 0, maxSeeds = 0;
			while(context.getPlayer(i) != null){
				// compare all players except the one who is playing
				if(context.getPlayer(i).getSeed() > maxSeeds && context.getPlayer(i)!= context.getNeededPlayer()){
					targetID = i;
					maxSeeds = context.getPlayer(i).getSeed();
					// select the player who has the higher menhir's points
				}
				i++;
			}
			return context.getPlayer(targetID);
	}
	/**
	 * 
	 * @return player whose his number of Score is the highest except player who is playing
	 */
	public Player whoHasMoreScore(){
		int targetID = 0,i = 0, maxScore = 0;
		while(context.getPlayer(i) != null){
			// compare all players except the one who is playing
			if(context.getPlayer(i).getScore() > maxScore && context.getPlayer(i)!= context.getNeededPlayer()){
				targetID = i;
				maxScore = context.getPlayer(i).getScore();
				// select the player who has the higher menhir's points
			}
			i++;
		}
		return context.getPlayer(targetID);
	}
	/**
	 * @param player
	 * @return sum of points geant of all the cards in all the season.
	 */
	public static int PointsGiant(Player player){
		int points =0;
		for(int i = 1; i <= 4 ; i++){ // number of seasons
			for(int j = 0; j < player.getIngredientCards().size() ; j++){ // number of cards
				points += player.getIngredientCard(j).getValue(IngredientMethod.GIANT, Game.intToSeason(i)); // resolve this.
			}
		}
		return points;
	}
	/**
	 * @param player
	 * @return sum of points Fertilizer of all cards in all season 
	 */
	public static int PointsFertilizer(Player player){
		int points =0;
		for(int i = 1; i <= 4 ; i++){ // number of seasons
			for(int j = 0; j < player.getIngredientCards().size()  ; j++){ // number of cards
				points += player.getIngredientCard(j).getValue(IngredientMethod.FERTILIZER, Game.intToSeason(i)); // resolve this.
			}
		}
		return points;
	}
	/**
	 * @param player
	 * @return sum of points Leprechaun of all cards in all season
	 */
	public static int PointsLeprechaun(Player player){
		int points =0;
		for(int i = 1; i <= 4 ; i++){ // number of seasons
			for(int j = 0; j < player.getIngredientCards().size() ; j++){ // number of cards
				points += player.getIngredientCard(j).getValue(IngredientMethod.LEPRECHAUN, Game.intToSeason(i)); // resolve this.
			}
		}
		return points;
	}
	/**
	 * give the most effective method to be played in function of season, compare all the cards
	 * @param player, check all the cards
	 * @param season
	 * @return Method which is the best to be played
	 */
	public static IngredientMethod methodTheMostEffective(Player player, Season season){
		int points =0, maxPoints = 0;
		IngredientMethod method = IngredientMethod.GIANT;
		for(int i = 0; i < 3 ; i++){ // number of method
			for(int j = 0; j < player.getIngredientCards().size() ; j++){ // number of cards
				points = player.getIngredientCard(j).getValue(IngredientCard.intToIngredientMethod(i), season);
				if(points > maxPoints){
					maxPoints = points;
					method = IngredientCard.intToIngredientMethod(i);
				}
			}
		}
		return method;
	}
	/**
	 * give the most effective method to be played for one card in function of season
	 * @param card
	 * @param season
	 * @return method which is the best to be played for the card given.
	 */
	public static IngredientMethod methodTheMostEffective(IngredientCard card, Season season){
		int points =0, maxPoints = 0;
		IngredientMethod method = IngredientMethod.GIANT;
		for(int i = 0; i < 3 ; i++){ // number of method
				points = card.getValue(IngredientCard.intToIngredientMethod(i), season);
				if(points > maxPoints){
					maxPoints = points;
					method = IngredientCard.intToIngredientMethod(i);
				}
		}
		return method;
	}
	/**
	 * give the most effective card in function of season
	 * @param player
	 * @param season
	 * @return Card which is the best to be played
	 */
	public static IngredientCard CardTheMostEffective(Player player,Season season){
		int points =0, maxPoints = 0;
		IngredientCard card = null;
		for(int i = 0; i < player.getIngredientCards().size() ; i++){ // number of cards
			for(int j = 0; j < 3 ; j++){ // number of method
				points = player.getIngredientCard(i).getValue(IngredientCard.intToIngredientMethod(j), season);
				if(points > maxPoints){
					maxPoints = points;
					card = player.getIngredientCard(i);
				}
			}
		}
		return card;
	}
	/**
	 * give the most effective card for a specific season and a specific method
	 * @param player
	 * @param season
	 * @param method
	 * @return IngredientCard which is the most effective for a specific season and a specific method
	 */
	public static IngredientCard CardTheMostEffective(Player player,Season season,IngredientMethod method){
		int points =0, maxPoints = 0;
		IngredientCard card = null;
		for(int i = 0; i < player.getIngredientCards().size() ; i++){ // number of cards
				points = player.getIngredientCard(i).getValue(method, season);
				if(points > maxPoints){
					maxPoints = points;
					card = player.getIngredientCard(i);
				}
		}
		return card;
	}
}
