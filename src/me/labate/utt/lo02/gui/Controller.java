package me.labate.utt.lo02.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.Player.Bonus;

public class Controller implements ActionListener  {

	MainWindow win;
	Game game;
	
	public Controller(Game gameParam) {
		
		game = gameParam;
		game.next();
		
		// Construct window
		win = new MainWindow();
		
		// Get actions
		win.getMolePanel().getBackBtn().addActionListener(this);
		win.getMolePanel().getNextBtn().addActionListener(this);
		win.getMolePanel().getFinishBtn().addActionListener(this);
		win.getMolePanel().getCancelBtn().addActionListener(this);
		win.getMoleButton().addActionListener(this);
		win.getLastActionPanel().getContinueBtn().addActionListener(this);
		win.getDeckPanel().setClickListener(this);
		win.getBonusPanel().getFinishBtn().addActionListener(this);
		win.getDefendPanel().getDefendBtn().addActionListener(this);
		win.getDefendPanel().getDontDefendBtn().addActionListener(this);
		win.getRankingPanel().getContinueBtn().addActionListener(this);
		win.getLeprechaunPanel().getFinishBtn().addActionListener(this);
		
		// Update data and draw on screen
		win.hydrate(game);
		
		win.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if ("moleBack".equals(e.getActionCommand())) {
	    	win.getMolePanel().hydrate(game);
	    }
	    else if ("moleFinish".equals(e.getActionCommand())) {
	    	Player player = (Player)win.getMolePanel().humanCombo.getSelectedItem();
	    	player.chooseMoleAttack((Player)win.getMolePanel().playerCombo.getSelectedItem());
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	    else if ("moleNext".equals(e.getActionCommand())) {
	    	win.getDeckPanel().hydrate(game, (Player)win.getMolePanel().getHumanCombo().getSelectedItem());
	    	win.getDeckPanel().validate();
	    	win.getDeckPanel().repaint();
	    }
	    else if ("moleStart".equals(e.getActionCommand())) {
	    	win.getMolePanel().hydrate(game);
    		win.selectMiddlePanel(win.getMolePanel());
	    	win.setVisible(true);
	    }
	    else if("continue".equals(e.getActionCommand())
	    		|| "moleCancel".equals(e.getActionCommand())) {
	    	game.next();
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	    else if("cardClick".equals(e.getActionCommand()) && game.getNeededPlayer() != null) {
	    	CardPanel panel = (CardPanel)e.getSource();
	    	if(panel.getIngredientCard() != null && panel.getClickedIMethod() != null)
	    	{
		    	if(panel.getClickedIMethod() == IngredientMethod.LEPRECHAUN)
		    	{
		    		win.selectMiddlePanel(win.getLeprechaunPanel());
		    		win.getLeprechaunPanel().hydrate(game);
		    		win.getLeprechaunPanel().setCard(panel.getIngredientCard());
		    		win.getLeprechaunPanel().setMethod(panel.getClickedIMethod());
		    		return;
		    	}
		    	game.getNeededPlayer().playIngredientCard(panel.getIngredientCard(), panel.getClickedIMethod());
	    	}
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	    else if("leprechaunFinish".equals(e.getActionCommand()) && game.getNeededPlayer() != null) {
	    	LeprechaunPanel panel = win.getLeprechaunPanel();
			game.getNeededPlayer().playIngredientCard(panel.getCard(), panel.getMethod(), (Player)panel.getPlayerCombo().getSelectedItem());
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	    else if("bonusFinish".equals(e.getActionCommand()) && game.getNeededPlayer() != null) {
	    	if(win.getBonusPanel().getBonusCombo().getSelectedIndex() == 0)
	    	{
	    		game.getNeededPlayer().chooseBonus(Bonus.SEEDS);
	    	}
	    	else
	    	{
	    		game.getNeededPlayer().chooseBonus(Bonus.ALLY);
	    	}
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	    else if("leprechaunDefend".equals(e.getActionCommand()) && game.getNeededPlayer() != null) {
			game.getNeededPlayer().chooseDefend(true);
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	    else if("leprechaunDontDefend".equals(e.getActionCommand()) && game.getNeededPlayer() != null) {
			game.getNeededPlayer().chooseDefend(false);
	    	win.hydrate(game);
	    	win.setVisible(true);
	    }
	}

}
