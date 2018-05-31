package com.vetClinic.visits;


import java.text.SimpleDateFormat;
import java.util.List;

import com.vetClinic.environmentalHelper.DateParser;

public class VisitBuilder {
	
	public String getString(List<Visit> visits, boolean forClients) {
		String visitString = ", events: [ ";
		
		
		for(int i = 0; i< visits.size(); i++ ) {
			
			Visit visit = visits.get(i);
			
			visitString+= " { ";
			visitString+= " id : '" + visit.getVisitId() + "',";
			if(!forClients) {
				visitString+= " title : '" + visit.getOwner_name() + "',";
			} else {
				visitString+= " title : '" + visit.getDoctor_name() + "',";
			}
			visitString+= " start : '" + DateParser.convertEventFormat(visit.getVisit_date(), "MM/dd/yyyy HH") + "',";
			visitString+= " duration : { hours:1 }, ";
			
			if(!visit.isActive()) {
				visitString+= " eventColor : 'grey'";
			}
			else{
				visitString+= " eventColor : 'red'";
			}
			visitString+=" } ";
			
			if(i<visits.size()-1)
			{
				visitString+=" , ";
			}
		}
		
		visitString+=" ]"
				+ ", eventRender: function(event, element) {\r\n" + 
				"   element.html(event.title + '<span class=\"removeEvent glyphicon glyphicon-trash pull-right\" id='+event.id+'  onclick=\"myFunction('+event.id+')\"\"></span>');\r\n" + 
				"} ";
		
		System.out.println(visitString);
		
		return visitString;
		
	}
	
}
