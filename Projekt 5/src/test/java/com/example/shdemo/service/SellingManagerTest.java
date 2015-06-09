package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Computer;
import com.example.shdemo.domain.Customer;
import com.example.shdemo.domain.Service;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String NAME_1 = "Ryszard";
	private final String PESEL_1 = "73031393833";
	private final String OCCUP_1 = "Handlarz";

	private final String NAME_2 = "Marian";
	private final String PESEL_2 = "51021825563";
	private final String OCCUP_2 = "Stolarz";

	private final String CPB_1 = "Intel";
	private final String CPU_1 = "Pentium III";
	private final String GPU_1 = "Intel Integrated";
	private final String HDD_1 = "WD5000MB";

	private final String CPB_2 = "AMD";
	private final String CPU_2 = "Athlon X2";
	private final String GPU_2 = "Radeon 4890HD";
	private final String HDD_2 = "MX42000MB";

	private final String CPB_3 = "Intel";
	private final String CPU_3 = "Atom";
	private final String GPU_3 = "GeForce 320";
	private final String HDD_3 = "BC3500GB";
	
	private final String SVN_1 = "Intel";
	private final String SVS_1 = "Professional";
	private final String SVC_1 = "33102";
	
	@Test
	public void addClientCheck() {

		List<Customer> retrievedClients = sellingManager.getAllClients();

		// If there is a client with PIN_1 delete it
		for (Customer client : retrievedClients) {
			if (client.getPesel().equals(PESEL_1)) {
				sellingManager.deleteClient(client);
			}
		}

		Customer customer = new Customer();
		customer.setName(NAME_1);
		customer.setOccupation(OCCUP_1);
		customer.setPesel(PESEL_1);
		// ... other properties here

		// Pin is Unique
		sellingManager.addClient(customer);

		Customer retrievedClient = sellingManager.findCustomerByPesel(PESEL_1);

		assertEquals(NAME_1, retrievedClient.getName());
		assertEquals(OCCUP_1, retrievedClient.getOccupation());
		assertEquals(PESEL_1, retrievedClient.getPesel());
		// ... check other properties here
	}

	@Test
	public void addComputerCheck() {

		Computer computer = new Computer();
		computer.setCpuBrand(CPB_1);
		computer.setCpu(CPU_1);
		computer.setGpu(GPU_1);
		computer.setHdd(HDD_1);
		// ... other properties here

		Long computerId = sellingManager.addNewComputer(computer);

		Computer retrievedComputer = sellingManager.findComputerById(computerId);
		assertEquals(CPB_1, retrievedComputer.getCpuBrand());
		assertEquals(CPU_1, retrievedComputer.getCpu());
		assertEquals(GPU_1, retrievedComputer.getGpu());
		assertEquals(HDD_1, retrievedComputer.getHdd());
		// ... check other properties here

	}

	@Test
	public void sellComputerCheck() {

		Customer customer = new Customer();
		customer.setName(NAME_2);
		customer.setOccupation(OCCUP_2);
		customer.setPesel(PESEL_2);

		sellingManager.addClient(customer);

		Customer retrievedCustomer = sellingManager.findCustomerByPesel(PESEL_2);

		Computer computer = new Computer();
		computer.setCpuBrand(CPB_2);
		computer.setCpu(CPU_2);
		computer.setGpu(GPU_2);
		computer.setHdd(HDD_2);

		Long computerId = sellingManager.addNewComputer(computer);

		sellingManager.sellComputer(retrievedCustomer.getId(), computerId);

		List<Computer> ownedComputers = sellingManager.getOwnedComputers(retrievedCustomer);

		assertEquals(1, ownedComputers.size());
		assertEquals(CPB_2, ownedComputers.get(0).getCpuBrand());
		assertEquals(CPU_2, ownedComputers.get(0).getCpu());
		assertEquals(GPU_2, ownedComputers.get(0).getGpu());
		assertEquals(HDD_2, ownedComputers.get(0).getHdd());
	}

	@Test
	public void computerServiceCheck() {
		
		Computer computer = new Computer();
		computer.setCpuBrand(CPB_3);
		computer.setCpu(CPU_3);
		computer.setGpu(GPU_3);
		computer.setHdd(HDD_3);

		sellingManager.addNewComputer(computer);
		
		Service service = new Service();
		service.setName(SVN_1);
		service.setRenown(SVS_1);
		service.setCode(SVC_1);

		sellingManager.addService(service);

		List<Service> availableServices = sellingManager.getAvailableServices(computer);
		
		assertEquals(1, availableServices.size());
		assertEquals(CPB_3, availableServices.get(0).getName());
		assertEquals(true, availableServices.get(0).getActive());
	}
	
	@Test
	public void disposeComputerCheck() {
		
		Customer customer = new Customer();
		customer.setName(NAME_2);
		customer.setOccupation(OCCUP_2);
		customer.setPesel(PESEL_2);

		sellingManager.addClient(customer);

		Computer computer = new Computer();
		computer.setCpu(CPU_2);
		computer.setGpu(GPU_2);
		computer.setHdd(HDD_2);

		Customer retrievedCustomer = sellingManager.findCustomerByPesel(PESEL_2);
		
		sellingManager.addNewComputer(computer);

		sellingManager.disposeComputer(customer, computer);

		List<Computer> ownedComputers = sellingManager.getOwnedComputers(retrievedCustomer);

		assertEquals(0, ownedComputers.size());
	}

}
