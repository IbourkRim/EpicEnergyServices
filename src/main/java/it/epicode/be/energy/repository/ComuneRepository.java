package it.epicode.be.energy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import it.epicode.be.energy.model.Comune;

public interface ComuneRepository extends JpaRepository<Comune, Long> {
	public Optional<Comune> findByProvinciaId(Long id);
}
