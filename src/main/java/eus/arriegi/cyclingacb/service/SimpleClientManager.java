package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Client;
import eus.arriegi.cyclingacb.repository.ClientDao;

@Component
public class SimpleClientManager implements ClientManager {

	@Autowired
	private ClientDao clientDao;
	
	public List<Client> getClients() {
		return clientDao.getClients();
	}
	
	public Client getClient(Long id) {
		return clientDao.getClient(id);
	}

	public Client addClient(Client client) {
		return clientDao.saveClient(client);
	}

	public Client updateClient(Client client) {
		return clientDao.updateClient(client);
	}

	public void removeClient(Client client) {
		clientDao.removeClient(client);
	}
	
	public void removeClient(Long id) {
		clientDao.removeClient(id);
	}

}
