/**
 * 
 */
package com.fms.model;

import java.util.List;

public interface IFacility {

	public void listUnits();

	public String getFacilityInfo();

	public int getAvailableCapacity();

	public boolean addNewUnit(Unit unit);

	public void addFacilityDetail(String detail);

	public boolean removeUnit(Unit unit);

	public List<Inspection> getInspections();

	public void addInspection(Inspection inspection);

	public double getUsage();

	void listPersons();

	void scheduleMaintenance(Maintenance maintenance);

	void requestMaintenance(Maintenance maintenance);

	public List<Maintenance> getScheduledMaintenance();

	public List<Maintenance> getMaintenanceRequests();

	public void scheduleMaintenance(List<Maintenance> maintenance);
}
