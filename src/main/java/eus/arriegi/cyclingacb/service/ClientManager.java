package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Client;

public interface ClientManager {
	
	public List<Client> getClients();
	
	public Client getClient(Long id);
	
	public Client addClient(Client client);

	public Client updateClient(Client client);
	
	public void removeClient(Client client);
	
	public void removeClient(Long id);
	
}
