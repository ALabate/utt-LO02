package me.labate.utt.lo02.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.AllyCard;
import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

public class Console {

	/**
	 * Print one ingredient card on the screen
	 * @param card
	 */
	public static void showIngredientCard(IngredientCard card) {
		ArrayList<IngredientCard> cards = new ArrayList<IngredientCard>();
		cards.add(card);
		showIngredientCard(cards);
	}

	/**
	 * Print ingredient cards on the screen
	 * @param cards
	 */
	public static void showIngredientCard(ArrayList<IngredientCard> cards) {
		
		// Print header
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("__________ " + (i+1) + " __________     ");
			}
		}
		System.out.println();
		
		// Print season line
		for(int i = 0 ; i < cards.size() ; i++) {
			if(cards.get(i) != null) {
				System.out.print("|             S S A W |     ");
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
	 * Print ally card on the screen
	 * @param cards
	 */
	public static void showAllyCard(AllyCard card) {
		if(card == null) {
			return;
		}
		// Find if it's a doc on mole card
		AllyMethod method;
		if(card.getValue(AllyMethod.DOG, Season.SPRING) != -1) {
			method = AllyMethod.DOG;
		}
		else {
			method = AllyMethod.MOLE;
		}
		
		// Print header lines
		System.out.println("_________________");
		System.out.println("|       S S A W |");
		
		// Print Value line
		if(method == AllyMethod.DOG) {
			System.out.print("| Dog:  ");
		}
		else {
			System.out.print("| Mole: ");
		}			
		System.out.print(card.getValue(method, Season.SPRING) + " ");
		System.out.print(card.getValue(method, Season.SUMMER) + " ");
		System.out.print(card.getValue(method, Season.AUTUMN) + " ");
		System.out.println(card.getValue(method, Season.WINTER) + " |");
		
		//Footer
		System.out.println("-----------------");
	}

	/**
	 * Print all public data the player can see
	 * @param context
	 */
	public static void showPublicData(Game context) {
		int playerCount = context.getPlayerCount();
		
		// Print user name and header
		System.out.print(".");
		for(int i = 0 ; i < playerCount ; i++) {
			// Format name and add space margin
			String name = context.getPlayer(i).getName();
			if(name.length() > 20) {
				name = name.substring(0, 20);
			}
			name =  " " + name + " ";
			
			String text = "_";
			int nameBegin = (22-name.length())/2;
			//Complete before
			for(int j = 0 ; j < nameBegin ; j++) {
				text += "_";
			}
			text += name;
			
			System.out.print(text);
			for(int j = text.length(); j < 22; j++) {
				System.out.print("_");
			}
			System.out.print(".");
		}
		System.out.println();
		
		// Print seeds
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			String text = " Seeds:   " + String.valueOf(context.getPlayer(i).getSeed());
			System.out.print(text);
			for(int j = text.length(); j < 22; j++) {
				System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.println();

		// Print Menhir
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			String text = " Menhirs: " + String.valueOf(context.getPlayer(i).getMenhir());
			System.out.print(text);
			for(int j = text.length(); j < 22; j++) {
				System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.println();
		
		// Print Score only if useful (ie a score is != 0)
		String line = "|";
		boolean useful = false;
		for(int i = 0 ; i < playerCount ; i++) {
			useful = useful | context.getPlayer(i).getScore() != 0;
			String text = " Score:   " + String.valueOf(context.getPlayer(i).getScore());
			line += text;
			for(int j = text.length(); j < 22; j++) {
				line += " ";
			}
			line += "|";
		}
		if(useful) {
			System.out.println(line);
		}

		// Print Number of cards
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			String text = " Cards left: " + String.valueOf(context.getPlayer(i).getIngredientCardCount());
			System.out.print(text);
			for(int j = text.length(); j < 22; j++) {
				System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.println();
		
		// Print If he have an ally card
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			String text = " Ally card: " + (context.getPlayer(i).hasAllyCard()?"Yes":"No");
			System.out.print(text);
			for(int j = text.length(); j < 22; j++) {
				System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.println();
		
		// Print if he is the current player
//		System.out.print("|");
//		for(int i = 0 ; i < playerCount ; i++) {
//			String text = "";
//			text = " Current player: " + ((context.getPlayerID(context.getNeededPlayer()) == i)?"Yes":"No");
//			System.out.print(text);
//			for(int j = text.length(); j < 22; j++) {
//				System.out.print(" ");
//			}
//			System.out.print("|");
//		}
//		System.out.println();
		
		// TODO DEBUG uncomment precedent and remove next block
		// Print player needed choice
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			String text = "";
			if(context.getPlayerID(context.getNeededPlayer()) == i) {
				text = " Choice: " + context.getNeededChoice();
			}
			else {
				text = " Choice:";
			}
			System.out.print(text);
			for(int j = text.length(); j < 22; j++) {
				System.out.print(" ");
			}
			System.out.print("|");
		}
		System.out.println();
		

		// Print middle line
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			System.out.print("______________________.");
		}
		System.out.println();
		
		// Print ingame date
		System.out.print("|");
		String text = " Season '" + context.getSeason().toString().toLowerCase() + "' of year " + (context.getYear()+1) + "/" + context.getYearCount() + " " ;
		String ctext = "";
		int textBegin = (23*playerCount -1 -text.length())/2;
		//Complete before
		for(int j = 0 ; j < textBegin ; j++) {
			ctext += " ";
		}
		text = ctext + text;
		System.out.print(text);
		for(int j = text.length(); j < 23*playerCount-1; j++) {
			System.out.print(" ");
		}
		System.out.println("|");


		// Print footer line
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			System.out.print("______________________.");
		}
		System.out.println();
	}
	

	/**
	 * Ask a question to the user with a list of propositions. The user have to enter a character and press enter
	 * Upper/Lowercase is ignored
	 * @param question The question written on top
	 * @param propositions the list with as index the character IN LOWERCASE that the user will write to choose
	 * this item and the description as value.
	 * @param If the user only press enter without any character, the content of defaultStr will be used.
	 * @return the index of the triggered item as lowercase
	 */
	public static String question(String question, HashMap<String, String> propositions) {
		return question(question, propositions, "");
	}
	
	/**
	 * Ask a question to the user with a list of propositions. The user have to enter a character and press enter
	 * Upper/Lowercase is ignored
	 * @param question The question written on top
	 * @param propositions the list with as index the character IN LOWERCASE that the user will write to choose
	 * this item and the description as value.
	 * @param If the user only press enter without any character, the content of defaultStr will be used.
	 * If defaultStr is empty or correspond to no option, an user choice is required.
	 * @return the index of the triggered item as lowercase
	 */
	public static String question(String question, HashMap<String, String> propositions, String defaultStr) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String answer = "";
		while(!propositions.containsKey(answer.toLowerCase())) {
			// Print question and propositions
			System.out.println(question);
			String proposition = "";
			for(Entry<String, String> entry : propositions.entrySet()) {
				if(defaultStr.toLowerCase() == entry.getKey().toLowerCase()) {
					System.out.println("\t" + entry.getKey().toUpperCase() + ": " + entry.getValue());
					defaultStr = entry.getKey();
				}
				else
				{
					System.out.println("\t" + entry.getKey().toLowerCase() + ": " + entry.getValue());
				}
				proposition = entry.getKey().toLowerCase();
			}
			// If there is only one option, it's not necessary to ask
			if(propositions.size() == 1) {
				return proposition;
			}
			
			// Ask for new input
			while(!in.hasNextLine()){in.next();}
			answer = in.nextLine();
			
			// Default output ?
			if(answer.equals("") &&  propositions.containsKey(defaultStr)) {
				return defaultStr;
			}
		}
		return answer;
	}

	/**
	 * Let the user see what happend by asking him to press any key to continue
	 */
	public static void waitToContinue(String playerName) {
		Console.jumpLine(1);
		System.out.println("--- " + playerName + ": Press any key to continue ! ---");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		in.nextLine();
		
	}
	
	/**
	 * Let the user tell when he is ready by asking him to press any key to continue
	 */
	public static void waitToBeReady(String playerName) {
		Console.jumpLine(1);
		System.out.println("--- " + playerName + ": Press any key when you are ready ! ---");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		in.nextLine();		
	}
	
	/**
	 * Jump some lines on the terminal
	 * @param line the number of line to jump
	 */
	public static void jumpLine(int line) {
		for(int i = 0; i < line; i++) {
			System.out.println();
		}
	}

	/**
	 * Remove everything written in the console
	 */
	public static void clear() {      
		final String os = System.getProperty("os.name");        
		if (os.contains("Windows"))
		{
			jumpLine(50); // User uses Windows     
		}     
		else   // User uses Linux/Unix System
		{     
			System.out.print("\033[H\033[2J");   
			System.out.flush();   
		}
	}
	

	/**
	 * Print the last action on the screen
	 * @param game Game data
	 */
	public static void showLastAction(Game game) {
		String name = game.getLastPlayer().getName();
		switch(game.getLastAction()) {
			case GIANT:
			case LEPRECHAUN:
			case FERTILIZER:
			{
				System.out.println(name + " used");
				System.out.println("\t " + game.getLastAction() + " in " + game.getLastSeason());
				Console.showIngredientCard(game.getLastIngredientCard());
				Console.jumpLine(2);
				switch(game.getLastAction()) {
					default:
					case GIANT:
					{
						System.out.println(name + " earn " + game.getLastPoints() + " seeds");
						break;
					}
					case FERTILIZER:
					{
						int originalPoints = game.getLastIngredientCard().getValue(IngredientMethod.FERTILIZER, game.getLastSeason());
						if(originalPoints != game.getLastPoints()) {
							System.out.println(name + " try to convert " + originalPoints + " seeds to menhir");
							System.out.println("\tbut "+ name + " had only " + game.getLastPoints() + " seeds");
						}
						Console.jumpLine(1);
						System.out.println(name + " convert " + game.getLastPoints() + " seeds to menhir");
						break;
					}
					case LEPRECHAUN:
					{
						int originalPoints = game.getLastIngredientCard().getValue(IngredientMethod.LEPRECHAUN, game.getLastSeason());
						int defendPoints = -1;
						if(game.getLastAllyCard() != null) {
							defendPoints = game.getLastAllyCard().getValue(AllyMethod.DOG, game.getLastSeason());
						}
						String targetName = game.getLastTarget().getName();
						if(originalPoints != game.getLastPoints() || defendPoints >= 0) {
							System.out.println(name + " try to stole " + originalPoints + " seeds from " + targetName + " but");
							// If target defend himself
							System.out.println("Console:416:"+defendPoints);
							if(defendPoints >= 0) {
								System.out.println("\t"+ targetName + " defend himself with " + defendPoints + " dogs");
								originalPoints -= defendPoints;							
							}
							else {
								defendPoints = 0;
							}
							// If target hasn't enough seeds
							if(originalPoints != game.getLastPoints()) {
								System.out.println("\t"+ targetName + " had only " + game.getLastPoints() + " seeds");
							}
						}
						Console.jumpLine(1);
						System.out.println(name + " stole " + game.getLastPoints() + " seeds from " + game.getLastTarget().getName());
						break;
					}
				}
				 break;
			}
			case BONUS_ALLY:
			{
				System.out.println(name + " chose as bonus");
				System.out.println("\t1 ally card");
				break;
			}
			case BONUS_SEEDS:
			{
				System.out.println(name + " chose as bonus");
				System.out.println("\t2 seeds");
				break;
			}
			case LEPRECHAUN_REQUEST:
			{
				System.out.println(name + " tried to stole");
				System.out.println("\t" + game.getLastIngredientCard().getValue(IngredientMethod.LEPRECHAUN, game.getLastSeason()) + " seeds from " +  game.getLastTarget().getName());
				System.out.println("Let see if " + game.getLastTarget().getName() + " will defend himself");
				break;
			}
			case MOLE:
			{
				int moles = game.getLastAllyCard().getValue(AllyMethod.MOLE, game.getLastSeason());
				System.out.println(name + " attacked " + game.getLastTarget().getName() + " with "+ moles + " mole");
				Console.jumpLine(1);
				Console.showAllyCard(game.getLastAllyCard());
				Console.jumpLine(1);
				if(moles != game.getLastPoints()) {
					System.out.println("\tbut " + game.getLastTarget().getName() + " had only " + game.getLastPoints() + " menhirs");
				}
				System.out.println("\the destroyed " + game.getLastPoints() + " menhirs");
				break;
			}
			default:
				break;
		
		}
	}
	
	
}
