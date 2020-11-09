package com.wartest.model;

/**
 * The War-Test Model 
 * @author liu.zehu
 *
 */
public class Wartest {
	private Integer warID;
	private Troop troop1;
	private Troop troop2;
	private String location;
	private Troop victor;
	private Integer arms_left;
	
	
	public Wartest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Wartest(Troop troop1, Troop troop2, String location, Troop victor, Integer arms_left) {
		super();
		this.troop1 = troop1;
		this.troop2 = troop2;
		this.location = location;
		this.victor = victor;
		this.arms_left = arms_left;
	}
	public Wartest(Integer warID, Troop troop1, Troop troop2, String location, Troop victor, Integer arms_left) {
		super();
		this.warID = warID;
		this.troop1 = troop1;
		this.troop2 = troop2;
		this.location = location;
		this.victor = victor;
		this.arms_left = arms_left;
	}
	
	
	public Integer getWarID() {
		return warID;
	}
	public void setWarID(Integer warID) {
		this.warID = warID;
	}
	public Troop getTroop1() {
		return troop1;
	}
	public void setTroop1(Troop troop1) {
		this.troop1 = troop1;
	}
	public Troop getTroop2() {
		return troop2;
	}
	public void setTroop2(Troop troop2) {
		this.troop2 = troop2;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Troop getVictor() {
		return victor;
	}
	public void setVictor(Troop victor) {
		this.victor = victor;
	}
	public Integer getArms_left() {
		return arms_left;
	}
	public void setArms_left(Integer arms_left) {
		this.arms_left = arms_left;
	}
	
	
	@Override
	public String toString() {
		return "Wartest [warID=" + warID + ", troop1=" + troop1 + ", troop2=" + troop2 + ", location=" + location
				+ ", victor=" + victor + ", arms_left=" + arms_left + "]";
	}
}
