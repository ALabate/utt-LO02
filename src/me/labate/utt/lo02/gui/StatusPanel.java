package me.labate.utt.lo02.gui;


import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import me.labate.utt.lo02.core.Game;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel{

	public StatusPanel() {
		super();

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		}

	public void hydrate(Game game)
	{
		this.removeAll();
		
		if(game.getYear() < game.getYearCount())
		{
			String season = "";
			switch(game.getSeason())
			{
			case AUTUMN: 
				season = "Automne";
				break;
			case INIT:
				season = "Préparation";
				break;
			case SPRING:
				season = "Printemps";
				break;
			case SUMMER:
				season = "Été";
				break;
			case WINTER:
				season = "Hiver";
				break;
			}
			this.add(new JLabel("Saison " + season + " de l'année " + (game.getYear()+1) + "/" + game.getYearCount()), 0);
		}
		else
			this.add(new JLabel("Fin du jeu"), 0);
	}

}
