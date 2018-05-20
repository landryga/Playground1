package com.vetClinic.admin;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vetClinic.DAO.UsersDAOimpl;

public class UserValidator implements ConstraintValidator<UserAddConstraint, String>  {

	@Override
	public void initialize(UserAddConstraint arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
		
		UsersDAOimpl usrdao = new UsersDAOimpl();
		
		System.out.println(nameField);
		
		return usrdao.checkIfUsernameUnique(nameField);
		
	}

	
}
