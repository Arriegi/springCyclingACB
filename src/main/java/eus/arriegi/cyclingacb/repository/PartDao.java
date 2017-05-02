package eus.arriegi.cyclingacb.repository;

import java.util.Date;
import java.util.List;

import eus.arriegi.cyclingacb.domain.Part;
import eus.arriegi.cyclingacb.domain.Worker;

public interface PartDao {
	
	public List<Part> getParts();
	
	public Part getPart(Long id);
	
    public List<Part> getParts(Date date);
    
    public List<Part> getParts(Worker worker);
    
    public Part savePart(Part part);
    
    public void removePart(Part part);
    
    public void removePart(Long id);
    
    public Part updatePart(Part part);
    
}
