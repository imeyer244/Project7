package edu.gac.mcs178.gack.domain;

import java.util.List;

import edu.gac.mcs178.gack.Registry;
import edu.gac.mcs178.gack.Utility;

public class AutoPerson extends Person {
	
	private static Registry registry;
	
	public static Registry getRegistry() {
		if(registry == null){
			registry = new Registry();
		}
		return registry;
	}

	private int threshold;
	private int restlessness;
	
	public AutoPerson(String name, Place place, int threshold) {
		super(name, place);
		this.threshold = threshold;
		this.restlessness = 0;
		getRegistry().add(this);
	}
	
	public void maybeAct() {
		if (restlessness < threshold) {
			restlessness++;
		} else {
			restlessness = 0;
			act();
		}
	}
	
	public void act() {
		List<Place> neighbors = getPlace().neighbors();
		if (!neighbors.isEmpty()) {
			Place newPlace = neighbors.get(Utility.randInt(neighbors.size()));
			moveTo(newPlace);
		}
	}
}
