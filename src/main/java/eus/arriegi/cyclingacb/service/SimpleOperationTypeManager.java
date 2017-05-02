package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.domain.OperationType;
import eus.arriegi.cyclingacb.repository.OperationTypeDao;

@Component
public class SimpleOperationTypeManager implements OperationTypeManager {

	@Autowired
	private OperationTypeDao operationTypeDao;
	
	public List<OperationType> getOperationTypes() {
		return operationTypeDao.getOperationTypes();
	}
	
	public List<Operation> getOperations(OperationType operationType) {
		return operationTypeDao.getOperations(operationType);
	}
	
	public List<Operation> getOperations(Long operationTypeId) {
		return operationTypeDao.getOperations(operationTypeId);
	}
	
	public List<Operation> getOperations(String text) {
		return operationTypeDao.getOperations(text);
	}
	
	public OperationType getOperationType(Long id) {
		return operationTypeDao.getOperationType(id);
	}

	public void addOperationType(OperationType operationType) {
		operationTypeDao.saveOperationType(operationType);
	}

	public OperationType updateOperationType(OperationType operationType) {
		return operationTypeDao.updateOperationType(operationType);
	}

	public void removeOperationType(OperationType operationType) {
		operationTypeDao.removeOperationType(operationType);
	}
	
	public void removeOperationType(Long id) {
		operationTypeDao.removeOperationType(id);
	}

}
