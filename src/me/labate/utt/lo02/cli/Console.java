package me.labate.utt.lo02.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;

import me.labate.utt.lo02.core.FastGame;
import me.labate.utt.lo02.core.FullGame;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.AllyCard;
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
/**
 * This class own the print console's methods in order to make Cli.java more readable. 
 * @author Benoit,Alabate
 *
 */
public class Console {

	/**
	 * Print only one ingredient card on the console's screen
	 * @param card
	 */
	public static void showIngredientCard(IngredientCard card) {
		ArrayList<IngredientCard> cards = new ArrayList<IngredientCard>();
		cards.add(card);
		showIngredientCard(cards);
	}

	/**
	 * Print all the ingredient cards on the screen
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
			if(name.length() > 19) {
				name = name.substring(0, 19);
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
		
		// Print If he has an ally card
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
		System.out.print("|");
		for(int i = 0 ; i < playerCount ; i++) {
			String text = "";
			text = " Current player: " + ((context.getPlayerID(context.getNeededPlayer()) == i)?"Yes":"No");
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
		String text;
		if(context.getYear() < context.getYearCount())
			text = " Season '" + context.getSeason().toString().toLowerCase() + "' of year " + (context.getYear()+1) + "/" + context.getYearCount() + " " ;
		else
			text = "Finish";
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
	 * Ask a simple question by printing the question
	 * @return what the user will tap
	 * @param question : what you want to ask
	 */
	public static String question(String question){
		String answer = "";
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		// print question
		System.out.println(question);
		// read the answer
		answer = in.nextLine();
		// limit length 
		return answer;
	}
	
	/**
	 * Let the user see what happened by asking him to press any key to continue
	 * @param playerName : name of player
	 */
	public static void waitToContinue(String playerName) {
		Console.jumpLine(1);
		question("--- " + playerName + ": Press any key to continue ! ---");
	}
	
	/**
	 * Let the user tell when he is ready by asking him to press any key to continue
	 * @param playerName : name of player
	 * @param game : the currentGame
	 */
	public static void waitToBeReady(String playerName,Game game) {
		boolean cont = true;
		while(cont){
			Console.jumpLine(1);
			String answer = question("--- " + playerName + ": Press any key when you are ready ! ---");
			// if user want to use his mole, he taped one of this option
			if(answer.contentEquals("m") || answer.contentEquals("M") || answer.contentEquals("Mole") || answer.contentEquals("mole") || answer.contentEquals("taupe") || answer.contentEquals("Taupe")||answer.contentEquals("Ally")||answer.contentEquals("ally")||answer.contentEquals("A")||answer.contentEquals("a")||answer.contentEquals("ALLY")||answer.contentEquals("MOLE")||answer.contentEquals("TAUPE")){
				purposeUseMole(game);
				cont = false;
			}
			else if(answer.contentEquals("?") || answer.contentEquals("help") || answer.contentEquals("aide") || answer.contentEquals("Help") || answer.contentEquals("HELP")||answer.contentEquals("AIDE")){
				instrucionMole();
			}
			else{
				cont = false;
			}
		}
	}
	
	/**
	 * Jump some lines on the terminal
	 * @param line : how many lines to jump
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
			System.out.flush();
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
	/**
	 * print the title of the game.
	 */
	public static void printTitle()
	{
		System.out.println(" _______________________________________________________________");
		System.out.println("|\t\t\t   GAME OF TROLLS\t\t\t|");
		System.out.println("|---------------------------------------------------------------|");
		System.out.println("|\t\t\t\t\t\t\t\t|");
		System.out.println("|\t\t\tby Alabate and Benoit\t\t\t|");
		System.out.println("|\t\t\t\t\t\t\t\t|");
		System.out.println("|_______________________________________________________________|");
	}
	/**
	 * Initialized game by interacting with the user.
	 * @return Game initialized
	 */
	public static Game gameBegin()
	{
		Console.printTitle();
		HashMap<String, String> propositions = new HashMap<String, String>();
		String question = "What kind of game do you want to play  :";
		propositions.put("1", "Play a FastGame");
		propositions.put("2", "Play a FullGame");
		String answer = Console.question(question, propositions);
		Game game;
		
		if(answer.equalsIgnoreCase("2")) {
			game = new FullGame();
		}
		else {
			game = new FastGame();
		}
		
		// let the user enter his name
		String name =  Console.question("What is your name ?\n");
		// add the user as human player
		game.addHuman(name);
		// let user give how many players
		propositions.clear();
		question = "How many players in the game :";
		for(Integer i = 2; i <= 6; i++)
			propositions.put(i.toString(), i.toString() + " Players play this game");
		answer = Console.question(question, propositions);
		Integer nbrPlayer = Integer.valueOf(answer);
		
		// for each player, enter informations.
		for(Integer i = 0; i < nbrPlayer;  i++)
		{
			name = "Bot Number " + (i+1);
			Random rd = new Random();
			game.addBot(name,rd.nextInt(11));
		}
		return game;
		
	}
	/**
	 * interact with user and purpose him to use his mole
	 * @param game
	 */
	public static void purposeUseMole(Game game){
		HashMap<String, String> propositions = new HashMap<String, String>();
		String question = "";
		String answer = "";
		int playerID;
		if(game.getSeason() != Season.INIT && game.getLastAction() != Action.LEPRECHAUN_REQUEST) {
			Console.clear();
			Console.showPublicData(game);
			Console.jumpLine(3);
			
			boolean ask = true;
			while(ask) // keep asking until "no one" is selected
			{
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
					playerID = Integer.parseInt(answer) - 1;
					Player player2 = game.getPlayer(playerID);
					AllyCard card = player2.getAllyCard();
					System.out.println(player2.getName() + ", your ally card:");
					Console.showAllyCard(card);
					if( card.getValue(AllyMethod.MOLE, game.getSeason()) >= 0 ) {
						// Do you want to attack
						propositions.clear();
						question = "Do you want to use your mole to attack someone ?";
						propositions.put("y", "Yes");
						propositions.put("n", "No");	
						answer = Console.question(player2.getName() + ": " + question, propositions, "");
						if(answer.equals("y")) {
							// Choose target
							propositions.clear();
							question = "Who do you want to attack with your mole ?";
							for(int i = 1; i <= game.getPlayerCount() ; i++) {
								if(i -1 != playerID) { // a player cannot attack himself
									propositions.put(String.valueOf(i), game.getPlayer(i-1).getName());
								}
							}
							// Execute order
							answer = Console.question(player2.getName() + ": " + question, propositions, "");
							Player target2 = game.getPlayer(Integer.parseInt(answer) - 1);
							// give the right to player for attacking
							player2.chooseMoleAttack(target2);
						
							// Show last action
							Console.clear();
							Console.showPublicData(game);
							Console.jumpLine(3);
							Console.showLastAction(game);
							// TODO change this line ^^
							waitToContinue("");
						}
					}
					else {
						System.out.println(player2.getName() + ": You cannot attack someone because you don't have any mole.");
						System.out.println("But remember that other players only see that you have an ally card. They don't know if it's a dog or a mole. So you can still bluff ;)");
						Console.waitToContinue(player2.getName());
					}
				}
				else ask = false;// stop asking
				Console.clear();
			}
		}
	}
	/**
	 * print the instruction for using a mole attack.
	 */
	public static void instrucionMole(){
		System.out.println("if you want to use or see your allyCard, dont forget that you can type \n");
		System.out.println("\"Ally\" or \"Mole\" or \"Help\" \t\tto reprint these information when you see :\n");
		System.out.println("\"--- :[ name ] Press any key when you are ready ! ---\" \n");
	}
}