/**
 * 
 */
package com.fms.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fms.model.Facility;
import com.fms.model.IFacility;
import com.fms.model.Inspection;
import com.fms.model.Person;

@Service
@Transactional(propagation = Propagation.REQUIRED)
/**
 * Facility usage utility methods
 *
 * Uses Hibernate to save/retrieve values from database
 */
public class FacilityUsage implements IFacilityUsage {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	IPersonService personService;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public boolean isInUseDuringInterval() {
		return false;
	}

	public void assignFacilityToUse(Person person, IFacility facility) {
		System.out.println("Assigning facility " + facility.getFacilityInfo() + " to Person : " + person.getId() + ", "
				+ person.getName());
		person.usesFacility.add((Facility) facility);
		personService.updatePerson(person);
	}

	public boolean vacateFacility(Person person, IFacility facility) {
		if (person.usesFacility.contains(facility)) {
			System.out.println("Vacating facility " + facility.getFacilityInfo() + " by Person : " + person.getId() + ", "
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
		for (Inspection i : facility.getInspections()) {
			System.out.println(i.toString());
		}
	}

	public void listActualUsage(IFacility facility) {
		System.out.println(facility.getUsage());
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
