package com.vetClinic.goods;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vetClinic.consultation.Consultation;
import com.vetClinic.consultation.ConsultationService;


@Controller
public class GoodContoller {
	
	@Autowired
	GoodService service;
	
	@RequestMapping(value="/goods", method = RequestMethod.GET) 
	public String showmMainPanel (ModelMap model) {
		model.addAttribute("goods",service.retrieveGoods());
		return "goods";
	}
	
	@RequestMapping(value="/good-add", method = RequestMethod.GET) 
	public String showAddConsultationPage (ModelMap model) {
		model.addAttribute("good", new Good());
		return "good-add";
	}
	
	@RequestMapping(value="/good-add", method = RequestMethod.POST) 
	public String addGood(ModelMap model, @Valid Good good, BindingResult result) {
		
		if(result.hasErrors()) {
			return "good-add";
		}
		
		service.addGood(good);
		model.clear();
		return "redirect:goods";
	}
	
	@RequestMapping(value="/good-update", method = RequestMethod.GET) 
	public String showUpdateGoodPage (ModelMap model, @RequestParam int id) {

		Good good = new Good();
		
		good = service.retrieveGood(id);

		model.addAttribute("good", good);
		
		return "good-update";
	}
	
	@RequestMapping(value="/good-update", method = RequestMethod.POST) 
	public String updateGood(ModelMap model, @Valid Good good, BindingResult result) {
		
		if(result.hasErrors()) {
			return "consultation-update";
		}
		
		service.updateGood(good);
		model.addAttribute("name", good.getName());
		model.addAttribute("price", good.getPrice());
		
		model.clear();
		
		return "redirect:goods";
	}
}
