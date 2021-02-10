package com.minty.leemonmc.underground.core;

public class UndergroundData {

	private Integer experience;
	private Integer maxExp;
	private Integer armorLevel;
	
	private Integer swordLevel;
	private Integer pickaxeLevel;
	private boolean spawned;
	private Integer kills;
	private Integer deaths;
	
	public UndergroundData() {
		experience = 0;
		kills = 0;
		armorLevel = 1;
		deaths = 0;
		maxExp = 1000;
		swordLevel = 1;
		pickaxeLevel = 1;
		spawned = true;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	public boolean isSpawned() {
		return spawned;
	}
	
	public Integer getKills() {
		return kills;
	}
	
	public void setArmorLevel(Integer armorLevel) {
		this.armorLevel = armorLevel;
	}
	
	public Integer getArmorLevel() {
		return armorLevel;
	}
	
	public void setDeaths(Integer deaths) {
		this.deaths = deaths;
	}
	
	public void addKill() {
		kills++;
	}
	
	public void addDeath() {
		deaths++;
	}
	
	public void setKills(Integer kills) {
		this.kills = kills;
	}
	
	public Integer getDeaths() {
		return deaths;
	}
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
	
	public Integer getSwordLevel() {
		return swordLevel;
	}
	
	public Integer getPickaxeLevel() {
		return pickaxeLevel;
	}
	
	public void setPickaxeLevel(Integer pickaxeLevel) {
		this.pickaxeLevel = pickaxeLevel;
	}
	
	public void setSwordLevel(Integer swordLevel) {
		this.swordLevel = swordLevel;
	}
	
	public void addExperience(Integer _experience) {
		experience += _experience;
		if(experience > getMaxExp()) {
			experience = getMaxExp();
		}
	}
	
	public void removeExperience(Integer _experience) {
		experience -= _experience;
		if(experience < 0) {
			experience = 0;
		}
	}
	
	public Integer getMaxExp() {
		return maxExp;
	}
	
	public Integer getExperience() {
		return experience;
	}
	
}
