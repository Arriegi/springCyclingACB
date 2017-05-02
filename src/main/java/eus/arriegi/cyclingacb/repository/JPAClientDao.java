package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Client;

@Repository(value = "clientDao")
public class JPAClientDao implements ClientDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Client> getClients() {
		return em.createQuery("select c from Client c order by c.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Client> getClients(String name) {
		String query = "select c from Client c where lower(c.name) LIKE :name";
		return em.createQuery(query).setParameter("name", "%" + name.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	public Client getClient(Long id) {
		return (Client) em.createQuery("select c from Client c where c.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Client saveClient(Client client) {
		Client cl = em.merge(client);
		return cl;
	}

	@Transactional(readOnly = false)
	public void removeClient(Client client) {
		Client cl = em.find(Client.class,client.getId());
		if (cl != null) {
			em.remove(cl);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeClient(Long id) {
		Client cl = em.find(Client.class,id);
		if (cl != null) {
			em.remove(cl);
		}
	}

	@Transactional(readOnly = false)
	public Client updateClient(Client client) {
		Client cl = em.merge(client);
		return cl;
	}

}
