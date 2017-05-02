package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Client;
import eus.arriegi.cyclingacb.domain.FabricationOrder;

@Repository(value = "fabricationOrderDao")
public class JPAFabricationOrderDao implements FabricationOrderDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<FabricationOrder> getFabricationOrders() {
		return em.createQuery("select fo from FabricationOrder fo order by fo.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<FabricationOrder> getFabricationOrders(Client client) {
		String query = "select fo from FabricationOrder fo where fo.client = :client";
		return em.createQuery(query).setParameter("client", client).getResultList();
	}

	@Transactional(readOnly = true)
	public FabricationOrder getFabricationOrder(Long id) {
		return (FabricationOrder) em.createQuery("select fo from FabricationOrder fo where fo.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public FabricationOrder saveFabricationOrder(FabricationOrder fabricationOrder) {
		FabricationOrder fo = em.merge(fabricationOrder);
		return fo;
	}

	@Transactional(readOnly = false)
	public void removeFabricationOrder(FabricationOrder fabricationOrder) {
		FabricationOrder fo = em.find(FabricationOrder.class,fabricationOrder.getId());
		if (fo != null) {
			em.remove(fo);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeFabricationOrder(Long id) {
		FabricationOrder fo = em.find(FabricationOrder.class,id);
		if (fo != null) {
			em.remove(fo);
		}
	}

	@Transactional(readOnly = false)
	public FabricationOrder updateFabricationOrder(FabricationOrder fabricationOrder) {
		FabricationOrder fo = em.merge(fabricationOrder);
		return fo;
	}

}
