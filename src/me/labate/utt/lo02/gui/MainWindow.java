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

import me.labate.utt.lo02.core.Game;

public class MainWindow extends JFrame implements ActionListener {

	ScorePanel scorePanel;
	StatusPanel statusPanel;
	
	
	
	public MainWindow() {
		super();
		


		JLabel label1 = new JLabel("lable 1");
		label1.setBackground(Color.GREEN);
		JLabel label2 = new JLabel("lable 2");
		label2.setForeground(Color.BLUE);
		
		
		// Configure window
		setTitle("Le jeu du Menhir");
		this.setMinimumSize(new Dimension(320, 240));
		setSize(new Dimension(1000, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create scroll area
		JScrollPane scrollArea = new JScrollPane();
		setContentPane(scrollArea);
		
		// Create subpanels
		scorePanel = new ScorePanel();
		scorePanel.setAlignmentX(ScorePanel.CENTER_ALIGNMENT);
		
		statusPanel = new StatusPanel();
		statusPanel.setAlignmentX(ScorePanel.CENTER_ALIGNMENT);
		
		JButton moleButton = new JButton("Attaquer quelqu'un avec une taupe géante..");
		moleButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		moleButton.setActionCommand("mole");
		moleButton.addActionListener(this); 
		
		// Create main vertical layout
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(statusPanel);
		mainPanel.add(scorePanel);
		mainPanel.add(moleButton);
		scrollArea.setViewportView(mainPanel);
	}
	
	public void hydrate(Game game)
	{
		scorePanel.hydrate(game);
		statusPanel.hydrate(game);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if ("mole".equals(e.getActionCommand())) {
	    	System.out.println("Mole Attack ! ");
	    }
		
	}
}
