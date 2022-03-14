package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class ProvinciaControllerWeb {

	@Autowired
	ProvinciaService provinciaService;
	
	/*@Autowired
	CorsoDiLaureaService corsoService;*/

	@GetMapping("/province/mostraelenco")
	public ModelAndView mostraElencoProvince() {
		log.info("Test elenco province su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoprovince"); //nome html
		view.addObject("listaProvince",provinciaService.findAll());
		//model.addAttribute("listaStudenti", studenteService.findAll());
		return view;
	}
}
