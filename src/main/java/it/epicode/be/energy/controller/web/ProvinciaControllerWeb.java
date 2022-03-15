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

	@GetMapping("/provincia/mostraformaggiungi")
	public String mostraFormAggiungi(Provincia provincia, Model model) {
		log.info("Test form aggiungi provincia");
		model.addAttribute("listaIndirizzi", indirizzoService.findAll());
		return "formProvincia";
	}

	@PostMapping("/addProvincia")
	public String aggiungiProvincia(@Valid Provincia provincia, BindingResult result, Model model) {
		log.info("aggiungi provincia");
		if(result.hasErrors()) {
			model.addAttribute("listaIndirizzi", indirizzoService.findAll());
			return "addProvincia";
		}
		provinciaService.save(provincia);
		return "redirect:/web/province/mostraelenco";
	}

	@GetMapping("/provincia/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
	log.info("Test mostra form aggiorna provincia");
	Optional<Provincia> provinciaTemp =  provinciaService.findById(id);
	if(provinciaTemp.isPresent()) {
		ModelAndView view = new ModelAndView("editProvincia");
		view.addObject("provincia", provinciaTemp.get());
		view.addObject("listaIndirizzi", indirizzoService.findAll());
		return view;
	}
	
	
	return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornaProvincia/{id}")
	public String aggiornaProvincia(@PathVariable Long id, Provincia provincia, BindingResult result, Model model) {
		provinciaService.update(provincia,id);
		
		log.info("Provincia aggiorna");
	
		return "redirect:/web/mostraelenco";
	}

	@GetMapping("/eliminaprovincia/{id}")
	public ModelAndView eliminaProvincia(@PathVariable Long id, Model model) {
		Optional<Provincia> provinciaDaEliminare =provinciaService.findById(id);
		if(provinciaDaEliminare.isPresent()) {
			provinciaService.delete(provinciaDaEliminare.get().getId());
			ModelAndView view = new ModelAndView("elencoprovince");
			view.addObject("listaProvince",provinciaService.findAll());
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}
		
	}


}
