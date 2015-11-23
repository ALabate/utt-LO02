 package me.labate.utt.lo02.cli;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.Player.Bonus;
import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Choice;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.AllyCard;
import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.FastGame;
import me.labate.utt.lo02.core.FullGame;

import java.util.HashMap;
	

public class Cli {	

	// TODO let the cli say everyone instead of botname 
	// TODO And to fix the bug of showing twice the message on leprechaun
	
	public static void main(String[] args) {
		
		// Let user set parameters.
		// choose Fast or FullGame
		HashMap<String, String> propositions = new HashMap<String, String>();
		String question = "What kind of game do you want to play  :";
		propositions.put("1", "Play a FastGame");
		propositions.put("2", "Play a FullGame");
		String answer = Console.question(question, propositions);
		Game game;
		if(answer == "2") {
			game = new FullGame();			
		}
		else {
			game = new FastGame();
		}
		
		// let user give how many players
		//Console.clear();
		propositions.clear();
		question = "How many players in the game :";
		for(Integer i = 2; i <= 6; i++)
			propositions.put(i.toString(), i.toString() + " Players play this game");
		answer = Console.question(question, propositions);
		Integer nbrPlayer = Integer.valueOf(answer);
		
		// for each player, enter informations.
		for(Integer i = 0; i < nbrPlayer;  i++)
		{
			Console.clear();
			question = "Player number " + (i+1)  + " is Human or Bot";
			propositions.clear();
			propositions.put("h", "Human");
			propositions.put("b", "Bot");
			answer = Console.question(question, propositions);
			String name =  Console.question("Name of player " + (i+1) + " :");
			switch(answer)
			{
			case "h":
				game.addHuman(name);
				break;
			case "b":
				propositions.clear();
				question = "Set the level of this Bot";
				// let user give the level of bot player
				for(Integer j = 0; j <= 10; j++)
					propositions.put(j.toString(), "Level " + j.toString());
				answer = Console.question(question, propositions);
				game.addBot(name, Integer.valueOf(answer));
				break;
			}
		}
		
		// Init
		while(game.next()) {
			if(game.getNeededPlayer() != null && game.getNeededChoice() != Choice.NOTHING) {
				Player player = game.getNeededPlayer();
				String playerName = player.getName();
				int playerID = game.getPlayerID(player);
				switch(game.getNeededChoice()) {
					case INGREDIENT:
					{
						// Show cards
						Console.clear();
						Console.showPublicData(game);
						Console.jumpLine(3);
						System.out.println(playerName + ", Your cards :");
						Console.showIngredientCard(player.getIngredientCards());
						
						// Ask question
						propositions.clear();
						question = "Which card do you want to play ?";
						for(int i = 1; i <= player.getIngredientCardCount() ; i++) {
							propositions.put(String.valueOf(i), "Play the card number "+i);
						}		
						answer = Console.question(playerName + ": " + question, propositions);
						
						// Store answer
						IngredientCard card = player.getIngredientCard(Integer.parseInt(answer) - 1);
		
						// Ask question 2
						propositions.clear();
						question = "What method do you want to use ?";
						propositions.put("g", "Giant");
						propositions.put("f", "Fertilizer");
						propositions.put("l", "Leprechaun");
						answer = Console.question(playerName + ": " + question, propositions);
										
						// store answer
						IngredientMethod method;
						switch(answer) {
							default:
							case "g" :	method = IngredientMethod.GIANT;		break;
							case "f" :	method = IngredientMethod.FERTILIZER;	break;
							case "l" :	method = IngredientMethod.LEPRECHAUN;	break;
						}
						
						// Choose target if leprechaun
						Player targetPlayer = null;
						if(method == IngredientMethod.LEPRECHAUN) {
							// Ask target question
							propositions.clear();
							question = "Choose your target :";
							for(int i = 1; i <= game.getPlayerCount() ; i++) {
								// Don't show the current player
								if(playerID != i-1) {
									propositions.put(String.valueOf(i), game.getPlayer(i-1).getName());
								}
							}
							answer = Console.question(playerName + ": " + question, propositions, "");
							targetPlayer = game.getPlayer(Integer.parseInt(answer) - 1);
						}
		
						
						// Execute answer
						player.playIngredientCard(card, method, targetPlayer);
						break;
					}
					case BONUS:
					{
						Console.clear();
						Console.showPublicData(game);
						Console.jumpLine(3);
						System.out.println(playerName + ", Your cards :");
						Console.showIngredientCard(player.getIngredientCards());
		
						// Ask question
						propositions.clear();
						question = "What bonus do you want ?";
						propositions.put("a", "One ally card");
						propositions.put("s", "Two seeds");				
						answer = Console.question(playerName + ": " + question, propositions, "");
						// Execute answer
						switch(answer) {
						case "a":
							player.chooseBonus(Bonus.ALLY);
							// Show new card
							Console.clear();
							System.out.println(playerName + ": Your new ally card:");
							Console.showAllyCard(player.getAllyCard());
							Console.waitToContinue(playerName);
							break;
						case "s":
						default:
							player.chooseBonus(Bonus.SEEDS);
							break;
						}
						break;
					}
					case DEFEND: // Defense agains leprechaun request
					{
						// Explain the situation to players
						Console.clear();
						Console.showPublicData(game);
						Console.jumpLine(3);
						Console.showLastAction(game);
						Console.waitToBeReady(playerName);
						
						// Check if the target has dogs
						AllyCard card = player.getAllyCard();
						if(card.getValue(AllyMethod.DOG, game.getSeason()) >= 0) {		
							Console.clear();
							Console.showPublicData(game);
							Console.jumpLine(3);
							Console.showAllyCard(card);
							// Ask question
							propositions.clear();
							question = "Do you want to use your dog to defend yourself ?";
							propositions.put("y", "Yes");
							propositions.put("n", "No");	
							answer = Console.question(playerName + ": " + question, propositions, "");
							player.chooseDefend(answer.equals("y"));
						}
						else {
							Console.clear();
							Console.showPublicData(game);
							Console.jumpLine(3);
							Console.showAllyCard(card);
							System.out.println(playerName + ": You don't have any dogs to defend yourself against leprechaun.");
							System.out.println("But remember that other players only see that you have an ally card. They don't know if it's a dog or a mole. So you can still bluff ;)");
							Console.waitToContinue(playerName);
							player.chooseDefend(false);
						}
						
						break;
					}
					default:
						break;
				}				
				
				// Show last action
				// TODO fix the fact that you have to see twice the last action on leprechaun attack
				Console.clear();
				Console.showPublicData(game);
				Console.jumpLine(3);
				Console.showLastAction(game);
				Console.waitToBeReady(game.getNextPlayer().getName());
				

				// TODO uncomment and propose until no one try
				// Propose to use mole
				if(game.getSeason() != Season.INIT && game.getLastAction() != Action.LEPRECHAUN_REQUEST) {
					Console.clear();
					Console.showPublicData(game);
					Console.jumpLine(3);
					propositions.clear();
					question = "Who wants to attack with his mole or want to see his ally card ?";
					for(int i = 1; i <= game.getPlayerCount() ; i++) {
						if(!game.getPlayer(i-1).isBot() &&  game.getPlayer(i-1).getAllyCard() != null) {
							propositions.put(String.valueOf(i), game.getPlayer(i-1).getName());
						}
					}
					propositions.put("n", "No one");
					answer = Console.question("Everyone: " + question, propositions, "n");
					// Show ally card
					if(!answer.equals("n")) {
						Player player2 = game.getPlayer(Integer.parseInt(answer) - 1);
						AllyCard card = player2.getAllyCard();
						System.out.println(player2.getName() + ", your ally card:");
						Console.showAllyCard(card);
						if( card.getValue(AllyMethod.MOLE, game.getSeason()) >= 0 ) {
							// Do you want to attack
							propositions.clear();
							question = "Do you want to use your mole to attack someone ?";
							propositions.put("y", "Yes");
							propositions.put("n", "No");	
							answer = Console.question(playerID + ": " + question, propositions, "");
							if(answer.equals("y")) {
								// Choose target
								propositions.clear();
								question = "Who do you want to attack with your mole ?";
								for(int i = 1; i <= game.getPlayerCount() ; i++) {
									if(i -1 != playerID) {
										propositions.put(String.valueOf(i), game.getPlayer(i-1).getName());
									}
								}
								// Execute order
								answer = Console.question(playerID + ": " + question, propositions, "");
								Player target2 = game.getPlayer(Integer.parseInt(answer) - 1);
								player2.chooseMoleAttack(target2);
								
								// Show last action
								Console.clear();
								Console.showPublicData(game);
								Console.jumpLine(3);
								Console.showLastAction(game);
								if(game.getNextPlayer() != null) {
									Console.waitToBeReady(game.getNextPlayer().getName());
								}
								else {
									Console.waitToBeReady("Everyone");
								}
							}
						}
						else {
							System.out.println(player2.getName() + ": You cannot attack someone because you don't have any mole.");
							System.out.println("But remember that other players only see that you have an ally card. They don't know if it's a dog or a mole. So you can still bluff ;)");
							Console.waitToContinue(player2.getName());
						}
					}
				}
			}
			// Bot action or End of a Round
			else {
				if(game.getLastAction() != Action.NOTHING) {
					// Bot action
					Console.clear();
					Console.showPublicData(game);
					Console.jumpLine(3);
					Console.showLastAction(game);
					if(game.getNextPlayer() != null) {
						Console.waitToBeReady(game.getNextPlayer().getName());
					}
					else {
						Console.waitToBeReady("Everyone");
					}
				}
				else {
					// End of the round
					Console.clear();
					System.out.println("Hey it's the end of the year !");
					System.out.println("Here is the scores !");
					Console.showPublicData(game);
					Console.waitToBeReady(game.getNextPlayer().getName());
				}
			}
		}

		Console.clear();
		System.out.println("End of the game !");
		Console.showPublicData(game);
		
	}
}

