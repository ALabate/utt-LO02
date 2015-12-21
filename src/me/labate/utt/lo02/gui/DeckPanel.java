package me.labate.utt.lo02.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import me.labate.utt.lo02.cli.Console;
import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Card;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

public class DeckPanel extends JPanel{
	
	Player player = null;
	ArrayList<CardPanel> list;
	CardPanel allyCard = null;
	ActionListener clickListener = null;
	
	public DeckPanel() {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		
		list = new ArrayList<CardPanel>();
	}

	public void hydrate(Game game)
	{
		if(game.getNeededPlayer() != null && !game.getNeededPlayer().isBot())
		{
			showCards(game.getNeededPlayer());
		}
		else if(player != null)
		{
			showCards(player);
		}
	}

	public void hydrate(Game game, Player player)
	{
		showCards(player);
	}
	
	public void showCards(Player player)
	{
		this.player = player;
		
		this.removeAll();
		
		this.add(new JLabel("Cartes de " + player.getName()));
		
		JPanel cardLine = new JPanel();
		cardLine.setLayout(new BoxLayout(cardLine, BoxLayout.X_AXIS));
		cardLine.setBorder(new EmptyBorder(10,5,10,5));
		add(cardLine);
		
		for (IngredientCard card : player.getIngredientCards()) {
			list.add(new CardPanel(card));
			cardLine.add(list.get(list.size()-1));
			if(clickListener != null)
			{
				list.get(list.size()-1).getClickButton().addActionListener(clickListener);
			}
		}
		if(player.getIngredientCardCount() > 0 && player.hasAllyCard())
		{
			JSeparator separator = new JSeparator(JSeparator.VERTICAL);
			separator.setMaximumSize(new Dimension(10, 10));
			cardLine.add(separator);
		}
		if(player.hasAllyCard())
		{
			allyCard = new CardPanel(player.getAllyCard());
			cardLine.add(allyCard);
		}
	}


	public void enableClick(boolean enable, Season season)
	{
		if(enable)
		{ 
			for (CardPanel card : list) {
				card.enableClick(enable);
				card.highlightSeason(season, true);
			}
		}
	}

	public void highlightAllySeason(Season season)
	{
		if(allyCard != null)
			allyCard.highlightSeason(season, false);
	}

	public void setClickListener(ActionListener listener)
	{
		clickListener = listener;
			
		for (CardPanel card : list) {
			card.getClickButton().addActionListener(listener);
		}
	}
}
