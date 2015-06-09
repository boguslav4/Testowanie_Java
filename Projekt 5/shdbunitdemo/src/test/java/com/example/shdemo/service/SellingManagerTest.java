package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Motocycle;
import com.example.shdemo.domain.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String NAME_1 = "Janek";
	private final String PIN_1 = "1234";

	private final String NAME_2 = "Winry";
	private final String PIN_2 = "4321";

	private final String MODEL_1 = "GN 125";
	private final String MAKE_1 = "Suzuki";

	private final String MODEL_2 = "Kawasaki";
	private final String MAKE_2 = "Vulcan";

	@Test
	public void addPersonCheck() {

		List<Person> retrievedPersons = sellingManager.getAllPersons();

		// If there is a person with PIN_1 delete it
		for (Person person : retrievedPersons) {
			if (person.getPin().equals(PIN_1)) {
				sellingManager.deletePerson(person);
			}
		}

		Person person = new Person();
		person.setFirstName(NAME_1);
		person.setPin(PIN_1);
		// ... other properties here

		// Pin is Unique
		sellingManager.addPerson(person);

		Person retrievedPerson = sellingManager.findPersonByPin(PIN_1);

		assertEquals(NAME_1, retrievedPerson.getFirstName());
		assertEquals(PIN_1, retrievedPerson.getPin());
		// ... check other properties here
	}
	
	@Test
	public void deletePersonCheck() {

		List<Person> retrievedPersons = sellingManager.getAllPersons();

		// If there is a person with PIN_1 delete it
		for (Person person : retrievedPersons) {
			if (person.getPin().equals(PIN_1)) {
				sellingManager.deletePerson(person);
			}
		}

		Person person = new Person();
		person.setFirstName(NAME_1);
		person.setPin(PIN_1);
		// ... other properties here

		// Pin is Unique
		sellingManager.addPerson(person);

		Person retrievedPerson = sellingManager.findPersonByPin(PIN_1);
		
		sellingManager.deletePerson(retrievedPerson);
		
		retrievedPerson = sellingManager.findPersonByPin(PIN_1);
		
		assertNull(retrievedPerson);
	}

	@Test
	public void addMotocykleCheck() {

		Motocycle motocykle = new Motocycle();
		motocykle.setMake(MAKE_1);
		motocykle.setModel(MODEL_1);
		// ... other properties here

		Long motocykleId = sellingManager.addNewMotocycle(motocykle);

		Motocycle retrievedMotocykle = sellingManager.findMotocycleById(motocykleId);
		assertEquals(MAKE_1, retrievedMotocykle.getMake());
		assertEquals(MODEL_1, retrievedMotocykle.getModel());
		// ... check other properties here

	}


	@Test
	public void sellMotocykleCheck() {

		Person person = new Person();
		person.setFirstName(NAME_2);
		person.setPin(PIN_2);

		sellingManager.addPerson(person);

		Person retrievedPerson = sellingManager.findPersonByPin(PIN_2);

		Motocycle motocykle = new Motocycle();
		motocykle.setMake(MAKE_2);
		motocykle.setModel(MODEL_2);

		Long motocykleId = sellingManager.addNewMotocycle(motocykle);

		sellingManager.sellMotocycle(retrievedPerson.getId(), motocykleId);

		List<Motocycle> ownedMotocykles = sellingManager.getOwnedMotocycles(retrievedPerson);

		assertEquals(1, ownedMotocykles.size());
		assertEquals(MAKE_2, ownedMotocykles.get(0).getMake());
		assertEquals(MODEL_2, ownedMotocykles.get(0).getModel());
	}

	// @Test -
	public void disposeMotocykleCheck() {
		// Do it yourself
	}

}
