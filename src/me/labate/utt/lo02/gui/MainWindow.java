package me.labate.utt.lo02.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import me.labate.utt.lo02.core.AllyCard;
import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Game.Action;
import me.labate.utt.lo02.core.Game.Choice;
import me.labate.utt.lo02.core.Game.Season;

public class MainWindow extends JFrame {

	
	ScorePanel scorePanel;
	StatusPanel statusPanel;
	DeckPanel deckPanel;
	MolePanel molePanel;
	LastActionPanel lastActionPanel;
	IngredientPanel ingredientPanel;
	BonusPanel bonusPanel;

	LeprechaunPanel leprechaunPanel;

	JButton moleButton;

	public MainWindow() {
		super();
		
		
		// Configure window
		setTitle("Le jeu du Menhir");
		this.setMinimumSize(new Dimension(320, 240));
		setSize(new Dimension(1000, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create scroll area
//		JScrollPane scrollArea = new JScrollPane();
//		setContentPane(scrollArea);
//		
		// Create subpanels
		scorePanel = new ScorePanel();
		scorePanel.setAlignmentX(ScorePanel.CENTER_ALIGNMENT);
		
		statusPanel = new StatusPanel();
		statusPanel.setAlignmentX(ScorePanel.CENTER_ALIGNMENT);
		
		moleButton = new JButton("Attaquer quelqu'un avec une taupe géante..");
		moleButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		moleButton.setActionCommand("moleStart");

		molePanel = new MolePanel();
		molePanel.setAlignmentX(ScorePanel.CENTER_ALIGNMENT);

		lastActionPanel = new LastActionPanel();
		lastActionPanel.setAlignmentX(ScorePanel.CENTER_ALIGNMENT);

		ingredientPanel = new IngredientPanel();
		ingredientPanel.setAlignmentX(ingredientPanel.CENTER_ALIGNMENT);

		leprechaunPanel = new LeprechaunPanel();
		leprechaunPanel.setAlignmentX(leprechaunPanel.CENTER_ALIGNMENT);
		
		bonusPanel = new BonusPanel();
		bonusPanel.setAlignmentX(bonusPanel.CENTER_ALIGNMENT);

		deckPanel = new DeckPanel();
		
		// Create main vertical layout
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(statusPanel);
		mainPanel.add(scorePanel);
		mainPanel.add(moleButton);
		mainPanel.add(molePanel);
		mainPanel.add(lastActionPanel);
		mainPanel.add(ingredientPanel);
		mainPanel.add(leprechaunPanel);
		mainPanel.add(bonusPanel);
		mainPanel.add(deckPanel);
//		scrollArea.setViewportView(mainPanel);
		this.add(mainPanel);
	}
	
	/**
	 * @return the moleButton
	 */
	public JButton getMoleButton() {
		return moleButton;
	}

	public void hydrate(Game game)
	{
		scorePanel.hydrate(game);
		statusPanel.hydrate(game);
		deckPanel.hydrate(game);
		
		// Middle panel selection
		
		if(game.getLastAction() != Action.NOTHING)
		{
			lastActionPanel.hydrate(game);
			selectMiddlePanel(lastActionPanel);
			game.clearLastAction();
		}
		else if(game.getNeededChoice() == Choice.INGREDIENT)
		{
			ingredientPanel.hydrate(game);
			selectMiddlePanel(ingredientPanel);
			deckPanel.enableClick(true, game.getSeason());
		}
		else if(game.getNeededChoice() == Choice.BONUS)
		{
			bonusPanel.hydrate(game);
			selectMiddlePanel(bonusPanel);
		}
		else
		{
			System.out.print("Error cannot select panel : choice=");
			System.out.println(game.getNeededChoice());
		}
		
		// Hide mole button if there no ally card on the table
		moleButton.setVisible(false);
		for(int i=0; i<game.getPlayerCount(); i++)
		{
			if(game.getPlayer(i).hasAllyCard())
			{
				moleButton.setVisible(true);
				break;
			}
		}
	}

	public void selectMiddlePanel(JPanel panel)
	{
		molePanel.setVisible(false);
		lastActionPanel.setVisible(false);
		ingredientPanel.setVisible(false);
		leprechaunPanel.setVisible(false);
		bonusPanel.setVisible(false);
		
		panel.setVisible(true);
	}
	/**
	 * @return the scorePanel
	 */
	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	/**
	 * @return the statusPanel
	 */
	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	/**
	 * @return the molePanel
	 */
	public MolePanel getMolePanel() {
		return molePanel;
	}


	/**
	 * @return the lastActionPanel
	 */
	public LastActionPanel getLastActionPanel() {
		return lastActionPanel;
	}

	/**
	 * @return the deckPanel
	 */
	public DeckPanel getDeckPanel() {
		return deckPanel;
	}
	
	/**
	 * @return the leprechaunPanel
	 */
	public LeprechaunPanel getLeprechaunPanel() {
		return leprechaunPanel;
	}
	/**
	 * @return the ingredientPanel
	 */
	public IngredientPanel getIngredientPanel() {
		return ingredientPanel;
	}
	/**
	 * @return the bonusPanel
	 */
	public BonusPanel getBonusPanel() {
		return bonusPanel;
	}
}
