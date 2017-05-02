package eus.arriegi.cyclingacb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eus.arriegi.cyclingacb.domain.Worker;
import eus.arriegi.cyclingacb.repository.WorkerDao;

@Component
public class SimpleWorkerManager implements WorkerManager {

	@Autowired
	private WorkerDao workerDao;
	
	public List<Worker> getWorkers() {
		return workerDao.getWorkers();
	}
	
	public Worker getWorker(Long id) {
		return workerDao.getWorker(id);
	}
	
	public Worker getWorker(String email) {
		return workerDao.getWorker(email);
	}

	public void addWorker(Worker worker) {
		workerDao.saveWorker(worker);
	}

	public Worker updateWorker(Worker worker) {
		return workerDao.updateWorker(worker);
	}

	public void removeWorker(Worker worker) {
		workerDao.removeWorker(worker);
	}
	
	public void removeWorker(Long id) {
		workerDao.removeWorker(id);
	}

}
