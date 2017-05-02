package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.domain.OperationType;

public interface OperationTypeManager {
	
public List<OperationType> getOperationTypes();

	public List<Operation> getOperations(Long id);
	
	public List<Operation> getOperations(OperationType operationType);
	
	public List<Operation> getOperations(String operationTypeName);
	
	public OperationType getOperationType(Long id);
	
	public void addOperationType(OperationType operationType);

	public OperationType updateOperationType(OperationType operationType);
	
	public void removeOperationType(OperationType operationType);
	
	public void removeOperationType(Long id);
	
}
