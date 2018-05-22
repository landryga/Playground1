package com.vetClinic.shifts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class ShiftController {

	@Autowired
	ShiftService service;
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));   
	}
	/*
	@RequestMapping(value="/list-shifts", method = RequestMethod.POST)
	public @ResponseBody String listShifts() {
		
		System.out.println("dsdfd: ");
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		List<Shift> listo = service.retrieveShifts();
		
		ShiftBuilder bld = new ShiftBuilder();
		String events = bld.getString(listo);
	
		 
		if(listo!=null) {
			map.put("data",  events);
		}
		
		return events;
		
	}
	*/
	
	@RequestMapping(value="/list-shifts", method = RequestMethod.GET) 
	public String listShifts (ModelMap model){
		
		String events = null;
		
		System.out.println("dsdfd: ");
		
		List<Shift> listo = service.retrieveShifts();
		
		ShiftBuilder bld = new ShiftBuilder();
		if(listo!=null) {
			events = bld.getString(listo);
		}
		
		model.addAttribute("data",  events);
		
		return "list-shifts";
	}
	
	
}
