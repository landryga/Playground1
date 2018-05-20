package com.vetClinic.consultation;

public class Consultation {
	
	private int consultation_id;
	private String consultation_name;
	private float price;
	
	public String getConsultation_name() {
		return consultation_name;
	}
	public void setConsultation_name(String consultation_name) {
		this.consultation_name = consultation_name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getConsultation_id() {
		return consultation_id;
	}
	public void setConsultation_id(int consultation_id) {
		this.consultation_id = consultation_id;
	}
	
}
