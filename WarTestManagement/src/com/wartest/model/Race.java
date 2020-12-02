package com.wartest.model;

/**
 * The Race Model
 * @author liu.zehu
 *
 */
public class Race {
	private String race;
	private String location;
	private String description;
	
	
	public Race() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Override
	public String toString() {
		return this.race;
	}
}
