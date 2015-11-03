package homework.model;

import java.util.TreeMap;

public class Venue {

	private String name;
	private TreeMap<Integer, SeatingLevel> seatingLevels;
	
	public Venue() {
		seatingLevels = new TreeMap<Integer, SeatingLevel>() ;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public TreeMap<Integer, SeatingLevel>getSeatingLevels() {
		return seatingLevels;
	}
}
