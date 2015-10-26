package me.labate.utt.lo02.cli;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.Player.Choice;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player.Bonus;
import me.labate.utt.lo02.core.FastGame;

import java.util.ArrayList;
import java.util.Scanner;


public class Cli {
	
	/**
	 * Print on the console scores, seeds and menhir of every players
	 * @param game - The game data
	 * @param showScore - On fastGame score will always be 0 so you can remove it
	 */
	public static void showScores(Game game, boolean showScore) {
		// Print header
		System.out.println("------------------- Scores -------------------");
		System.out.print("---- Year : " + (game.getYear()+1) + "/" + game.getYearCount());
		System.out.println(", Season : " + game.getSeason());
		if(showScore) {
			System.out.println("- Name: seed, menhir, score");
		}
		else {
			System.out.println("- Name: seed, menhir");
		}
		// Print values
		for(int i = 0; i < game.getPlayerCount(); i++) {
			if(i == game.getCurrentPlayerID()) {
				System.out.print("+");
			}
			System.out.print(game.getPlayerName(i) + ": " + game.getPlayerSeed(i) + ", " + game.getPlayerMenhir(i));
			if(showScore) {
				System.out.println(", " + game.getPlayerScore(i));
			}
			else {
				System.out.println();
			}
		}
	}

	/**
	 * Print one card on the screen
	 * @param card
	 */
	public static void showCard(IngredientCard card) {
		ArrayList<IngredientCard> cards = new ArrayList<IngredientCard>();
		cards.add(card);
		showCards(cards, true);
	}

	/**
	 * Print one card on the screen
	 * @param card
	 */
	public static void showCard(IngredientCard card, boolean showHeader) {
		ArrayList<IngredientCard> cards = new ArrayList<IngredientCard>();
		cards.add(card);
		showCards(cards, showHeader);
	}
	

	/**
	 * Print cards on the screen
	 * @param cards
	 */
	public static void showCards(ArrayList<IngredientCard> cards) {
		showCards(cards, true);
	}
	
