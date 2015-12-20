package me.labate.utt.lo02.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;

public class LastActionPanel extends JPanel implements ActionListener {

	JButton continueBtn;

	public LastActionPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		setMaximumSize(new Dimension(600, 99999));

		// Create buttons and input
		continueBtn = new JButton("Continuer");
		continueBtn.setActionCommand("lastActionContinue");
	}

	public void hydrate(Game game)
	{
		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		String text = "<html><h1>Dernière action</h1>";
		CardPanel card = null;
		
		switch(game.getLastAction()) {
			case GIANT:
			{
				text += "<u>" + game.getLastPlayer().getName() + "</u> a invoqué les géants qui lui ont accordés <u>"
						+ game.getLastPoints() + " graine(s)</u></html>";
				card = new CardPanel(game.getLastIngredientCard());
				card.highlightValue(IngredientMethod.GIANT, game.getLastSeason());
				break;
			}
			case FERTILIZER:
			{
				int originalPoints = game.getLastIngredientCard().getValue(IngredientMethod.FERTILIZER, game.getLastSeason());
				text += "<u>" + game.getLastPlayer().getName() + "</u> a utilisé " + originalPoints + " unité(s) d'engrais sur ses graines<br/>";
				if(originalPoints != game.getLastPoints()) {
					text += "Mais il n'avait que " + game.getLastPoints() + " graine(s)<br/>";
				}
				text += "Il obtient donc <u>"  + game.getLastPoints() + " menhir(s)</u> contre " + game.getLastPoints() + " graine(s)</html>";
				card = new CardPanel(game.getLastIngredientCard());
				card.highlightValue(IngredientMethod.FERTILIZER, game.getLastSeason());
				break;
			}
			case LEPRECHAUN:
			{
				int originalPoints = game.getLastIngredientCard().getValue(IngredientMethod.LEPRECHAUN, game.getLastSeason());
				int defendPoints = -1;
				if(game.getLastAllyCard() != null) {
					defendPoints = game.getLastAllyCard().getValue(AllyMethod.DOG, game.getLastSeason());
				}
				if(originalPoints != game.getLastPoints() || defendPoints >= 0) {
					text += game.getLastPlayer().getName() + " a essayé de voler " + originalPoints + " graine(s) à " + game.getLastTarget().getName() + " mais<br/>";
					// If target defend himself
					if(defendPoints >= 0) {
						text += "- " + game.getLastTarget().getName() + " s'est défendu avec " + defendPoints + " chien(s)<br/>";
						originalPoints -= defendPoints;							
					}
					else {
						defendPoints = 0;
					}
					// If target hasn't enough seeds
					if(originalPoints != game.getLastPoints()) {
						text += "- " + game.getLastTarget().getName() + " n'avait que " + game.getLastPoints() + " graine(s)<br/>";
					}
				}
				text += "<br/><u>" + game.getLastPlayer().getName() + "</u> a volé <u>" + game.getLastPoints() + " graine(s)</u> à <u>" + game.getLastTarget().getName() + "</u><br/>";

				card = new CardPanel(game.getLastIngredientCard());
				card.highlightValue(IngredientMethod.LEPRECHAUN, game.getLastSeason());
				break;
			}
			/*
			case BONUS_ALLY:
			{
				System.out.println(name + " chose as bonus");
				System.out.println("\t1 ally card");
				break;
			}
			case BONUS_SEEDS:
			{
				System.out.println(name + " chose as bonus");
				System.out.println("\t2 seeds");
				break;
			}
			case LEPRECHAUN_REQUEST:
			{
				System.out.println(name + " tried to stole");
				System.out.println("\t" + game.getLastIngredientCard().getValue(IngredientMethod.LEPRECHAUN, game.getLastSeason()) + " seeds from " +  game.getLastTarget().getName());
				System.out.println("Let see if " + game.getLastTarget().getName() + " will defend himself");
				break;
			}
			case MOLE:
			{
				int moles = game.getLastAllyCard().getValue(AllyMethod.MOLE, game.getLastSeason());
				System.out.println(name + " attacked " + game.getLastTarget().getName() + " with "+ moles + " mole");
				Console.jumpLine(1);
				Console.showAllyCard(game.getLastAllyCard());
				Console.jumpLine(1);
				if(moles != game.getLastPoints()) {
					System.out.println("\tbut " + game.getLastTarget().getName() + " had only " + game.getLastPoints() + " menhirs");
				}
				System.out.println("\the destroyed " + game.getLastPoints() + " menhirs");
				break;
			}
		*/
			default:
				text = "<html><h1>Dernière action</h1>"
						+ "Rien ne s'est passé..</html>";
				break;
		}
		

		JPanel textLine = new JPanel();
		textLine.setLayout(new BoxLayout(textLine, BoxLayout.X_AXIS));
		textLine.setBorder(new EmptyBorder(10,0,10,0));

		if(card != null)
		{
			textLine.add(card);
		}
		JLabel textLabel = new JLabel(text);
		textLabel.setBorder(new EmptyBorder(0,10,0,0));
		textLine.add(textLabel);
		
		this.add(textLine);

		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		buttonLine.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(continueBtn);
		
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the finishBtn
	 */
	public JButton getContinueBtn() {
		return continueBtn;
	}


}
