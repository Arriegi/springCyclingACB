package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Client;

public interface ClientDao {
	
	public List<Client> getClients();
	
	public List<Client> getClients(String name);
	
	public Client getClient(Long id);
	
    public Client saveClient(Client client);
    
    public void removeClient(Client client);

    public void removeClient(Long id);
    
    public Client updateClient(Client client);
    
}
