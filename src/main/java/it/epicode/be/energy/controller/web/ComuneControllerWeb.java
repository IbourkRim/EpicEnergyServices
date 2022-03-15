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
		//model.addAttribute("listaStudenti", studenteService.findAll());
		return view;
	}

	@GetMapping("/comune/mostraformaggiungi")
	public String mostraFormAggiungi(Comune comune, Model model) {
		log.info("Test form aggiungi comune");
		model.addAttribute("listaProvince", provinciaService.findAll());
		return "formComune";
	}

	@PostMapping("/addComune")
	public String aggiungiComune(@Valid Comune comune, BindingResult result, Model model) {
		log.info("aggiungi comune");
		if(result.hasErrors()) {
			model.addAttribute("listaProvince", provinciaService.findAll());
			return "addComune";
		}
		comuneService.save(comune);
		return "redirect:/web/comuni/mostraelenco";
	}

	@GetMapping("/comune/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
	log.info("Test mostra form aggiorna comune");
	Optional<Comune> comuneTemp =  comuneService.findById(id);
	if(comuneTemp.isPresent()) {
		ModelAndView view = new ModelAndView("editComune");
		view.addObject("comune", comuneTemp.get());
		view.addObject("listaProvince", provinciaService.findAll());
		return view;
	}
	
	
	return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornaComune/{id}")
	public String aggiornaComune(@PathVariable Long id, Comune comune, BindingResult result, Model model) {
		comuneService.update(comune,id);
		
		log.info("Comune aggiorna");
	
		return "redirect:/web/comuni/mostraelenco";
	}

	@GetMapping("/eliminacomune/{id}")
	public ModelAndView eliminaComune(@PathVariable Long id, Model model) {
		Optional<Comune> comuneDaEliminare =comuneService.findById(id);
		if(comuneDaEliminare.isPresent()) {
			comuneService.delete(comuneDaEliminare.get().getId());
			ModelAndView view = new ModelAndView("elencocomuni");
			view.addObject("listaComuni",comuneService.findAll());
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}
		
	}
}
