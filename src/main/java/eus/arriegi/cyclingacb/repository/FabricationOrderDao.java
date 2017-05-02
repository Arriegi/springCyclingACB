package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Client;
import eus.arriegi.cyclingacb.domain.FabricationOrder;

public interface FabricationOrderDao {
	
	public List<FabricationOrder> getFabricationOrders();
	
	public FabricationOrder getFabricationOrder(Long id);
	
    public List<FabricationOrder> getFabricationOrders(Client client);
    
    public FabricationOrder saveFabricationOrder(FabricationOrder fabricationOrder);
    
    public void removeFabricationOrder(FabricationOrder fabricationOrder);
    
    public void removeFabricationOrder(Long id);
    
    public FabricationOrder updateFabricationOrder(FabricationOrder fabricationOrder);
    
}
