package com.fms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fms.model.Person;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
/**
 * Person utility methods
 * 
 * Uses Hibernate to save/retrieve values from database
 * 
 */
public class PersonServiceImpl implements IPersonService {

	@Autowired
	SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addPerson(Person p) {
		System.out.println("Saving Person: " + p.getName() + " to DB");
		getCurrentSession().save(p);
	}

	public void removePerson(Person p) {
		getCurrentSession().delete(p);
	}

	public void updatePerson(Person p) {
		getCurrentSession().saveOrUpdate(p);
	}

	public Person getPerson(long personId) {
		return (Person) getCurrentSession().get(Person.class, personId);
	}

}
