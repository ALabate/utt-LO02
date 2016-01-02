package me.labate.utt.lo02.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import me.labate.utt.lo02.core.AllyCard.AllyMethod;
import me.labate.utt.lo02.core.Game.Season;
import me.labate.utt.lo02.core.Game;
import me.labate.utt.lo02.core.Player;

public class MolePanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5885522801459004212L;
	JButton cancelBtn;
	JButton nextBtn;
	JButton backBtn;
	JButton finishBtn;
	JComboBox<Player> humanCombo;
	JComboBox<Player> playerCombo;

	public MolePanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
		setMaximumSize(new Dimension(600, 99999));

		// Create buttons and input
		cancelBtn = new JButton("Annuler");
		cancelBtn.setActionCommand("moleCancel");

		nextBtn = new JButton("Suivant");
		nextBtn.setActionCommand("moleNext");
		nextBtn.addActionListener(this);

		backBtn = new JButton("Précédent");
		backBtn.setActionCommand("moleBack");
		backBtn.addActionListener(this);

		finishBtn = new JButton("Terminer");
		finishBtn.setActionCommand("moleFinish");

		humanCombo = new JComboBox<Player>();
		playerCombo = new JComboBox<Player>();

	}
	
	public void step1()
	{
		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		JLabel text = new JLabel("<html><h1>Attaque de taupe géante !</h1>"
				+ "N'importe qui peut attaquer avec une taupe géante à tout moment.<br/>"
				+ "Les taupes géantes détruisent les menhirs des ennemis.<br/><br/>"
				+ "<i>Qui êtes vous ?</i></html>");
		text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(text);
		
		add(humanCombo);

		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		buttonLine.add(cancelBtn);
		buttonLine.add(nextBtn);
		
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	public void step2()
	{
		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		// Remove current player from list
		for(int i=0; i < playerCombo.getItemCount();i++)
		{
			if(humanCombo.getSelectedItem().equals(playerCombo.getItemAt(i)))
			{
				playerCombo.removeItemAt(i);
			}
		}

		JLabel text = new JLabel("<html><h1>Attaque de taupe géante !</h1>"
				+ "N'importe qui peut attaquer avec une taupe géante à tout moment.<br/>"
				+ "Les taupes géantes détruisent les menhirs des ennemis.<br/><br/>"
				+ "Vous êtes " + humanCombo.getSelectedItem().toString() + ".<br/>"
				+ "<i>Qui souhaitez vous attaquer ?</i></html>");
		text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(text);
		
		add(playerCombo);

		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		buttonLine.add(backBtn);
		buttonLine.add(finishBtn);
		
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	public void stepNoMole()
	{
		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		// Remove current player from list
		JLabel text = new JLabel("<html><h1>Attaque de taupe géante !</h1>"
				+ "N'importe qui peut attaquer avec une taupe géante à tout moment.<br/>"
				+ "Les taupes géantes détruisent les menhirs des ennemis.<br/><br/>"
				+ "Vous n'avez pas de taupes !</html>");
		text.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(text);
		

		JPanel buttonLine = new JPanel();
		buttonLine.setLayout(new FlowLayout());
		buttonLine.add(backBtn);
		buttonLine.add(cancelBtn);
		
		this.add(buttonLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	public void hydrate(Game game)
	{
		// Hydrate fields
		humanCombo.removeAllItems();
		playerCombo.removeAllItems();
		for(int i=0; i<game.getPlayerCount();i++)
		{
			if(!game.getPlayer(i).isBot() && game.getPlayer(i).hasAllyCard())
			{
				humanCombo.addItem(game.getPlayer(i));
			}
			playerCombo.addItem(game.getPlayer(i));
		}
		step1();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	    if ("moleNext".equals(e.getActionCommand())) {
	    	if(((Player)humanCombo.getSelectedItem()).getAllyCard().getValue(AllyMethod.MOLE, Season.SUMMER) < 0)
	    	{
	    		stepNoMole();
	    	}
	    	else
	    	{
				step2();
	    	}
			this.validate();
			this.repaint();
	    }
	    else if ("moleBack".equals(e.getActionCommand())) {
			step1();
			this.validate();
			this.repaint();
	    }
	}

	/**
	 * @return the cancelBtn
	 */
	public JButton getCancelBtn() {
		return cancelBtn;
	}

	/**
	 * @return the nextBtn
	 */
	public JButton getNextBtn() {
		return nextBtn;
	}

	/**
	 * @return the backBtn
	 */
	public JButton getBackBtn() {
		return backBtn;
	}

	/**
	 * @return the finishBtn
	 */
	public JButton getFinishBtn() {
		return finishBtn;
	}
	
	/**
	 * @return the humanCombo
	 */
	public JComboBox<Player> getHumanCombo() {
		return humanCombo;
	}

	/**
	 * @return the playerCombo
	 */
	public JComboBox<Player> getPlayerCombo() {
		return playerCombo;
	}

}
