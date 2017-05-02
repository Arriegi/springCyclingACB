package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Worker;

@Repository(value = "workerDao")
public class JPAWorkerDao implements WorkerDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Worker> getWorkers() {
		return em.createQuery("select w from Worker w order by w.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Worker> getWorkers(String name) {
		String query = "select w from Worker w where lower(w.name) LIKE :name OR lower(w.name) LIKE :firstName "
				+ "OR lower(w.name) LIKE :secondName";
		return em.createQuery(query)
				.setParameter("name", "%" + name.toLowerCase() + "%")
				.setParameter("firstName", "%" + name.toLowerCase() + "%")
				.setParameter("secondName", "%" + name.toLowerCase() + "%")
				.getResultList();
	}

	@Transactional(readOnly = true)
	public Worker getWorker(Long id) {
		return (Worker) em.createQuery("select w from Worker w where w.id = :id").setParameter("id", id)
				.getSingleResult();
	}
	
	@Transactional(readOnly = true)
	public Worker getWorker(String email) {
		return (Worker) em.createQuery("select w from Worker w where w.email = :email").setParameter("email", email)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Worker saveWorker(Worker worker) {
		Worker wk = em.merge(worker);
		return wk;
	}

	@Transactional(readOnly = false)
	public void removeWorker(Worker worker) {
		Worker wk = em.find(Worker.class,worker.getId());
		if (wk != null) {
			em.remove(wk);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeWorker(Long id) {
		Worker wk = em.find(Worker.class,id);
		if (wk != null) {
			em.remove(wk);
		}
	}

	@Transactional(readOnly = false)
	public Worker updateWorker(Worker worker) {
		Worker wk = em.merge(worker);
		return wk;
	}

}
