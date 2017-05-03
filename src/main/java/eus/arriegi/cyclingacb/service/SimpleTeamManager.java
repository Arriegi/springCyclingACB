package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Team;
import eus.arriegi.cyclingacb.repository.TeamDao;

@Component
public class SimpleTeamManager implements TeamManager {

	@Autowired
	private TeamDao teamDao;
	
	public List<Team> getTeams() {
		return teamDao.getTeams();
	}
	
	public Team getTeam(Long id) {
		return teamDao.getTeam(id);
	}
	
	public void addTeam(Team team) {
		teamDao.saveTeam(team);
	}

	public Team updateTeam(Team team) {
		return teamDao.updateTeam(team);
	}

	public void removeTeam(Team team) {
		teamDao.removeTeam(team);
	}
	
	public void removeTeam(Long id) {
		teamDao.removeTeam(id);
	}

}
