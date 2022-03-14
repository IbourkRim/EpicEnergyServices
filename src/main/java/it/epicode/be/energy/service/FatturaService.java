package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.epicode.be.energy.model.Fattura;
import it.epicode.be.energy.repository.FatturaRepository;
import it.epicode.be.energy.security.exception.EpicEnergyException;

@Service
public class FatturaService {
	
	@Autowired
	FatturaRepository fatturaRepo;

	public Fattura save(Fattura fattura) {
		fatturaRepo.save(fattura);
		return fattura;
	}

	public void delete(Long id) {
		fatturaRepo.deleteById(id);
	}

	public Fattura update(Fattura fattura, Long id) {
		Optional<Fattura> fatturaResult = fatturaRepo.findById(id);
		if (fatturaResult.isPresent()) {
			Fattura fatturaUpdate = fatturaResult.get();
			fatturaUpdate.setAnno(fattura.getAnno());
			fatturaUpdate.setData(fattura.getData());
			fatturaUpdate.setImporto(fattura.getImporto());
			fatturaUpdate.setNumero(fattura.getNumero());
			return fatturaRepo.save(fatturaUpdate);
			
		}else {
			throw new EpicEnergyException("Fattura non aggiornata");
		}
	}
	
	public Page<Fattura> findAll(Pageable pageable) {
		return fatturaRepo.findAll(pageable);
	}

	public List<Fattura> findAll() {
		return fatturaRepo.findAll();
	}
	
	public Optional<Fattura> findById(Long id) {
		return fatturaRepo.findById(id);
	}
}
