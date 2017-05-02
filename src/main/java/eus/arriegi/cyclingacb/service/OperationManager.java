package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Operation;

public interface OperationManager {
	
public List<Operation> getOperations();
	
	public Operation getOperation(Long id);
	
	public void addOperation(Operation operation);

	public Operation updateOperation(Operation operation);
	
	public void removeOperation(Operation operation);
	
	public void removeOperation(Long id);
	
}
