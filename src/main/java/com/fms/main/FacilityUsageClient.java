package com.fms.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.service.IFacilityUsage;
import com.fms.service.IPersonService;
import com.fms.util.FMSLogger;
import com.fms.model.*;

public class FacilityUsageClient {
	public static void main(String[] args) {
		FMSLogger.log.debug("********************************************************");
		FMSLogger.log.debug("***************** Demo Facility Usage ******************");
		FMSLogger.log.debug("********************************************************\n");

		FMSLogger.log.debug("Initializing Spring...");

		// Create Spring Application Context
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		FMSLogger.log.debug("Spring initialized...");

		/**
		 * Test the facility Usage
		 */
		IFacilityUsage facilityUsage = context.getBean(IFacilityUsage.class);
		IPersonService personService = context.getBean(IPersonService.class);
		DataCreator dataCreator = context.getBean(DataCreator.class);

		FMSLogger.log.debug("\n<<< Creating facilities >>>");

		// Create a new facility with address
		Facility facilitySkyScraper = dataCreator.createFacility("SkyScraper", "400x400", 1000, "800 N Michigan Ave", "Chicago", "IL", "60611", "USA", "3125561234", 400, 400, 400);
		Facility facilityDownTown = dataCreator.createFacility("DownTown", "200x200", 1000, "1 Downtown Ave", "New York", "NY", "10018", "USA", "2125562234", 200, 200, 200);
		Facility facilityVillage = dataCreator.createFacility("Village", "600x600", 1000, "1 Village Ave", "New York", "NY", "07094", "USA", "2125563234", 600, 600, 600);
		Facility facilityOffshore = dataCreator.createFacility("Offshore", "300x300", 1000, "1 Offshore Ave", "Offshore", "NA", "10018", "India", "2125564234", 300, 300, 300);

		FMSLogger.log.debug("\n<<< Creating Inspections >>>");
		// Add some sample inspections
		Inspection inspectionMar = dataCreator.createInspection("5-Mar-2014", "Well Maintained");
		facilitySkyScraper.addInspection(inspectionMar);

		Inspection inspectionFeb = dataCreator.createInspection("1-Feb-2014", "Not Well Maintained");
		facilityDownTown.addInspection(inspectionFeb);
		
		Inspection inspectionJan = dataCreator.createInspection("2-Jan-2014", "Well Maintained");
		facilityVillage.addInspection(inspectionJan);
		
		Inspection inspectionApr = dataCreator.createInspection("2-Jan-2014", "Not Well Maintained");
		facilityOffshore.addInspection(inspectionApr);

		FMSLogger.log.debug("\n<<< Adding facilities to DB >>>");
		// This saves the facility to database
		facilityUsage.addFacility(facilitySkyScraper);
		facilityUsage.addFacility(facilityDownTown);
		facilityUsage.addFacility(facilityVillage);
		facilityUsage.addFacility(facilityOffshore);

		FMSLogger.log.debug("\n<<< Listing facilities: >>>");

		FMSLogger.log.debug("\n----");
		FMSLogger.log.debug("Facility Info: \t" + facilitySkyScraper.getFacilityInfo());
		FMSLogger.log.debug("Facility Units: \t");
		facilitySkyScraper.listUnits();
		FMSLogger.log.debug("Facility Capacity: \t" + facilitySkyScraper.getAvailableCapacity());
		FMSLogger.log.debug("Facility Inspections: \t" + facilitySkyScraper.getInspections());
		FMSLogger.log.debug("Facility Usage: \t" + facilitySkyScraper.getUsage());
		FMSLogger.log.debug("----\n");

		FMSLogger.log.debug("\n----");
		FMSLogger.log.debug("Facility Info: \t" + facilityDownTown.getFacilityInfo());
		FMSLogger.log.debug("Facility Units: \t");
		facilityDownTown.listUnits();
		FMSLogger.log.debug("Facility Capacity: \t" + facilityDownTown.getAvailableCapacity());
		FMSLogger.log.debug("Facility Inspections: \t" + facilityDownTown.getInspections());
		FMSLogger.log.debug("Facility Usage: \t" + facilityDownTown.getUsage());
		FMSLogger.log.debug("----\n");

		FMSLogger.log.debug("\n----");
		FMSLogger.log.debug("Facility Info: \t" + facilityVillage.getFacilityInfo());
		FMSLogger.log.debug("Facility Units: \t");
		facilityVillage.listUnits();
		FMSLogger.log.debug("Facility Capacity: \t" + facilityVillage.getAvailableCapacity());
		FMSLogger.log.debug("Facility Inspections: \t" + facilityVillage.getInspections());
		FMSLogger.log.debug("Facility Usage: \t" + facilityVillage.getUsage());
		FMSLogger.log.debug("----\n");

		FMSLogger.log.debug("\n----");
		FMSLogger.log.debug("Facility Info: \t" + facilityOffshore.getFacilityInfo());
		FMSLogger.log.debug("Facility Units: \t");
		facilityOffshore.listUnits();
		FMSLogger.log.debug("Facility Capacity: \t" + facilityOffshore.getAvailableCapacity());
		FMSLogger.log.debug("Facility Inspections: \t" + facilityOffshore.getInspections());
		FMSLogger.log.debug("Facility Usage: \t" + facilityOffshore.getUsage());
		FMSLogger.log.debug("----\n");

		FMSLogger.log.debug("\n<<< Creating persons: >>>");

		// Add a person to the facility
        Person person1 = dataCreator.createPerson("Irfan", "Raziuddin", "iraziud@gmail.com", "01-01-1985", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person2 = dataCreator.createPerson("Zain", "Maqsood", "zmaqsood@gmail.com", "01-01-1986", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661237");
        Person person3 = dataCreator.createPerson("Frank", "Thumb", "frank@gmail.com", "01-01-1987", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661238");
        Person person4 = dataCreator.createPerson("Chris", "Cross", "chris@gmail.com", "01-01-1988", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661239");
        Person person5 = dataCreator.createPerson("Julia", "Stiles", "julia@gmail.com", "01-01-1989", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661240");
        Person person6 = dataCreator.createPerson("Robert", "Redford", "robert@gmail.com", "01-01-1990", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661241");
        Person person7 = dataCreator.createPerson("Hugh", "Grant", "hugh@gmail.com", "01-01-1991", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661242");

        FMSLogger.log.debug("\n<<< Assigning facility to person >>>");
		// Assign facility to use
		facilityUsage.assignFacilityToUse(person1, facilitySkyScraper);
		facilityUsage.assignFacilityToUse(person1, facilityDownTown);
		facilityUsage.assignFacilityToUse(person2, facilitySkyScraper);
		facilityUsage.assignFacilityToUse(person2, facilityDownTown);
		facilityUsage.assignFacilityToUse(person2, facilityVillage);
		facilityUsage.assignFacilityToUse(person3, facilityVillage);
		facilityUsage.assignFacilityToUse(person3, facilitySkyScraper);
		facilityUsage.assignFacilityToUse(person4, facilitySkyScraper);
		facilityUsage.assignFacilityToUse(person4, facilityVillage);
		facilityUsage.assignFacilityToUse(person5, facilityOffshore);
		facilityUsage.assignFacilityToUse(person6, facilitySkyScraper);
		facilityUsage.assignFacilityToUse(person6, facilityVillage);
		facilityUsage.assignFacilityToUse(person6, facilityDownTown);
		facilityUsage.assignFacilityToUse(person7, facilityDownTown);
		facilityUsage.assignFacilityToUse(person7, facilitySkyScraper);

		FMSLogger.log.debug("\n<<< Persons in the facility: >>>");
		// Check if facility is used by person
		person1.listPersonFacilities();
		person2.listPersonFacilities();
		person3.listPersonFacilities();
		person4.listPersonFacilities();
		person5.listPersonFacilities();
		person6.listPersonFacilities();
		person7.listPersonFacilities();

		FMSLogger.log.debug("\n<<< Vacating person from the facility: >>>");
		// Vacate
		facilityUsage.vacateFacility(person1, facilitySkyScraper);
		facilityUsage.vacateFacility(person2, facilityDownTown);
		facilityUsage.vacateFacility(person3, facilityVillage);
		facilityUsage.vacateFacility(person4, facilitySkyScraper);
		facilityUsage.vacateFacility(person6, facilityVillage);
		facilityUsage.vacateFacility(person7, facilitySkyScraper);

		FMSLogger.log.debug("\n<<< Persons from the facility: >>>");
		person1.listPersonFacilities();
		person2.listPersonFacilities();
		person3.listPersonFacilities();
		person4.listPersonFacilities();
		person5.listPersonFacilities();
		person6.listPersonFacilities();
		person7.listPersonFacilities();

		FMSLogger.log.debug("\n<<< Adding a new inspection to facility: >>>");
		// Add an inspection
		Inspection inspectionPreviousYear = dataCreator.createInspection("31-Dec-2013", "Well Maintained");
		facilityUsage.addInspection(facilitySkyScraper, inspectionPreviousYear);

		FMSLogger.log.debug("\n<<< Total inspections so far to facility: >>>");
		// Should be four inspections so far
		facilityUsage.listInspection(facilitySkyScraper);

		FMSLogger.log.debug("\n<<< Update facility: >>>");
		// Update tests
		facilitySkyScraper.setCapacity(5000);
		facilitySkyScraper.setDetail("SkyScraper New");
		facilityUsage.updateFacility(facilitySkyScraper);

		FMSLogger.log.debug("\n<<< Remove unit: >>>");
		facilitySkyScraper.removeUnit(facilitySkyScraper.getUnits().get(0));

		FMSLogger.log.debug("\n<<< Add new unit: >>>");
		Unit newunit = context.getBean(Unit.class);
		newunit.setWidth(200);
		newunit.setLength(200);
		newunit.setHeight(2000);
		newunit.setType("US");

		facilitySkyScraper.addNewUnit(newunit);

		FMSLogger.log.debug("\n<<< Update facility: >>>");
		facilityUsage.updateFacility(facilitySkyScraper);

		FMSLogger.log.debug("\n<<< Facility from DB: >>>");
		facilityUsage.getFacility(facilitySkyScraper.getId());
		FMSLogger.log.debug(facilitySkyScraper);

		FMSLogger.log.debug("\n<<< Remove facility: >>>");
		// Before removing, vacate all persons
		facilityUsage.vacateFacility(person5, facilityOffshore);
		facilityUsage.removeFacility(facilityOffshore);

		FMSLogger.log.debug("\n<<< Removing all data from DB: >>>");

		personService.removePerson(person1);
		personService.removePerson(person2);
		personService.removePerson(person3);
		personService.removePerson(person4);
		personService.removePerson(person5);
		personService.removePerson(person6);
		personService.removePerson(person7);
		
		FMSLogger.log.debug("\n<<< Removing facilities: >>>");
		facilityUsage.removeFacility(facilitySkyScraper);
		facilityUsage.removeFacility(facilityDownTown);
		facilityUsage.removeFacility(facilityVillage);

		// Close the spring application context
		context.close();

		FMSLogger.log.debug("\n*******************************************************");
		FMSLogger.log.debug("***************** End Facility Usage ******************");
		FMSLogger.log.debug("*******************************************************");
	}

	

}
