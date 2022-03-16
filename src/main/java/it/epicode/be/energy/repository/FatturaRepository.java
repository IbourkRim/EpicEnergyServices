package it.epicode.be.energy.repository;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.epicode.be.energy.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura,Long>{

	Page<Optional<Fattura>> findByData(Pageable pageable, LocalDate data);
	
	@Query("SELECT f FROM Fattura f WHERE f.statoFattura LIKE %:statoFattura%")
    Page<Optional<Fattura>> findByStatoFattura(Pageable pageable, String statoFattura);
}
