package com.fms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fms.util.FMSLogger;
import com.fms.model.Person;

/**
 * Person utility methods
 * 
 * Uses Hibernate to save/retrieve values from database
 * 
 */
public class PersonServiceImpl implements IPersonService {

	SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void addPerson(Person p) {
		FMSLogger.log.debug("\nSaving Person: " + p.getName() + " to DB");
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
