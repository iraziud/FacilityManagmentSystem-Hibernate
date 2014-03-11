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
import com.fms.model.Maintenance;
import com.fms.model.Unit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class MaintenanceUsageTest {
	@Autowired
	private IFacilityUsage facilityUsage;

	@Autowired
	private IPersonService personService;

	@Autowired
	private IMaintenanceUsage maintenanceUsage;

	private Facility facility;

	@Before
	public void setup() {
		// Create a new facility with address
		Address address = new Address();
		address.setAddress1("620 8th Ave");
		address.setCity("New York");
		address.setState("NY");
		address.setCountry("USA");
		address.setZip("07094");

		facility = new Facility("SkyScraper", "400x400", 10000, address);

		Unit unit = new Unit();
		unit.setWidth(400);
		unit.setLength(400);
		unit.setHeight(4000);
		unit.setType("metric");

		facility.addNewUnit(unit);

		facilityUsage.addFacility(facility);
	}

	@Test
	public void testMakeFacilityMaintRequestAndListAndRevoke() {
		System.out.println("***************** Adding gas maintenance request: ******************");

		Maintenance gasmaintenance = new Maintenance("gas", 250);
		maintenanceUsage.makeFacilityMaintRequest(gasmaintenance, facility);

		System.out.println("***************** List Maintenance requests: ******************");
		maintenanceUsage.listMaintenanceRequests();

		// Make sure maintenance is saved
		Assert.assertTrue(gasmaintenance.getId() > 0);
		Assert.assertEquals(gasmaintenance, facility.getMaintenanceRequests().get(0));

		// Now revoke and test
		System.out.println("***************** Revoke maintenance: ******************");
		maintenanceUsage.revokeMaintenanceRequest(gasmaintenance, facility);

		Assert.assertEquals(0, facility.getMaintenanceRequests().size());
	}

	@Test
	public void testScheduleMaintenanceAndList() {
		Maintenance watermaintenance = new Maintenance("water", 100);
		Maintenance electricmaintenance = new Maintenance("electric", 1000);
		System.out
				.println("***************** Adding water and electric scheduled maintenance: ******************");
		maintenanceUsage.scheduleMaintenance(facility, watermaintenance);
		maintenanceUsage.scheduleMaintenance(facility, electricmaintenance);

		System.out.println("***************** List scheduled maintenance: ******************");
		maintenanceUsage.listScheduledMaintenance();

		// Make sure maintenance is saved
		Assert.assertTrue(watermaintenance.getId() > 0);
		Assert.assertTrue(electricmaintenance.getId() > 0);
		Assert.assertEquals(watermaintenance, facility.getScheduledMaintenance().get(0));
		Assert.assertEquals(electricmaintenance, facility.getScheduledMaintenance().get(1));
	}

	@Test
	public void testCalcMaintenanceCost() {
		Maintenance watermaintenance = new Maintenance("water", 100);
		maintenanceUsage.scheduleMaintenance(facility, watermaintenance);

		// Only water - maintenance cost is 100
		Assert.assertEquals(100, maintenanceUsage.calcMaintenanceCost(facility), 0);

		Maintenance electricmaintenance = new Maintenance("electric", 1000);
		maintenanceUsage.scheduleMaintenance(facility, electricmaintenance);

		// Electric and water - maintenance cost is 100+1000
		Assert.assertEquals(1100, maintenanceUsage.calcMaintenanceCost(facility), 0);

		Maintenance gasmaintenance = new Maintenance("gas", 250);
		maintenanceUsage.scheduleMaintenance(facility, gasmaintenance);

		// Electric, water and gas - maintenance cost is 1000+100+250
		Assert.assertEquals(1350, maintenanceUsage.calcMaintenanceCost(facility), 0);
	}

	@Test
	public void testCalcProblemRate() {
		Maintenance watermaintenance = new Maintenance("water", 100);
		maintenanceUsage.makeFacilityMaintRequest(watermaintenance, facility);

		// Only water - problem rate is 1
		Assert.assertEquals(1, maintenanceUsage.calcProblemRate(facility), 0);

		Maintenance electricmaintenance = new Maintenance("electric", 1000);
		maintenanceUsage.makeFacilityMaintRequest(electricmaintenance, facility);

		// Electric and water - problem rate is 1+5
		Assert.assertEquals(6, maintenanceUsage.calcProblemRate(facility), 0);

		Maintenance gasmaintenance = new Maintenance("gas", 250);
		maintenanceUsage.makeFacilityMaintRequest(gasmaintenance, facility);

		// Electric, water and gas - problem rate is 5+2+1
		Assert.assertEquals(8, maintenanceUsage.calcProblemRate(facility), 0);
	}

}
