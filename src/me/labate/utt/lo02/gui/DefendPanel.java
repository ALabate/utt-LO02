package me.labate.utt.lo02.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

public class DefendPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6284799628145527834L;
	JButton defendBtn;
	JButton dontDefendBtn;
	
	public DefendPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		setMaximumSize(new Dimension(600, 99999));
		
		defendBtn = new JButton("Se défendre");
		defendBtn.setActionCommand("leprechaunDefend");
		
		dontDefendBtn = new JButton("Ne pas se défendre");
		dontDefendBtn.setActionCommand("leprechaunDontDefend");

	}
	
	public void hydrate(Game game)
	{

		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		String text = "<html><h1>" + game.getNeededPlayer()  + " : Vous êtes attaqués par des farfadets !</h1>"
				+ game.getLastPlayer() + " vous attaque avec " + game.getLastIngredientCard().getValue(IngredientMethod.LEPRECHAUN, game.getLastSeason()) + " farfadets qui veulent vous voler des graines.<br/><br/>";


		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		
    	if(game.getNeededPlayer().getAllyCard().getValue(AllyMethod.DOG, Season.SUMMER) < 0)
    	{ 
    		// you have to make a disti
    		text += "Bien que vous ayez une carte allié, ce n'est pas des chiens de gardes. Vous n'avez donc pas de défense.</html>";
    		buttonLine.add(dontDefendBtn);
    	}
    	else
    	{
    		text += "<i>Souhaitez-vous utiliser vos chiens de gardes ?</i></html>";
    		buttonLine.add(dontDefendBtn);
    		buttonLine.add(defendBtn);
    	}
    	
    	JLabel textLabel = new JLabel(text);
		textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(textLabel);
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}
	
	/**
	 * @return the defendBtn
	 */
	public JButton getDefendBtn() {
		return defendBtn;
	}

	/**
	 * @return the dontDefendBtn
	 */
	public JButton getDontDefendBtn() {
		return dontDefendBtn;
	}
}
