package com.vetClinic.DAO;

import java.util.List;

import com.vetClinic.patients.Patient;

public interface PatientsDAO {
	
public void delete(int patient_id);
    
    public Patient get(int patient_id);
     
    public List<Patient> list();

	void addPatient(Patient patient);
	
	void updatePatient(Patient patient);

	int addPatientId(Patient patient);
}
