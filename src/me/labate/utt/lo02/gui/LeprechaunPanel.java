package me.labate.utt.lo02.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player;

public class LeprechaunPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3260146273137281977L;
	JButton finishBtn;
	JComboBox<Player> playerCombo;
	
	IngredientCard card = null;
	IngredientMethod method = null;

	public LeprechaunPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		setMaximumSize(new Dimension(600, 99999));
		
		finishBtn = new JButton("Terminer");
		finishBtn.setActionCommand("leprechaunFinish");

		playerCombo = new JComboBox<Player>();

	}
	
	public void hydrate(Game game)
	{
		// Hydrate field
		playerCombo.removeAllItems();
		for(int i=0; i<game.getPlayerCount();i++)
		{
			if(!game.getNeededPlayer().equals(game.getPlayer(i)))
			{
				playerCombo.addItem(game.getPlayer(i));
			}
		}

		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		JLabel text = new JLabel("<html><h1>Attaque de farfadet !</h1>"
				+ "Vous (" + game.getNeededPlayer() + ") avez choisis d'attaquer un autre joueur avec des farfadets.<br/>"
				+ "Les farfadets permettent de voler des graines aux autres joueurs.<br/><br/>"
				+ "<i>Qui souhaitez vous attaquer ?</i></html>");
		text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(text);
		
		add(playerCombo);

		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		buttonLine.add(finishBtn);
		
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	/**
	 * @return the card
	 */
	public IngredientCard getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(IngredientCard card) {
		this.card = card;
	}

	/**
	 * @return the method
	 */
	public IngredientMethod getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(IngredientMethod method) {
		this.method = method;
	}

	/**
	 * @return the finishBtn
	 */
	public JButton getFinishBtn() {
		return finishBtn;
	}
	

	/**
	 * @return the playerCombo
	 */
	public JComboBox<Player> getPlayerCombo() {
		return playerCombo;
	}

}
