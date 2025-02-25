package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Thing;

public class EatActionListener implements ActionListener {
	
	private static final Thing INTSRUCTIONS = new Thing("Eat ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox eatJComboBox;
	private boolean enabled;
	private List<Thing> things;

	public EatActionListener(GraphicalUserInterface gui, Person player, JComboBox eatJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.eatJComboBox = eatJComboBox;
		this.enabled = true;
		things = player.getPossessions();
		eatJComboBox.addItem(INTSRUCTIONS);
		for (Thing thing : things) {
			eatJComboBox.addItem(thing);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		eatJComboBox.removeAllItems();
		things = player.getPossessions();
		eatJComboBox.addItem(INTSRUCTIONS);
		for (Thing thing : things) {
			eatJComboBox.addItem(thing);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Thing item = (Thing) eatJComboBox.getSelectedItem();
			if (!item.equals(INTSRUCTIONS)) {
				gui.displayMessage("\n>>> Eat " + item);
				player.eat(item);
				gui.playTurn();
			}
		}
	}
}
