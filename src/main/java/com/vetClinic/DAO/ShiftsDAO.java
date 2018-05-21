package com.vetClinic.DAO;

import java.util.List;

import com.vetClinic.shifts.Shift;


public interface ShiftsDAO {
	
	public Shift get(int shift_id);
    
    public List<Shift> list();
    
    public List<Shift> list(String doctor_name);

	public int addShift(Shift shift);
	
	void updateShift(Shift shift);

}
