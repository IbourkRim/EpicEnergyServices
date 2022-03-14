package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import it.epicode.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class IndirizzoControllerWeb {

	@Autowired
	IndirizzoService indirizzoService;
	
	/*@Autowired
	CorsoDiLaureaService corsoService;*/

	@GetMapping("/indirizzi/mostraelenco")
	public ModelAndView mostraElencoIndirizzi() {
		log.info("Test elenco indirizzi su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoindirizzi"); //nome html
		view.addObject("listaIndirizzi",indirizzoService.findAll());
		//model.addAttribute("listaStudenti", studenteService.findAll());
		return view;
	}
}
