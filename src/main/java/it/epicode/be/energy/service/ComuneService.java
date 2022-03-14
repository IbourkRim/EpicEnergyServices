package it.epicode.be.energy.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.model.Comune;
import it.epicode.be.energy.repository.ComuneRepository;
import it.epicode.be.energy.security.exception.EpicEnergyException;

@Service
public class ComuneService {
	
	@Autowired
	ComuneRepository comuneRepo;

	public Comune save(Comune comune) {
		comuneRepo.save(comune);
		return comune;
	}

	public void delete(Long id) {
		comuneRepo.deleteById(id);
	}

	public Comune update(Comune comune, Long id) {
		Optional<Comune> comuneResult = comuneRepo.findById(id);
		if (comuneResult.isPresent()) {
			Comune comuneUpdate = comuneResult.get();
			comuneUpdate.setNome(comune.getNome());
			return comuneRepo.save(comuneUpdate);
			
		}else {
			throw new EpicEnergyException("Comune non aggiornato");
		}
	}
	
	public Page<Comune> findAll(Pageable pageable) {
		return comuneRepo.findAll(pageable);
	}

	public List<Comune> findAll() {
		return comuneRepo.findAll();
	}
	
	public Optional<Comune> findById(Long id) {
		return comuneRepo.findById(id);
	}
}
