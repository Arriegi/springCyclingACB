package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.FabricationOrder;
import eus.arriegi.cyclingacb.domain.Part;
import eus.arriegi.cyclingacb.domain.Worker;

public interface OrderManager {
	
	public List<Part> getParts();
	
	public List<Part> getParts(FabricationOrder order);
	
	public List<Part> getParts(FabricationOrder order, Worker worker);

	public List<Part> getParts(Worker worker);
	
	public void addPart(Part part);
	
}
