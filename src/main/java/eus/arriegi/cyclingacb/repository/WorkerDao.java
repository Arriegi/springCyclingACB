package eus.arriegi.cyclingacb.repository;

import java.util.List;

import eus.arriegi.cyclingacb.domain.Worker;

public interface WorkerDao {
	
	public List<Worker> getWorkers();
	
	public List<Worker> getWorkers(String name);
	
	public Worker getWorker(Long id);
	
	public Worker getWorker(String email);
	
    public Worker saveWorker(Worker worker);
    
    public void removeWorker(Worker worker);

    public void removeWorker(Long id);
    
    public Worker updateWorker(Worker worker);
    
}
