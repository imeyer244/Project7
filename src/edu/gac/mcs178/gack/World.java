package edu.gac.mcs178.gack;

import edu.gac.mcs178.gack.domain.Person;

public class World {
	
	private Person player;
	
	public Person getPlayer() {
		return player;
	}

	protected void setPlayer(Person player) {
		this.player = player;
	}

	public World() {}
}
