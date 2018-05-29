package com.vetClinic.visits;

import java.sql.Date;

public class Visit {
	private int visitId;
	private String visit_date;
	private int doctor_id;
	private int patient_id;
	private String visit_description;
	private String patient_name;
	private String owner_name = null;
	private String doctor_name;
	private String good_name;
	private boolean past;
	private boolean today;
	private boolean active;
	private int qty;
	private String name;
	private String description;
	private float price;
	private String email;
	private boolean complete_owner_data;
	
	//TODO - get rid of this crappy functionality, replace with proper AJAX logic once learned
		private int test;
	
	
		
	public boolean isComplete_owner_data() {
		
		return complete_owner_data;
	}
	public void setComplete_owner_data(boolean complete_owner_data) {
			this.complete_owner_data = complete_owner_data;
	}
	public int getVisitId() {
		return visitId;
	}
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public String getVisit_description() {
		return visit_description;
	}
	public void setVisit_description(String visit_description) {
		this.visit_description = visit_description;
	}
	
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public boolean isPast() {
		return past;
	}
	public void setPast(boolean past) {
		this.past = past;
	}
	public boolean isToday() {
		return today;
	}
	public void setToday(boolean today) {
		this.today = today;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	public String getGood_name() {
		return good_name;
	}
	public void setGood_name(String good_name) {
		this.good_name = good_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
