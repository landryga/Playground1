package com.vetClinic.shifts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.environmentalHelper.DateParser;
import com.vetClinic.goods.Good;

@Controller
@SessionAttributes("name")
public class ShiftController {

	@Autowired
	ShiftService service;
	
	@Autowired
	private MessageSource messageSource;
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));   
	}
	
	@RequestMapping(value="/webservice/list-shifts", method = RequestMethod.POST) 
	public String addShift(ModelMap model, @Valid Shift shift, BindingResult result) {
		
		String message =  "";
		
		String helper = "";
		
		helper  = result.getRawFieldValue("user_id").toString();
		
		if(result.hasErrors()||!service.compareDates(shift)) {
			for (Object object : result.getAllErrors()) {
				if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;

			        message = messageSource.getMessage(fieldError, null);
			    }
			}
			if(!service.compareDates(shift)) {
				message = "Start date must be before end date!";
			}
			AdminService usr = new AdminService();
			model.addAttribute("message", message);
			List<UserMaintainer> users = usr.retrieveUsers();
			model.addAttribute("users", users);
			model.addAttribute("shift", shift);
			return "/webservice/list-shifts";
		}
		
		
		System.out.println(shift.getEnd_date());
		
		service.addShift(shift);
		model.clear();
		return "redirect:list-shifts";
	}
	
	@RequestMapping(value="/webservice/list-shifts", method = RequestMethod.GET) 
	public String listShifts (ModelMap model){
		
		String events = null;
		
		System.out.println("dsdfd: ");
		
		List<Shift> listo = service.retrieveShifts();
		
		ShiftBuilder bld = new ShiftBuilder();
		if(listo!=null) {
			events = bld.getString(listo);
		}
		
		AdminService usr = new AdminService();
		
		List<UserMaintainer> users = usr.retrieveUsers();
		model.addAttribute("users", users);
		
		model.addAttribute("data",  events);
		
		model.addAttribute("shift",  new Shift());
		
		return "/webservice/list-shifts";
	}
	
	@RequestMapping(value="/webservice/{id}", method = RequestMethod.GET) 
	public String deleteShift(ModelMap model, @PathVariable("id")int id) {
		
		
		service.deleteShift(id);
		
		model.clear();
		
		return "redirect:/webservice/list-shifts";
	}
	
	
}
