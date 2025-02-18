package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class DropActionListener implements ActionListener {
	
	private static final Thing INTSRUCTIONS = new Thing("Drop ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox dropJComboBox;
	private boolean enabled;
	private List<Thing> things;

	public DropActionListener(GraphicalUserInterface gui, Person player, JComboBox dropJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.dropJComboBox = dropJComboBox;
		this.enabled = true;
		things = player.getPossessions();
		dropJComboBox.addItem(INTSRUCTIONS);
		for (Thing thing : things) {
			dropJComboBox.addItem(thing);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		dropJComboBox.removeAllItems();
		things = player.getPossessions();
		dropJComboBox.addItem(INTSRUCTIONS);
		for (Thing thing : things) {
			dropJComboBox.addItem(thing);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Thing item = (Thing) dropJComboBox.getSelectedItem();
			if (!item.getName().equals(INTSRUCTIONS.getName())) {
				gui.displayMessage("\n>>> Drop " + item);
				player.lose(item);
				gui.playTurn();
			}
		}
	}
}
