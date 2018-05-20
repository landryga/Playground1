package com.vetClinic.consultation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.ConsultationDAOimpl;
import com.vetClinic.DAO.UsersDAOimpl;

@Service
public class ConsultationService {

private static List<Consultation> consultations = new ArrayList<Consultation>();
	
	public void addConsultation(Consultation consultation) {
		
		ConsultationDAOimpl consdao = new ConsultationDAOimpl();
		
		consdao.addConsultation(consultation);
	}
	
	public void updateConsultation(Consultation consultation) {
		ConsultationDAOimpl consdao = new ConsultationDAOimpl();
		
		consdao.updateConsultation(consultation);
	}
	
	public List<Consultation> retrieveConsultations() {
		ConsultationDAOimpl consdao = new ConsultationDAOimpl();
		
		consultations = consdao.list();
			
		return consultations;
	}
	
	public Consultation retrieveConsultation(int consultation_id) {
		Consultation consultation = new Consultation();
		
		ConsultationDAOimpl consdao = new ConsultationDAOimpl();
		
		consultation = consdao.get(consultation_id);
		
		return consultation;
	}
	
	public Consultation retrieveConsultation(String consultation_name) {
		Consultation consultation = new Consultation();
		
		return consultation;
	}
	
}
