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
	private Lord lord;
	private List<Arm> arms;
	private String memeo;
	
	
	public Troop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Troop(String name, Integer userID, Lord lord, List<Arm> arms, String memeo) {
		super();
		this.name = name;
		this.userID = userID;
		this.lord = lord;
		this.arms = arms;
		this.memeo = memeo;
	}
	
	public Troop(Integer troopID, String name, Integer userID, Lord lord, List<Arm> arms, String memeo) {
		super();
		this.troopID = troopID;
		this.name = name;
		this.userID = userID;
		this.lord = lord;
		this.arms = arms;
		this.memeo = memeo;
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
	public Lord getLord() {
		return lord;
	}
	public void setLord(Lord lord) {
		this.lord = lord;
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
	
	@Override
	public String toString() {
		return "Troop [troopID=" + troopID + ", name=" + name + ", userID=" + userID + ", lordID=" + lord + ", arms="
				+ arms + ", memeo=" + memeo + "]";
	}
	
}
