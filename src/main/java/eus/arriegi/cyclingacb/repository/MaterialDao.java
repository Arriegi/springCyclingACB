package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Material;

public interface MaterialDao {
	
	public List<Material> getMaterials();
	
	public Material getMaterial(Long id);
	
    public List<Material> getMaterials(String name);
    
    public Material saveMaterial(Material material);
    
    public void removeMaterial(Material material);
    
    public void removeMaterial(Long id);
    
    public Material updateMaterial(Material material);
    
}
