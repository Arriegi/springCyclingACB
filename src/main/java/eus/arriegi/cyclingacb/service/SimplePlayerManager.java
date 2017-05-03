package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Player;
import eus.arriegi.cyclingacb.repository.PlayerDao;

@Component
public class SimplePlayerManager implements PlayerManager {

	@Autowired
	private PlayerDao playerDao;
	
	public List<Player> getPlayers() {
		return playerDao.getPlayers();
	}
	
	public Player getPlayer(Long id) {
		return playerDao.getPlayer(id);
	}
	
	public Player getPlayer(String email) {
		return playerDao.getPlayer(email);
	}

	public void addPlayer(Player player) {
		playerDao.savePlayer(player);
	}

	public Player updatePlayer(Player player) {
		return playerDao.updatePlayer(player);
	}

	public void removePlayer(Player player) {
		playerDao.removePlayer(player);
	}
	
	public void removePlayer(Long id) {
		playerDao.removePlayer(id);
	}

}
