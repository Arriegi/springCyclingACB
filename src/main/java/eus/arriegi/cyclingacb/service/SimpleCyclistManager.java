package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Country;
import eus.arriegi.cyclingacb.domain.Cyclist;
import eus.arriegi.cyclingacb.repository.CyclistDao;

@Component
public class SimpleCyclistManager implements CyclistManager {

	@Autowired
	private CyclistDao cyclistDao;
	
	public List<Cyclist> getCyclists() {
		return cyclistDao.getCyclists();
	}
	
	public Cyclist getCyclist(Long id) {
		return cyclistDao.getCyclist(id);
	}

	public Cyclist addCyclist(Cyclist cyclist) {
		return cyclistDao.saveCyclist(cyclist);
	}

	public Cyclist updateCyclist(Cyclist cyclist) {
		return cyclistDao.updateCyclist(cyclist);
	}

	public void removeCyclist(Cyclist cyclist) {
		cyclistDao.removeCyclist(cyclist);
	}
	
	public void removeCyclist(Long id) {
		cyclistDao.removeCyclist(id);
	}

	public Country getCountry(Long id) {
		return cyclistDao.getCountry(id);
	}
	
	public List<Country> getCountries() {
		return cyclistDao.getCountries();
	}

}
