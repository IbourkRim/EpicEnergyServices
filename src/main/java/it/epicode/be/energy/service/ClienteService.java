package it.epicode.be.energy.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import it.epicode.be.energy.model.Cliente;
import it.epicode.be.energy.repository.ClienteRepository;
import it.epicode.be.energy.security.exception.EpicEnergyException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepo;

	public Cliente save(Cliente cliente) {
		clienteRepo.save(cliente);
		return cliente;
	}

	public void delete(Long id) {
		clienteRepo.deleteById(id);
	}

	public Cliente update(Cliente cliente, Long id) {
		Optional<Cliente> clienteResult = clienteRepo.findById(id);
		if (clienteResult.isPresent()) {
			Cliente clienteUpdate = clienteResult.get();
			clienteUpdate.setRagioneSociale(cliente.getRagioneSociale());
			clienteUpdate.setTipoCliente(cliente.getTipoCliente());
			clienteUpdate.setPartitaIva(cliente.getPartitaIva());
			clienteUpdate.setEmail(cliente.getEmail());
			clienteUpdate.setDataInserimento(cliente.getDataInserimento());
			clienteUpdate.setDataUltimoContatto(cliente.getDataUltimoContatto());
			clienteUpdate.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			clienteUpdate.setPec(cliente.getPec());
			clienteUpdate.setTelefono(cliente.getTelefono());
			clienteUpdate.setEmailContatto(cliente.getEmailContatto());
			clienteUpdate.setNomeContatto(cliente.getNomeContatto());
			clienteUpdate.setCognomeContatto(cliente.getCognomeContatto());
			clienteUpdate.setTelefonoContatto(cliente.getTelefonoContatto());
			clienteUpdate.setIndirizzoSedeLegale(cliente.getIndirizzoSedeLegale());
			clienteUpdate.setIndirizzoSedeOperativa(cliente.getIndirizzoSedeOperativa());
			clienteUpdate.setFatture(cliente.getFatture());
			return clienteRepo.save(clienteUpdate);
			
		}else {
			throw new EpicEnergyException("Cliente non aggiornato");
		}
	}
	
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepo.findAll(pageable);
	}

	public List<Cliente> findAll() {
		return clienteRepo.findAll();
	}
	
	public Optional<Cliente> findById(Long id) {
		return clienteRepo.findById(id);
	}

	/*public Page<Optional<Cliente>> findByOrderByRagioneSociale(Pageable pageable){
		return clienteRepo.findByOrderByRagioneSociale(pageable);
	}

	public Page<Optional<Cliente>> findByOrderByFatturatoAnnuale(Pageable pageable){
		return clienteRepo.findByOrderByFatturatoAnnuale(pageable);
	}

	public Page<Optional<Cliente>> findByOrderByDataInserimento(Pageable pageable){
		return clienteRepo.findByOrderByDataInserimento(pageable);
	}

	public Page<Optional<Cliente>> findByOrderByDataUltimoContatto(Pageable pageable){
		return clienteRepo.findByOrderByDataUltimoContatto(pageable);
	}

	public Page<Optional<Cliente>> findByOrderByIndirizzoSedeLegaleComuneProvincia(Pageable pageable){
		return clienteRepo.findByOrderByIndirizzoSedeLegaleComuneProvincia(pageable);
	}

	public Page<Optional<Cliente>> findByFatturatoAnnuale(Pageable pageable, Double minimo , Double massimo){
		return clienteRepo.findByFatturatoAnnuale(pageable, minimo, massimo);
	}

	public Page<Optional<Cliente>> findByDataInserimento(Pageable pageable, LocalDate dataInserimento){
		return clienteRepo.findByDataInserimento(pageable, dataInserimento);
	}

	public Page<Optional<Cliente>> findByDataUltimoContatto(Pageable pageable, LocalDate dataUltimoContatto){
		return clienteRepo.findByDataUltimoContatto(pageable, dataUltimoContatto);
	}

	public Page<Optional<Cliente>> findByRagioneSociale(Pageable pageable, String ragioneSociale){
		return clienteRepo.findByRagioneSociale(pageable, ragioneSociale);
	}*/
}
