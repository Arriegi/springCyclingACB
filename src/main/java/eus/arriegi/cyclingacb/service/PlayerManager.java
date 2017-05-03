package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Player;

public interface PlayerManager {

	public List<Player> getPlayers();

	public Player getPlayer(Long id);

	public Player getPlayer(String email);

	public void addPlayer(Player player);

	public Player updatePlayer(Player player);

	public void removePlayer(Player player);

	public void removePlayer(Long id);

}
