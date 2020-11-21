package com.wartest.model;

/**
 * The Lord Model
 * @author liu.zehu
 *
 */
public class Lord {
	private Integer lordID;
	private String name;
	private String race;
	private Integer hp;
	private Integer attack;
	private Integer armor;
	private Integer speed;
	
	
	public Lord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getLordID() {
		return lordID;
	}
	public void setLordID(Integer lordID) {
		this.lordID = lordID;
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
		return this.name;
	}
	
	
}
