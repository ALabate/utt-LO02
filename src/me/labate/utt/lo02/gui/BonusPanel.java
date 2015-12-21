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

import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.IngredientCard;
import me.labate.utt.lo02.core.IngredientCard.IngredientMethod;
import me.labate.utt.lo02.core.Player;
import me.labate.utt.lo02.core.Player.Bonus;

public class BonusPanel extends JPanel{

	JButton finishBtn;
	JComboBox<String> bonusCombo;

	public BonusPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		setMaximumSize(new Dimension(600, 99999));
		
		finishBtn = new JButton("Terminer");
		finishBtn.setActionCommand("bonusFinish");

		bonusCombo = new JComboBox<String>();
		bonusCombo.addItem("2 graines");
		bonusCombo.addItem("Une carte allié");
	}
	
	public void hydrate(Game game)
	{

		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		JLabel text = new JLabel("<html><h1>Choix du bonus de départ !</h1>"
				+ "En partie longue, vous pouvez choisir entre une carte allié et deux graines.<br/><br/>"
				+ "<i>" + game.getNeededPlayer().getName() + ", que souhaitez vous prendre ?</i></html>");
		text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(text);
		
		add(bonusCombo);

		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		buttonLine.add(finishBtn);
		
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}


	/**
	 * @return the finishBtn
	 */
	public JButton getFinishBtn() {
		return finishBtn;
	}
	

	/**
	 * @return the bonusCombo
	 */
	public JComboBox<String> getBonusCombo() {
		return bonusCombo;
	}

}
