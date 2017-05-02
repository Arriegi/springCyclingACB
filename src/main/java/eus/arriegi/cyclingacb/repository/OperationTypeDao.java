package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.domain.OperationType;

public interface OperationTypeDao {
	
	public List<OperationType> getOperationTypes();
	
	public List<Operation> getOperations(OperationType operationType);
	
	public List<Operation> getOperations(String text);
	
	public List<Operation> getOperations(Long operationTypeId);
	
	public OperationType getOperationType(Long id);
	
    public List<OperationType> getOperationTypes(String name);
    
    public OperationType saveOperationType(OperationType operationType);
    
    public void removeOperationType(OperationType operationType);
    
    public void removeOperationType(Long id);
    
    public OperationType updateOperationType(OperationType operationType);
    
}
