package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.FabricationOrder;
import eus.arriegi.cyclingacb.domain.WorkOperation;

public interface WorkOperationDao {
	
	public List<WorkOperation> getWorkOperations();
	
	public WorkOperation getWorkOperation(Long id);
	
    public List<WorkOperation> getWorkOperations(FabricationOrder fabricationOrderO);
    
    public WorkOperation saveWorkOperation(WorkOperation workOperation);
    
    public void removeWorkOperation(WorkOperation workOperation);
    
    public void removeWorkOperation(Long id);
    
    public WorkOperation updateWorkOperation(WorkOperation workOperation);
    
}
