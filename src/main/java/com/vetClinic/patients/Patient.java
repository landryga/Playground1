package com.vetClinic.patients;

import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;

public class Patient {
	
	private int id;
	private String race;
	private String species;
	private boolean sex;
	private String patient_name;
	private int owner_id;
	private String owner_name;
	private Date birth_date;
	private Date death_date;
	private int microchip_id;
	private boolean alive;
	
	public int getId() {
		return id;
	}
	public void setId(int patient_id) {
		this.id = patient_id;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public Date getDeath_date() {
		return death_date;
	}
	public void setDeath_date(Date death_date) {
		this.death_date = death_date;
	}
	public int getMicrochip_id() {
		return microchip_id;
	}
	public void setMicrochip_id(int microchip_id) {
		this.microchip_id = microchip_id;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public String getOwner_name() {
		return owner_name;
	}
	
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	

}
