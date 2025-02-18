package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class TakeActionListener implements ActionListener {
	
	private static final Thing INTSRUCTIONS = new Thing("Take ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox takeJComboBox;
	private boolean enabled;
	private List<Thing> things;

	public TakeActionListener(GraphicalUserInterface gui, Person player, JComboBox takeJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.takeJComboBox = takeJComboBox;
		this.enabled = true;
		things = player.otherThingsAtSamePlace();
		takeJComboBox.addItem(INTSRUCTIONS);
		for (Thing thing : things) {
			takeJComboBox.addItem(thing);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		takeJComboBox.removeAllItems();
		things = player.otherThingsAtSamePlace();
		takeJComboBox.addItem(INTSRUCTIONS);
		for (Thing thing : things) {
			takeJComboBox.addItem(thing);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Thing item = (Thing) takeJComboBox.getSelectedItem();
			if (!item.equals(INTSRUCTIONS)) {
				gui.displayMessage("\n>>> Take " + item);
				player.take(item);
				gui.playTurn();
			}
		}
	}
}
