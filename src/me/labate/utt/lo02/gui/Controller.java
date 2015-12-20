package me.labate.utt.lo02.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import me.labate.utt.lo02.core.FullGame;
import me.labate.utt.lo02.core.Game;

public class Controller implements ActionListener  {

	MainWindow win;
	Game game;
	
	public static void main(String[] args) {
		Controller controller = new Controller();
	}
	
	public Controller() {
		
		game = new FullGame();
		game.addHuman("Bob");
		game.addHuman("Albert");
		game.addBot("Zero", 0);
		game.addBot("Smith", 0);
		
		game.next();
		
		// Construct window
		win = new MainWindow();
		
		// Get actions
		JButton moleBackBtn = win.getMolePanel().getBackBtn();
		moleBackBtn.addActionListener(this); 
		
		// Update data and draw on screen
		win.hydrate(game);
		win.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Mole action/Panel
	    if ("moleBack".equals(e.getActionCommand())) {
	    	win.getMolePanel().hydrate(game);
	    }
	}

}
