package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Player;

public interface PlayerDao {
	
	public List<Player> getPlayers();
	
	public List<Player> getPlayers(String name);
	
	public Player getPlayer(Long id);
	
	public Player getPlayer(String email);
	
    public Player savePlayer(Player player);
    
    public void removePlayer(Player player);

    public void removePlayer(Long id);
    
    public Player updatePlayer(Player player);
    
}
