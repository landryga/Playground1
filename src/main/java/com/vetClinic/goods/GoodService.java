package com.vetClinic.goods;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.ConsultationDAOimpl;
import com.vetClinic.DAO.GoodDAOimpl;
import com.vetClinic.consultation.Consultation;
import com.vetClinic.visits.VisitGood;

@Service
public class GoodService {
	
	private static List<Good> goods = new ArrayList<Good>();
	
	public void addGood(Good good) {
		
		GoodDAOimpl gooddao = new GoodDAOimpl();
		
		gooddao.addGood(good);
	}
	
	
	
	public void addVisitGood(VisitGood visitgood) {
		
		GoodDAOimpl gooddao = new GoodDAOimpl();
		
		gooddao.addVisitGood(visitgood);
	}
	
	public void addVisitGoods(List<VisitGood> vgs) {
		
		GoodDAOimpl gooddao = new GoodDAOimpl();
		
		gooddao.addVisitGoods(vgs);
	}
	
	public void updateGood(Good good) {
		GoodDAOimpl goodsdao = new GoodDAOimpl();
		
		goodsdao.updateGood(good);
	}
	
	public List<Good> retrieveGoods() {
		GoodDAOimpl goodsdao = new GoodDAOimpl();
		
		goods = goodsdao.list();
			
		return goods;
	}
	
	public Good retrieveGood(int good_id) {
		Good good = new Good();
		
		GoodDAOimpl goodsdao = new GoodDAOimpl();
		
		good = goodsdao.get(good_id);
		
		return good;
	}
	
	public Good retrieveGood(String name) {
		Good good = new Good();
		
		GoodDAOimpl goodsdao = new GoodDAOimpl();
		
		good = goodsdao.get(name);
		
		return good;
	}
	
	//TODO - change accordingly
	public Consultation retrieveConsultation(String consultation_name) {
		Consultation consultation = new Consultation();
		
		return consultation;
	}
}
