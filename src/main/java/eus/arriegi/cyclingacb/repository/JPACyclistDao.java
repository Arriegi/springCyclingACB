package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Country;
import eus.arriegi.cyclingacb.domain.Cyclist;

@Repository(value = "cyclistDao")
public class JPACyclistDao implements CyclistDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Cyclist> getCyclists() {
		return em.createQuery("select c from Cyclist c order by c.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Cyclist> getCyclists(String name) {
		String query = "select c from Cyclist c where lower(c.lastName) LIKE :name";
		return em.createQuery(query).setParameter("name", "%" + name.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	public Cyclist getCyclist(Long id) {
		return (Cyclist) em.createQuery("select c from Cyclist c where c.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Cyclist saveCyclist(Cyclist cyclist) {
		Cyclist cl = em.merge(cyclist);
		return cl;
	}

	@Transactional(readOnly = false)
	public void removeCyclist(Cyclist cyclist) {
		Cyclist cl = em.find(Cyclist.class,cyclist.getId());
		if (cl != null) {
			em.remove(cl);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeCyclist(Long id) {
		Cyclist cl = em.find(Cyclist.class,id);
		if (cl != null) {
			em.remove(cl);
		}
	}

	@Transactional(readOnly = false)
	public Cyclist updateCyclist(Cyclist cyclist) {
		Cyclist cl = em.merge(cyclist);
		return cl;
	}
	
	@Transactional(readOnly = true)
	public Country getCountry(Long id) {
		return (Country) em.createQuery("select c from Country c where c.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Country> getCountries() {
		return em.createQuery("select c from Country c order by c.id").getResultList();
	}
	
}
