package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.WorkOperation;

public interface WorkOperationManager {
	
public List<WorkOperation> getWorkOperations();
	
	public WorkOperation getWorkOperation(Long id);
	
	public WorkOperation addWorkOperation(WorkOperation workOperation);

	public WorkOperation updateWorkOperation(WorkOperation workOperation);
	
	public void removeWorkOperation(WorkOperation workOperation);
	
	public void removeWorkOperation(Long id);
	
}
