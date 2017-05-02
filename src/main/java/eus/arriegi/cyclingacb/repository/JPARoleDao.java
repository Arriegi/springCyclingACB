package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.authentication.Role;

@Repository(value = "RoleDao")
public class JPARoleDao implements RoleDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Role> getRoles() {
		return em.createQuery("select o from Role o order by o.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Role> getRoles(String name) {
		String query = "select o from Role o where lower(o.name) LIKE :name";
		return em.createQuery(query).setParameter("name", "%" + name.toLowerCase() + "%").getResultList();
	}

	@Transactional(readOnly = true)
	public Role getRole(Long id) {
		return (Role) em.createQuery("select o from Role o where o.id = :id").setParameter("id", id)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Role saveRole(Role Role) {
		Role ot = em.merge(Role);
		return ot;
	}

	@Transactional(readOnly = false)
	public void removeRole(Role Role) {
		Role ot = em.find(Role.class,Role.getId());
		if (ot != null) {
			em.remove(ot);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeRole(Long id) {
		Role ot = em.find(Role.class,id);
		if (ot != null) {
			em.remove(ot);
		}
	}

	@Transactional(readOnly = false)
	public Role updateRole(Role Role) {
		Role ot = em.merge(Role);
		return ot;
	}

}
