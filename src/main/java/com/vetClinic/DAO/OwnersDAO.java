package com.vetClinic.DAO;

import java.util.List;

import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.owner.Owner;

public interface OwnersDAO {
	 	public void delete(int user_id);
     
	    public Owner get(int user_id);
	     
	    public List<Owner> list();

		void addOwner(Owner owner);
		
		void updateOwner(Owner owner);

		int addOwnerInt(Owner owner);
}
