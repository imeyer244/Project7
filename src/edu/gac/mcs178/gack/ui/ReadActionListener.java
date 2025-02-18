package edu.gac.mcs178.gack.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Scroll;

public class ReadActionListener implements ActionListener {
	
	private static final Scroll INTSRUCTIONS = new Scroll("Read ...");
	
	private GraphicalUserInterface gui;
	private Person player;
	private JComboBox readJComboBox;
	private boolean enabled;
	private List<Scroll> scrolls;

	public ReadActionListener(GraphicalUserInterface gui, Person player, JComboBox readJComboBox) {
		super();
		this.gui = gui;
		this.player = player;
		this.readJComboBox = readJComboBox;
		this.enabled = true;
		scrolls = Scroll.scrollsIn(player.getPlace());
		readJComboBox.addItem(INTSRUCTIONS);
		for (Scroll scroll : scrolls) {
			readJComboBox.addItem(scroll);
		}
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void updateJComboBox() {
		readJComboBox.removeAllItems();
		scrolls = Scroll.scrollsIn(player.getPlace());
		readJComboBox.addItem(INTSRUCTIONS);
		for (Scroll scroll : scrolls) {
			readJComboBox.addItem(scroll);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (enabled) {
			Scroll item = (Scroll) readJComboBox.getSelectedItem();
			if (!item.getName().equals(INTSRUCTIONS.getName())) {
				gui.displayMessage("\n>>> Read " + item);
				player.read(item);
				gui.playTurn();
			}
		}
	}
}
