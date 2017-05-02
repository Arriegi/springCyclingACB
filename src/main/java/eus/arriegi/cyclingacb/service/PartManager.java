package eus.arriegi.cyclingacb.service;

import java.util.Date;
import java.util.List;

import eus.arriegi.cyclingacb.domain.Part;
import eus.arriegi.cyclingacb.domain.Worker;

public interface PartManager {
	
	public Part updatePart(Part part);

	public List<Part> getParts();
	
	public Part getPart(Long id);
	
	public List<Part> getParts(Date date);

	public List<Part> getParts(Worker worker);

	public Part addPart(Part part);
	
}
