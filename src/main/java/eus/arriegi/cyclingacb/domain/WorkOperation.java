package eus.arriegi.cyclingacb.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class WorkOperation {

	public WorkOperation() {}

	public WorkOperation(Date startTime, Date endTime, Operation operation, FabricationOrder order) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.operation = operation;
		this.order = order;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(value = TemporalType.TIME)
	private Date startTime, endTime;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "operationId")
	private Operation operation;
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "orderId")
	private FabricationOrder order;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public FabricationOrder getOrder() {
		return order;
	}

	public void setOrder(FabricationOrder order) {
		this.order = order;
	}
	
	public void setDayToTime(Date date) {
		Calendar aDate = Calendar.getInstance();
	    aDate.setTime(date);

	    Calendar aStartTime = Calendar.getInstance();
	    aStartTime.setTime(this.startTime);
	    Calendar anEndTime = Calendar.getInstance();
	    anEndTime.setTime(this.endTime);

	    Calendar aDateTime = Calendar.getInstance();
	    aDateTime.set(Calendar.DAY_OF_MONTH, aDate.get(Calendar.DAY_OF_MONTH));
	    aDateTime.set(Calendar.MONTH, aDate.get(Calendar.MONTH));
	    aDateTime.set(Calendar.YEAR, aDate.get(Calendar.YEAR));
	    aDateTime.set(Calendar.HOUR_OF_DAY, aStartTime.get(Calendar.HOUR_OF_DAY));
	    aDateTime.set(Calendar.MINUTE, aStartTime.get(Calendar.MINUTE));
	    aDateTime.set(Calendar.SECOND, 0);
	    
	    this.startTime = aDateTime.getTime();
	    aDateTime.set(Calendar.HOUR_OF_DAY, anEndTime.get(Calendar.HOUR_OF_DAY));
	    aDateTime.set(Calendar.MINUTE, anEndTime.get(Calendar.MINUTE));
	    aDateTime.set(Calendar.SECOND, 0);
	    this.endTime = aDateTime.getTime();
	    
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
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
		if (!(obj instanceof WorkOperation)) {
			return false;
		}
		WorkOperation other = (WorkOperation) obj;
		if (endTime == null) {
			if (other.endTime != null) {
				return false;
			}
		} else if (!endTime.equals(other.endTime)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (operation == null) {
			if (other.operation != null) {
				return false;
			}
		} else if (!operation.equals(other.operation)) {
			return false;
		}
		if (order == null) {
			if (other.order != null) {
				return false;
			}
		} else if (!order.equals(other.order)) {
			return false;
		}
		if (startTime == null) {
			if (other.startTime != null) {
				return false;
			}
		} else if (!startTime.equals(other.startTime)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "WorkOperation [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", operation="
				+ operation + ", order=" + order.getName() + "]";
	}
	
}
