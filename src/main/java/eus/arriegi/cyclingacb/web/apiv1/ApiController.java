package eus.arriegi.cyclingacb.web.apiv1;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eus.arriegi.cyclingacb.domain.Client;
import eus.arriegi.cyclingacb.service.ClientManager;
import eus.arriegi.cyclingacb.service.PartManager;
import eus.arriegi.cyclingacb.service.WorkerManager;

@RestController
@RequestMapping(value = "/api/v1/*")
public class ApiController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
    ClientManager clientManager;
	@Autowired
	WorkerManager workerManager;
	@Autowired
	PartManager partManager;
	
	@PreAuthorize(value = "isAuthenticated()")
	@RequestMapping(value = "clients", method = RequestMethod.GET)
	public ResponseEntity<List<Client>> getClients() {
		List<Client> clients = clientManager.getClients();
        if(clients.isEmpty()){
            return new ResponseEntity<List<Client>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}
	
	@PreAuthorize(value = "isAuthenticated()")
	@RequestMapping(value = "client/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {
		Client client = clientManager.getClient(id);
		logger.warn(client);
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		}
	}
	
	@PreAuthorize(value = "hasAuthority('ADMIN','LANGILE')")
	@RequestMapping(value = "client", method = RequestMethod.PUT)
	public ResponseEntity<Client> updateClient(@RequestBody Client client) {
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		} else {
			client = clientManager.updateClient(client);
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		}
	}
	
	@PreAuthorize(value = "hasAuthority('ADMIN','LANGILE')")
	@RequestMapping(value = "client", method = RequestMethod.POST)
	public ResponseEntity<Client> createClient(@RequestBody Client client) {
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		} else {
			client = clientManager.addClient(client);
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		}
	}
	
	@PreAuthorize(value = "hasAuthority('ADMIN','LANGILE')")
	@RequestMapping(value = "client/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Client> removeClient(@PathVariable("id") Long id) {
		Client client = clientManager.getClient(id);
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		} else {
			clientManager.removeClient(id);
			return new ResponseEntity<Client>(client, HttpStatus.OK);
		}
	}

}
