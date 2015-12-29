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

public class IngredientPanel extends JPanel {

	public IngredientPanel() {
		super();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(10, 15, 10, 15));
	}

	public void hydrate(Game game)
	{
		this.removeAll();
		this.add(new JSeparator(JSeparator.HORIZONTAL));
		
		Player player = game.getNeededPlayer();
		JPanel textLine = new JPanel();
		textLine.setLayout(new FlowLayout());
		textLine.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		textLine.add(new JLabel("<html><h1>A toi de jouer " + player + " !</h1>"
				+ "Choisis une carte ingrédient dans ton deck de cartes et clique sur une des valeurs en bleu</html>"));
		this.add(textLine);
		this.add(new JSeparator(JSeparator.HORIZONTAL));
	}

}
