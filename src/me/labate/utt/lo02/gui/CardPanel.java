package me.labate.utt.lo02.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import me.labate.utt.lo02.core.AllyCard;
import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
/**
 * Panel where the cards are shown
 * @author Benoit,Alabate
 *
 */
public class CardPanel extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = -6399946122226830893L;
	
	ArrayList<ArrayList<JLabel>> table;
	int highlightValueRow = -1;
	Season highlightValueSeason = null;
	Season highlightSeason = null;
	boolean clickEnabled = false;
	int clickedRow = -1;
	IngredientCard ingredientCard = null;
	AllyCard allyCard = null;
	JButton clickButton;

/**
 * Constructor for an IngredientCard
 * @param card
 */
	public CardPanel(IngredientCard card) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new LineBorder(Color.BLACK, 1, true));
		setMaximumSize(new Dimension(140, 150));
		
		this.ingredientCard = card;
		clickButton = new JButton();
		clickButton.setVisible(false);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.anchor = GridBagConstraints.CENTER;
		
		// Title line
		addLabel(1,0,c, new JLabel("P"));
		table.get(0).get(1).setToolTipText("Printemps");
		addLabel(2,0,c, new JLabel("É"));
		table.get(0).get(1).setToolTipText("Été");
		addLabel(3,0,c, new JLabel("A"));
		table.get(0).get(1).setToolTipText("Automne");
		addLabel(4,0,c, new JLabel("H"));
		table.get(0).get(1).setToolTipText("Hiver");
		
		// Values
		int x = 0, y = 0;
		for (IngredientMethod method : IngredientMethod.values())
		{
			x = 0;
			y++;
			
			for (Season season : Season.values()) {
				if(season == Season.INIT)
				{
					switch(method)
					{
					case FERTILIZER:
						addLabel(x,y,c,new JLabel("Engrais"));
						table.get(y).get(x).setToolTipText("Engrais");
						break;
					case GIANT:
						addLabel(x,y,c,new JLabel("Géant"));
						table.get(y).get(x).setToolTipText("Géant");
						break;
					case LEPRECHAUN:
						addLabel(x,y,c,new JLabel("Farfadet"));
						table.get(y).get(x).setToolTipText("Farfadet");
						break;
					}
				}
				else
				{
					addLabel(x,y,c,new JLabel(String.valueOf(card.getValue(method, season))));
					table.get(y).get(x).addMouseListener(this);
				}
				x++;
			}
		}
	}

	/**
	 * Constructor for an Allycard
	 * @param card
	 */
	public CardPanel(AllyCard card) {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new LineBorder(Color.BLACK, 1, true));
		setMaximumSize(new Dimension(150, 150));
		

		this.allyCard = card;
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,3,3,3);
		c.anchor = GridBagConstraints.CENTER;

		// Title line
		addLabel(1,0,c, new JLabel("P"));
		table.get(0).get(1).setToolTipText("Printemps");
		addLabel(2,0,c, new JLabel("É"));
		table.get(0).get(1).setToolTipText("Été");
		addLabel(3,0,c, new JLabel("A"));
		table.get(0).get(1).setToolTipText("Automne");
		addLabel(4,0,c, new JLabel("H"));
		table.get(0).get(1).setToolTipText("Hiver");
		
		// Spacing
		addLabel(0,1,c, new JLabel(" "));
		
		// Values
		int x = 0, y = 1;
		for (AllyMethod method : AllyMethod.values())
		{
			x = 0;
			y++;
			
			for (Season season : Season.values()) {
				x = season.ordinal();
				if(season == Season.INIT && card.getValue(method, Season.SPRING) >= 0)
				{
					switch(method)
					{
					case DOG:
						addLabel(x,y,c,new JLabel("Chiens"));
						break;
					case MOLE:
						addLabel(x,y,c,new JLabel("Taupes"));
						break;
					}
				}
				else
				{
					if(card.getValue(method, season) >= 0)
					{
						addLabel(x,y,c,new JLabel(String.valueOf(card.getValue(method, season))));
						table.get(y).get(x).addMouseListener(this);
					}
				}
			}
		}
		
		// Spacing
		y++;
		addLabel(0,y,c, new JLabel(" "));
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param c
	 * @param label
	 */
	private void addLabel(int x, int y, GridBagConstraints c, JLabel label)
	{
		// Check initialization
		if(table == null)
		{
			table = new ArrayList<ArrayList<JLabel>>();
		}
		if(table.size() <= y)
		{
			while (table.size() <= y) {
				table.add(new ArrayList<JLabel>());
			}
		}
		if(table.get(y).size() <= x)
		{
			while (table.get(y).size() <= x) {
				table.get(y).add(new JLabel());
			}
		}
		
		// add value
		c.gridx = x;
		c.gridy = y;
		table.get(y).set(x, label);
		label.setBorder(new EmptyBorder(1,1,1,1));
		add(table.get(y).get(x), c);
	}
	/**
	 * 
	 * @param season
	 * @param link
	 */
	public void highlightSeason(Season season, boolean link) {
		
		// Gray all other seasons
		for (int m = 0; m < table.size() ; m++)
		{
			for (Season seasonI : Season.values()) {
				if(table.get(m).size() > seasonI.ordinal())
				{
					if(season != seasonI && seasonI != Season.INIT)
					{
						table.get(m).get(seasonI.ordinal()).setForeground(Color.LIGHT_GRAY);
					}
					else if(season == seasonI && seasonI != Season.INIT && m != 0 && link)
					{
						table.get(m).get(seasonI.ordinal()).setForeground(Color.BLUE);
					}
					else
					{
						table.get(m).get(seasonI.ordinal()).setForeground(Color.BLACK);
					}
				}
			}
		}
		highlightSeason = season;
	}

	public void highlightValue(IngredientMethod method, Season season) {
		highlightValue(method.ordinal()+1, season);
	}
	public void highlightValue(AllyMethod method, Season season) {
		highlightValue(method.ordinal()+2, season);
	}
	private void highlightValue(int row, Season season) {
		if(highlightValueRow >= 0)
		{
			table.get(highlightValueRow).get(highlightValueSeason.ordinal()).setBorder(new EmptyBorder(1,1,1,1));
			table.get(highlightValueRow).get(0).setForeground(Color.BLACK);
		}
		if(row >= 0)
		{
			table.get(row).get(season.ordinal()).setBorder(new LineBorder(new Color(46,117,9), 1, false));
			table.get(row).get(0).setForeground(new Color(46,117,9));
		}
		highlightValueRow = row;
		highlightValueSeason = season;
	}
	
	public void enableClick(boolean enable)
	{
		clickEnabled = enable;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(clickEnabled && highlightSeason != null)
		{
			// Search season and method
			for (int m = 0; m < table.size() ; m++)
			{
				if(e.getSource().equals(table.get(m).get(highlightSeason.ordinal())))
				{
					clickedRow = m;
					
					// emit action from click button
					for(ActionListener a: clickButton.getActionListeners()) {
					    a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "cardClick") {
							private static final long serialVersionUID = 3347318790331609530L;
						});
					}
					break;
				}
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		if(clickEnabled && highlightSeason != null)
		{
			// Search season and method
			for (int m = 0; m < table.size() ; m++)
			{
				if(e.getSource().equals(table.get(m).get(highlightSeason.ordinal())))
				{
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					highlightValue(m, highlightSeason);
					return;
				}
			}
		}
	}


	@Override
	public void mouseExited(MouseEvent e) {
		if(clickEnabled)
		{
			// Search season and method
			highlightValue(-1,Season.INIT);
			setCursor(Cursor.getDefaultCursor());
		}
		getParent().dispatchEvent(e);
	}


	@Override
	public void mousePressed(MouseEvent e) {
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	}

	public IngredientMethod getClickedIMethod()
	{
		if(ingredientCard != null && clickedRow-1 >= 0 && clickedRow-1 < IngredientMethod.values().length)
			return IngredientMethod.values()[clickedRow-1];
		else
			return null;
	}

	public AllyMethod getClickedAMethod()
	{
		if(allyCard != null && clickedRow-2 >= 0 && clickedRow-2 < AllyMethod.values().length)
			return AllyMethod.values()[clickedRow-2];
		else
			return null;
	}

	/**
	 * @return the card
	 */
	public IngredientCard getIngredientCard() {
		return ingredientCard;
	}
	
	/**
	 * @return the card
	 */
	public AllyCard getAllyCard() {
		return allyCard;
	}


	/**
	 * @return the clickButton
	 */
	public JButton getClickButton() {
		return clickButton;
	}
}
