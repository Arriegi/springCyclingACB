package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Team;

@Repository(value = "teamDao")
public class JPATeamDao implements TeamDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Team> getTeams() {
		return em.createQuery("select t from Team t order by t.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Team> getTeams(String name) {
		String query = "select t from Team t where lower(t.basicName) LIKE :name";
		return em.createQuery(query)
				.setParameter("name", "%" + name.toLowerCase() + "%")
				.getResultList();
	}

	@Transactional(readOnly = true)
	public Team getTeam(Long id) {
		return (Team) em.createQuery("select t from Team t where t.id = :id").setParameter("id", id)
				.getSingleResult();
	}
	
	@Transactional(readOnly = false)
	public Team saveTeam(Team team) {
		Team t = em.merge(team);
		return t;
	}

	@Transactional(readOnly = false)
	public void removeTeam(Team team) {
		Team t = em.find(Team.class,team.getId());
		if (t != null) {
			em.remove(t);
		}
	}
	
	@Transactional(readOnly = false)
	public void removeTeam(Long id) {
		Team t = em.find(Team.class,id);
		if (t != null) {
			em.remove(t);
		}
	}

	@Transactional(readOnly = false)
	public Team updateTeam(Team team) {
		Team t = em.merge(team);
		return t;
	}

}
