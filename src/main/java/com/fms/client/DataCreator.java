package com.fms.client;

import com.fms.model.Address;
import com.fms.model.Facility;
import com.fms.model.Person;
import com.fms.model.Phone;
import com.fms.model.Unit;

public class DataCreator {
	public static Person createPerson(String firstName, String lastName, String email, String dob, String address, String city, String state, String zip, String country, String area, String phoneNumber) {
		// Add a person to the facility
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setEmail(email);
		person.setDOB(dob);

		Address addressObj = createAddress(address, city, state, zip, country);
		person.addAddress(addressObj);

		Phone phone = new Phone();
        phone.setCountry("+1");
        phone.setArea(area);
        phone.setNumber(phoneNumber);
		phone.setType("H");


		person.addPhone(phone);
		
		return person;
	}
	
	public static Facility createFacility(String name, String dimension, int capacity, String address, String city, String state, String zip, String country, String phoneNumber, int length, int width, int height) {
		// Create a new facility with address
		Address addressObj = createAddress(address, city, state, zip, country);
		Facility facility = new Facility(name, dimension, capacity, addressObj);

		Unit unit = createUnit(length, width, height);

		facility.addNewUnit(unit);
		
		return facility;
	}

	private static Unit createUnit(int length, int width, int height) {
		Unit unit = new Unit();
		unit.setWidth(width);
		unit.setLength(length);
		unit.setHeight(height);
		unit.setType("metric");
		
		return unit;
	}
	
	public static Address createAddress(String address, String city, String state, String zip, String country) {
		Address addressObj = new Address();
		addressObj.setAddress1(address);
		addressObj.setCity(city);
		addressObj.setState(state);
		addressObj.setCountry(country);
		addressObj.setZip(zip);
		
		return addressObj;
	}
}
