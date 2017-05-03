package eus.arriegi.cyclingacb.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Email;

import eus.arriegi.cyclingacb.domain.authentication.Role;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;
	@Email
	private String email;
	private String password;
	@ColumnDefault(value = "false")
	private Boolean enabled;
	@ElementCollection(targetClass = UserTeam.class)
	private Map<Race, UserTeam> teams;
	@ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "player_role", joinColumns = @JoinColumn(name = "playerId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
	private List<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Map<Race, UserTeam> getTeams() {
		return teams;
	}

	public void setTeams(Map<Race, UserTeam> teams) {
		this.teams = teams;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<Role>();
		}
		this.roles.add(role);
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", enabled=" + enabled + ", teams=" + teams + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((teams == null) ? 0 : teams.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Player other = (Player) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (teams == null) {
			if (other.teams != null)
				return false;
		} else if (!teams.equals(other.teams))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
