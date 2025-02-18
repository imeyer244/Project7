package edu.gac.mcs178.gack;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.domain.AutoPerson;

public class Registry {
	
	List<AutoPerson> autoPersons;

	public Registry() {
		super();
		autoPersons = new ArrayList<AutoPerson>();
	}
	
	public void add(AutoPerson autoPerson) {
		autoPersons.add(autoPerson);
	}
	
	public void remove(AutoPerson autoPerson) {
		autoPersons.remove(autoPerson);
	}
	
	public void trigger() {
		for (AutoPerson autoPerson : autoPersons) {
			autoPerson.maybeAct();
		}
	}
	
	public void trigger(int numTimes) {
		for (int i = 0; i < numTimes; i++) {
			trigger();
		}
	}
}
