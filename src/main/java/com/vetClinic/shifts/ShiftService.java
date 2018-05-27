package com.vetClinic.shifts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.ShiftsDAOimpl;
import com.vetClinic.environmentalHelper.DateParser;


@Service
public class ShiftService {
	
	private static List<Shift> shifts = new ArrayList<Shift>();
	

	public int addShift(Shift shift) {
		
		ShiftsDAOimpl shiftDAO = new ShiftsDAOimpl();
		
		shiftDAO.addShift(shift);
		
		return 0;
	}
	
	public void updateShift(Shift shift) {
		
	}
	
	public boolean compareDates(Shift shift) {
		Date start = new Date();
		Date end = new Date();
		
		start = DateParser.getFormatedDate(shift.getStart_date(), "MM/dd/yyyy HH:mm");
		end = DateParser.getFormatedDate(shift.getEnd_date(), "MM/dd/yyyy HH:mm");
		
		if(end.before(start)) {
			return false;
		}
		
		return true;
	}
	
	public List<Shift> retrieveShifts() {
		
		ShiftsDAOimpl shiftsdao = new ShiftsDAOimpl();
		
		shifts = shiftsdao.list();
		return shifts;
	}
	
	public List<Shift> retrieveDoctorsShifts() {
		
		ShiftsDAOimpl shiftsdao = new ShiftsDAOimpl();
		
		shifts = shiftsdao.listDoctorsShifts();
		return shifts;
	}
	
	public void deleteShift(int shiftId) {
		ShiftsDAOimpl shiftsdao = new ShiftsDAOimpl();
		
		shiftsdao.deleteShift(shiftId);
	}
	
	
	
	
}
