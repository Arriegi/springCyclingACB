package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Material;

@Repository(value = "materialDao")
public class JPAMaterialDao implements MaterialDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Material> getMaterials() {
		return em.createQuery("select m from Material m order by m.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Material> getMaterials(String name) {
		String query = "select m from Material m where lower(m.name) LIKE :name";
		return em.createQuery(query).setParameter("name", "%" + name.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	public Material getMaterial(Long id) {
		return (Material) em.createQuery("select m from Material m where m.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Material saveMaterial(Material material) {
		Material mt = em.merge(material);
		return mt;
	}

	@Transactional(readOnly = false)
	public void removeMaterial(Material material) {
		Material mt = em.find(Material.class,material.getId());
		if (mt != null) {
			em.remove(mt);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeMaterial(Long id) {
		Material mt = em.find(Material.class,id);
		if (mt != null) {
			em.remove(mt);
		}
	}

	@Transactional(readOnly = false)
	public Material updateMaterial(Material material) {
		Material mt = em.merge(material);
		return mt;
	}

}
