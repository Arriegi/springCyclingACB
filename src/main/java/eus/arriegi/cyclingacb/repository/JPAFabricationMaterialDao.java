package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.FabricationMaterial;
import eus.arriegi.cyclingacb.domain.FabricationOrder;

@Repository(value = "fabricationMaterialDao")
public class JPAFabricationMaterialDao implements FabricationMaterialDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<FabricationMaterial> getFabricationMaterials() {
		return em.createQuery("select fm from FabricationMaterial fm order by fm.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<FabricationMaterial> getFabricationMaterials(FabricationOrder order) {
		String query = "select fm from FabricationMaterial fm where fm.fabricationOrder = :order";
		return em.createQuery(query).setParameter("order", order).getResultList();
	}

	@Transactional(readOnly = true)
	public FabricationMaterial getFabricationMaterial(Long id) {
		return (FabricationMaterial) em.createQuery("select fm from FabricationMaterial fm where fm.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public FabricationMaterial saveFabricationMaterial(FabricationMaterial fabricationMaterial) {
		FabricationMaterial fm = em.merge(fabricationMaterial);
		return fm;
	}

	@Transactional(readOnly = false)
	public void removeFabricationMaterial(FabricationMaterial fabricationMaterial) {
		FabricationMaterial fm = em.find(FabricationMaterial.class,fabricationMaterial.getId());
		if (fm != null) {
			em.remove(fm);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeFabricationMaterial(Long id) {
		FabricationMaterial fm = em.find(FabricationMaterial.class,id);
		if (fm != null) {
			em.remove(fm);
		}
	}

	@Transactional(readOnly = false)
	public FabricationMaterial updateFabricationMaterial(FabricationMaterial fabricationMaterial) {
		FabricationMaterial fm = em.merge(fabricationMaterial);
		return fm;
	}

}
