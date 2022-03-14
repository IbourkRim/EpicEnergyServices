package it.epicode.be.energy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.be.energy.model.Fattura;

public interface FatturaRepository extends JpaRepository<Fattura,Long>{

}
