package com.vetClinic.finances;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.patients.Patient;

@Controller
@SessionAttributes("name")
public class FinanceController {
	
	@Autowired
	FinanceService service;
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));   
	}

	@RequestMapping(value="/webservice/finance-report", method = RequestMethod.GET) 
	public String listVisits (ModelMap model) {
		
		model.addAttribute("finance_days", service.retrieveFinanceDays());
		
		return "/webservice/finance-report";
	}
}
