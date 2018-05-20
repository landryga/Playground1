package com.vetClinic.DAO;

import java.util.List;

import com.vetClinic.consultation.Consultation;
import com.vetClinic.owner.Owner;

public interface ConsultationDAO {
	public void delete(int consultation_id);
    
    public Consultation get(String consultation_name);
    
    public Consultation get(int consultation_id);
     
    public List<Consultation> list();

	void addConsultation(Consultation consultation);
	
	void updateConsultation(Consultation consultation);
}
