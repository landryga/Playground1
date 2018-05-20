package com.vetClinic.DAO;

import java.util.List;

import com.vetClinic.visits.Visit;

public interface VisitsDAO {
    public Visit get(int visit_id);
    
    public Visit getLastVisit();
    
    public List<Visit> list();
    
    public List<Visit> list(int patient_id);
    
    public List<Visit> list(String doctor_name);

	public int addVisit(Visit visit);
	
	void scheduleVisit(Visit visit);
	
	void updateVisit(Visit visit);
	
	void updateGoodsUsage(Visit visit);
	
}
