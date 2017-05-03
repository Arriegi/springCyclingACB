package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Team;

public interface TeamDao {
	
	public List<Team> getTeams();
	
	public List<Team> getTeams(String name);
	
	public Team getTeam(Long id);
	
    public Team saveTeam(Team team);
    
    public void removeTeam(Team team);

    public void removeTeam(Long id);
    
    public Team updateTeam(Team team);
    
}
