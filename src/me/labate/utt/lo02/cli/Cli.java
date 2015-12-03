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

import java.util.HashMap;
	


public class Cli {	
	
	public static void main(String[] args) {
		
		// Let user set parameters.
		Game game = Console.gameBegin();
		
		HashMap<String, String> propositions = new HashMap<String, String>();
		String question;
		String answer;
		
		// the game begin
		boolean stop = false;
		while(!stop){
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
						question = "Wich bonus do you want ?";
						propositions.put("a", "One ally card");
						propositions.put("s", "Two seeds");				
						answer = Console.question(playerName + ": " + question, propositions, "");
						// Execute answer
						switch(answer) {
						case "a":
							player.chooseBonus(Bonus.ALLY);
							// Show new card
							Console.clear();
							Console.instrucionMole();
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
						Console.waitToBeReady(playerName,game);
						
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
							System.out.println("But remember that other players only see that you have an ally card. They don't know if it's a dog or a mole. So you can still bluff");
							Console.waitToContinue(playerName);
							player.chooseDefend(false);
						}
						
						break;
					}
					default:
						break;
				}				
				
				// Show last action
				Console.clear();
				Console.showPublicData(game);
				Console.jumpLine(3);
				Console.showLastAction(game);
				Console.waitToBeReady(game.getNextPlayer().getName(),game);
				
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
						Console.waitToBeReady(game.getNextPlayer().getName(),game);
					}
					else {
						Console.waitToBeReady("Everyone",game);
					}
				}
				else {
					// End of the round
					Console.clear();
					System.out.println("Hey it's the end of the year !");
					System.out.println("Here is the scores !");
					Console.showPublicData(game);
					Console.waitToBeReady(game.getNextPlayer().getName(),game);
				}
			}
		}
		Console.clear();
		System.out.println("End of the game !");
		// update the score if it's fullGame
		if(game.isFull()){
			int i = 0;
			while(game.getPlayer(i) != null){
				game.getPlayer(i).updateScore();
				i++;
			}
		}
		Console.showPublicData(game);
		
		// suggest to replay the game
		propositions.clear();
		question = "\nDo you want to replay the game with the same palyers?";
		propositions.put("y", "Yes");
		propositions.put("n", "No");	
		answer = Console.question(question, propositions,"n");
		switch(answer){
		case "n":
			stop = true;
			break;
		case "y":
			game.reset();
			break;
		}
	}
		// credits and quit the program
		System.out.println("Thanks for playing");
	}
}
