package it.epicode.be.energy.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.epicode.be.energy.service.ComuneService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class ComuneControllerWeb {
	
	@Autowired
	ComuneService comuneService;
	
	/*@Autowired
	CorsoDiLaureaService corsoService;*/

	@GetMapping("/comuni/mostraelenco")
	public ModelAndView mostraElencoComuni() {
		log.info("Test elenco comuni su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencocomuni"); //nome html
		view.addObject("listaComuni",comuneService.findAll());
		//model.addAttribute("listaStudenti", studenteService.findAll());
		return view;
	}
}
