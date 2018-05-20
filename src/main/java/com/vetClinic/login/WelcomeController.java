package com.vetClinic.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.visits.VisitService;

@Controller 
@SessionAttributes("name")
public class WelcomeController {
	
	@Autowired
	VisitService service;
	
	private String retrieveLoggedinUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails)
			return ((UserDetails)principal).getUsername();
		
		return principal.toString();
	}

	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		
		model.addAttribute("name", retrieveLoggedinUser());
		
		UserMaintainer user = new UserMaintainer();
		
		AdminService admin = new AdminService();
		
		user = admin.retrieveUser(retrieveLoggedinUser());
		
		if(user.isIs_doctor()) {
			
			model.addAttribute("doctor_id", user.getDoctor_id());
			
			model.addAttribute("visits", service.retrieveVisits(retrieveLoggedinUser()));
			return "welcome_doctor";
		}
		
		return "welcome_user";
	}
	
}
