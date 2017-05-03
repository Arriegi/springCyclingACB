package eus.arriegi.cyclingacb.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RacingCyclist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "cyclistId")
	private Cyclist cyclist;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "raceId")
	private Race race;
	private Integer price;
	private Integer year;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cyclist getCyclist() {
		return cyclist;
	}
	public void setCyclist(Cyclist cyclist) {
		this.cyclist = cyclist;
	}
	public Race getRace() {
		return race;
	}
	public void setRace(Race race) {
		this.race = race;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
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
		result = prime * result + ((cyclist == null) ? 0 : cyclist.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		RacingCyclist other = (RacingCyclist) obj;
		if (cyclist == null) {
			if (other.cyclist != null)
				return false;
		} else if (!cyclist.equals(other.cyclist))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
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
	@Override
	public String toString() {
		return "RacingCyclist [id=" + id + ", cyclist=" + cyclist + ", race=" + race + ", price=" + price + ", year="
				+ year + "]";
	}
	
}
