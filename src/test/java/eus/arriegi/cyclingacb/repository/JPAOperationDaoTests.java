package eus.arriegi.cyclingacb.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eus.arriegi.cyclingacb.domain.Operation;
import eus.arriegi.cyclingacb.domain.OperationType;

public class JPAOperationDaoTests {
	private ApplicationContext context;
	private OperationDao operationDao;
	private OperationTypeDao operationTypeDao;

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
		operationDao = (OperationDao) context.getBean("operationDao");
		operationTypeDao = (OperationTypeDao) context.getBean("operationTypeDao");
		
		List<OperationType> operationTypes = operationTypeDao.getOperationTypes("inpro");
		List<Operation> operations = operationDao.getOperations();
		for(Operation operation : operations) {
			operationDao.removeOperation(operation);
		}
		Operation operation = new Operation();
		operation.setName("Bilera internoa");
		operation.setOperationType(operationTypes.get(0));
		operation = operationDao.saveOperation(operation);
	}
	
	@After
	public void tearDown() {
		List<Operation> operations = operationDao.getOperations();
		for(Operation operation : operations) {
			operationDao.removeOperation(operation);
		}
	}
	
	@Test
	public void testOperationsByType() {
		List<Operation> operations = operationTypeDao.getOperations("inpro");
		assertEquals(1,operations.size());
		Operation op = new Operation();
		op.setName("Bezeroarekin bilera");
		op.setOperationType(operationTypeDao.getOperationTypes("inpro").get(0));
		op = operationDao.saveOperation(op);
		operations = operationTypeDao.getOperations("inpro");
		assertEquals(2,operations.size());
		operationDao.removeOperation(op);
		operations = operationTypeDao.getOperations(operationTypeDao.getOperationTypes("inpro").get(0));
		assertEquals(1,operations.size());
	}
	
	@Test
	public void testOperations() {
		List<Operation> operations = operationDao.getOperations();
		assertEquals(1,operations.size());
		Operation operation = operations.get(0);
		assertTrue(operation.getId()>0);
		operations = operationDao.getOperations();
		assertEquals(1,operations.size());
	}

}
