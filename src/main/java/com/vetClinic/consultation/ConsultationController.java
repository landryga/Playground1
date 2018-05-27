package com.vetClinic.consultation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vetClinic.DAO.ConsultationDAOimpl;
import com.vetClinic.DAO.UsersDAOimpl;
import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.mail.MailHandler;
import com.vetClinic.visits.Visit;

@Controller 
public class ConsultationController {
	
	@Autowired
	ConsultationService service;
	
	@RequestMapping(value="/consultations", method = RequestMethod.GET) 
	public String showmMainPanel (ModelMap model) {
		model.addAttribute("consultations",service.retrieveConsultations());
		return "/webservice/consultations";
	}
	
	@RequestMapping(value="/consultation-add", method = RequestMethod.GET) 
	public String showAddConsultationPage (ModelMap model) {
		model.addAttribute("consultation", new Consultation());
		return "/webservice/consultation-add";
	}
	
	@RequestMapping(value="/consultation-add", method = RequestMethod.POST) 
	public String addConsultation(ModelMap model, @Valid Consultation consultation, BindingResult result) {
		
		if(result.hasErrors()) {
			return "consultation-add";
		}
		
		service.addConsultation(consultation);
		model.clear();
		return "redirect:consultations";
	}
	
	@RequestMapping(value="/consultation-update", method = RequestMethod.GET) 
	public String showUpdateConsultationPage (ModelMap model, @RequestParam int consultation_id) {

		Consultation consultation = new Consultation();
		
		consultation = service.retrieveConsultation(consultation_id);

		model.addAttribute("consultation", consultation);
		
		return "consultation-update";
	}
	
	@RequestMapping(value="/consultation-update", method = RequestMethod.POST) 
	public String updateConsultation(ModelMap model, @Valid Consultation consultation, BindingResult result) {
		
		if(result.hasErrors()) {
			return "consultation-update";
		}
		
		service.updateConsultation(consultation);
		model.addAttribute("consultation_name", consultation.getConsultation_name());
		model.addAttribute("price", consultation.getPrice());
		
		model.clear();
		
		return "redirect:consultations";
	}

}
