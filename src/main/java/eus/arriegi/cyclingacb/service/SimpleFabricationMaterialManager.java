package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.FabricationMaterial;
import eus.arriegi.cyclingacb.repository.FabricationMaterialDao;

@Component
public class SimpleFabricationMaterialManager implements FabricationMaterialManager {

	@Autowired
	private FabricationMaterialDao fabricationMaterialDao;
	
	public List<FabricationMaterial> getFabricationMaterials() {
		return fabricationMaterialDao.getFabricationMaterials();
	}
	
	public FabricationMaterial getFabricationMaterial(Long id) {
		return fabricationMaterialDao.getFabricationMaterial(id);
	}

	public FabricationMaterial addFabricationMaterial(FabricationMaterial fabricationMaterial) {
		return fabricationMaterialDao.saveFabricationMaterial(fabricationMaterial);
	}

	public FabricationMaterial updateFabricationMaterial(FabricationMaterial fabricationMaterial) {
		return fabricationMaterialDao.updateFabricationMaterial(fabricationMaterial);
	}

	public void removeFabricationMaterial(FabricationMaterial fabricationMaterial) {
		fabricationMaterialDao.removeFabricationMaterial(fabricationMaterial);
	}
	
	public void removeFabricationMaterial(Long id) {
		fabricationMaterialDao.removeFabricationMaterial(id);
	}

}
