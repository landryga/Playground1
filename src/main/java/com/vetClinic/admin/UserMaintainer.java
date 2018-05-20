package com.vetClinic.admin;

public class UserMaintainer {
	
	
	private String username;
	private String userSurName;
	
	@UserAddConstraint
	private String email;
	private boolean is_admin;
	private boolean is_doctor;
	
	//to be improved in the future, by proper error handling
	private boolean unique;
	private String password;
	private int id;
	private int doctor_id = 0;

	
	public UserMaintainer() {
		
	}
	
	public UserMaintainer(String username, boolean is_admin) {
		super();
		this.username = username;
		this.is_admin = is_admin;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserSurname() {
		return userSurName;
	}

	public void setUserSurname(String userSurName) {
		this.userSurName = userSurName;
	}
	
	
	public boolean getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isIs_doctor() {
		return is_doctor;
	}

	public void setIs_doctor(boolean is_doctor) {
		this.is_doctor = is_doctor;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	
	public int getDoctor_id() {
		return doctor_id;
	}

	
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	
	
}
