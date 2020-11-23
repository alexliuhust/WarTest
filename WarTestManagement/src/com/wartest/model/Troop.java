package com.wartest.model;

import java.util.List;

/**
 * The Troop Model
 * @author liu.zehu
 *
 */
public class Troop {
	private Integer troopID;
	private String name;
	private Integer userID;
	private Integer lordID;
	private List<Integer> armIDs;
	private String memo;
	
	
	public Troop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Troop(String name) {
		this.name = name;
	}
	
	
	public Integer getTroopID() {
		return troopID;
	}
	public void setTroopID(Integer troopID) {
		this.troopID = troopID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getLordID() {
		return lordID;
	}
	public void setLord(Integer lordID) {
		this.lordID = lordID;
	}
	public List<Integer> getArms() {
		return armIDs;
	}
	public void setArms(List<Integer> armIDs) {
		this.armIDs = armIDs;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}


	@Override
	public String toString() {
		return this.name;
	}
	
}
