package eus.arriegi.cyclingacb.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eus.arriegi.cyclingacb.domain.Client;

public class JPAClientDaoTests {
	private ApplicationContext context;
	private ClientDao clientDao;
	
	private List<Client> initialClients = new ArrayList<Client>();

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
		clientDao = (ClientDao) context.getBean("clientDao");
		
		List<Client> clients = clientDao.getClients();
		initialClients = clients;
		for(Client client : clients) {
			clientDao.removeClient(client);
		}
		Client client = new Client();
		client.setName("Irizar S.Coop.");
		client.setEmail("esanjuan@irizar.com");
		client.setPhone("943564587");
		client.setContactName("Edurne Sanjuan");
		client = clientDao.saveClient(client);
	}
	
	@After
	public void tearDown() {
		List<Client> clients = clientDao.getClients();
		for(Client client : clients) {
			clientDao.removeClient(client);
		}
		for(Client client:initialClients) {
			clientDao.saveClient(client);
		}
	}
	
	@Test
	public void updateClient() {
		List<Client> clients = clientDao.getClients();
		assertEquals(1,clients.size());
		Client client1 = clients.get(0);
		client1.setContactName("Ana Eceiza");
		Client updatedClient = clientDao.updateClient(client1);
		assertEquals("Ana Eceiza",updatedClient.getContactName());
		clients = clientDao.getClients();
		assertEquals(1,clients.size());
		assertEquals(updatedClient,clients.get(0));
		clients = clientDao.getClients("irizar");
		assertEquals(1,clients.size());
	}

	@Test
	public void testClients() {
		List<Client> clients = clientDao.getClients();
		assertEquals(1,clients.size());
		Client client = clients.get(0);
		assertTrue(client.getId()>0);
		clients = clientDao.getClients();
		assertEquals(1,clients.size());
		clientDao.removeClient(client);
		clients = clientDao.getClients();
		assertEquals(0,clients.size());
	}

}
