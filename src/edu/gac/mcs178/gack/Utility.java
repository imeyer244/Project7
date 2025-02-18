package edu.gac.mcs178.gack;

import java.util.ArrayList;
import java.util.List;

import edu.gac.mcs178.gack.domain.Person;
import edu.gac.mcs178.gack.domain.Place;
import edu.gac.mcs178.gack.domain.Thing;
import edu.gac.mcs178.gack.ui.MessageDisplay;

public class Utility {
	
	private static MessageDisplay userInterface;
	public static void setUserInterface(MessageDisplay userInterface) {
		Utility.userInterface = userInterface;
	}

	public static void displayMessage(String text) {
		if (userInterface == null) {
			System.out.println(text);
		} else {
			userInterface.displayMessage(text);
		}
	}
	
	public static String verbalizeList(List<?> items, String ifItemsEmpty) {
		if (items.size() == 0) {
			return ifItemsEmpty;
		} else if (items.size() == 1) {
			return items.get(0).toString();
		} else if (items.size() == 2) {
			return items.get(0) + " and " + items.get(1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < items.size(); i++) {
				if (i < items.size() - 1) {
					sb.append(items.get(i) + ", ");
				} else {
					sb.append("and " + items.get(i));
				}
			}
			return sb.toString();
		}
	}
	
	public static int randInt(int n) {
		return (int) (n * Math.random());
	}
	
	public static void main(String[] args) {
		Place olin312 = new Place("Olin 312");
		Person karl = new Person("Karl Knight", olin312);
		Thing karlsComputer = new Thing("Karl's computer");
		List<Object> testList = new ArrayList<Object>(3);
		testList.add(olin312);
		testList.add(karl);
		testList.add(karlsComputer);
		testList.add("test string");
		displayMessage(verbalizeList(testList, "testList is empty"));
		for (int i = 0; i < 20; i++) {
			System.out.println("randInt(10) = " + randInt(10));
		}
	}
}
