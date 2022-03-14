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
import it.epicode.be.energy.model.Indirizzo;
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


	/*@GetMapping("/indirizzo/mostraformaggiungi")
	public String mostraFormAggiungi(Indirizzo indirizzo, Model model) {
		log.info("Test form aggiungi indirizzo");
		model.addAttribute("listaCorsi", corsoService.findAll());
		return "formStudente";
	}

	@PostMapping("/addIndirizzo")
	public String aggiungiIndirizzo(@Valid Indirizzo indirizzo, BindingResult result, Model model) {
		log.info("aggiungi indirizzo");
		if(result.hasErrors()) {
			model.addAttribute("listaCorsi", corsoService.findAll());
			return "addIndirizzo";
		}
		indirizzoService.save(indirizzo);
		return "redirect:/web/indirizzi/mostraelenco";
	}

	@GetMapping("/indirizzo/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
	log.info("Test mostra form aggiorna indirizzo");
	Optional<Indirizzo> indirizzoTemp =  indirizzoService.findById(id);
	if(indirizzoTemp.isPresent()) {
		ModelAndView view = new ModelAndView("editIndirizzo");
		view.addObject("indirizzo", indirizzoTemp.get());
		view.addObject("listaCorsi", corsoService.findAll());
		return view;
	}
	
	
	return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornaIndirizzo/{id}")
	public String aggiornaIndirizzo(@PathVariable Long id, Indirizzo indirizzo, BindingResult result, Model model) {
		indirizzoService.update(indirizzo,id);
		
		log.info("Indirizzo aggiorna");
	
		return "redirect:/web/indirizzi/mostraelenco";
	}

	@GetMapping("/eliminastudente/{id}")
	public ModelAndView eliminaStudente(@PathVariable Long id, Model model) {
		Optional<Indirizzo> indirizzoDaEliminare =indirizzoService.findById(id);
		if(indirizzoDaEliminare.isPresent()) {
			indirizzoService.delete(indirizzoDaEliminare.get().getId());
			ModelAndView view = new ModelAndView("elencoindirizzo");
			view.addObject("listaIndirizzi",indirizzoService.findAll());
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}
		
	}*/


}
