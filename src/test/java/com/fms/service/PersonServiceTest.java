package com.fms.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fms.model.Address;
import com.fms.model.Person;
import com.fms.model.Phone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class PersonServiceTest {

	@Autowired
	private IPersonService personService;

	private Person person;
	private Address address;
	private Phone phone;

	@Before
	public void setup() {
		// Create a new person
		person = new Person();
		person.setFirstName("Tom");
		person.setLastName("Thumb");
		person.setEmail("tomthumb@gmail.com");
		person.setDOB("01-01-1985");

		address = new Address();
		address.setAddress1("620 8th Ave");
		address.setCity("New York");
		address.setState("NY");
		address.setCountry("USA");
		address.setZip("07094");
		person.addAddress(address);

		phone = new Phone();
		phone.setNumber("2126661234");
		phone.setType("H");
		phone.setCountry("+1");

		person.addPhone(phone);

		personService.addPerson(person);
	}

	@Test
	public void testAddPerson() {
		// Just make sure person is added in db and assigned a primary key
		Assert.assertTrue(person.getId() > 0);

		// Make sure address is saved
		Assert.assertTrue(address.getId() > 0);
		Assert.assertEquals(address, person.getAddress().get(0));

		// Make sure phone is saved
		Assert.assertTrue(phone.getId() > 0);
		Assert.assertEquals(phone, person.getPhone().get(0));
	}

	@Test
	public void testRemovePerson() {
		personService.removePerson(person);

		Person person2 = personService.getPerson(person.getId());
		Assert.assertTrue(person2 == null);
	}

	@Test
	public void testUpdateGetPerson() {
		person.setEmail("tomnew@gmail.com");
		personService.updatePerson(person);

		Person person2 = personService.getPerson(person.getId());
		Assert.assertEquals(person.getId(), person2.getId());
		Assert.assertEquals("tomnew@gmail.com", person2.getEmail());

		// Make sure address is available
		Assert.assertEquals(address.getId(), person.getAddress().get(0).getId());

		// Make sure phone is available
		Assert.assertEquals(phone.getId(), person.getPhone().get(0).getId());
	}
}
