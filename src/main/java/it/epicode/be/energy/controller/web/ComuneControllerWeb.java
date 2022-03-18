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

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.service.ComuneService;
import it.epicode.be.energy.service.ProvinciaService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class ComuneControllerWeb {
	
	@Autowired
	ComuneService comuneService;
	
	@Autowired
	ProvinciaService provinciaService;

	@GetMapping("/epicenergy")
	public String showView(Model model) {
		return "pagprincipale";
	}
	
	@GetMapping("/comuni/mostraelenco")
	public ModelAndView mostraElencoComuni() {
		log.info("Test elenco comuni su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencocomuni"); //nome html
		view.addObject("listaComuni",comuneService.findAll());
		return view;
	}

}
