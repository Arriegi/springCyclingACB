package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.domain.OperationType;

@Repository(value = "operationTypeDao")
public class JPAOperationTypeDao implements OperationTypeDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Operation> getOperations(OperationType operationType) {
		return em.createQuery("select o from Operation o where o.operationType = :operationType")
				.setParameter("operationType", operationType).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Operation> getOperations(Long operationTypeId) {
		return em.createQuery("select o from Operation o where o.operationType.id = :operationTypeId")
				.setParameter("operationTypeId", operationTypeId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Operation> getOperations(String text) {
		return em.createQuery("select o from Operation o where lower(o.operationType.name) LIKE :name")
				.setParameter("name", "%" + text.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<OperationType> getOperationTypes() {
		return em.createQuery("select o from OperationType o order by o.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<OperationType> getOperationTypes(String name) {
		String query = "select o from OperationType o where lower(o.name) LIKE :name";
		return em.createQuery(query).setParameter("name", "%" + name.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	public OperationType getOperationType(Long id) {
		return (OperationType) em.createQuery("select o from OperationType o where o.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public OperationType saveOperationType(OperationType operationType) {
		OperationType ot = em.merge(operationType);
		return ot;
	}

	@Transactional(readOnly = false)
	public void removeOperationType(OperationType operationType) {
		OperationType ot = em.find(OperationType.class,operationType.getId());
		if (ot != null) {
			em.remove(ot);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeOperationType(Long id) {
		OperationType ot = em.find(OperationType.class,id);
		if (ot != null) {
			em.remove(ot);
		}
	}

	@Transactional(readOnly = false)
	public OperationType updateOperationType(OperationType operationType) {
		OperationType ot = em.merge(operationType);
		return ot;
	}

}
