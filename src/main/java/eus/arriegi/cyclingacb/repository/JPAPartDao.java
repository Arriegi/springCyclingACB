package eus.arriegi.cyclingacb.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Part;
import eus.arriegi.cyclingacb.domain.Worker;

@Repository(value = "partDao")
public class JPAPartDao implements PartDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Part> getParts() {
		return em.createQuery("select p from Part p order by p.id").getResultList();
	}

	@Transactional(readOnly = true)
	public Part getPart(Long id) {
		return (Part) em.createQuery("select p from Part p where p.id = :id").setParameter("id", id)
				.getSingleResult();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Part> getParts(Date date) {
		return em.createQuery("select p from Part p where p.date = :date").setParameter("date", date)
				.getResultList();
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Part> getParts(Worker worker) {
		return em.createQuery("select p from Part p where p.worker = :worker").setParameter("worker", worker)
				.getResultList();
	}

	@Transactional(readOnly = false)
	public Part savePart(Part part) {
		Part p = em.merge(part);
		return p;
	}

	@Transactional(readOnly = false)
	public void removePart(Part part) {
		Part p = em.find(Part.class,part.getId());
		if (p != null) {
			em.remove(p);
		}
	}
	
	@Transactional(readOnly = false)
	public void removePart(Long id) {
		Part p = em.find(Part.class,id);
		if (p != null) {
			em.remove(p);
		}
	}

	@Transactional(readOnly = false)
	public Part updatePart(Part part) {
		Part p = em.merge(part);
		return p;
	}

}
