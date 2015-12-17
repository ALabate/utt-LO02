package me.labate.utt.lo02.gui;

import me.labate.utt.lo02.core.FullGame;
import me.labate.utt.lo02.core.Game;

public class Controller {

	public static void main(String[] args) {
		
		Game game = new FullGame();
		game.addHuman("Bob");
		game.addHuman("Albert");
		game.addBot("Zero", 0);
		game.addBot("Smith", 0);
		
		game.next();
		
		MainWindow win = new MainWindow();
		
		win.hydrate(game);
		
		win.setVisible(true);
	}

}
