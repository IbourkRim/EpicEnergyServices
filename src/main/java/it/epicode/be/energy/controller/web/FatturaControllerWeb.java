package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.service.FatturaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class FatturaControllerWeb {
	
	@Autowired
	FatturaService fatturaService;
	
	/*@Autowired
	CorsoDiLaureaService corsoService;*/

	@GetMapping("/fatture/mostraelenco")
	public ModelAndView mostraElencoFatture() {
		log.info("Test elenco fatture su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencofatture"); //nome html
		view.addObject("listaFatture",fatturaService.findAll());
		//model.addAttribute("listaStudenti", studenteService.findAll());
		return view;
	}
}
