package com.vetClinic.DAO;

import java.util.List;

import com.vetClinic.goods.Good;
import com.vetClinic.visits.VisitGood;

public interface GoodDAO {

	public void delete(int good_id);
    
    public Good get(String good_name);
    
    public Good get(int id);
     
    public List<Good> list();

	public void addGood(Good good);
	
	public void updateGood(Good good);
	
	public void updateGoodQuantity(Good good);

	public void addVisitGood(VisitGood visitgood);
}
