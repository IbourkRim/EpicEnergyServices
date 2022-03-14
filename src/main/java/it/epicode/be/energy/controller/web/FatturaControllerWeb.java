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
import it.epicode.be.energy.model.Fattura;
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

	/*@GetMapping("/fattura/mostraformaggiungi")
	public String mostraFormAggiungi(Fattura fattura, Model model) {
		log.info("Test form aggiungi fattura");
		model.addAttribute("listaCorsi", corsoService.findAll());
		return "formFattura";
	}

	@PostMapping("/addFattura")
	public String aggiungiFattura(@Valid Fattura fattura, BindingResult result, Model model) {
		log.info("aggiungi fattura");
		if(result.hasErrors()) {
			model.addAttribute("listaCorsi", corsoService.findAll());
			return "addFattura";
		}
		fatturaService.save(fattura);
		return "redirect:/web/fatture/mostraelenco";
	}

	@GetMapping("/fattura/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
	log.info("Test mostra form aggiorna fattura");
	Optional<Fattura> fatturaTemp =  fatturaService.findById(id);
	if(fatturaTemp.isPresent()) {
		ModelAndView view = new ModelAndView("editFattura");
		view.addObject("fattura", fatturaTemp.get());
		view.addObject("listaCorsi", corsoService.findAll());
		return view;
	}
	
	
	return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornaFattura/{id}")
	public String aggiornaFattura(@PathVariable Long id, Fattura fattura, BindingResult result, Model model) {
		fatturaService.update(fattura,id);
		
		log.info("Fattura aggiorna");
	
		return "redirect:/web/fatture/mostraelenco";
	}

	@GetMapping("/eliminastudente/{id}")
	public ModelAndView eliminaFattura(@PathVariable Long id, Model model) {
		Optional<Fattura> fatturaDaEliminare =fatturaService.findById(id);
		if(fatturaDaEliminare.isPresent()) {
			fatturaService.delete(fatturaDaEliminare.get().getId());
			ModelAndView view = new ModelAndView("elencofatture");
			view.addObject("listaFatture",fatturaService.findAll());
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}
		
	}*/


}
