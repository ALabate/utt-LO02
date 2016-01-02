package me.labate.utt.lo02.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Player;

@SuppressWarnings("serial")
public class RankingPanel extends JPanel{

	JButton continueBtn;
	
	public RankingPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		setMaximumSize(new Dimension(600, 99999));

		continueBtn = new JButton("Continuer");
		continueBtn.setActionCommand("continue");

	}
	
	public void hydrate(Game game)
	{

		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		String text = "<html>";
		
		if(game.getYearCount() <= game.getYear())
		{
			text += "<h1>Fin du jeu</h1>";
		}
		else
		{
			text += "<h1>Fin du round</h1>";
		}
		
		// Create player list that will be sorted
		LinkedList<Player> players = new LinkedList<Player>();
		for (int i = 0; i < game.getPlayerCount(); i++) {
			players.add(game.getPlayer(i));
		}
		
		if(game.getYear() > 1)
		{
			// Rank player for the round
			Collections.sort(players, new Comparator<Player>() {
		        @Override
		        public int compare(Player p1, Player p2)
		        {
		        	if(p1.getMenhir() > p2.getMenhir())
		        		return -1;
		        	if(p1.getMenhir() == p2.getMenhir()){ // check the seeds
		        		if(p1.getSeed() > p2.getSeed())
		        			return -1;
		        		if(p1.getSeed() == p2.getSeed())
		        			return 0;	
		        	}
		        	return 1;
		        }
		    });
			
			// Print player round rank
			text += "<h2>Classement pour ce round</h2>";
			int i=1;
			for (Player player : players) {
				text += i + " - " + player.getName() + " : " + player.getMenhir() + " menhir(s)<br/>";
				i++;
			}
		}
		
		// Rank player for the game
		Collections.sort(players, new Comparator<Player>() {
	        @Override
	        public int compare(Player p1, Player p2)
	        {
	        	if((p1.getMenhir()+p1.getScore()) > (p2.getMenhir()+p2.getScore()))
	        		return -1;
	        	if((p1.getMenhir()+p1.getScore()) == (p2.getMenhir()+p2.getScore())){
	        		if(p1.getSeed() > p2.getSeed())
	        			return -1;
	        		if(p1.getSeed() == p2.getSeed())
	        			return 0;
	        	}
	        	return 1;
	        }
	    });
		
		// Print player round rank
		text += "<h2>Classement général</h2>";
		int i=1;
		for (Player player : players) {
			text += i + " - " + player.getName() + " : " + (player.getMenhir()+player.getScore()) + " menhir(s)<br/>";
			i++;
		}

		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		buttonLine.add(continueBtn);
		if(game.getYearCount() <= game.getYear())
		{
			continueBtn.setVisible(false);
		}
    	
    	JLabel textLabel = new JLabel(text);
		textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(textLabel);
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}


	/**
	 * @return the finishBtn
	 */
	public JButton getContinueBtn() {
		return continueBtn;
	}
}
