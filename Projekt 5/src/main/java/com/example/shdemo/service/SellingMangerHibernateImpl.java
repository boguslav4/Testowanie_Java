package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Computer;
import com.example.shdemo.domain.Customer;
import com.example.shdemo.domain.Service;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addClient(Customer customer) {
		customer.setId(null);
		sessionFactory.getCurrentSession().persist(customer);
	}
	
	@Override
	public void deleteClient(Customer customer) {
		customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class,
				customer.getId());
		
		// lazy loading here
		for (Computer computer : customer.getComputers()) {
			computer.setSold(false);
			sessionFactory.getCurrentSession().update(computer);
		}
		sessionFactory.getCurrentSession().delete(customer);
	}

	@Override
	public List<Computer> getOwnedComputers(Customer customer) {
		customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class,
				customer.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Computer> computers = new ArrayList<Computer>(customer.getComputers());
		return computers;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("customer.all")
				.list();
	}

	@Override
	public Customer findCustomerByPesel(String pesel) {
		return (Customer) sessionFactory.getCurrentSession().getNamedQuery("customer.byPesel").setString("pesel", pesel).uniqueResult();
	}


	@Override
	public Long addNewComputer(Computer computer) {
		computer.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(computer);
	}

	@Override
	public void sellComputer(Long customerId, Long computerId) {
		Customer customer = (Customer) sessionFactory.getCurrentSession().get(
				Customer.class, customerId);
		Computer computer = (Computer) sessionFactory.getCurrentSession()
				.get(Computer.class, computerId);
		computer.setSold(true);
		customer.getComputers().add(computer);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Computer> getAvailableComputers() {
		return sessionFactory.getCurrentSession().getNamedQuery("computer.unsold")
				.list();
	}
	@Override
	public void disposeComputer(Customer customer, Computer computer) {

		customer = (Customer) sessionFactory.getCurrentSession().get(Customer.class,
				customer.getId());
		computer = (Computer) sessionFactory.getCurrentSession().get(Computer.class,
				computer.getId());

		Computer toRemove = null;
		// lazy loading here (customer.getComputers)
		for (Computer aComputer : customer.getComputers())
			if (aComputer.getId().compareTo(computer.getId()) == 0) {
				toRemove = aComputer;
				break;
			}

		if (toRemove != null)
			customer.getComputers().remove(toRemove);

		computer.setSold(false);
	}

	@Override
	public Computer findComputerById(Long id) {
		return (Computer) sessionFactory.getCurrentSession().get(Computer.class, id);
	}

	@Override
	public void addService(Service service) {
		service.setId(null);
		sessionFactory.getCurrentSession().persist(service);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Service> getAvailableServices(Computer computer) {
		return sessionFactory.getCurrentSession().getNamedQuery("service.byBrand").setString("name", computer.getCpuBrand()).list();
	}

}
