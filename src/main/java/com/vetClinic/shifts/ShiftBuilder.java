package com.vetClinic.shifts;

import java.text.SimpleDateFormat;
import java.util.List;

import com.vetClinic.environmentalHelper.DateParser;

public class ShiftBuilder {
	
	public String getString(List<Shift> shifts) {
		String shiftString = ", events: [ ";
		
		String dateFormat = "yyyy-MM-dd";
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		for(int i = 0; i< shifts.size(); i++ ) {
			
			Shift shift = shifts.get(i);
			
			shiftString+= " { ";
			shiftString+= " id : '" + shift.getId() + "',";
			shiftString+= " title : '" + shift.getUsername() + "',";
			shiftString+= " start : '" + DateParser.convertEventFormat(shift.getStart_date(), "MM/dd/yyyy hh:mm") + "'";
			if(shift.getEnd_date()!=null) {
				shiftString+= ", end : '" + DateParser.convertEventFormat(shift.getEnd_date(), "MM/dd/yyyy hh:mm") + "'";
			}
			shiftString+=" } ";
			
			if(i<shifts.size()-1)
			{
				shiftString+=" , ";
			}
		}
		
		shiftString+=" ]"
				+ ", eventRender: function(event, element) {\r\n" + 
				"   element.html(event.title + '<span class=\"removeEvent glyphicon glyphicon-trash pull-right\" id='+event.id+'  onclick=\"myFunction('+event.id+')\"\"></span>');\r\n" + 
				"} ";
		
		System.out.println(shiftString);
		
		return shiftString;
		
	}
	
}
