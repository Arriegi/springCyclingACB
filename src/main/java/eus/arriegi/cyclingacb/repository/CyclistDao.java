package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Country;
import eus.arriegi.cyclingacb.domain.Cyclist;

public interface CyclistDao {
	
	public List<Cyclist> getCyclists();
	
	public List<Cyclist> getCyclists(String name);
	
	public Cyclist getCyclist(Long id);
	
    public Cyclist saveCyclist(Cyclist cyclist);
    
    public void removeCyclist(Cyclist cyclist);

    public void removeCyclist(Long id);
    
    public Cyclist updateCyclist(Cyclist cyclist);
    
    public Country getCountry(Long id);
    
    public List<Country> getCountries();
    
}
