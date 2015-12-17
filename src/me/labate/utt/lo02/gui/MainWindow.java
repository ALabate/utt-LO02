package me.labate.utt.lo02.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

import me.labate.utt.lo02.core.Game;

public class MainWindow extends JFrame {

	ScorePanel scorePanel;
	
	
	
	public MainWindow() {
		super();
		


		JLabel label1 = new JLabel("lable 1");
		label1.setBackground(Color.GREEN);
		JLabel label2 = new JLabel("lable 2");
		label2.setForeground(Color.BLUE);
		
		
		// Configure window
		setTitle("Le jeu du Menhir");
		this.setMinimumSize(new Dimension(320, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		
		// Create scroll area
		JScrollPane scrollArea = new JScrollPane();
		setContentPane(scrollArea);
		
		// Create subpanels
//		statusPanel = new StatusPanel();
		scorePanel = new ScorePanel();
		
		// Create main vertical layout
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(scorePanel);
		scrollArea.setViewportView(mainPanel);
	}
	
	public void hydrate(Game game)
	{
		scorePanel.hydrate(game);
	}
}
