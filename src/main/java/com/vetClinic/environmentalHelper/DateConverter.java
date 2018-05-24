package com.vetClinic.environmentalHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	public static String convertFormat(String inputDate, String inputFormat) {
		
		Date outputDate = new Date();
		String outputString = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		SimpleDateFormat nsdf = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		
		try {
			outputDate = sdf.parse(inputDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		outputString = nsdf.format(outputDate);
		
		return outputString;
	}
	
	//Dlaformatowania daty uzywanej w fullcalendar
	public static String convertEventFormat(String inputDate, String inputFormat) {
		
		Date outputDate = new Date();
		String outputStringD = null;
		String outputStringH = null;
		
		String outputString;
		
		SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
		SimpleDateFormat nsdfD = new SimpleDateFormat("MM-dd-yyyy");
		SimpleDateFormat nsdfH = new SimpleDateFormat("hh:mm:ss");
		
		try {
			outputDate = sdf.parse(inputDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		outputString = nsdfD.format(outputDate)+"T"+nsdfH.format(outputDate);
		
		System.out.println(outputString);
		
		return outputString;
	}
	
	
	public static Date getFormatedDate(String inputDate, String inputFormat) {
		
		Date dt = new Date();
		
		SimpleDateFormat of = new SimpleDateFormat(inputFormat);
		
		try {
			dt = of.parse(inputDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
		
		
	}
	
}
