package edu.gac.mcs178.gack.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.gac.mcs178.gack.GackWorld;
import edu.gac.mcs178.gack.Utility;
import edu.gac.mcs178.gack.domain.AutoPerson;
import edu.gac.mcs178.gack.domain.Person;

public class GraphicalUserInterface extends JFrame implements MessageDisplay {
	
	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 800;

	private static final long serialVersionUID = 1L;
	
	private int pace;
	
	private JTextArea textArea;
	private JComboBox goJComboBox;
	private JComboBox takeJComboBox;
	private JComboBox dropJComboBox;
	private JComboBox readJComboBox;
	private JComboBox giveJComboBox;
	private GoActionListener goActionListener;
	private TakeActionListener takeActionListener;
	private DropActionListener dropActionListener;
	private ReadActionListener readActionListener;
	private GiveActionListener giveActionListener;
	
	public GraphicalUserInterface(Person player) {
		super();
		this.pace = 2;
		
		setTitle("The Imaginary Land of Gack");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// textScrollPane contains textArea, which is the where output goes
		textArea = new JTextArea(8, 40);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
        textArea.append("Welcome to the Imaginary Land of Gack\n");
		JScrollPane textScrollPane = new JScrollPane(textArea);
		textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(textScrollPane, BorderLayout.CENTER);
		
		// controlPanel contains all of the controls for the game
		JPanel controlPanel = new JPanel(new GridLayout(3, 3));
		add(controlPanel, BorderLayout.NORTH);
		
		// Look around button
		
		JButton lookAroundButton = new JButton("Look around");
		lookAroundButton.addActionListener(new LookAroundActionListener(this, player));
		controlPanel.add(lookAroundButton);
		
		// Pace slider
		
		JSlider paceSlider = new JSlider(1, 5, pace);
		paceSlider.setPaintTicks(true);
		paceSlider.setSnapToTicks(true);
		paceSlider.setPaintLabels(true);
		paceSlider.setMajorTickSpacing(1);
		paceSlider.setMinorTickSpacing(1);
		ChangeListener paceSliderListener = new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				// update text field when the slider value changes
				JSlider source = (JSlider) event.getSource();
				pace = source.getValue();
			}
		};
		paceSlider.addChangeListener(paceSliderListener);
		controlPanel.add(paceSlider);
		
		// Take combo box
		
		takeJComboBox = new JComboBox();
		takeActionListener = new TakeActionListener(this, player, takeJComboBox);
		takeJComboBox.addActionListener(takeActionListener);
		controlPanel.add(takeJComboBox);
		
		// List possessions button
		
		JButton listPossessionsButton = new JButton("List possessions");
		listPossessionsButton.addActionListener(new ListPossessionsActionListener(this, player));
		controlPanel.add(listPossessionsButton);
		
		// Go combo box
		
		goJComboBox = new JComboBox();
		goActionListener = new GoActionListener(this, player, goJComboBox);
		goJComboBox.addActionListener(goActionListener);
		controlPanel.add(goJComboBox);
		
		// Give combo box
		
		giveJComboBox = new JComboBox();
		giveActionListener = new GiveActionListener(this, player, giveJComboBox);
		giveJComboBox.addActionListener(giveActionListener);
		controlPanel.add(giveJComboBox);

		// Change player's name button
		
		JButton changeNameButton = new JButton("Change player's name");
		changeNameButton.addActionListener(new ChangePlayersNameActionListener(this, player));
		controlPanel.add(changeNameButton);

		// Read combo box
		
		readJComboBox = new JComboBox();
		readActionListener = new ReadActionListener(this, player, readJComboBox);
		readJComboBox.addActionListener(readActionListener);
		controlPanel.add(readJComboBox);

		// Drop combo box
		
		dropJComboBox = new JComboBox();
		dropActionListener = new DropActionListener(this, player, dropJComboBox);
		dropJComboBox.addActionListener(dropActionListener);
		controlPanel.add(dropJComboBox);
	}
	
	public void playTurn() {
		AutoPerson.getRegistry().trigger(pace);
		updateJComboBoxes();
	}
	
	private void updateJComboBoxes() {
		enableJComboListeners(false);
		goActionListener.updateJComboBox();
		takeActionListener.updateJComboBox();
		dropActionListener.updateJComboBox();
		readActionListener.updateJComboBox();
		giveActionListener.updateJComboBox();
		enableJComboListeners(true);
	}
	
	private void enableJComboListeners(boolean b) {
		goActionListener.setEnabled(b);
		takeActionListener.setEnabled(b);
		dropActionListener.setEnabled(b);
		readActionListener.setEnabled(b);
		giveActionListener.setEnabled(b);
	}
	
	public void displayMessage(String text) {
        textArea.append(text + "\n");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				GraphicalUserInterface frame = 
					new GraphicalUserInterface(new GackWorld().getPlayer());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				Utility.setUserInterface(frame);
			}
		});
	}
}
