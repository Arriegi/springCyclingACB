package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Team;

public interface TeamManager {

	public List<Team> getTeams();

	public Team getTeam(Long id);

	public void addTeam(Team team);

	public Team updateTeam(Team team);

	public void removeTeam(Team team);

	public void removeTeam(Long id);

}
