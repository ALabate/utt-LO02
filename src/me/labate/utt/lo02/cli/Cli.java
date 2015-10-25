package me.labate.utt.lo02.cli;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.Player.Choice;
import me.labate.utt.lo02.core.Player.Method;
import me.labate.utt.lo02.core.FastGame;

import java.util.ArrayList;
import java.util.Scanner;

import me.labate.utt.lo02.core.Card;

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
	public static void showCard(Card card) {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(card);
		showCards(cards, true);
	}

	/**
	 * Print one card on the screen
	 * @param card
	 */
	public static void showCard(Card card, boolean showHeader) {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(card);
		showCards(cards, showHeader);
	}
	

	/**
	 * Print cards on the screen
	 * @param cards
	 */
	public static void showCards(ArrayList<Card> cards) {
		showCards(cards, true);
	}
	
	/**
	 * Print cards on the screen
	 * @param cards
	 */
	public static void showCards(ArrayList<Card> cards, boolean showHeader) {
		
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
				System.out.print(cards.get(i).getGiantValue(Season.SPRING) + " ");
				System.out.print(cards.get(i).getGiantValue(Season.SUMMER) + " ");
				System.out.print(cards.get(i).getGiantValue(Season.AUTUMN) + " ");
				System.out.print(cards.get(i).getGiantValue(Season.WINTER) + " |     ");
			}	
		}
		System.out.println();
		
		// Print fertilizer line
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("| Fertilizer: ");
				System.out.print(cards.get(i).getFertilizerValue(Season.SPRING) + " ");
				System.out.print(cards.get(i).getFertilizerValue(Season.SUMMER) + " ");
				System.out.print(cards.get(i).getFertilizerValue(Season.AUTUMN) + " ");
				System.out.print(cards.get(i).getFertilizerValue(Season.WINTER) + " |     ");
			}	
		}
		System.out.println();
		
		// Print leprechaun line
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("| Leprechaun: ");
				System.out.print(cards.get(i).getLeprechaunValue(Season.SPRING) + " ");
				System.out.print(cards.get(i).getLeprechaunValue(Season.SUMMER) + " ");
				System.out.print(cards.get(i).getLeprechaunValue(Season.AUTUMN) + " ");
				System.out.print(cards.get(i).getLeprechaunValue(Season.WINTER) + " |     ");
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
		if(game.getLastAction() == Action.GIANT 
				|| game.getLastAction() == Action.LEPRECHAUN 
				|| game.getLastAction() == Action.FERTILIZER) {
			System.out.println("------------------- Last Action -------------------");
			String name = game.getPlayerName(game.getLastPlayerID());
			System.out.println(name + " has play " + game.getLastAction().toString() + " on :");
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
		      String c = in.nextLine();
			
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
		game.addBot("John", 0);
		game.addHuman("Benoit");
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
				Method method;
				switch(m) {
				case 0 : method = Method.GIANT; break;
				case 1 : method = Method.FERTILIZER; break;
				default: method = Method.LEPRECHAUN; break;
				}
				// Choose target
				int t = -1;
				if(method == Method.LEPRECHAUN) {
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
				//TODO
				break;
			case DEFENSE:
				//TODO
				break;
			default:
				
				break;
			}
		}
		/*
		System.out.println("Yeah ! It's the first turn ! Wa do we do ?2");
		System.out.println(game.getCurrentPlayerID());
		game.next();
		showScores(game, false);
		showCards(game.getPlayerCards());
		game.chooseCard(0, Player.Method.GIANT);
		showLastAction(game);
		game.next();
		showLastAction(game);
		showScores(game, false);
		game.next();*/
		
		
	}
}
