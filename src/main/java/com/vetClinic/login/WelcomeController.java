package com.vetClinic.login;

import java.util.List;

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
import com.vetClinic.shifts.Shift;
import com.vetClinic.shifts.ShiftBuilder;
import com.vetClinic.shifts.ShiftService;
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
			return "/webservice/welcome_doctor";
		}
		
		else {
			ShiftService shfSvc = new ShiftService();
			
			String events = null;
			
			List<Shift> listo = shfSvc.retrieveShifts();
			
			ShiftBuilder bld = new ShiftBuilder();
			if(listo!=null) {
				events = bld.getString(listo);
			}
			
			AdminService usr = new AdminService();
			
			List<UserMaintainer> users = usr.retrieveUsers();
			
			model.addAttribute("data",  events);
			
			return "/webservice/welcome_user";
		}
		
	}
	
}
