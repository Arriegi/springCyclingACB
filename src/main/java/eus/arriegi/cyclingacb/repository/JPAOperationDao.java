package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Operation;

@Repository(value = "operationDao")
public class JPAOperationDao implements OperationDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Operation> getOperations() {
		return em.createQuery("select o from Operation o order by o.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Operation> getOperations(String name) {
		String query = "select o from Operation o where lower(o.name) LIKE :name";
		return em.createQuery(query).setParameter("name", "%" + name.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	public Operation getOperation(Long id) {
		return (Operation) em.createQuery("select o from Operation o where o.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Operation saveOperation(Operation operation) {
		Operation ot = em.merge(operation);
		return ot;
	}

	@Transactional(readOnly = false)
	public void removeOperation(Operation operation) {
		Operation ot = em.find(Operation.class,operation.getId());
		if (ot != null) {
			em.remove(ot);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeOperation(Long id) {
		Operation ot = em.find(Operation.class,id);
		if (ot != null) {
			em.remove(ot);
		}
	}

	@Transactional(readOnly = false)
	public Operation updateOperation(Operation operation) {
		Operation ot = em.merge(operation);
		return ot;
	}

}
