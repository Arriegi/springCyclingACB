package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Country;
import eus.arriegi.cyclingacb.domain.Cyclist;

public interface CyclistManager {
	
	public List<Cyclist> getCyclists();
	
	public Cyclist getCyclist(Long id);
	
	public Cyclist addCyclist(Cyclist cyclist);

	public Cyclist updateCyclist(Cyclist cyclist);
	
	public void removeCyclist(Cyclist cyclist);
	
	public void removeCyclist(Long id);
	
	public Country getCountry(Long id);
	
	public List<Country> getCountries();
	
}
