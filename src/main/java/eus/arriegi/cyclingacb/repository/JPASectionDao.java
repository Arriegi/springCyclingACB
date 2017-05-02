package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Section;

@Repository(value = "sectionDao")
public class JPASectionDao implements SectionDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Section> getSections() {
		return em.createQuery("select s from Section s order by s.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Section> getSections(String name) {
		String query = "select s from Section s where lower(s.name) LIKE :name";
		return em.createQuery(query).setParameter("name", "%" + name.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	public Section getSection(Long id) {
		return (Section) em.createQuery("select s from Section s where s.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Section saveSection(Section section) {
		Section wk = em.merge(section);
		return wk;
	}

	@Transactional(readOnly = false)
	public void removeSection(Section section) {
		Section wk = em.find(Section.class,section.getId());
		if (wk != null) {
			em.remove(wk);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeSection(Long id) {
		Section wk = em.find(Section.class,id);
		if (wk != null) {
			em.remove(wk);
		}
	}

	@Transactional(readOnly = false)
	public Section updateSection(Section section) {
		Section wk = em.merge(section);
		return wk;
	}

}
