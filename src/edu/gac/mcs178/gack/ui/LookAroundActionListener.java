package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.gac.mcs178.gack.domain.Person;

public class LookAroundActionListener implements ActionListener {
	
	private GraphicalUserInterface gui;
	private Person player;
	
	public LookAroundActionListener(GraphicalUserInterface gui, Person player) {
		super();
		this.gui = gui;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		gui.displayMessage("\n>>> Look around");
		player.lookAround();
	}
}
