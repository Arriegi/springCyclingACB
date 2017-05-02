package eus.arriegi.cyclingacb.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Part {

	public Part() {
		this.operations = new TreeMap<Date,WorkOperation>();
	}

	public Part(Long id, String name) {
		this.id = id;
		this.name = name;
		this.operations = new TreeMap<Date,WorkOperation>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Temporal(value = TemporalType.DATE)
	private Date date;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "workerId")
	private Worker worker;
	private String observations;
	@OneToMany(cascade=CascadeType.REFRESH, fetch=FetchType.EAGER)
	@OrderBy("startTime DESC")
	private SortedMap<Date,WorkOperation> operations;

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

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public SortedMap<Date,WorkOperation> getOperations() {
		return operations;
	}

	public void setOperations(SortedMap<Date,WorkOperation> operations) {
		this.operations = operations;
	}
	
	public void addOperation(WorkOperation operation) {
		this.operations.put(operation.getStartTime(),operation);
	}
	
	public void removeOperation(Date date) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		try {
			cal.setTime(sdf.parse(this.date + " " + date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.operations.remove(cal.getTime());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((observations == null) ? 0 : observations.hashCode());
		result = prime * result + ((operations == null) ? 0 : operations.hashCode());
		result = prime * result + ((worker == null) ? 0 : worker.hashCode());
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
		if (!(obj instanceof Part)) {
			return false;
		}
		Part other = (Part) obj;
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
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (observations == null) {
			if (other.observations != null) {
				return false;
			}
		} else if (!observations.equals(other.observations)) {
			return false;
		}
		if (operations == null) {
			if (other.operations != null) {
				return false;
			}
		} else if (!operations.equals(other.operations)) {
			return false;
		}
		if (worker == null) {
			if (other.worker != null) {
				return false;
			}
		} else if (!worker.equals(other.worker)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", name=" + name + ", date=" + date + ", worker=" + worker + ", observations="
				+ observations + ", operations=" + operations + "]";
	}

}
