package it.epicode.be.energy.controller.web;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import it.epicode.be.energy.model.Provincia;
import it.epicode.be.energy.service.IndirizzoService;
import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class ProvinciaControllerWeb {

	@Autowired
	ProvinciaService provinciaService;
	
	@Autowired
	IndirizzoService indirizzoService;
	

	@GetMapping("/province/mostraelenco")
	public ModelAndView mostraElencoProvince() {
		log.info("Test elenco province su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoprovince"); //nome html
		view.addObject("listaProvince",provinciaService.findAll());
		//model.addAttribute("listaStudenti", studenteService.findAll());
		return view;
	}


}
