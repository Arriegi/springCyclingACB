package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.FabricationOrder;
import eus.arriegi.cyclingacb.repository.FabricationOrderDao;

@Component
public class SimpleFabricationOrderManager implements FabricationOrderManager {

	@Autowired
	private FabricationOrderDao fabricationOrderDao;
	
	public List<FabricationOrder> getFabricationOrders() {
		return fabricationOrderDao.getFabricationOrders();
	}
	
	public FabricationOrder getFabricationOrder(Long id) {
		return fabricationOrderDao.getFabricationOrder(id);
	}

	public void addFabricationOrder(FabricationOrder fabricationOrder) {
		fabricationOrderDao.saveFabricationOrder(fabricationOrder);
	}

	public FabricationOrder updateFabricationOrder(FabricationOrder fabricationOrder) {
		return fabricationOrderDao.updateFabricationOrder(fabricationOrder);
	}

	public void removeFabricationOrder(FabricationOrder fabricationOrder) {
		fabricationOrderDao.removeFabricationOrder(fabricationOrder);
	}
	
	public void removeFabricationOrder(Long id) {
		fabricationOrderDao.removeFabricationOrder(id);
	}

}
