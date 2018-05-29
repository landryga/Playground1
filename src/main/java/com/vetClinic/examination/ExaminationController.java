package com.vetClinic.examination;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.patients.Patient;
import com.vetClinic.patients.PatientService;

@Controller 
@SessionAttributes("name")
public class ExaminationController {

	@Autowired
	ExaminationService service;
	
	private static List<Examination> examinations = new ArrayList<Examination>();
	
	
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));   
	}

	@RequestMapping(value="/webservice/list-examinations", method = RequestMethod.GET) 
	public String listExaminations (ModelMap model) {
		
		AdminService admin = new AdminService();
		
		String username = retrieveLoggedinUser();
		
		UserMaintainer usr = admin.retrieveUser(username);
		
		model.addAttribute("doctor_id", usr.getDoctor_id());
		
		model.addAttribute("examinations", service.retrieveExaminations());
		
		return "/webservice/list-examinations";
	}
	
	@RequestMapping(value="/webservice/list-patient-examinations", method = RequestMethod.GET) 
	public String listPatientExaminations (ModelMap model, @RequestParam int id_patient) {
		
		AdminService admin = new AdminService();
		
		String username = retrieveLoggedinUser();
		
		UserMaintainer usr = admin.retrieveUser(username);
		
		model.addAttribute("doctor_id", usr.getDoctor_id());
		
		model.addAttribute("id_patient", id_patient);
		
		model.addAttribute("examinations", service.retrieveExaminations(id_patient));
		
		return "/webservice/list-patient-examinations";
	}
	
	@RequestMapping(value="/webservice/examination-add-patient", method = RequestMethod.GET) 
	public String showAddExaminationPatientPage (ModelMap model, @RequestParam int doctor_id, @RequestParam int patient_id) {
		
		List<ExaminationType> et = new ArrayList<ExaminationType>();
		
		et = service.getExaminationTypes();
		
		PatientService ps = new PatientService();
		
		List<Patient> patients = new ArrayList<Patient>();
		
		patients = ps.retrievePatients();
		
		model.clear();
		
		model.addAttribute("patient_id", patient_id);
		
		int final_visit_id = 0;
		
		Examination examination = new Examination();
		
		
		model.addAttribute("et", et);
		
		model.addAttribute("doctor_id", doctor_id);
		
		if(doctor_id!=0) {
			
			model.addAttribute("examination", examination);
			
			return "/webservice/examination-add-patient";
		}
		else {
			model.addAttribute("errormessage", "Tylko lekarz moze dodawac wyniki badan!");
			
			return "/webservice/list-patient-examinations";
		}
		
	}
	
	@RequestMapping(value="/webservice/examination-add-patient", method = RequestMethod.POST) 
	public String addExaminationPatient(ModelMap model, @Valid Examination examination, BindingResult result) {
		
		System.out.println("Examination patient id is " + examination.getPatient_id());
		
		if(result.hasErrors()) {
			List<ObjectError> or = result.getAllErrors();
			
			for(int i = 0; i<or.size(); i++) {
				System.out.println(or.get(i));
			}
			
			return "/webservice/examination-add-patient";
		}
		
		service.addExamination(examination);
		
		examination.setActive(false);
			
			
		return "redirect:/webservice/list-examinations";
		
		
	}
	
	
	@RequestMapping(value="/webservice/examination-add", method = RequestMethod.GET) 
	public String showAddExaminationPage (ModelMap model, @RequestParam int doctor_id) {
		
		List<ExaminationType> et = new ArrayList<ExaminationType>();
		
		et = service.getExaminationTypes();
		
		PatientService ps = new PatientService();
		
		List<Patient> patients = new ArrayList<Patient>();
		
		patients = ps.retrievePatients();
		
		model.clear();
		
		int final_visit_id = 0;
		
		Examination examination = new Examination();
		
		
		model.addAttribute("et", et);
		model.addAttribute("patients", patients);
		model.addAttribute("doctor_id", doctor_id);
		
		if(doctor_id!=0) {
			
			model.addAttribute("examination", examination);
			
			return "/webservice/examination-add";
		}
		else {
			model.addAttribute("errormessage", "Tylko lekarz moze dodawac wyniki badan!");
			
			return "/webservice/list-examinations";
		}
		
	}
	
	@RequestMapping(value="/webservice/examination-add", method = RequestMethod.POST) 
	public String addExamination(ModelMap model, @Valid Examination examination, BindingResult result) {
		
		System.out.println("Examination patient id is " + examination.getPatient_id());
		
		if(result.hasErrors()) {
			
				System.out.println("kupa");
			
			
			return "/webservice/examination-add";
		}
		
		service.addExamination(examination);
		
		examination.setActive(false);
			
			
		return "redirect:/webservice/list-examinations";
		
		
	}
	
	
	@RequestMapping(value="/webservice/examination-view", method = RequestMethod.GET) 
	public String showViewExaminationPage (ModelMap model, @RequestParam int id) {
		
		Examination examination = new Examination();
		
		examination = service.retrieveExamination(id);
		
		AdminService admin = new AdminService();
		
		String username = retrieveLoggedinUser();
		
		UserMaintainer usr = admin.retrieveUser(username);
		
		if(usr.getDoctor_id()!=0) {
			model.addAttribute("examination",examination);
			return "/webservice/examination-view";
		}
		else {
			model.addAttribute("doctor_id", usr.getDoctor_id());
			model.addAttribute("errormessage", "Tylko lekarz moze zobaczyc wyniki!");
			
			return "/webservice/list-examinations";
		}
		
	}
	
	@RequestMapping(value="/webservice/examination-view", method = RequestMethod.POST) 
	public String viewExamination(ModelMap model, @Valid Examination examination, BindingResult result) {
		
		model.clear();
		
		model.addAttribute("result", examination.getResult());
		
		return "redirect:/webservice/list-examinations";
		
	}
	
	@RequestMapping(value="/webservice/examination-schedule", method = RequestMethod.GET) 
	public String showScheduleExaminationPage (ModelMap model) {
		
		model.addAttribute("examination", new Examination());
		
		PatientService pS = new PatientService();
		
		List<Patient> patients = pS.retrievePatients();
		
		model.addAttribute("patients", patients);
		
		return "/webservice/examination-schedule";
	}
	
	@RequestMapping(value="/webservice/examination-schedule", method = RequestMethod.POST) 
	public String scheduleExamination(ModelMap model, @Valid Examination examination, BindingResult result) {
		
		if(result.hasErrors()) {
			return "/webservice/examination-schedule";
		}
		
		service.scheduleExamination(examination);
		
		model.clear();
		
		model.addAttribute("patient_id", examination.getPatient_id());
		
		return "list-examinations";
	}

	private String retrieveLoggedinUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		System.out.println("username is " + username);
		return username;
	}
	
}
