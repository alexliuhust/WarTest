package com.wartest.model;

import java.util.ArrayList;
import java.util.List;

public class Location {
	private List<String> locations;
	
	public Location() {
		this.locations = new ArrayList<>();
		this.locations.add("Gurvart Mount");
		this.locations.add("Taemoral Lake");
		this.locations.add("Skaven Undercity");
		this.locations.add("Athalorine Woods");
		this.locations.add("Flicken Mount");
		this.locations.add("Rabnort Lake");
		this.locations.add("Devil's Undercity");
		this.locations.add("Auken Woods");
	}
	
	public List<String> getLocations() {
		return this.locations;
	}
}
