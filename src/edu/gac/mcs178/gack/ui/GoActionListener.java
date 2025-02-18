package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;

public class GoActionListener implements ActionListener {
	
	private static final String INTSRUCTIONS = "Go ...";
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox goJComboBox;
	private boolean enabled;
	private List<String> exits;

	public GoActionListener(GraphicalUserInterface gui, Person player, JComboBox goJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.goJComboBox = goJComboBox;
		this.enabled = true;
		exits = player.getPlace().exits();
		goJComboBox.addItem(INTSRUCTIONS);
		for (String exit : exits) {
			goJComboBox.addItem(exit);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		goJComboBox.removeAllItems();
		exits = player.getPlace().exits();
		goJComboBox.addItem(INTSRUCTIONS);
		for (String exit : exits) {
			goJComboBox.addItem(exit);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			String exit = (String) goJComboBox.getSelectedItem();
			if (!exit.equals(INTSRUCTIONS)) {
				gui.displayMessage("\n>>> Go " + exit);
				player.go(exit);
				gui.playTurn();
			}
		}
	}
}