	/**
	 * Print cards on the screen
	 * @param cards
	 */
	public static void showCards(ArrayList<IngredientCard> cards, boolean showHeader) {
		
		// Print header
		if(showHeader) {
			System.out.println("------------------- Cards -------------------");
		}
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("__________ " + i + " _________      ");
			}
		}
		System.out.println();
		
		// Print season line
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("|             ");
				System.out.print("S ");
				System.out.print("S ");
				System.out.print("A ");
				System.out.print("W |     ");
			}	
		}
		System.out.println();
		
		// Print Giant line
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("| Giant:      ");
				System.out.print(cards.get(i).getValue(IngredientMethod.GIANT, Season.SPRING) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.GIANT, Season.SUMMER) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.GIANT, Season.AUTUMN) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.GIANT, Season.WINTER) + " |     ");
			}	
		}
		System.out.println();
		
		// Print fertilizer line
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("| Fertilizer: ");
				System.out.print(cards.get(i).getValue(IngredientMethod.FERTILIZER, Season.SPRING) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.FERTILIZER, Season.SUMMER) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.FERTILIZER, Season.AUTUMN) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.FERTILIZER, Season.WINTER) + " |     ");
			}	
		}
		System.out.println();
		
		// Print leprechaun line
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("| Leprechaun: ");
				System.out.print(cards.get(i).getValue(IngredientMethod.LEPRECHAUN, Season.SPRING) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.LEPRECHAUN, Season.SUMMER) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.LEPRECHAUN, Season.AUTUMN) + " ");
				System.out.print(cards.get(i).getValue(IngredientMethod.LEPRECHAUN, Season.WINTER) + " |     ");
			}	
		}
		System.out.println();
		
		//Footer
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("-----------------------     ");
			}
		}
		System.out.println();

	}
	
	/**
	 * Print the last action on the screen
	 * @param game Game data
	 */
	public static void showLastAction(Game game) {
		String name = game.getPlayerName(game.getLastPlayerID());
		switch(game.getLastAction()) {
		case GIANT:
		case LEPRECHAUN:
		case FERTILIZER:
			System.out.println("------------------- Last Action -------------------");
			System.out.println(name + " has choosed " + game.getLastAction().toString() + " on :");
			showCard(game.getLastCard(), false);
			System.out.print("On Year : " + (game.getLastYear()+1) + "/" + game.getYearCount());
			System.out.println(", Season : " + game.getLastSeason() + "");
			switch(game.getLastAction()) {
			case GIANT:
				System.out.println(name + " earn " + game.getLastPoints() + " seeds");
				break;
			case FERTILIZER:
				System.out.println(name + " convert " + game.getLastPoints() + " seeds to menhir");
				break;
			case LEPRECHAUN:
				System.out.println(name + " stole " + game.getLastPoints() + " seeds from " + game.getPlayerName(game.getLastTargetID()));
				break;
			default:
				break;
			}
			System.out.println("Press any keys to continue");
			Scanner in = new Scanner(System.in);
			in.nextLine();
			in.close();
			 break;
		case BONUS_ALLY:
			System.out.println("------------------- Last Action -------------------");
			System.out.println(name + " has choosed to take an ally card as bonus");
			break;
		case BONUS_SEEDS:
			System.out.println("------------------- Last Action -------------------");
			System.out.println(name + " has choosed to take two seeds as bonus");
			break;
		case LEPRECHAUN_REQUEST:
			break;
		case MOLE:
			break;
		default:
			break;
		
		}
		if(game.getLastAction() == Action.GIANT 
				|| game.getLastAction() == Action.LEPRECHAUN 
				|| game.getLastAction() == Action.FERTILIZER) {
			
		}
		else {
			// TODO
		}
	}
	
	
	public static void main(String[] args) {

      Scanner in = new Scanner(System.in);
		
		
		
		System.out.println("Start the game !");
		Game game = new FastGame();
		game.addHuman("Alabate");
		//game.addBot("John", 0);
		//game.addHuman("Benoit");
		game.addBot("Bob", 0);
		
		// Init
		while(game.next()) {
			showLastAction(game);
			showScores(game, false);
			switch(game.getPlayerNeededChoice()) {
			case CARD:
				// Select cards
				int c = -1;
				while(c < 0 || c > 3 || game.getPlayerCards().get(c) == null) {
					showCards(game.getPlayerCards());
					System.out.println(game.getPlayerName(game.getCurrentPlayerID()) + " : What card do you want to play ? [0-3]");
					c = Integer.parseInt(in.nextLine());
				}
				// Select methode
				int m = -1;
				while(m < 0 || m > 2) {
					showCard(game.getPlayerCards().get(c));
					System.out.println(game.getPlayerName(game.getCurrentPlayerID()) + " : What Method do you want to use ? [0-2]");
					System.out.println("0 : Giant");
					System.out.println("1 : Fertilizer");
					System.out.println("2 : Leprechaun");
					m = Integer.parseInt(in.nextLine());
				}
				IngredientMethod method;
				switch(m) {
				case 0 : method = IngredientMethod.GIANT; break;
				case 1 : method = IngredientMethod.FERTILIZER; break;
				default: method = IngredientMethod.LEPRECHAUN; break;
				}
				// Choose target
				int t = -1;
				if(method == IngredientMethod.LEPRECHAUN) {
					while(t < 0 || t > game.getPlayerCount()) {
						showCard(game.getPlayerCards().get(c));
						System.out.println(game.getPlayerName(game.getCurrentPlayerID()) + " : What Player do you want to target ? [0-" + game.getPlayerCount() + "]");
						for(int i = 0; i < game.getPlayerCount() ; i++) {
							System.out.println(i + " : " + game.getPlayerName(i));
						}
						t = Integer.parseInt(in.nextLine());
					}
				}
				
				// PLay
				game.chooseCard(c, method, t);
				showLastAction(game);
				
				break;
			case BONUS:
				// Select Bonus
				int b = -1;
				while(b < 0 || b > 1) {
					showCard(game.getPlayerCards().get(b));
					System.out.println(game.getPlayerName(game.getCurrentPlayerID()) + " : What bonus do you want to choose ? [0-1]");
					System.out.println("0 : Ally card");
					System.out.println("1 : Two seeds");
					b = Integer.parseInt(in.nextLine());
				}
				Bonus bonus;
				switch(b) {
					case 0 : bonus = Bonus.ALLY; break;
					default: bonus = Bonus.SEEDS; break;
				}
				
				// PLay
				game.chooseBonus(bonus);
				showLastAction(game);

				break;
			case DEFENSE:
				//TODO
				break;
			default:
				
				break;
			}
		}
		
		
	}
}
