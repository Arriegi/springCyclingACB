package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.WorkOperation;
import eus.arriegi.cyclingacb.repository.WorkOperationDao;

@Component
public class SimpleWorkOperationManager implements WorkOperationManager {

	@Autowired
	private WorkOperationDao workOperationDao;
	
	public List<WorkOperation> getWorkOperations() {
		return workOperationDao.getWorkOperations();
	}
	
	public WorkOperation getWorkOperation(Long id) {
		return workOperationDao.getWorkOperation(id);
	}

	public WorkOperation addWorkOperation(WorkOperation workOperation) {
		return workOperationDao.saveWorkOperation(workOperation);
	}

	public WorkOperation updateWorkOperation(WorkOperation workOperation) {
		return workOperationDao.updateWorkOperation(workOperation);
	}

	public void removeWorkOperation(WorkOperation workOperation) {
		workOperationDao.removeWorkOperation(workOperation);
	}
	
	public void removeWorkOperation(Long id) {
		workOperationDao.removeWorkOperation(id);
	}

}
