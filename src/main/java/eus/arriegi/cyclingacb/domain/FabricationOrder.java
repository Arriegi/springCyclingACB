package eus.arriegi.cyclingacb.domain;

import java.util.Date;
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

import org.springframework.util.AutoPopulatingList;

@Entity
public class FabricationOrder {

	public FabricationOrder() {
		this.materials = new AutoPopulatingList<FabricationMaterial>(FabricationMaterial.class);
	}

	public FabricationOrder(Long id, String name) {
		this.id = id;
		this.name = name;
		this.materials = new AutoPopulatingList<FabricationMaterial>(FabricationMaterial.class);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Date date, timeLimit, closeDate;
	@OneToMany(cascade = CascadeType.REFRESH , fetch = FetchType.EAGER, mappedBy = "order")
	private List<FabricationMaterial> materials;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "clientId")
	private Client client;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<FabricationMaterial> getMaterials() {
		return materials;
	}

	public void setMaterials(AutoPopulatingList<FabricationMaterial> materials) {
		this.materials = materials;
	}
	
	public void addMaterial(FabricationMaterial material) {
		this.materials.add(material);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((closeDate == null) ? 0 : closeDate.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((materials == null) ? 0 : materials.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((timeLimit == null) ? 0 : timeLimit.hashCode());
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
		if (!(obj instanceof FabricationOrder)) {
			return false;
		}
		FabricationOrder other = (FabricationOrder) obj;
		if (client == null) {
			if (other.client != null) {
				return false;
			}
		} else if (!client.equals(other.client)) {
			return false;
		}
		if (closeDate == null) {
			if (other.closeDate != null) {
				return false;
			}
		} else if (!closeDate.equals(other.closeDate)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (materials == null) {
			if (other.materials != null) {
				return false;
			}
		} else if (!materials.equals(other.materials)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (timeLimit == null) {
			if (other.timeLimit != null) {
				return false;
			}
		} else if (!timeLimit.equals(other.timeLimit)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FabricationOrder [id=" + id + ", name=" + name + ", date=" + date + ", timeLimit=" + timeLimit
				+ ", closeDate=" + closeDate + ", materials=" + materials + ", client=" + client + "]";
	}

}
