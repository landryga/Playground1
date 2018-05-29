package com.vetClinic.owner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.vetClinic.DAO.OwnersDAOimpl;
import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.mail.MailHandler;

@Service
public class OwnerService {

	private static List<Owner> owners = new ArrayList<Owner>();
	
	public void addOwner(Owner owner) {
		
		OwnersDAOimpl ownerdao = new OwnersDAOimpl();
		
		ownerdao.addOwner(owner);
	}
	
	public int addOwnerInt(Owner owner) {
		
		OwnersDAOimpl ownerdao = new OwnersDAOimpl();
		
		int id = ownerdao.addOwnerInt(owner);
		
		return id;
	}
	
	
	//TODO - fill in
	public void updateOwner(UserMaintainer userMaintainer) {
		UsersDAOimpl userdao = new UsersDAOimpl();
		
	
		UserMaintainer dbRef = new UserMaintainer();
		
		int UserId = userMaintainer.getId();
		
		UsersDAOimpl userDB = new UsersDAOimpl();
		
		dbRef = userDB.get(UserId);
		
		//userMaintainer.getIs_admin();
		
		if(userMaintainer.getIs_admin() != dbRef.getIs_admin()) {
			
			userDB.updateUserRole(userMaintainer);
		}
		
		userdao.updateUser(userMaintainer);
	}
	
	public List<Owner> retrieveOwners() {
		
		OwnersDAOimpl ownerdao = new OwnersDAOimpl();
		
		owners = ownerdao.list();
		
		return owners;
	}
	
	public Owner retrieveOwner(int id) {
		OwnersDAOimpl ownerdao = new OwnersDAOimpl();
		
		Owner owner = new Owner();
		
		owner = ownerdao.get(id);
		return owner;
	}
	
	public Owner retrieveOwner(String email) {
		OwnersDAOimpl ownerdao = new OwnersDAOimpl();
		
		Owner owner = new Owner();
		
		owner= ownerdao.get(email);
		return owner;
	}
	
}
