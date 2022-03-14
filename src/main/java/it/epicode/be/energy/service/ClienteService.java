package it.epicode.be.energy.service;

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
			clienteUpdate.setPartitaIva(cliente.getPartitaIva());
			clienteUpdate.setEmail(cliente.getEmail());
			clienteUpdate.setDataInserimento(cliente.getDataInserimento());
			clienteUpdate.setDataUltimoContatto(cliente.getDataUltimoContatto());
			clienteUpdate.setFatturatoAnnuale(cliente.getFatturatoAnnuale());
			clienteUpdate.setPec(cliente.getPec());
			clienteUpdate.setTelefono(cliente.getTelefono());
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
}
