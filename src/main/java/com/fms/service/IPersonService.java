package com.fms.service;

import com.fms.model.Person;

/**
 * Person utility methods
 * 
 */
public interface IPersonService {
	void addPerson(Person p);

	void removePerson(Person p);

	void updatePerson(Person p);

	Person getPerson(long personId);
}
