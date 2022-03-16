package it.epicode.be.energy.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
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
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.service.FatturaService;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api")
public class FatturaController {
	
	@Autowired
	private FatturaService fatturaService;

	@GetMapping(path = "/fatture")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<Page<Fattura>> findAll(Pageable pageable) {
		Page<Fattura> findAll = fatturaService.findAll(pageable);

		if (findAll.hasContent()) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/fattura/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	public ResponseEntity<Fattura> findById(@PathVariable(required = true) Long id) {
		Optional<Fattura> find = fatturaService.findById(id);

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(path = "/fattura")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> save(@RequestBody Fattura fattura) {
		Fattura save = fatturaService.save(fattura);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Fattura> update(@PathVariable Long id, @RequestBody Fattura fattura) {
		Fattura save = fatturaService.update(fattura, id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "/fattura/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		fatturaService.delete(id);
		return new ResponseEntity<>("Fattura cancellata.", HttpStatus.OK);

	}

	@GetMapping(path = "/fattura/datafattura/{data}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Fattura>>> findByData(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data, Pageable pageable) {
        Page<Optional<Fattura>> findByData = fatturaService.findByData(pageable, data);

        if (findByData.hasContent()) { 
            return new ResponseEntity<>(findByData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

	}

	@GetMapping(path = "/fattura/bystatofattura/{statoFattura}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Fattura>>> findByStatoFattura(@PathVariable String statoFattura, Pageable pageable) {
   	 Page<Optional<Fattura>> findByStatoFattura = fatturaService.findByStatoFattura(pageable, statoFattura);

         if (findByStatoFattura.hasContent()) { 
               return new ResponseEntity<>(findByStatoFattura, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
           }	
    }

	@GetMapping(path = "/fattura/byanno/{anno}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Fattura>>> findByAnno(@PathVariable String anno, Pageable pageable) {
   	 Page<Optional<Fattura>> findByAnno = fatturaService.findByAnno(pageable, anno);

         if (findByAnno.hasContent()) { 
               return new ResponseEntity<>(findByAnno, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
           }	
    }
	
	@GetMapping(path = "/fattura/importo/{minimo}/{massimo}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Fattura>>> findByImporto(@PathVariable BigDecimal minimo , @PathVariable BigDecimal massimo ,Pageable pageable) {
        Page<Optional<Fattura>> findByImporto = fatturaService.findByImporto(pageable,minimo, massimo);

        if (findByImporto.hasContent()) { 
            return new ResponseEntity<>(findByImporto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
	
	/*@GetMapping(path = "/fattura/bycliente/{cliente}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity <Page<Optional<Fattura>>> findByCliente(@PathVariable String cliente, Pageable pageable) {
   	 Page<Optional<Fattura>> findByCliente = fatturaService.findByCliente(pageable, cliente);

         if (findByCliente.hasContent()) { 
               return new ResponseEntity<>(findByCliente, HttpStatus.OK);
           } else {
               return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
           }	
    }*/

}
