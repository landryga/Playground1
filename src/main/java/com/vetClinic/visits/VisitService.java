package com.vetClinic.visits;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.PatientsDAOimpl;
import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.DAO.VisitsDAOImpl;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.goods.GoodService;
import com.vetClinic.patients.Patient;


@Service
public class VisitService {
	private static List<Visit> visits = new ArrayList<Visit>();
	private static List<VisitGood> visitgoods = new ArrayList<VisitGood>();
	
	public int addVisit(Visit visit) {
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		return visitdao.addVisit(visit);
	}
	
	public void scheduleVisit(Visit visit) {
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		visitdao.scheduleVisit(visit);
	}
	
	
	public void updateVisit(Visit visit) {
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		UserMaintainer dbRef = new UserMaintainer();
		
		visitdao.updateVisit(visit);
	}
	
	public List<Visit> retrieveVisits() {
		
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		visits = visitdao.list();
		return visits;
	}
	
	public List<Visit> retrieveVisits(int patient_id) {
		
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		visits = visitdao.list(patient_id);
		return visits;
	}
	
	public List<Visit> retrieveVisits(String doctor_name) {
		
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		visits = visitdao.list(doctor_name);
		return visits;
	}
	
	public Visit retrieveVisit(int id) {
		for (Visit visit : visits) {
			if (visit.getVisitId() == id)
				return visit;
		}
		return null;
	}
	
	public Visit retrieveLastVisit() {
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		Visit visit =  new Visit();
		
		visit = visitdao.getLastVisit();
		
		return visit;
	}
	
	public void addGood (VisitGood visitgood) {
		GoodService gservice = new GoodService();
		
		gservice.addVisitGood(visitgood);
	}
	
	public List<VisitGood> retrieveVisitGoods() {
		return visitgoods;
	}
	
}

