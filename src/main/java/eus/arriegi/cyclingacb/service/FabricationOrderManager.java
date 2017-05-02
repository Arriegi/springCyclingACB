package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.FabricationOrder;

public interface FabricationOrderManager {
	
public List<FabricationOrder> getFabricationOrders();
	
	public FabricationOrder getFabricationOrder(Long id);
	
	public void addFabricationOrder(FabricationOrder fabricationOrder);

	public FabricationOrder updateFabricationOrder(FabricationOrder fabricationOrder);
	
	public void removeFabricationOrder(FabricationOrder fabricationOrder);
	
	public void removeFabricationOrder(Long id);
	
}
