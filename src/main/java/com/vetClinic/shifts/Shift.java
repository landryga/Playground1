package com.vetClinic.shifts;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Shift {

	private int id;
	private String start_date;
	private String end_date;
	private int user_id;
	private String user_email;
	private String username;
	
	String date_format = "MM/dd/yyyy hh:mm a";
	
	
	public int getId() {
		return id;
	}
	public void setId(int shiftId) {
		this.id = shiftId;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
