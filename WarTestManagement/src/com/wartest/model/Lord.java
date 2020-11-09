package com.wartest.model;

/**
 * The Lord Model
 * @author liu.zehu
 *
 */
public class Lord {
	private Integer lordID;
	private String name;
	private Race race;
	private Integer hp;
	private Integer attack;
	private Integer armor;
	private Integer speed;
	
	
	public Lord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Lord(String name, Race race, Integer hp, Integer attack, Integer armor, Integer speed) {
		super();
		this.name = name;
		this.race = race;
		this.hp = hp;
		this.attack = attack;
		this.armor = armor;
		this.speed = speed;
	}
	public Lord(Integer lordID, String name, Race race, Integer hp, Integer attack, Integer armor, Integer speed) {
		super();
		this.lordID = lordID;
		this.name = name;
		this.race = race;
		this.hp = hp;
		this.attack = attack;
		this.armor = armor;
		this.speed = speed;
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
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
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
		return "Lord [lordID=" + lordID + ", name=" + name + ", race=" + race + ", hp=" + hp + ", attack=" + attack
				+ ", armor=" + armor + ", speed=" + speed + "]";
	}
	
	
}
