package com.vetClinic.shifts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.goods.Good;

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
	
	@RequestMapping(value="/shift-add", method = RequestMethod.POST) 
	public String addGood(ModelMap model, @Valid Shift shift, BindingResult result) {
		
		if(result.hasErrors()) {
			return "list-shifts";
		}
		
		service.addShift(shift);
		model.clear();
		return "redirect:list-shifts";
	}
	
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
		
		model.addAttribute("shift",  new Shift());
		
		return "list-shifts";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET) 
	public String deleteShift(ModelMap model, @PathVariable("id")int id) {
		
		
		service.deleteShift(id);
		
		model.clear();
		
		return "redirect:list-shifts";
	}
	
	
}
