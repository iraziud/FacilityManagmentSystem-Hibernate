/**
 * 
 */
package com.fms.service;

import com.fms.model.Facility;
import com.fms.model.IFacility;
import com.fms.model.Inspection;
import com.fms.model.Person;

/**
 * Facility usage utility methods
 */
public interface IFacilityUsage {

	boolean isInUseDuringInterval();

	void assignFacilityToUse(Person person, IFacility facility);

	boolean vacateFacility(Person person, IFacility facility);

	void listInspection(IFacility facility);

	void listActualUsage(IFacility facility);

	double calcUsageRate();

	void addInspection(IFacility facility, Inspection inspection);

	void addFacility(Facility f);

	void removeFacility(Facility f);

	void updateFacility(Facility f);

	Facility getFacility(long facilityId);

}
