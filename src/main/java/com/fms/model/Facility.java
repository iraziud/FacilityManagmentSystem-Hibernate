/**
 * 
 */
package com.fms.model;

import com.fms.util.FMSLogger;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Note: 1. We maintain a list of facilities for each facility that holds all
 * the facilities that it can have. We didn't implement any specific facility
 * (e.g. house, apartment, shop because they are all subtype of facility and any
 * subtype that's added in future would just have to implement the facility
 * interface and "this" class can hold it's objects (list of facility), so makes
 * design more extensible and flexible.
 * 
 * 2. Floors are made a separate entity because a floor can have any number of
 * facility and to be able to access any facility on any floor we've to make it
 * separate entity, you can't make floor as an attribute of facility because if
 * you do so then you won't be able to achieve this functionality which makes
 * the design more fragile.
 * 
 */

public class Facility implements IFacility {


	private Long id;

	// facility type: any type of facility, could have made it an entity but all
	// of the subclasses won't be doing much
	// so made a general entity
	private String facilityType = null;
	private String dimensions = null;

	private Address address;

	private int capacity;
	private String detail = null;

	private List<Floors> floors = new ArrayList<Floors>();


	private List<Unit> units = new ArrayList<Unit>();


	public List<Inspection> inspections = new ArrayList<Inspection>();

	public List<Maintenance> maintenanceRequests = new ArrayList<Maintenance>();

	public List<Maintenance> scheduledMaintenances = new ArrayList<Maintenance>();

	public List<Person> persons = new ArrayList<Person>();

	private double usage;

	public Facility() {
		// if no arguements passed set them as default
		this.dimensions = "1000 * 1000";
		this.capacity = 5;
		this.facilityType = "general";
		floors = new ArrayList<Floors>(10);
		units = new ArrayList<Unit>(10);
	}

	public Facility(String type, String Dim, int Capacity, Address address) {
		this.facilityType = type;
		this.dimensions = Dim;
		this.address = address;
		this.capacity = Capacity;
		inspections = new ArrayList<Inspection>();
	}

	public void listUnits() {
		FMSLogger.log.debug(units);
	}

	public String getFacilityInfo() {
		return "[Facility: " + this.id + "; " + this.facilityType + "; " + this.address + " ]";
	}

	public int getAvailableCapacity() {
		return capacity;
	}

	public boolean addNewUnit(Unit unit) {
		if (!this.units.contains(unit.getId())) {
			this.units.add(unit);
			return true;
		} else
			return false;
	}

	public void addFacilityDetail(String s) {
		this.detail = s;
	}

	public boolean removeUnit(Unit unit) {
		if (!this.units.contains(unit)) {
			this.units.remove(unit);
			return true;
		} else
			return false;
	}

	public void setUsage(double use) {
		usage += use;
	}

	public double getUsage() {
		return usage;
	}

	public List<Inspection> getInspections() {
		return inspections;
	}

	public void addInspection(Inspection inspection) {
		inspections.add(inspection);
	}

	public void listPersons() {
		FMSLogger.log.debug(this.id + ":" + this.facilityType + ", Persons: " + persons);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setFloors(List<Floors> floors) {
		this.floors = floors;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}

	public void setMaintenanceRequests(List<Maintenance> maintenanceRequests) {
		this.maintenanceRequests = maintenanceRequests;
	}

	public void setScheduledMaintenances(List<Maintenance> scheduledMaintenances) {
		this.scheduledMaintenances = scheduledMaintenances;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "Facility [id=" + id + ", facilityType=" + facilityType + ", dimensions=" + dimensions
				+ ", address=" + address + ", capacity=" + capacity + ", detail=" + detail + ", floors="
				+ floors + ", units=" + units + ", inspections=" + inspections + ", maintenanceRequests="
				+ maintenanceRequests + ", scheduledMaintenances=" + scheduledMaintenances + ", persons="
				+ persons + ", usage=" + usage + "]";
	}

	public void scheduleMaintenance(Maintenance maintenance) {
		scheduledMaintenances.add(maintenance);
	}

	public void requestMaintenance(Maintenance maintenance) {
		maintenanceRequests.add(maintenance);
	}

	public List<Maintenance> getScheduledMaintenance() {
		return scheduledMaintenances;
	}

	public List<Maintenance> getMaintenanceRequests() {
		return maintenanceRequests;
	}

	public void scheduleMaintenance(List<Maintenance> maintenance) {
		scheduledMaintenances.addAll(maintenance);
	}

	public Long getId() {
		return id;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public String getDimensions() {
		return dimensions;
	}

	public Address getAddress() {
		return address;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getDetail() {
		return detail;
	}

	public List<Floors> getFloors() {
		return floors;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public List<Maintenance> getScheduledMaintenances() {
		return scheduledMaintenances;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void removeMaintenance(Maintenance maintenance) {
		maintenanceRequests.remove(maintenance);
	}
}// end class
