package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.gac.mcs178.gack.domain.Person;

public class ListPossessionsActionListener implements ActionListener {

	private GraphicalUserInterface gui;
	private Person player;
	
	public ListPossessionsActionListener(GraphicalUserInterface gui, Person player) {
		super();
		this.gui = gui;
		this.player = player;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		gui.displayMessage("\n>>> List possessions");
		player.listPossessions();
	}

}
