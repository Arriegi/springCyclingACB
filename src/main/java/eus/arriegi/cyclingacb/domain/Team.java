package eus.arriegi.cyclingacb.domain;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String basicName;
	@ElementCollection(targetClass = String.class)
	private Map<Integer,String> names;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBasicName() {
		return basicName;
	}
	public void setBasicName(String basicName) {
		this.basicName = basicName;
	}
	public Map<Integer, String> getNames() {
		return names;
	}
	public void setNames(Map<Integer, String> names) {
		this.names = names;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicName == null) ? 0 : basicName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((names == null) ? 0 : names.hashCode());
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
		Team other = (Team) obj;
		if (basicName == null) {
			if (other.basicName != null)
				return false;
		} else if (!basicName.equals(other.basicName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (names == null) {
			if (other.names != null)
				return false;
		} else if (!names.equals(other.names))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", basicName=" + basicName + ", names=" + names + "]";
	}
	
}
