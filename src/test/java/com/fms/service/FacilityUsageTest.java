package com.fms.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fms.model.Address;
import com.fms.model.Facility;
import com.fms.model.Inspection;
import com.fms.model.Person;
import com.fms.model.Phone;
import com.fms.model.Unit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class FacilityUsageTest {

	@Autowired
	private IFacilityUsage facilityUsage;

	@Autowired
	private IPersonService personService;

	private Facility facility;
	private Address address;
	private Unit unit;

	@Before
	public void setup() {
		// Create a new facility with address
		address = new Address();
		address.setAddress1("620 8th Ave");
		address.setCity("New York");
		address.setState("NY");
		address.setCountry("USA");
		address.setZip("07094");

		facility = new Facility("SkyScraper", "400x400", 10000, address);

		unit = new Unit();
		unit.setWidth(400);
		unit.setLength(400);
		unit.setHeight(4000);
		unit.setType("metric");

		facility.addNewUnit(unit);

		facilityUsage.addFacility(facility);
	}

	private Person createPerson() {
		// Add a person to the facility
		Person person = new Person();
		person.setFirstName("Tom");
		person.setLastName("Thumb");
		person.setEmail("tomthumb@gmail.com");
		person.setDOB("01-01-1985");

		Address address = new Address();
		address.setAddress1("620 8th Ave");
		address.setCity("New York");
		address.setState("NY");
		address.setCountry("USA");
		address.setZip("07094");
		person.addAddress(address);

		Phone phone = new Phone();
		phone.setNumber("2126661234");
		phone.setType("H");
		phone.setCountry("+1");

		person.addPhone(phone);

		return person;
	}

	@Test
	public void testAssignAndVacateFacility() {
		Person person = createPerson();

		System.out.println("***************** Assigning facility to person ******************");
		// Assign facility to use
		facilityUsage.assignFacilityToUse(person, facility);

		System.out.println("***************** Person is using facility: ******************");
		// Check if facility is used by person
		person.listPersonFacilities();

		Assert.assertEquals(facility, person.getUsesFacility().get(0));

		System.out.println("***************** Vacating facility: ******************");
		facilityUsage.vacateFacility(person, facility);

		Assert.assertTrue(person.getUsesFacility().size() == 0);
	}

	@Test
	public void testAddListInspection() {
		System.out.println("***************** Adding a new inspection to facility: ******************");
		// Add an inspection
		Inspection inspectionPreviousYear = new Inspection("31-Dec-2013", "Well Maintained");
		facilityUsage.addInspection(facility, inspectionPreviousYear);

		System.out.println("***************** Total inspections so far to facility: ******************");
		// Should be four inspections so far
		facilityUsage.listInspection(facility);

		// Make sure the inspection is the same
		Assert.assertEquals(inspectionPreviousYear, facility.getInspections().get(0));
	}

	@Test
	public void testAddFacility() {
		// Already added, so just make sure ID is not zero
		Assert.assertTrue(facility.getId() > 0);

		// Make sure address is saved
		Assert.assertTrue(address.getId() > 0);
		Assert.assertEquals(address, facility.getAddress());

		// Make sure phone is saved
		Assert.assertTrue(unit.getId() > 0);
		Assert.assertEquals(unit, facility.getUnits().get(0));
	}

	@Test
	public void testRemoveFacility() {
		facilityUsage.removeFacility(facility);
		Facility facility2 = facilityUsage.getFacility(facility.getId());
		Assert.assertEquals(null, facility2);
	}

	@Test
	public void testUpdateGetFacility() {
		// Update name
		facility.setCapacity(500);
		facility.setDetail("New SkyScraper");

		facilityUsage.updateFacility(facility);

		Facility facility2 = facilityUsage.getFacility(facility.getId());
		Assert.assertEquals(facility.getId(), facility2.getId());
		Assert.assertEquals("New SkyScraper", facility2.getDetail());
		Assert.assertEquals(500, facility2.getCapacity());

		// Make sure address is available
		Assert.assertEquals(address.getId(), facility.getAddress().getId());

		// Make sure unit is available
		Assert.assertEquals(unit.getId(), facility.getUnits().get(0).getId());
	}

}
