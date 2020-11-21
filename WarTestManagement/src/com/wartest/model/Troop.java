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
	private List<Arm> arms;
	private String memeo;
	
	
	public Troop() {
		super();
		// TODO Auto-generated constructor stub
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
	public List<Arm> getArms() {
		return arms;
	}
	public void setArms(List<Arm> arms) {
		this.arms = arms;
	}
	public String getMemeo() {
		return memeo;
	}
	public void setMemeo(String memeo) {
		this.memeo = memeo;
	}
	
}
