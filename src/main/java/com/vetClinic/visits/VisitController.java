package com.vetClinic.visits;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
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
import com.vetClinic.goods.Good;
import com.vetClinic.goods.GoodService;
import com.vetClinic.patients.Patient;
import com.vetClinic.patients.PatientService;

@Controller 
@SessionAttributes("name")
public class VisitController {
	
	@Autowired
	VisitService service;
	
	private static List<VisitGood> visitgoods = new ArrayList<VisitGood>();
	private static String visit_description = null;
	
	
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));   
	}

	@RequestMapping(value="/list-visits", method = RequestMethod.GET) 
	public String listVisits (ModelMap model, @RequestParam int patient_id) {
		
		visitgoods.clear();
		
		visit_description = null;
		
		PatientService patientService = new PatientService();
		
		Patient patient = new Patient();
		
		patient = patientService.retrievePatient(patient_id);
		
		model.addAttribute("patient_id", patient_id);
		
		String patient_name = patient.getPatient_name();
		
		model.addAttribute("patient_name", patient_name);
		
		AdminService admin = new AdminService();
		
		String username = retrieveLoggedinUser();
		
		UserMaintainer usr = admin.retrieveUser(username);
		
		model.addAttribute("doctor_id", usr.getDoctor_id());
		
		model.addAttribute("visits", service.retrieveVisits(patient_id));
		return "list-visits";
	}
	
	@RequestMapping(value="/list-user-visits", method = RequestMethod.GET) 
	public String listUserVisits (ModelMap model) {
		
		visitgoods.clear();
		visit_description = null;
		
		AdminService admin = new AdminService();
		
		UserMaintainer usr = admin.retrieveUser(retrieveLoggedinUser());
		
		model.addAttribute("doctor_id", usr.getDoctor_id());
		model.addAttribute("visits", service.retrieveVisits(usr.getUsername()));
		return "list-visits";
	}
	
	@RequestMapping(value="/visit-add", method = RequestMethod.GET) 
	public String showAddVisitPage (ModelMap model, @RequestParam int patient_id, @RequestParam int doctor_id, @RequestParam int visitId) {
		
		
		
		model.clear();
		
		int final_visit_id = 0;
		
		Visit visit = new Visit();
		
		GoodService gservice = new GoodService();
		
		List<Good> goodslist = new ArrayList<Good>();
		
		goodslist = gservice.retrieveGoods();
		
		List<String> goodnames = new ArrayList<String>();
		
		for(Good good : goodslist) {
			goodnames.add(good.getName());
			System.out.println("bleble" + good.getName());
		}
		
			model.addAttribute("good_name", goodnames);
		
		String name = "";
		
		model.addAttribute("patient_id", patient_id);
		
		model.addAttribute("name", name);
		
		System.out.println("This is visit description" + visit_description);
		
		visit.setVisit_description(visit_description);
		
		model.addAttribute("visit_description", visit_description);
		
		model.addAttribute("visitgoods", visitgoods);
		
		
		
		
		VisitGood visitgood = new VisitGood();
		
		visit.setDoctor_id(doctor_id);
		visit.setPatient_id(patient_id);
		visit.setName(name);
		
		if(visitId==0) {
			final_visit_id = service.addVisit(visit);
			visitId = final_visit_id;
		}
		
		visit.setVisitId(visitId);
		
		model.addAttribute("visitId", visitId);
		model.addAttribute("doctor_id", doctor_id);
		
		System.out.println("visit id is" + visit.getVisitId());
		
		System.out.println("visitId is" + visitId);
		
		if(doctor_id!=0) {
			
			model.addAttribute("visit", visit);
			
			model.addAttribute("visitgood", visitgood);
			model.addAttribute("visitgood_name");
			
			System.out.println("final visit id3 is" + visit.getVisitId());
			
			return "visit-add";
		}
		
		model.addAttribute("errormessage", "Only doctor can add a visit!");
		
		return "list-visits";
		
	}
	
	@RequestMapping(value="/visit-add", method = RequestMethod.POST) 
	public String addVisit(ModelMap model, @Valid Visit visit, BindingResult result) {
		
		
		Visit visit_reference = new Visit();
		
		visit_reference = service.retrieveLastVisit();
		
		if(result.hasErrors()) {
			return "visit-add";
		}
		
		String comparator = null;
		
		comparator = result.getRawFieldValue("test").toString();
		
		if(comparator.equals("1")) {
			model.clear();
			
			String good_name = (String)result.getFieldValue("good_name");
			visit_description = visit.getVisit_description();
			
			VisitGood vg = new VisitGood();
			vg.setName(good_name);
			vg.setQty(visit.getQty());
			vg.setVisit_id(visit_reference.getVisitId());
			
			GoodService gs = new GoodService();
			
			Good good = gs.retrieveGood(vg.getName());
			
			vg.setId(good.getId());
			
			visitgoods.add(vg);
			
			
			return "redirect:visit-add?visitId=" + visit_reference.getVisitId() + "&doctor_id=" + visit.getDoctor_id() + "&patient_id=" + visit.getPatient_id();
		}
		
		visit.setActive(false);

		service.updateVisit(visit_reference);
		
		for(VisitGood visitgood : visitgoods) {
			
			service.addGood(visitgood);
		}
		
		visitgoods.clear();
		
		visit_description = null;
		
		return "redirect:list-visits?patient_id=" + visit.getPatient_id();
	}
	
	@RequestMapping(value="/visit-update", method = RequestMethod.GET) 
	public String showUpdateVisitPage (ModelMap model, @RequestParam int visitId, @RequestParam int doctor_id) {
		
		model.addAttribute("visitId", visitId);
		
		if(doctor_id!=0) {
			model.addAttribute("visit", new Visit());
			
			return "visit-update";
		}
		
		
		model.addAttribute("doctor_id", doctor_id);
		model.addAttribute("errormessage", "Only doctor can add a visit!");
		
		return "list-visits";
		
	}
	
	@RequestMapping(value="/visit-update", method = RequestMethod.POST) 
	public String updateVisit(ModelMap model, @Valid Visit visit, BindingResult result) {
		
		if(result.hasErrors()) {
			return "visit-schedule";
		}
		
		visit.setActive(false);

		service.updateVisit(visit);
		
		model.addAttribute("patient_id", visit.getPatient_id());
		
		model.clear();
		
		return "redirect:list-visits?patient_id=" + visit.getPatient_id();
	}
	
	@RequestMapping(value="/visit-view", method = RequestMethod.GET) 
	public String showViewVisitPage (ModelMap model, @RequestParam int visitId, @RequestParam int doctor_id) {
		
		model.addAttribute("visitId", visitId);
		
		Visit visit = new Visit();
		
		visit = service.retrieveVisit(visitId);
		
		
		
		if(doctor_id!=0) {
			model.addAttribute("visit",visit);
			return "visit-view";
		}
		
		model.addAttribute("doctor_id", doctor_id);
		model.addAttribute("errormessage", "Only doctor can view a visit!");
		
		return "list-visits";
	}
	
	@RequestMapping(value="/visit-view", method = RequestMethod.POST) 
	public String viewVisit(ModelMap model, @Valid Visit visit, BindingResult result) {
		
		model.addAttribute("patient_id", visit.getPatient_id());
		
		model.clear();
		
		model.addAttribute("visit_description", visit.getVisit_description());
		
		return "redirect:list-visits?patient_id=" +  visit.getPatient_id();
		
	}
	
	@RequestMapping(value="/visit-schedule", method = RequestMethod.GET) 
	public String showScheduleVisitPage (ModelMap model, @RequestParam int patient_id) {
		
		model.addAttribute("visit", new Visit());
		
		AdminService admin = new AdminService();
		
		List<UserMaintainer> doctor_list = admin.retrieveDoctors();
		
		List<String> names_list = new ArrayList<String>();
		
		for(UserMaintainer userMaintainer : doctor_list) {
			names_list.add(userMaintainer.getUsername().toString());
		}
		
		model.addAttribute("doctor_name", names_list);
		
		model.addAttribute("patient_id", patient_id);
		
		return "visit-schedule";
	}
	
	@RequestMapping(value="/visit-schedule", method = RequestMethod.POST) 
	public String scheduleVisit(ModelMap model, @Valid Visit visit, BindingResult result) {
		
		if(result.hasErrors()) {
			return "visit-schedule";
		}
		
		AdminService adminService = new AdminService();
		
		String doctor_name = (String)result.getFieldValue("doctor_name");
		
		UserMaintainer usr = adminService.retrieveUser(doctor_name);
		
		visit.setDoctor_id(usr.getDoctor_id());
		
		service.scheduleVisit(visit);
		
		model.clear();
		
		model.addAttribute("patient_id", visit.getPatient_id());
		
		return "redirect:list-visits";
	}

	private String retrieveLoggedinUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		return username;
	}
	
}
