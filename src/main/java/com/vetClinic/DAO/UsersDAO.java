package com.vetClinic.DAO;

import java.sql.SQLException;
import java.util.List;

import com.vetClinic.admin.UserMaintainer;

public interface UsersDAO {
	
    public void delete(int user_id);
     
    public UserMaintainer get(int user_id);
    
    public UserMaintainer get(String username);
     
    public List<UserMaintainer> list();
    
    public List<UserMaintainer> listDoctors();

	void addUser(UserMaintainer maintainer);
	
	void updateUser(UserMaintainer maintainer);
	
	void updateUserRole(UserMaintainer maintainer);

	void updateDoctorBoolean(UserMaintainer maintainer);
	
	public boolean checkIfUsernameUnique (String username);
    
}
