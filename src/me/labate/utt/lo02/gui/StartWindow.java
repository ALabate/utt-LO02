package me.labate.utt.lo02.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import me.labate.utt.lo02.core.FastGame;
import me.labate.utt.lo02.core.FullGame;
import me.labate.utt.lo02.core.Game;

@SuppressWarnings("serial")
public class StartWindow extends JFrame implements ActionListener{

	private JComboBox<Integer> nbPlayer; // choice for number of players
	private JComboBox<String> typeGame;  // choice for the type of game
	private JTextField name;			 // where user type his name
	private JButton button;				 // button for launch the game
	
	public StartWindow() { // constructor
		super(); // use the constructor of JFrame
		
		// Initialisation of TextField
		name = new JTextField("Humain");
		name.setActionCommand("name has been entered");
		name.addActionListener(this);
		
		// Initialisation of nbPlayer
		nbPlayer = new JComboBox<Integer>();
		nbPlayer.setSize(this.getWidth(),10);
		nbPlayer.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
		nbPlayer.addItem(2);
		nbPlayer.addItem(3);
		nbPlayer.addItem(4);
		nbPlayer.addItem(5);
		nbPlayer.addItem(6);
		nbPlayer.setActionCommand("nbr of palyer has been entered");
		nbPlayer.addActionListener(this);
		
		// Initialisattion of typeGame
		typeGame = new JComboBox<String>();
		typeGame.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
		typeGame.addItem("Partie Rapide");
		typeGame.addItem("Partie Avancée");
		typeGame.setActionCommand("Type Game selected");
		typeGame.addActionListener(this);
		
		// Initialisation of button
		button = new JButton("Commencer");
		button.setActionCommand("button Pressed");
		button.addActionListener(this);
		
		// put components in the window
		JPanel panel = new JPanel();
		JPanel ComboBoxPanel1 = new JPanel();
		JPanel ComboBoxPanel2 = new JPanel();
		JLabel label1 = new JLabel("Choix du nombre de joueurs : ");
		JLabel label2 = new JLabel("Type de Partie");
		
		label1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(name,BorderLayout.NORTH);
		panel.add(label1);
		ComboBoxPanel1.add(nbPlayer);
		ComboBoxPanel1.setLayout(new FlowLayout());
		panel.add(ComboBoxPanel1);
		
		panel.add(label2);
		ComboBoxPanel2.add(typeGame);
		ComboBoxPanel2.setLayout(new FlowLayout());
		panel.add(ComboBoxPanel2);
		this.add(button,BorderLayout.SOUTH );
		this.add(panel);
		this.setTitle("Le jeu du Menhir");
		this.setMinimumSize(new Dimension(320, 240));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	// start the app
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		StartWindow sw = new StartWindow();

	}

	@Override
	public void actionPerformed(ActionEvent e) { // when an event occur
		if("name has been entered".equals(e.getActionCommand())){
			// user press enter in textFiel
			nbPlayer.requestFocus(); // suggest him to choose how many players
		}
		else if("nbr of palyer has been entered".equals(e.getActionCommand())){
			// user select how many players
			typeGame.requestFocus(); // suggest to choose the type of game
		}
		else if("button Pressed".equals(e.getActionCommand())){
			// Commencer button has been pressed
			// create a game depends on user choices
			Game game = null;
			if(typeGame.getSelectedIndex() == 0){
				game = new FastGame();
				game.addHuman(name.getText());
				for(int i = 2; i <= (int)nbPlayer.getSelectedItem(); i++){
					game.addBot("Bot " + (i-1));
				}
			}
			else{
				game = new FullGame();
				game.addHuman(name.getText());
				for(int i = 2; i <= (int)nbPlayer.getSelectedItem(); i++){
					game.addBot("Bot " + (i-1));
				}
			}
			// start the controller
			@SuppressWarnings("unused")
			Controller controller = new Controller(game);
			dispose(); // destroy this window
		}
		else if("Type Game selected".equals(e.getActionCommand())){
			button.requestFocus();
		}
	}

}
