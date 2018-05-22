package com.vetClinic.shifts;

import java.text.SimpleDateFormat;
import java.util.List;

public class ShiftBuilder {
	
	public String getString(List<Shift> shifts) {
		String shiftString = ", events: [ ";
		
		String dateFormat = "yyyy-MM-dd";
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		for(int i = 0; i< shifts.size(); i++ ) {
			
			Shift shift = shifts.get(i);
			
			shiftString+= " { ";
			shiftString+= " title : '" + shift.getUsername() + "',";
			shiftString+= " start : '" + sdf.format(shift.getStart_date()) + "'";
			if(shift.getEnd_date()!=null) {
				shiftString+= ", end : '" + sdf.format(shift.getEnd_date()) + "'";
			}
			
			
			shiftString+=" } ";
			
			if(i<shifts.size()-1)
			{
				shiftString+=" , ";
			}
		}
		
		shiftString+=" ] ";
		
		System.out.println(shiftString);
		
		return shiftString;
		
	}
	
}
