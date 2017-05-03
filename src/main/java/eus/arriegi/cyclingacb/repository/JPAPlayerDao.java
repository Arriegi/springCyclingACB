package eus.arriegi.cyclingacb.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import eus.arriegi.cyclingacb.domain.Player;

@Repository(value = "playerDao")
public class JPAPlayerDao implements PlayerDao {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Player> getPlayers() {
		return em.createQuery("select p from Player p order by p.id").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Player> getPlayers(String name) {
		String query = "select p from Player p where lower(p.username) LIKE :username "
				+ "OR lower(p.email) LIKE :email";
		return em.createQuery(query)
				.setParameter("username", "%" + name.toLowerCase() + "%")
				.setParameter("email", "%" + name.toLowerCase() + "%")
				.getResultList();
	}

	@Transactional(readOnly = true)
	public Player getPlayer(Long id) {
		return (Player) em.createQuery("select p from Player p where p.id = :id").setParameter("id", id)
				.getSingleResult();
	}
	
	@Transactional(readOnly = true)
	public Player getPlayer(String username) {
		return (Player) em.createQuery("select p from Player p where p.username = :username").setParameter("username", username)
				.getSingleResult();
	}

	@Transactional(readOnly = false)
	public Player savePlayer(Player player) {
		Player p = em.merge(player);
		return p;
	}

	@Transactional(readOnly = false)
	public void removePlayer(Player player) {
		Player p = em.find(Player.class,player.getId());
		if (p != null) {
			em.remove(p);
		}
	}
	
	@Transactional(readOnly = false)
	public void removePlayer(Long id) {
		Player p = em.find(Player.class,id);
		if (p != null) {
			em.remove(p);
		}
	}

	@Transactional(readOnly = false)
	public Player updatePlayer(Player player) {
		Player p = em.merge(player);
		return p;
	}

}
