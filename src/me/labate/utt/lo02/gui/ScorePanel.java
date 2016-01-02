package me.labate.utt.lo02.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Player;

@SuppressWarnings("serial")
public class ScorePanel extends JPanel {

	public ScorePanel() {
		super();
		
		
		// Layout configuration
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setAlignmentX(CENTER_ALIGNMENT);
		setBorder(new EmptyBorder(0, 0, 10, 0));
		
		
	}
	
	public void hydrate(Game game)
	{
		this.removeAll();
		
		// TODO change for iterator
		for(int i=0; i<game.getPlayerCount(); i++)
		{
			if(i != 0)
			{
				JSeparator separator = new JSeparator(JSeparator.VERTICAL);
				separator.setMaximumSize(new Dimension(1, this.getMaximumSize().height));
				add(separator);
			}
			
			
			Player player = game.getPlayer(i);

			// Create player box
			JPanel playerBox = new JPanel();
			playerBox.setLayout(new BoxLayout(playerBox, BoxLayout.Y_AXIS));
			playerBox.setBorder(new EmptyBorder(10, 15, 10, 15));
			add(playerBox);
			
			// Needed player
			if(game.getNeededPlayer() == player 
					|| (game.getNeededPlayer() == null && game.getLastPlayer() == player))
			{
				playerBox.setBackground(Color.YELLOW);
			}

			// Name
			String name = player.getName();
			if(player.isBot())
				name += " (Robot)";
			playerBox.add(new JLabel(name));

			// Seeds
			String seed = new String();
			seed += player.getSeed();
			if(player.getSeed() <= 1)
				seed += " graine";
			else
				seed += " graines";
			playerBox.add(new JLabel(seed));

			// Menhir
			String menhir = new String();
			menhir += player.getMenhir();
			if(player.getMenhir() <= 1)
				menhir += " menhir";
			else
				menhir += " menhirs";
			playerBox.add(new JLabel(menhir));

			// Score
			if(player.getScore() != 0)
			{
				String score = new String();
				score += player.getScore();
				if(player.getScore() <= 1)
					score += " point";
				else
					score += " points";
				playerBox.add(new JLabel(score));
			}

			// Cards
			String cards = "Cartes : ";
			for(int c=0; c<player.getIngredientCardCount(); c++) // TODO get list
			{
				cards += "I";
			}
			if(player.hasAllyCard())
			{
				cards += " A";
			}
			playerBox.add(new JLabel(cards));
			

		}
		
	}
	

}
