package me.labate.utt.lo02.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

public class MainWindow {

	public MainWindow() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JLabel label = new JLabel("lable 0");
		JLabel label1 = new JLabel("lable 1");
		label1.setBackground(Color.GREEN);
		JLabel label2 = new JLabel("lable 2");
		label2.setForeground(Color.BLUE);

		//Layout component horizontally
		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
//		scorePanel.setBackground(Color.RED);
		scorePanel.add(label);
		scorePanel.add(label2);
		
		//Layout component vertically
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(label1);
		mainPanel.add(scorePanel);
		
		// Add a scroll bar if window is too low
		JScrollPane scroller = new JScrollPane(mainPanel);
		
		//Put everything together
		JFrame window = new JFrame();
		Container container = window.getContentPane();
		container.add(scroller, BorderLayout.CENTER);
				
		// Configure window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}

}
