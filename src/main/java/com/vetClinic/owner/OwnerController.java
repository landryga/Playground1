package com.vetClinic.owner;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.patients.Patient;
import com.vetClinic.patients.PatientService;

@Controller 
@SessionAttributes({"owner_id", "name"})
public class OwnerController {

	@Autowired
	OwnerService service;

	@RequestMapping(value="/list-owners", method = RequestMethod.GET) 
	public String listOwners (ModelMap model) {
		model.addAttribute("owners", service.retrieveOwners());
		return "list-owners";
	}
	
	
	@RequestMapping(value="/owner-add", method = RequestMethod.GET) 
	public String showAddUserPage (ModelMap model) {
		model.addAttribute("owner", new Owner());
		return "owner-add";
	}
	
	@RequestMapping(value="/owner-add", method = RequestMethod.POST) 
	public String addOwner(ModelMap model, @Valid Owner owner, BindingResult result/*, HttpServletRequest request*/) {
		
		if(result.hasErrors()) {
			return "owner-add";
		}
		
		service.addOwner(owner);
		
		int owner_id = owner.getOwner_id();
		
		model.addAttribute("owner_id", owner_id);
		
		return "redirect:/patient-add";
	}
	
	
	@RequestMapping(value="/update-owner", method = RequestMethod.GET)
	public String updatePatient( ModelMap model, @RequestParam int id) {
		/*
		UserMaintainer user = service.retrieveUser(id);
		model.addAttribute("user", user);
		*/
		return "redirect:list-owners";
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
