package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Motocycle;
import com.example.shdemo.domain.Person;

public interface SellingManager {
	
	void addPerson(Person person);
	List<Person> getAllPersons();
	void deletePerson(Person person);
	Person findPersonByPin(String pin);
	
	//
	Long addNewMotocycle(Motocycle motocycle);
	List<Motocycle> getAvailableMotocycles();
	void disposeMotocycle(Person person, Motocycle motocycle);
	Motocycle findMotocycleById(Long id);

	List<Motocycle> getOwnedMotocycles(Person person);
	void sellMotocycle(Long personId, Long motocycleId);

}
