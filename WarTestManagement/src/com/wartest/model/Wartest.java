package com.wartest.model;

/**
 * The War-Test Model 
 * @author liu.zehu
 *
 */
public class Wartest {
	private Integer warID;
	private Integer userID;
	private Integer troop1;
	private Integer troop2;
	private String location;
	private Integer victor;
	private Integer arms_left;
	
	
	public Wartest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Wartest(Integer warID, Integer troop1, Integer troop2, String location, Integer victor, Integer arms_left) {
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
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getTroop1() {
		return troop1;
	}
	public void setTroop1(Integer troop1) {
		this.troop1 = troop1;
	}
	public Integer getTroop2() {
		return troop2;
	}
	public void setTroop2(Integer troop2) {
		this.troop2 = troop2;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getVictor() {
		return victor;
	}
	public void setVictor(Integer victor) {
		this.victor = victor;
	}
	public Integer getArms_left() {
		return arms_left;
	}
	public void setArms_left(Integer arms_left) {
		this.arms_left = arms_left;
	}
	
}
