package com.vetClinic.visits;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.PatientsDAOimpl;
import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.DAO.VisitsDAOImpl;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.goods.Good;
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
	
	public boolean scheduleVisit(Visit visit) {
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		if(!visitdao.scheduleVisit(visit)) {
			return false;
		} else
		{
			return true;
		}
		
	}
	
	
	public void closeVisit(Visit visit, List<VisitGood> vg) {
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		
		float price_summary = 0;
		float single_price = 0;
		
		GoodService gs = new GoodService();
		
		
		for(VisitGood visitgood : vg) {
			
			Good good = gs.retrieveGood(visitgood.getId());
			
			single_price = good.getPrice();
			
			price_summary+=visitgood.getQty()*single_price;
		}
		
		visit.setPrice(price_summary);
		
		
		visitdao.closeVisit(visit);
	}
	
	public List<Visit> retrieveVisits() {
		
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		visits = visitdao.list();
		return visits;
	}
	
	public List<Visit> retrieveVisitsClientView() {
		
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		visits = visitdao.listAllVisits();
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
		
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		return visitdao.get(id);
	}
	
	public Visit retrieveLastVisit() {
		VisitsDAOImpl visitdao = new VisitsDAOImpl();
		
		Visit visit =  new Visit();
		
		visit = visitdao.getLastVisit();
		
		return visit;
	}
	
	public void addGoods (List<VisitGood> visitgoodse) {
		
		GoodService gservice = new GoodService();
		
		gservice.addVisitGoods(visitgoodse);
	}
	
	public List<VisitGood> retrieveVisitGoods() {
		return visitgoods;
	}
	
}

