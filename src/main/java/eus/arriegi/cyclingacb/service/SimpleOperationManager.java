package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.repository.OperationDao;

@Component
public class SimpleOperationManager implements OperationManager {

	@Autowired
	private OperationDao operationDao;
	
	public List<Operation> getOperations() {
		return operationDao.getOperations();
	}
	
	public Operation getOperation(Long id) {
		return operationDao.getOperation(id);
	}

	public void addOperation(Operation operation) {
		operationDao.saveOperation(operation);
	}

	public Operation updateOperation(Operation operation) {
		return operationDao.updateOperation(operation);
	}

	public void removeOperation(Operation operation) {
		operationDao.removeOperation(operation);
	}
	
	public void removeOperation(Long id) {
		operationDao.removeOperation(id);
	}

}
