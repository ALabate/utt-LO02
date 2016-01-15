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
/**
 * The Bonus Panel, it's whith this Panel user choose if he wants Seed or one AllyCard at the beginning of a year.
 * @author Benoit,Alabate
 *
 */
public class BonusPanel extends JPanel{

	private static final long serialVersionUID = -3981619847669821563L;
	/**
	 * The button user can click on to make his choice
	 */
	JButton finishBtn;
	/**
	 *  The combo of choices the payer have
	 */
	JComboBox<String> bonusCombo;

	/**
	 * Constructor
	 */
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
	/**
	 * refresh the panel
	 * @param game
	 */
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
