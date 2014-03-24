/**
 * 
 */
package com.fms.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fms.util.FMSLogger;
import com.fms.model.Facility;
import com.fms.model.Maintenance;

/**
 * Facility Maintenance usage utility methods
 * 
 * Uses Hibernate to save/retrieve values from database
 * 
 */
public class MaintenanceUsage implements IMaintenanceUsage {

	SessionFactory sessionFactory;

	IFacilityUsage facilityUsage;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void setFacilityUsage(IFacilityUsage facilityUsage) {
		this.facilityUsage = facilityUsage;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	// interfaces methods begin here
	public void makeFacilityMaintRequest(Maintenance maintenance, Facility facility) {
		FMSLogger.log.debug("\nMaking maintenance request for: " + maintenance.getType() + " by facility"
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
				FMSLogger.log.debug("\nMaintenance requested for: " + maintenance.getType()
						+ " made by facility" + facility.getFacilityInfo());
			}
		}
	}

	public void scheduleMaintenance(Facility facility, Maintenance maintenance) {
		FMSLogger.log.debug("\nScheduling maintenance for: " + maintenance.getType() + " by facility"
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
				FMSLogger.log.debug("\nScheduled Maintenance : " + maintenance.getType() + " made by facility"
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

		FMSLogger.log.debug("\nMaintenance cost is: " + cost + " for facility" + facility.getFacilityInfo());

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

		FMSLogger.log.debug("\nProblem rate is: " + problemrate + " for facility" + facility.getFacilityInfo());
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

		FMSLogger.log.debug("\nDowntime is: " + downtime + " for facility" + facility.getFacilityInfo());
		return downtime;
	}

	public void listFacilityProblems(Facility facility) {
		FMSLogger.log.debug("\nProblems for facility : " + facility.getFacilityInfo() + " : ");
		List<Maintenance> scheduledMaintenance = facility.getScheduledMaintenance();
		for (Maintenance maintenance : scheduledMaintenance) {
			FMSLogger.log.debug(maintenance.getType());
		}
	}

	public void revokeMaintenanceRequest(Maintenance maintenance, Facility facility) {
		FMSLogger.log.debug("\nRevoking maintenance request for: " + maintenance.getType() + " by facility"
				+ facility.getFacilityInfo());
		facility.removeMaintenance(maintenance);
		facilityUsage.updateFacility(facility);
	}

}// end class

