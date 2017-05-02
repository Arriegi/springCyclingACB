package eus.arriegi.cyclingacb.service;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Worker;

public interface WorkerManager {
	
public List<Worker> getWorkers();
	
	public Worker getWorker(Long id);
	
	public Worker getWorker(String email);
	
	public void addWorker(Worker worker);

	public Worker updateWorker(Worker worker);
	
	public void removeWorker(Worker worker);
	
	public void removeWorker(Long id);
	
}
