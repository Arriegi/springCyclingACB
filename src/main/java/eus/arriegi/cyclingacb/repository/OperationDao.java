package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Operation;

public interface OperationDao {
	
	public List<Operation> getOperations();
	
	public Operation getOperation(Long id);
	
    public List<Operation> getOperations(String name);
    
    public Operation saveOperation(Operation operation);
    
    public void removeOperation(Operation operation);
    
    public void removeOperation(Long id);
    
    public Operation updateOperation(Operation operation);
    
}
