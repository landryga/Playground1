package com.vetClinic.patients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.vetClinic.DAO.OwnersDAOimpl;
import com.vetClinic.DAO.PatientsDAOimpl;
import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.mail.MailHandler;
import com.vetClinic.owner.Owner;
import com.vetClinic.patients.Patient;

@Service
public class PatientService {
private static List<Patient> patients = new ArrayList<Patient>();
	
	public void addPatient(Patient patient) {
		
		
		PatientsDAOimpl patientdao = new PatientsDAOimpl();
		patientdao.addPatient(patient);
	}
	
	
	//TODO - fill in
	public void updatePatient(UserMaintainer userMaintainer) {
		PatientsDAOimpl patientdao = new PatientsDAOimpl();
		
		UserMaintainer dbRef = new UserMaintainer();
		
		int UserId = userMaintainer.getId();
		
		UsersDAOimpl userDB = new UsersDAOimpl();
		
		dbRef = userDB.get(UserId);
		
		//userMaintainer.getIs_admin();
		
		if(userMaintainer.getIs_admin() != dbRef.getIs_admin()) {
			
			userDB.updateUserRole(userMaintainer);
		}
		
		//patientdao.updateUser(userMaintainer);
	}
	
	public List<Patient> retrievePatients() {
		
		PatientsDAOimpl patientdao = new PatientsDAOimpl();
		
		patients = patientdao.list();
		return patients;
	}
	
	public Patient retrievePatient(int id) {
		for (Patient patient : patients) {
			if (patient.getId() == id)
				return patient;
		}
		return null;
	}


	public int addPatientInt(Patient patient) {
		PatientsDAOimpl patientdao = new PatientsDAOimpl();
		int id = patientdao.addPatientId(patient);
		return id;
	}
}
