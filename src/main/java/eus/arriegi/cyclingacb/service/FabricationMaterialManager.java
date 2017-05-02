package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.FabricationMaterial;

public interface FabricationMaterialManager {
	
public List<FabricationMaterial> getFabricationMaterials();
	
	public FabricationMaterial getFabricationMaterial(Long id);
	
	public FabricationMaterial addFabricationMaterial(FabricationMaterial fabricationMaterial);

	public FabricationMaterial updateFabricationMaterial(FabricationMaterial fabricationMaterial);
	
	public void removeFabricationMaterial(FabricationMaterial fabricationMaterial);
	
	public void removeFabricationMaterial(Long id);
	
}
