/**
 * 
 */
package com.fms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fms.model.Facility;
import com.fms.model.Maintenance;

@Service
@Transactional(propagation = Propagation.REQUIRED)
/**
 * Facility Maintenance usage utility methods
 * 
 * Uses Hibernate to save/retrieve values from database
 * 
 */
public class MaintenanceUsage implements IMaintenanceUsage {

	@Autowired
	SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	IFacilityUsage facilityUsage;

	// interfaces methods begin here
	public void makeFacilityMaintRequest(Maintenance maintenance, Facility facility) {
		System.out.println("Making maintenance request for: " + maintenance.getType() + " by facility"
				+ facility.getFacilityInfo());
		facility.requestMaintenance(maintenance);
		facilityUsage.updateFacility(facility);
	}

	@SuppressWarnings("unchecked")
	public void listMaintenanceRequests() {
		// Fetch from DB
		List<Facility> list = getCurrentSession().createCriteria(Facility.class).list();

		for (Facility facility : list) {
			for (Maintenance maintenance : facility.getMaintenanceRequests()) {
				System.out.println("Maintenance requested for: " + maintenance.getType()
						+ " made by facility" + facility.getFacilityInfo());
			}
		}
	}

	public void scheduleMaintenance(Facility facility, Maintenance maintenance) {
		System.out.println("Scheduling maintenance for: " + maintenance.getType() + " by facility"
				+ facility.getFacilityInfo());
		facility.scheduleMaintenance(maintenance);
		facilityUsage.updateFacility(facility);
	}

	@SuppressWarnings("unchecked")
	public void listScheduledMaintenance() {
		// Fetch from DB
		List<Facility> list = getCurrentSession().createCriteria(Facility.class).list();

		for (Facility facility : list) {
			for (Maintenance maintenance : facility.getMaintenanceRequests()) {
				System.out.println("Scheduled Maintenance : " + maintenance.getType() + " made by facility"
						+ facility.getFacilityInfo());
			}
		}
	}

	public double calcMaintenanceCost(Facility facility) {
		List<Maintenance> scheduledMaintenance = facility.getScheduledMaintenance();
		double cost = 0.0;
		for (Maintenance maintenance : scheduledMaintenance) {
			cost += maintenance.getCost();
		}

		System.out.println("Maintenance cost is: " + cost + " for facility" + facility.getFacilityInfo());

		return cost;
	}

	public int calcProblemRate(Facility facility) {
		int problemrate = 0;
		List<Maintenance> maintenanceRequests = facility.getMaintenanceRequests();
		for (Maintenance maintenance : maintenanceRequests) {
			if (maintenance.getType().equals("water"))
				problemrate += 1;
			else if (maintenance.getType().equals("gas"))
				problemrate += 2;
			else if (maintenance.getType().equals("electric"))
				problemrate += 5;
		}

		System.out.println("Problem rate is: " + problemrate + " for facility" + facility.getFacilityInfo());
		return problemrate;
	}

	public int calcDownTime(Facility facility) {
		int downtime = 0;
		List<Maintenance> scheduledMaintenance = facility.getScheduledMaintenance();
		for (Maintenance maintenance : scheduledMaintenance) {
			if (maintenance.getType().equals("water"))
				downtime += 1;
			else if (maintenance.getType().equals("gas"))
				downtime += 2;
			else if (maintenance.getType().equals("electric"))
				downtime += 5;
		}

		System.out.println("Downtime is: " + downtime + " for facility" + facility.getFacilityInfo());
		return downtime;
	}

	public void listFacilityProblems(Facility facility) {
		System.out.println("Problems for facility : " + facility.getFacilityInfo() + " : ");
		List<Maintenance> scheduledMaintenance = facility.getScheduledMaintenance();
		for (Maintenance maintenance : scheduledMaintenance) {
			System.out.println(maintenance.getType());
		}
	}

	public void revokeMaintenanceRequest(Maintenance maintenance, Facility facility) {
		System.out.println("Revoking maintenance request for: " + maintenance.getType() + " by facility"
				+ facility.getFacilityInfo());
		facility.removeMaintenance(maintenance);
		facilityUsage.updateFacility(facility);
	}

}// end class

