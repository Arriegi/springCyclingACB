package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.FabricationMaterial;
import eus.arriegi.cyclingacb.domain.FabricationOrder;

public interface FabricationMaterialDao {
	
	public List<FabricationMaterial> getFabricationMaterials();
	
	public FabricationMaterial getFabricationMaterial(Long id);
	
    public List<FabricationMaterial> getFabricationMaterials(FabricationOrder fabricationOrderO);
    
    public FabricationMaterial saveFabricationMaterial(FabricationMaterial fabricationMaterial);
    
    public void removeFabricationMaterial(FabricationMaterial fabricationMaterial);
    
    public void removeFabricationMaterial(Long id);
    
    public FabricationMaterial updateFabricationMaterial(FabricationMaterial fabricationMaterial);
    
}
