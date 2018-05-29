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
	
	public boolean scheduleVisit(Visit visit);
	
	void closeVisit(Visit visit);
	
	void updateGoodsUsage(Visit visit);

	public List<Visit> listAllVisits();

	boolean checkVisit(Visit visit);

	void removeVisit(int id);
	
}
