package eus.arriegi.cyclingacb.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserTeam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "playerId")
	private Player player;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "raceId")
	private Race race;
	@OneToMany(cascade = CascadeType.REFRESH , fetch = FetchType.EAGER)
	private List<RacingCyclist> cyclists;
	private Integer year;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public List<RacingCyclist> getCyclists() {
		return cyclists;
	}
	public void setCyclists(List<RacingCyclist> cyclists) {
		this.cyclists = cyclists;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cyclists == null) ? 0 : cyclists.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((race == null) ? 0 : race.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserTeam other = (UserTeam) obj;
		if (cyclists == null) {
			if (other.cyclists != null)
				return false;
		} else if (!cyclists.equals(other.cyclists))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (race == null) {
			if (other.race != null)
				return false;
		} else if (!race.equals(other.race))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
}
