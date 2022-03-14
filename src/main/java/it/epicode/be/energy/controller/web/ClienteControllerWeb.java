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

import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;
import it.epicode.be.energy.service.IndirizzoService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/web")
public class ClienteControllerWeb {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	IndirizzoService indirizzoService;
	
	@GetMapping("/clienti/mostraelenco")
	public ModelAndView mostraElencoClienti() {
		log.info("Test elenco clienti su pagina Thymeleaf");
		ModelAndView view = new ModelAndView("elencoclienti"); //nome html
		view.addObject("listaClienti",clienteService.findAll());
		//model.addAttribute("listaStudenti", studenteService.findAll());
		return view;
	}

	@GetMapping("/cliente/mostraformaggiungi")
	public String mostraFormAggiungi(Cliente cliente, Model model) {
		log.info("Test form aggiungi cliente");
		model.addAttribute("listaIndirizzi", indirizzoService.findAll());
		return "formCliente";
	}

	@PostMapping("/addCliente")
	public String aggiungiCliente(@Valid Cliente cliente, BindingResult result, Model model) {
		log.info("aggiungi cliente");
		if(result.hasErrors()) {
			model.addAttribute("listaIndirizzi", clienteService.findAll());
			return "addCliente";
		}
		clienteService.save(cliente);
		return "redirect:/web/clienti/mostraelenco";
	}

	@GetMapping("/cliente/mostraformaggiorna/{id}")
	public ModelAndView mostraFormAggiorna(@PathVariable Long id, Model model) {
	log.info("Test mostra form aggiorna cliente");
	Optional<Cliente> clienteTemp =  clienteService.findById(id);
	if(clienteTemp.isPresent()) {
		ModelAndView view = new ModelAndView("editCliente");
		view.addObject("cliente", clienteTemp.get());
		view.addObject("listaIndirizzi", indirizzoService.findAll());
		return view;
	}
	
	
	return new ModelAndView("error").addObject("message", "id non trovato");
	}

	@PostMapping("/aggiornaCliente/{id}")
	public String aggiornaCliente(@PathVariable Long id, Cliente cliente, BindingResult result, Model model) {
		clienteService.update(cliente,id);
		
		log.info("Cliente aggiorna");
	
		return "redirect:/web/clienti/mostraelenco";
	}

	@GetMapping("/eliminacliente/{id}")
	public ModelAndView eliminaCliente(@PathVariable Long id, Model model) {
		Optional<Cliente> clienteDaEliminare =clienteService.findById(id);
		if(clienteDaEliminare.isPresent()) {
			clienteService.delete(clienteDaEliminare.get().getId());
			ModelAndView view = new ModelAndView("elencoclienti");
			view.addObject("listaClienti",clienteService.findAll());
			return view;
		}else {
			return new ModelAndView("error").addObject("message", "id non trovato");
		}
		
	}
}
