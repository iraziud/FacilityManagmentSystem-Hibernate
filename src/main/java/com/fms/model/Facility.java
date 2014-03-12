/**
 * 
 */
package com.fms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * 
 *
 * 
 */
@Entity
public class Facility implements IFacility {

	@Id
	@GeneratedValue
	private Long id;

	// facility type: any type of facility, could have made it an entity but all
	// of the subclasses won't be doing much
	// so made a general entity
	private String facilityType = null;
	private String dimensions = null;

	@OneToOne(cascade = CascadeType.ALL)
	private Address address;

	private int capacity;
	private String detail = null;

	@OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
	private List<Floors> floors = new ArrayList<Floors>();

	@OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
	private List<Unit> units = new ArrayList<Unit>();

	@OneToMany(mappedBy = "facility", cascade = CascadeType.ALL)
	public List<Inspection> inspections = new ArrayList<Inspection>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "facility_maintenance_requests", joinColumns = @JoinColumn(name = "facility_id"), inverseJoinColumns = @JoinColumn(name = "maintenance_id"))
	public List<Maintenance> maintenanceRequests = new ArrayList<Maintenance>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "facility_scheduled_maintenance", joinColumns = @JoinColumn(name = "facility_id"), inverseJoinColumns = @JoinColumn(name = "maintenance_id"))
	public List<Maintenance> scheduledMaintenances = new ArrayList<Maintenance>();

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "usesFacility")
	public List<Person> persons = new ArrayList<Person>();

	@Column(name = "facility_usage")
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
		System.out.println(units);
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
		System.out.println(this.id + ":" + this.facilityType + ", Persons: " + persons);
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
