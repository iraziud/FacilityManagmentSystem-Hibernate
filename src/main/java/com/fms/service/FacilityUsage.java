/**
 * 
 */
package com.fms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fms.util.FMSLogger;
import com.fms.model.*;

@Service
@Transactional(propagation = Propagation.REQUIRED)
/**
 * Facility usage utility methods
 *
 * Uses Hibernate to save/retrieve values from database
 */
public class FacilityUsage implements IFacilityUsage {

	SessionFactory sessionFactory;

	IPersonService personService;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setPersonService(IPersonService personService) {
		this.personService = personService;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean isInUseDuringInterval() {
		return false;
	}

	public void assignFacilityToUse(Person person, IFacility facility) {
		FMSLogger.log.debug("\nAssigning facility " + facility.getFacilityInfo() + " to Person : " + person.getId() + ", "
				+ person.getName());
		person.usesFacility.add((Facility) facility);
		personService.updatePerson(person);
	}

	public boolean vacateFacility(Person person, IFacility facility) {
		if (person.usesFacility.contains(facility)) {
			FMSLogger.log.debug("\nVacating facility " + facility.getFacilityInfo() + " by Person : " + person.getId() + ", "
					+ person.getName());
			person.usesFacility.remove(facility);
		}

		personService.updatePerson(person);
		return true;
	}

	public void addInspection(IFacility facility, Inspection inspection) {
		System.out
				.println("Adding Inspection " + inspection + " to facility : " + facility.getFacilityInfo());
		facility.addInspection(inspection);
	}

	public void listInspection(IFacility facility) {
		FMSLogger.log.debug("");
		for (Inspection i : facility.getInspections()) {
			FMSLogger.log.debug(i.toString());
		}
	}

	public void listActualUsage(IFacility facility) {
		FMSLogger.log.debug("");
		FMSLogger.log.debug(facility.getUsage());
	}

	public double calcUsageRate() {
		return 0;
	}

	public void addFacility(Facility f) {
		getCurrentSession().saveOrUpdate(f);
	}

	public void removeFacility(Facility f) {
		getCurrentSession().delete(f);
	}

	public void updateFacility(Facility f) {
		getCurrentSession().saveOrUpdate(f);
	}

	public Facility getFacility(long facilityId) {
		return (Facility) getCurrentSession().get(Facility.class, facilityId);
	}

}
