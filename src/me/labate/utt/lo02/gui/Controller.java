package me.labate.utt.lo02.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import me.labate.utt.lo02.core.FastGame;
import me.labate.utt.lo02.core.FullGame;
import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

public class Controller implements ActionListener  {

	MainWindow win;
	Game game;
	
	public static void main(String[] args) {
		Controller controller = new Controller();
	}
	
	public Controller() {
		
		game = new FastGame();
		game.addHuman("Bob");
		game.addHuman("Albert");
		game.addBot("Zero", 1);
		game.addBot("Smith", 1);
		
		game.next();
//		game.getNeededPlayer().playIngredientCard(game.getNeededPlayer().getIngredientCards().get(0), IngredientMethod.LEPRECHAUN, game.getPlayer(1));
		
		// Construct window
		win = new MainWindow();
		
		// Get actions
		win.getMolePanel().getBackBtn().addActionListener(this);
		win.getLastActionPanel().getContinueBtn().addActionListener(this);
		win.getDeckPanel().setClickListener(this);
		
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
	    else if("lastActionContinue".equals(e.getActionCommand())) {
	    	game.next();
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	    else if("cardClick".equals(e.getActionCommand())) {
	    	CardPanel panel = (CardPanel)e.getSource();
	    	if(panel.getIngredientCard() != null && panel.getClickedIMethod() != null)
	    	{
		    	game.getNeededPlayer().playIngredientCard(panel.getIngredientCard(), panel.getClickedIMethod());
	    	}
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	}

}
