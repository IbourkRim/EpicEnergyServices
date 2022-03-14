package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Indirizzo;
import it.epicode.be.energy.repository.IndirizzoRepository;
import it.epicode.be.energy.security.exception.EpicEnergyException;

@Service
public class IndirizzoService {
	
	@Autowired
	IndirizzoRepository indirizzoRepo;

	public Indirizzo save(Indirizzo indirizzo) {
		indirizzoRepo.save(indirizzo);
		return indirizzo;
	}

	public void delete(Long id) {
		indirizzoRepo.deleteById(id);
	}

	public Indirizzo update(Indirizzo indirizzo, Long id) {
		Optional<Indirizzo> indirizzoResult = indirizzoRepo.findById(id);
		if (indirizzoResult.isPresent()) {
			Indirizzo indirizzoUpdate = indirizzoResult.get();
			indirizzoUpdate.setVia(indirizzo.getVia());
			indirizzoUpdate.setCivico(indirizzo.getCivico());
			indirizzoUpdate.setLocalita(indirizzo.getLocalita());
			indirizzoUpdate.setCap(indirizzo.getCap());
			return indirizzoRepo.save(indirizzoUpdate);
			
		}else {
			throw new EpicEnergyException("Indirizzo non aggiornato");
		}
	}
	
	public Page<Indirizzo> findAll(Pageable pageable) {
		return indirizzoRepo.findAll(pageable);
	}

	public List<Indirizzo> findAll() {
		return indirizzoRepo.findAll();
	}
	
	public Optional<Indirizzo> findById(Long id) {
		return indirizzoRepo.findById(id);
	}
}
