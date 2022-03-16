package it.epicode.be.energy.controller;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.service.ClienteService;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping(path = "/clienti")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
		Page<Cliente> findAll = clienteService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Cliente> findById(@PathVariable(required = true) Long id) {
		Optional<Cliente> find = clienteService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/cliente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente save = clienteService.save(cliente);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente cliente) {
		Cliente save = clienteService.update(cliente, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/cliente/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		clienteService.delete(id);
		return new ResponseEntity<>("Cliente cancellato.", HttpStatus.OK);

	}

	@GetMapping(path = "/cliente/orderragionesociale")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Page<Optional<Cliente>>> findByOrderByRagioneSociale(Pageable pageable) {
		Page<Optional<Cliente>> findByOrderByRagioneSociale = clienteService.findByOrderByRagioneSociale(pageable);

		if (findByOrderByRagioneSociale.hasContent()) {
			return new ResponseEntity<>(findByOrderByRagioneSociale, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/orderfatturato")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Page<Optional<Cliente>>> findByOrderByFatturatoAnnuale(Pageable pageable) {
		Page<Optional<Cliente>> findByOrderByFatturatoAnnuale = clienteService.findByOrderByFatturatoAnnuale(pageable);

		if (findByOrderByFatturatoAnnuale.hasContent()) {
			return new ResponseEntity<>(findByOrderByFatturatoAnnuale, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/orderdatainserimento")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Page<Optional<Cliente>>> findByOrderByDataInserimento(Pageable pageable) {
		Page<Optional<Cliente>> findByOrderByDataInserimento = clienteService.findByOrderByDataInserimento(pageable);

		if (findByOrderByDataInserimento.hasContent()) {
			return new ResponseEntity<>(findByOrderByDataInserimento, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/orderdataultimocontatto")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Page<Optional<Cliente>>> findByOrderByDataUltimoContatto(Pageable pageable) {
		Page<Optional<Cliente>> findByOrderByDataUltimoContatto = clienteService.findByOrderByDataUltimoContatto(pageable);

		if (findByOrderByDataUltimoContatto.hasContent()) {
			return new ResponseEntity<>(findByOrderByDataUltimoContatto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/cliente/orderindirizzosedelegale")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Page<Optional<Cliente>>> findByOrderByIndirizzoSedeLegaleComuneProvincia(Pageable pageable) {
		Page<Optional<Cliente>> findByOrderByIndirizzoSedeLegaleComuneProvincia = clienteService.findByOrderByIndirizzoSedeLegaleComuneProvincia(pageable);

		if (findByOrderByIndirizzoSedeLegaleComuneProvincia.hasContent()) {
			return new ResponseEntity<>(findByOrderByIndirizzoSedeLegaleComuneProvincia, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}


	/*@GetMapping(path = "/cliente/fatturatoannuale/{minimo}/{massimo}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Cliente>>> findByFatturatoAnnuale(@PathVariable Double minimo , @PathVariable Double massimo ,Pageable pageable) {
        Page<Optional<Cliente>> findAll = clienteService.findByFatturatoAnnuale(pageable,minimo, massimo);

        if (findAll.hasContent()) { 
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

	@GetMapping(path = "/cliente/data/{dataInserimento}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Cliente>>> findByDataInserimento(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInserimento, Pageable pageable) {
        Page<Optional<Cliente>> findByDataInserimento = clienteService.findByDataInserimento(pageable, dataInserimento);

        if (findByDataInserimento.hasContent()) { 
            return new ResponseEntity<>(findByDataInserimento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

	@GetMapping(path = "/cliente/data/{dataUltimoContatto}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Cliente>>> findByDataUltimoContatto(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataUltimoContatto, Pageable pageable) {
        Page<Optional<Cliente>> findByDataUltimoContatto = clienteService.findByDataUltimoContatto(pageable, dataUltimoContatto);

        if (findByDataUltimoContatto.hasContent()) { 
            return new ResponseEntity<>(findByDataUltimoContatto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

	}
    
     @GetMapping(path = "/cliente/{ragioneSociale}")
     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
     public ResponseEntity <Page<Optional<Cliente>>> findByRagioneSociale(@PathVariable String ragioneSociale, Pageable pageable) {
    	 Page<Optional<Cliente>> findByRagioneSociale = clienteService.findByRagioneSociale(pageable, ragioneSociale);

          if (findByRagioneSociale.hasContent()) { 
                return new ResponseEntity<>(findByRagioneSociale, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }	
     }*/
	
	




}
