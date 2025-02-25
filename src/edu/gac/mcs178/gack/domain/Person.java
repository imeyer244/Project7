package edu.gac.mcs178.gack.domain;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.Utility;

public class Person {
	
	private String name;
	private Place place;
	private List<Thing> possessions;
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public Place getPlace() { return place; }
	public List<Thing> getPossessions() { return possessions; }

	public Person(String name, Place place) {
		super();
		this.name = name;
		this.place = place;
		this.possessions = new ArrayList<Thing>();
		place.gain(this);
	}
	
	public void say(String text) {
		Utility.displayMessage("At " + place + ": " + this + " says -- " + text);
	}
	
	public List<Thing> otherThingsAtSamePlace() {
		List<Thing> otherThingsAtSamePlace = new ArrayList<Thing>();
		for (Thing thing : place.getContents()) {
			if (!possessions.contains(thing)) {
				otherThingsAtSamePlace.add(thing);
			}
		}
		return otherThingsAtSamePlace;
	}
	
	public List<Person> otherPeopleAtSamePlace() {
		List<Person> otherPeopleAtSamePlace = new ArrayList<Person>();
		for (Person occupant : place.getOccupants()) {
			if (!occupant.equals(this)) {
				otherPeopleAtSamePlace.add(occupant);
			}
		}
		return otherPeopleAtSamePlace;
	}
	
	public void lookAround() {
		say("I see " + Utility.verbalizeList(otherPeopleAtSamePlace(), "no people") +
				" and " + Utility.verbalizeList(otherThingsAtSamePlace(), "no objects") +
				" and can go " + Utility.verbalizeList(place.exits(), "nowhere"));
	}
	
	public void listPossessions() {
		say("I have " + Utility.verbalizeList(possessions, "nothing"));
	}
	
	public void read(Scroll scroll) {
		if ((scroll.isOwned()) && (scroll.getOwner().equals(this))) {
			scroll.beRead();
		} else {
			Utility.displayMessage(this + " does not have " + scroll);
		}
	}
	
	public void haveFit() {
		say("Yaaaah! I am upset");
	}
	
	public void moveTo(Place newPlace) {
		Utility.displayMessage(this + " moves from " + place + " to " + newPlace);
		place.lose(this);
		newPlace.gain(this);
		for(Thing possession : possessions) {
			place.lose(possession);
			newPlace.gain(possession);
		}
		place = newPlace;
		greet(otherPeopleAtSamePlace());
	}
	
	public void go(String direction) {
		Place newPlace = place.neighborTowards(direction);
		if (newPlace == null) {
			Utility.displayMessage("You cannot go " + direction + " from " + place);
		} else {
			moveTo(newPlace);
		}
	}
	
	public void take(Thing thing) {
		if (equals(thing.getOwner())) {
			Utility.displayMessage(this + " already has " + thing);
		} else {
			if (thing.isOwned()) {
				Person owner = thing.getOwner();
				owner.lose(thing);
				owner.haveFit();
			}
			thing.setOwner(this);
			possessions.add(thing);
			say("I take " + thing);
		}
	}
	
	public void give(Thing thing, Person recipient) {
		if (!equals(thing.getOwner())) {
			Utility.displayMessage(this + " doesn't have " + thing);
		} else {
			// make thing's owner null
			thing.becomeUnowned();
			// remove thing from owner's possessions
			possessions.remove(thing);
			// set the new owner of the thing to the person receiving it
			thing.setOwner(recipient);
			// add the thing to the new owner's possessions
			recipient.possessions.add(thing);
			this.say("I give " + thing + " to " + recipient);
			
		}
	}
	
	public void eat(Thing thing) {
		if (!equals(thing.getOwner())) {
			Utility.displayMessage(this + " doesn't have " + thing);
		}else {
			// check to make sure that the thing being eaten is chocolate
			if (thing.getName() == "chocolate") {
				// call beEaten to make the object's owner null
				thing.beEaten();
				// remove thing from owner's possessions
				possessions.remove(thing);
				// remove the thing from the place so that it cannot be grabbed again
				place.lose(thing);
				say("Yummy, this " + thing + " is tasty.");
			}
			// if the person is trying to eat something other than chocolate then display
			// message telling them they can't eat that thing
			else {
				Utility.displayMessage(this + " can't eat " + thing);
			}
			
		}
	}
	
	public void lose(Thing thing) {
		if (!equals(thing.getOwner())) {
			Utility.displayMessage(this + " doesn't have " + thing);
		} else {
			thing.becomeUnowned();
			possessions.remove(thing);
			say("I lose " + thing);
		}
	}
	
	public void greet(List<Person> people) {
		if (!people.isEmpty()) {
			say("Hi " + Utility.verbalizeList(people, "no one")); // "no one" can't happen
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}
