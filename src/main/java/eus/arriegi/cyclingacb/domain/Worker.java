package eus.arriegi.cyclingacb.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Email;

import eus.arriegi.cyclingacb.domain.authentication.Role;

@Entity
public class Worker {

	public Worker() {}

	public Worker(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String lastName1;
	private String lastName2;
	@Email
	private String email;
	private String password;
	private Float hourCost;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "sectionId")
	private Section section;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "roleId")
	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName1() {
		return lastName1;
	}

	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}

	public String getLastName2() {
		return lastName2;
	}

	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.name + " " + this.lastName1;
	}

	public Float getHourCost() {
		return hourCost;
	}

	public void setHourCost(Float hourCost) {
		this.hourCost = hourCost;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hourCost == null) ? 0 : hourCost.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName1 == null) ? 0 : lastName1.hashCode());
		result = prime * result + ((lastName2 == null) ? 0 : lastName2.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Worker)) {
			return false;
		}
		Worker other = (Worker) obj;
		if (hourCost == null) {
			if (other.hourCost != null) {
				return false;
			}
		} else if (!hourCost.equals(other.hourCost)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (lastName1 == null) {
			if (other.lastName1 != null) {
				return false;
			}
		} else if (!lastName1.equals(other.lastName1)) {
			return false;
		}
		if (lastName2 == null) {
			if (other.lastName2 != null) {
				return false;
			}
		} else if (!lastName2.equals(other.lastName2)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (section == null) {
			if (other.section != null) {
				return false;
			}
		} else if (!section.equals(other.section)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Worker [id=" + id + ", name=" + name + ", lastName1=" + lastName1 + ", lastName2=" + lastName2
				+ ", hourCost=" + hourCost + ", section=" + section + "]";
	}

}
