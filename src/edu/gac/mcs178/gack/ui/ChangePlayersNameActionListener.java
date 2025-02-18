package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import edu.gac.mcs178.gack.domain.Person;

public class ChangePlayersNameActionListener implements ActionListener {

	private GraphicalUserInterface gui;
	private Person player;
	
	public ChangePlayersNameActionListener(GraphicalUserInterface gui, Person player) {
		super();
		this.gui = gui;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.displayMessage("\n>>> Change player's name");
		String inputValue = JOptionPane.showInputDialog("Please input a value");
		player.setName(inputValue);
		gui.displayMessage("Player's name changed to " + player);
	}

}
