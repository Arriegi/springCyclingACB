package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.FabricationOrder;
import eus.arriegi.cyclingacb.domain.WorkOperation;

@Repository(value = "workOperationDao")
public class JPAWorkOperationDao implements WorkOperationDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<WorkOperation> getWorkOperations() {
		return em.createQuery("select wo from WorkOperation wo order by wo.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<WorkOperation> getWorkOperations(FabricationOrder order) {
		String query = "select wo from WorkOperation wo where wo.order = :order";
		return em.createQuery(query).setParameter("order", order).getResultList();
	}

	@Transactional(readOnly = true)
	public WorkOperation getWorkOperation(Long id) {
		return (WorkOperation) em.createQuery("select wo from WorkOperation wo where wo.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public WorkOperation saveWorkOperation(WorkOperation workOperation) {
		WorkOperation fm = em.merge(workOperation);
		return fm;
	}

	@Transactional(readOnly = false)
	public void removeWorkOperation(WorkOperation workOperation) {
		WorkOperation fm = em.find(WorkOperation.class,workOperation.getId());
		if (fm != null) {
			em.remove(fm);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeWorkOperation(Long id) {
		WorkOperation fm = em.find(WorkOperation.class,id);
		if (fm != null) {
			em.remove(fm);
		}
	}

	@Transactional(readOnly = false)
	public WorkOperation updateWorkOperation(WorkOperation workOperation) {
		WorkOperation fm = em.merge(workOperation);
		return fm;
	}

}
