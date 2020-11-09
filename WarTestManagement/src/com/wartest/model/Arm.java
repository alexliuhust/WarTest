package com.wartest.model;

/**
 * The Arm Model
 * @author liu.zehu
 *
 */
public class Arm {
	private Integer armID;
	private String name;
	private Race race;
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
	public Arm(String name, Race race, String type, Integer cost, Integer scale, Integer hp, Integer attack,
			Integer armor, Integer speed) {
		super();
		this.name = name;
		this.race = race;
		this.type = type;
		this.cost = cost;
		this.scale = scale;
		this.hp = hp;
		this.attack = attack;
		this.armor = armor;
		this.speed = speed;
	}
	public Arm(Integer armID, String name, Race race, String type, Integer cost, Integer scale, Integer hp,
			Integer attack, Integer armor, Integer speed) {
		super();
		this.armID = armID;
		this.name = name;
		this.race = race;
		this.type = type;
		this.cost = cost;
		this.scale = scale;
		this.hp = hp;
		this.attack = attack;
		this.armor = armor;
		this.speed = speed;
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
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
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
		return "Arm [armID=" + armID + ", name=" + name + ", race=" + race + ", type=" + type + ", cost=" + cost
				+ ", scale=" + scale + ", hp=" + hp + ", attack=" + attack + ", armor=" + armor + ", speed=" + speed
				+ "]";
	}
}
