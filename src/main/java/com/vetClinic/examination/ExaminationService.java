package com.vetClinic.examination;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.ExaminationDAOimpl;
import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.DAO.VisitsDAOImpl;
import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.goods.Good;
import com.vetClinic.goods.GoodService;
import com.vetClinic.mail.MailHandler;
import com.vetClinic.owner.Owner;
import com.vetClinic.owner.OwnerService;
import com.vetClinic.patients.Patient;
import com.vetClinic.patients.PatientService;
import com.vetClinic.visits.Visit;
import com.vetClinic.visits.VisitGood;

@Service
public class ExaminationService {

	private static List<Examination> examinations = new ArrayList<Examination>();
	
	public int addExamination(Examination examination) {
		
		Examination examinationreference = new Examination();
		
		ExaminationDAOimpl examinationdao = new ExaminationDAOimpl();
		
		int examination_post_id = examinationdao.addExamination(examination);
		
		examinationreference = examinationdao.get(examination_post_id);
		
		MailHandler handler = new MailHandler();
		
		OwnerService ownerServ = new OwnerService();
		
		Owner owner = ownerServ.retrieveOwner(examinationreference.getOwnerId());
		
		try {
			handler.send("testdevpw@gmail.com", owner.getEmail(), examinationreference.getResult(), "Wyniki badania " + examinationreference.getPatient_name());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return examination_post_id;
		
		
		
	}
	
	public void scheduleExamination(Examination examination) {
		ExaminationDAOimpl examinationdao = new ExaminationDAOimpl();
		
		examinationdao.scheduleExamination(examination);
		
	}
	
	public List<Examination> retrieveExaminations() {
		
		ExaminationDAOimpl examinationdao = new ExaminationDAOimpl();
		
		examinations = examinationdao.list();
		return examinations;
	}
	
	public List<Examination> retrieveExaminations(int examination_id) {
		
		ExaminationDAOimpl examinationdao = new ExaminationDAOimpl();
		
		examinations = examinationdao.list(examination_id);
		return examinations;
	}
	
	public Examination retrieveExamination(int id) {
		
		ExaminationDAOimpl examinationdao = new ExaminationDAOimpl();
		
		return examinationdao.get(id);
	}

	public List<ExaminationType> getExaminationTypes() {
		ExaminationDAOimpl examinationdao = new ExaminationDAOimpl();
		
		List<ExaminationType> examinationTypes = new ArrayList<ExaminationType>();
		
		examinationTypes = examinationdao.listExaminationTypes();
		
		return examinationTypes;
		
	}
	
}
