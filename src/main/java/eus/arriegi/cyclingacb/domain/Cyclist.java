package eus.arriegi.cyclingacb.domain;

import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "firstName", "lastName", "birthdate" }) })
public class Cyclist {
	
	public Cyclist() {
		this.teams = new HashMap<Integer,Team>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "countryId")
	private Country country;
	@Temporal(value = TemporalType.DATE)
	private Date birthdate;
	@ElementCollection(targetClass = Team.class)
	private Map<Integer, Team> teams;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public Map<Integer, Team> getTeams() {
		return teams;
	}

	public void setTeams(Map<Integer, Team> teams) {
		this.teams = teams;
	}

	public Team getTeam() {
		return this.teams.get(Year.now().getValue());
	}

	public Team getTeamByYear(Integer year) {
		return this.teams.get(year);
	}

	public String getTeamName() {
		Team team = getTeam();
		Integer year = Year.now().getValue();
		if (team != null && team.getNames() != null && team.getNames().size() > 0 && team.getNames().containsKey(year)) {
			return team.getNames().get(year);
		} else {
			return "";
		}
	}
	
	public String getTeamNameByYear(Integer year) {
		Team team = getTeamByYear(year);
		if (team != null && team.getNames() != null && team.getNames().size() > 0 && team.getNames().containsKey(year)) {
			return team.getNames().get(year);
		} else {
			return "";
		}
	}

	@Override
	public String toString() {
		return "Cyclist [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ ", birthdate=" + birthdate + ", teams=" + teams + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((teams == null) ? 0 : teams.hashCode());
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
		Cyclist other = (Cyclist) obj;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (teams == null) {
			if (other.teams != null)
				return false;
		} else if (!teams.equals(other.teams))
			return false;
		return true;
	}

}
