package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Motocycle;
import com.example.shdemo.domain.Person;

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
	public void addPerson(Person person) {
		person.setId(null);
		sessionFactory.getCurrentSession().persist(person);
	}
	
	@Override
	public void deletePerson(Person person) {
		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		
		// lazy loading here
		for (Motocycle motocycle : person.getMotocycles()) {
			motocycle.setSold(false);
			sessionFactory.getCurrentSession().update(motocycle);
		}
		sessionFactory.getCurrentSession().delete(person);
	}

	@Override
	public List<Motocycle> getOwnedMotocycles(Person person) {
		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Motocycle> motocycles = new ArrayList<Motocycle>(person.getMotocycles());
		return motocycles;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons() {
		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
				.list();
	}

	@Override
	public Person findPersonByPin(String pin) {
		return (Person) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
	}


	@Override
	public Long addNewMotocycle(Motocycle motocycle) {
		motocycle.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(motocycle);
	}

	@Override
	public void sellMotocycle(Long personId, Long motocycleId) {
		Person person = (Person) sessionFactory.getCurrentSession().get(
				Person.class, personId);
		Motocycle motocycle = (Motocycle) sessionFactory.getCurrentSession()
				.get(Motocycle.class, motocycleId);
		motocycle.setSold(true);
		person.getMotocycles().add(motocycle);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Motocycle> getAvailableMotocycles() {
		return sessionFactory.getCurrentSession().getNamedQuery("motocycl.unsold")
				.list();
	}
	@Override
	public void disposeMotocycle(Person person, Motocycle motocycle) {

		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
				person.getId());
		motocycle = (Motocycle) sessionFactory.getCurrentSession().get(Motocycle.class,
				motocycle.getId());

		Motocycle toRemove = null;
		// lazy loading here (person.getMotocycles)
		for (Motocycle aMotocyle : person.getMotocycles())
			if (aMotocyle.getId().compareTo(motocycle.getId()) == 0) {
				toRemove = aMotocyle;
				break;
			}

		if (toRemove != null)
			person.getMotocycles().remove(toRemove);

		motocycle.setSold(false);
	}

	@Override
	public Motocycle findMotocycleById(Long id) {
		return (Motocycle) sessionFactory.getCurrentSession().get(Motocycle.class, id);
	}

}
