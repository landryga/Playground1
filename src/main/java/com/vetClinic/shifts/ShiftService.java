package com.vetClinic.shifts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.ShiftsDAOimpl;


@Service
public class ShiftService {
	
	private static List<Shift> shifts = new ArrayList<Shift>();
	

	public int addShift(Shift shift) {
		return 0;
	}
	
	public void updateShift(Shift shift) {
		
	}
	
	public List<Shift> retrieveShifts() {
		
		ShiftsDAOimpl shiftsdao = new ShiftsDAOimpl();
		
		shifts = shiftsdao.list();
		return shifts;
	}
	
	
	
	
}
