package com.fms.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.fms.model.*;

public class DataCreator implements ApplicationContextAware {

	  private ApplicationContext context;

	  public void setApplicationContext(ApplicationContext applicationContext) {
	    this.context = applicationContext;
	  }

	public Person createPerson(String firstName, String lastName, String email, String dob, String address, String city, String state, String zip, String country, String area, String phoneNumber) {
		// Add a person to the facility
		Person person = (Person)context.getBean("person");
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(email);
		person.setDOB(dob);

		Address addressObj = createAddress(address, city, state, zip, country);
		person.addAddress(addressObj);

		Phone phone = (Phone)context.getBean("phone");
		phone.setNumber(phoneNumber);
		phone.setType("H");
		phone.setCountry("+1");
        phone.setArea(area);
		person.addPhone(phone);
		
		return person;
	}
	
	public Facility createFacility(String name, String dimension, int capacity, String address, String city, String state, String zip, String country, String phoneNumber, int length, int width, int height) {
		// Create a new facility with address
		Address addressObj = createAddress(address, city, state, zip, country);

		Facility facility = (Facility)context.getBean("facility");
		facility.setFacilityType(name);
		facility.setDimensions(dimension);
		facility.setCapacity(capacity);
		facility.setAddress(addressObj);

		Unit unit = createUnit(length, width, height);

		facility.addNewUnit(unit);
		
		return facility;
	}

	private Unit createUnit(int length, int width, int height) {
		Unit unit = (Unit) context.getBean("unit");
		unit.setWidth(width);
		unit.setLength(length);
		unit.setHeight(height);
		unit.setType("metric");
		
		return unit;
	}
	
	public Address createAddress(String address, String city, String state, String zip, String country) {
		Address addressObj = (Address) context.getBean("address");
		addressObj.setAddress1(address);
		addressObj.setCity(city);
		addressObj.setState(state);
		addressObj.setCountry(country);
		addressObj.setZip(zip);
		
		return addressObj;
	}

	public Inspection createInspection(String time, String summary) {
		Inspection inspection = (Inspection) context.getBean("inspection");
		inspection.setTime(time);
		inspection.setSummary(summary);

		return inspection;
	}

	public Maintenance createMaintenance(String type, double cost) {
		Maintenance maintenance = (Maintenance)context.getBean("maintainance");
		maintenance.setType(type);
		maintenance.setCost(cost);

		return maintenance;
	}
}
