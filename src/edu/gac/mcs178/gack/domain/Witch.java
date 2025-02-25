package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Witch extends AutoPerson {
	
	private Place pond;
	
	public Witch(String name, Place place, int threshold, Place pond) {
		super(name, place, threshold);
		this.pond = pond;
	}

	@Override
	public void act() {
		List<Person> others = otherPeopleAtSamePlace();
		if (!others.isEmpty()) {
			Person victim = others.get(Utility.randInt(others.size()));
			// make a list out of the victim's possessions to loop through
			List<Thing> victimPossessions = new ArrayList<Thing>(victim.getPossessions());
			// get the size of the possessions to use in the for loop
			int possessionsSize = victimPossessions.size();
			// set a boolean value that will be evaluated when deciding if the
			// victim gets cursed or not
			boolean willCurse = true;
			// if the user doesn't have any possessions say that and then curse them
			if (possessionsSize == 0) {
				say("Victim has no possessions.");
			} 
			// otherwise, the user has stuff, so look for a chocolate
			else{
				// loop through possessions to look for chocolate
				for (int i = 0; i < possessionsSize; i++) {
					if (victimPossessions.get(i).getName() == "chocolate") {
						// take chocolate from victim
						take(victimPossessions.get(i));
						// eat newly acquired chocolate
						eat(victimPossessions.get(i));
						// change the willCurse value to false so the victim isn't
						// cursed
						willCurse = false;
						say("Thank you for your chocolate, you can stay human :)");
						// break from for loop to prevent any unnecessary looping
						break;
					}
				}
			}
			// curse the victim if no chocolate was eaten
			if(willCurse) {
				curse(victim);
			}
		} else {
			super.act();
		}
	}

	public void curse(Person person) {
		say("Hah hah hah, I'm going to turn you into a frog, " + person);
		turnIntoFrog(person);
		say("Hee hee " + person + " looks better in green!");
	}
	
	public void turnIntoFrog(Person person) {
		// need to copy person.getPossessions() in order to avoid a ConcurrentModificationException
		List<Thing> personsPossessions = new ArrayList<Thing>(person.getPossessions());
		for (Thing thing : personsPossessions) {
			person.lose(thing);
		}
		person.say("Ribbitt!");
		person.moveTo(pond);
	}
}
