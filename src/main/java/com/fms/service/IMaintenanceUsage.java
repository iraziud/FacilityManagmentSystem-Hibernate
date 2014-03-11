/**
 * 
 */
package com.fms.service;

import com.fms.model.Facility;
import com.fms.model.Maintenance;

/**
 * Facility Maintenance usage utility methods
 */
public interface IMaintenanceUsage {

	public void makeFacilityMaintRequest(Maintenance maintenance, Facility facility);

	public void scheduleMaintenance(Facility facility, Maintenance maintenance);

	public double calcMaintenanceCost(Facility facility);

	public int calcProblemRate(Facility facility);

	public int calcDownTime(Facility facility);

	public void listMaintenanceRequests();

	public void listScheduledMaintenance();

	public void listFacilityProblems(Facility facility);// you can make it to
														// list problems for
														// all the
														// facilities a list
														// maintained by
														// implementing
														// class

	public void revokeMaintenanceRequest(Maintenance gasmaintenance, Facility facility);
}
