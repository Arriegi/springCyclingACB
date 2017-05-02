package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Material;
import eus.arriegi.cyclingacb.repository.MaterialDao;

@Component
public class SimpleMaterialManager implements MaterialManager {

	@Autowired
	private MaterialDao materialDao;
	
	public List<Material> getMaterials() {
		return materialDao.getMaterials();
	}
	
	public Material getMaterial(Long id) {
		return materialDao.getMaterial(id);
	}

	public void addMaterial(Material material) {
		materialDao.saveMaterial(material);
	}

	public Material updateMaterial(Material material) {
		return materialDao.updateMaterial(material);
	}

	public void removeMaterial(Material material) {
		materialDao.removeMaterial(material);
	}
	
	public void removeMaterial(Long id) {
		materialDao.removeMaterial(id);
	}

}
