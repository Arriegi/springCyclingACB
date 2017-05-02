package eus.arriegi.cyclingacb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Part;
import eus.arriegi.cyclingacb.domain.Worker;
import eus.arriegi.cyclingacb.repository.PartDao;

@Component
public class SimplePartManager implements PartManager {

	@Autowired
	private PartDao partDao;
	
	public Part updatePart(Part part) {
		return partDao.updatePart(part);
	}

	public List<Part> getParts() {
		return partDao.getParts();
	}

	public List<Part> getParts(Date date) {
		return partDao.getParts(date);
	}

	public List<Part> getParts(Worker worker) {
		return partDao.getParts(worker);
	}

	public Part addPart(Part part) {
		return partDao.savePart(part);
	}

	public Part getPart(Long id) {
		return partDao.getPart(id);
	}

}
