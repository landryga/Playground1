package com.vetClinic.admin;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.todo.Todo;

@Controller 
@SessionAttributes("name")
public class AdminController {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	AdminService service;

	@RequestMapping(value="/webservice/admin", method = RequestMethod.GET) 
	public String listActions () {
		return "/webservice/admin";
	}
	
	@RequestMapping(value="/webservice/list-users", method = RequestMethod.GET)
	public String listUsers( ModelMap model) {
		model.addAttribute("users",service.retrieveUsers());
		return "/webservice/list-users";
	}
	
	@RequestMapping(value="/webservice/add-user", method = RequestMethod.GET) 
	public String showAddUserPage (ModelMap model) {
		model.addAttribute("user", new UserMaintainer());
		return "/webservice/add-user";
	}
	
	@RequestMapping(value="/webservice/add-user", method = RequestMethod.POST) 
	public String addUser(ModelMap model, @Valid UserMaintainer maintainer, BindingResult result) {
		String message =  "";
		
		if(result.hasErrors()) {
			for (Object object : result.getAllErrors()) {
				if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;

			        message = messageSource.getMessage(fieldError, null);
			    }
			}
			model.addAttribute("message", message);
			model.addAttribute("user", maintainer);
			return "/webservice/add-user";
		}
		service.addUser(maintainer);
		model.clear();
		return "redirect:admin";
	}
	
	@RequestMapping(value="/webservice/remove-user", method = RequestMethod.GET) 
	public String removeUser (ModelMap model, @RequestParam int id) {
		service.removeUser(id);

		return "redirect:admin";
	}
	
	
	
	@RequestMapping(value="/webservice/update-user", method = RequestMethod.GET)
	public String updateUser( ModelMap model, @RequestParam int id) {
		UserMaintainer user = service.retrieveUser(id);
		model.addAttribute("user", user);
		return "/webservice/add-user";
	}
	
	@RequestMapping(value="/webservice/update-user", method = RequestMethod.POST)
	public String updateToDo( ModelMap model, @Valid UserMaintainer user, BindingResult result) {
		
		service.updateUser(user);
		
		return "redirect:list-users";
	}
	
	@RequestMapping(value="/webservice/change-calendar", method = RequestMethod.GET) 
	public String changeCalendar () {
		return "/webservice/change-calendar";
	}
	
}
