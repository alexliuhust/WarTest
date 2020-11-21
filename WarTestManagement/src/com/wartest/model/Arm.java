package com.wartest.model;

/**
 * The Arm Model
 * @author liu.zehu
 *
 */
public class Arm {
	private Integer armID;
	private String name;
	private String race;
	private String type;
	private Integer cost;
	private Integer scale;
	private Integer hp;
	private Integer attack;
	private Integer armor;
	private Integer speed;
	
	
	public Arm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getArmID() {
		return armID;
	}
	public void setArmID(Integer armID) {
		this.armID = armID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
	}
	public Integer getHp() {
		return hp;
	}
	public void setHp(Integer hp) {
		this.hp = hp;
	}
	public Integer getAttack() {
		return attack;
	}
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	public Integer getArmor() {
		return armor;
	}
	public void setArmor(Integer armor) {
		this.armor = armor;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	
	@Override
	public String toString() {
		return this.name + " - " + this.type;
	}
}
