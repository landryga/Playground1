package com.vetClinic.external_requests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vetClinic.admin.AdminService;
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.mail.MailHandler;
import com.vetClinic.owner.Owner;
import com.vetClinic.owner.OwnerService;
import com.vetClinic.patients.Patient;
import com.vetClinic.patients.PatientService;
import com.vetClinic.shifts.Shift;
import com.vetClinic.shifts.ShiftBuilder;
import com.vetClinic.shifts.ShiftService;
import com.vetClinic.visits.Visit;
import com.vetClinic.visits.VisitBuilder;
import com.vetClinic.visits.VisitService;


@Controller
public class ExternalController {
	
	@Autowired
	private MessageSource messageSource;
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH");
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));   
	}
	
	@RequestMapping(value="/external", method = RequestMethod.GET)
	public String showPage(ModelMap model) {
		
			return "/external";
		
	}
	
	@RequestMapping(value="/client-consultation", method = RequestMethod.GET)
	public String showConsultations(ModelMap model) {
		
		String events = null;
		
		VisitService vstService = new VisitService();
		
		List<Visit> listo = vstService.retrieveVisitsClientView();
		
		VisitBuilder bld = new VisitBuilder();
		if(listo!=null) {
			events = bld.getString(listo, true);
		}
		
		AdminService usr = new AdminService();
		
		List<UserMaintainer> users = usr.retrieveDoctors();
		model.addAttribute("users", users);
		
		model.addAttribute("data",  events);
		
		model.addAttribute("visit",  new Visit());
		
		return "/client-consultation";
		
	}
	
	
	
	@RequestMapping(value="/client-consultation", method = RequestMethod.POST) 
	public String addShift(ModelMap model, @Valid Visit visit, BindingResult result) {
		
		VisitService service = new VisitService();
		OwnerService oService = new OwnerService();
		PatientService pService = new PatientService();
		
		String message =  "";
		
		String helper = "";
		
		helper  = result.getRawFieldValue("doctor_id").toString();
		
		if(result.hasErrors()) {
			for (Object object : result.getAllErrors()) {
				if(object instanceof FieldError) {
			        FieldError fieldError = (FieldError) object;
			        message = messageSource.getMessage(fieldError, null);
			    }
			}
			
			AdminService usr = new AdminService();
			model.addAttribute("message", message);
			List<UserMaintainer> users = usr.retrieveUsers();
			model.addAttribute("users", users);
			model.addAttribute("visit", visit);
			return "/client-consultation";
		}
		
		if(!service.checkVisit(visit)) {
			message = "Wybierz wolny termin w trakcie dyzuru lekarza ";
			model.addAttribute("message", message);
			
			AdminService usr = new AdminService();
			
			List<UserMaintainer> users = usr.retrieveDoctors();
			
			VisitService vstService = new VisitService();
			
			List<Visit> listo = vstService.retrieveVisitsClientView();
			
			String events = null;
			
			VisitBuilder bld = new VisitBuilder();
			
			if(listo!=null) {
				events = bld.getString(listo, true);
			}
			
			model.addAttribute("users", users);
			model.addAttribute("visit", visit);
			model.addAttribute("data",  events);
			return "/client-consultation";
		} else {
			
			Owner owner = new Owner();
			Patient patient = new Patient();
			
			owner.setEmail(visit.getEmail());
			patient.setPatient_name(visit.getPatient_name());
			
			//check if there is already owner with given email
			Owner owner_reference = oService.retrieveOwner(visit.getEmail());
			
			if(!owner_reference.equals(null)) {
				patient.setOwner_id(owner_reference.getOwner_id());
			} else {
				int client_id = oService.addOwnerInt(owner);
				patient.setOwner_id(client_id);
			}
			
			int patient_id = pService.addPatientInt(patient);
			
			visit.setPatient_id(patient_id);
			
			service.scheduleVisit(visit);
			
			MailHandler handler = new MailHandler();
			
			AdminService adm = new AdminService();
			
			
			try {
				handler.send("testdevpw@gmail.com", visit.getEmail(), "Data wizyty: " + visit.getVisit_date() + ", Lekarz:" + adm.retrieveUser(visit.getDoctor_id()).getUsername() + adm.retrieveUser(visit.getDoctor_id()).getUserSurName()  , "VetPrzychodnia: Zapisano na wizyte pacjenta " + visit.getPatient_name());
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		model.clear();
		return "redirect:client-consultation";
	}
	
	@RequestMapping(value="/clients-shifts", method = RequestMethod.GET)
	public String showDoctorsShifts(ModelMap model) {
		
		String events = null;
		
		ShiftService shftSrv = new ShiftService();
		
		List<Shift> listo = shftSrv.retrieveDoctorsShifts();
		
		ShiftBuilder bld = new ShiftBuilder();
		if(listo!=null) {
			events = bld.getString(listo);
		}
		
		AdminService usr = new AdminService();
		
		List<UserMaintainer> users = usr.retrieveDoctors();
		model.addAttribute("users", users);
		
		model.addAttribute("data",  events);
		
		return "/clients-shifts";
		
	}
	
}
