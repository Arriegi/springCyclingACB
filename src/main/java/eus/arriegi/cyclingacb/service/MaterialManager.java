package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Material;

public interface MaterialManager {
	
public List<Material> getMaterials();
	
	public Material getMaterial(Long id);
	
	public void addMaterial(Material material);

	public Material updateMaterial(Material material);
	
	public void removeMaterial(Material material);
	
	public void removeMaterial(Long id);
	
}
