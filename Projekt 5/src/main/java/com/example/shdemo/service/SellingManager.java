package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Computer;
import com.example.shdemo.domain.Customer;
import com.example.shdemo.domain.Service;

public interface SellingManager {
	
	void addClient(Customer customer);
	List<Customer> getAllClients();
	void deleteClient(Customer customer);
	Customer findCustomerByPesel(String pesel);
	
	Long addNewComputer(Computer computer);
	List<Computer> getAvailableComputers();
	void disposeComputer(Customer customer, Computer computer);
	Computer findComputerById(Long id);

	List<Computer> getOwnedComputers(Customer customer);
	void sellComputer(Long customerId, Long computerId);

	void addService(Service service);
	List<Service> getAvailableServices(Computer computer);
}
