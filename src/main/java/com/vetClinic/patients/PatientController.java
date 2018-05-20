package com.vetClinic.patients;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.owner.Owner;
import com.vetClinic.owner.OwnerService;

@Controller 
@SessionAttributes("name")
public class PatientController {

	@Autowired
	PatientService service;
	
	private String retrieveLoggedinUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails)
			return ((UserDetails)principal).getUsername();
		
		return principal.toString();
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));   
	}

	@RequestMapping(value="/list-patients", method = RequestMethod.GET) 
	public String listPatients (ModelMap model) {
		model.addAttribute("patients", service.retrievePatients());
		return "list-patients";
	}
	
	
	@RequestMapping(value="/patient-add", method = RequestMethod.GET) 
	public String showAddUserPage (ModelMap model) {
		model.addAttribute("patient", new Patient());
		return "patient-add";
	}
	
	@RequestMapping(value="/patient-add", method = RequestMethod.POST) 
	public String addPatient(ModelMap model, @Valid Patient patient, BindingResult result) {
		
		if(result.hasErrors()) {
			return "patient-add";
		}
		
		service.addPatient(patient);
		
		model.clear();
		
		return "redirect:list-patients";
		
	}
	
	
	@RequestMapping(value="/update-patient", method = RequestMethod.GET)
	public String updatePatient( ModelMap model, @RequestParam int id) {
		/*
		UserMaintainer user = service.retrieveUser(id);
		model.addAttribute("user", user);
		*/
		return "redirect:list-patients";
	}
	
	/*
	@RequestMapping(value="/update-user", method = RequestMethod.POST)
	public String updateToDo( ModelMap model, @Valid UserMaintainer user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user-add";
		}
		service.updateUser(user);
		
		return "redirect:list-patients";
	}

	*/
}
