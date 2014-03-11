package com.fms.client;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.service.IFacilityUsage;
import com.fms.service.IPersonService;
import com.fms.model.Facility;
import com.fms.model.Inspection;
import com.fms.model.Person;
import com.fms.model.Unit;

public class FacilityUsageClient {

	public static void main(String[] args) {
		System.out.println("********************************************************");
		System.out.println("***************** Demo Facility Usage ******************");
		System.out.println("********************************************************\n");

		System.out.println("Initializing Spring...");

		// Create Spring Application Context
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println("Spring initialized...");

		/**
		 * Test the facility Usage
		 */
		IFacilityUsage facilityUsage = context.getBean(IFacilityUsage.class);
		IPersonService personService = context.getBean(IPersonService.class);

		System.out.println("\n<<< Creating facilities >>>");

		// Create a new facility with address
		Facility facilitySkyScraper = DataCreator.createFacility("Skyscraper", "400x400", 1000, "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3125561234", 400, 400, 400);
		Facility facilityDownTown = DataCreator.createFacility("Downtown", "200x200", 1000, "1 Downtown Ave", "New York", "NY", "10018", "USA", "2125562234", 200, 200, 200);
		Facility facilityVillage = DataCreator.createFacility("Village", "600x600", 1000, "1 Village Ave", "New York", "NY", "07094", "USA", "2125563234", 600, 600, 600);
		Facility facilityOffshore = DataCreator.createFacility("Offshore", "300x300", 1000, "1 Offshore Ave", "Offshore", "NA", "10018", "India", "9195564234", 300, 300, 300);

		System.out.println("\n<<< Creating Inspections >>>");
		// Add some sample inspections
		Inspection inspectionMar = new Inspection("5-Mar-2014", "Well Maintained");
		facilitySkyScraper.addInspection(inspectionMar);

		Inspection inspectionFeb = new Inspection("1-Feb-2014", "Not Well Maintained");
		facilityDownTown.addInspection(inspectionFeb);
		
		Inspection inspectionJan = new Inspection("2-Jan-2014", "Well Maintained");
		facilityVillage.addInspection(inspectionJan);
		
		Inspection inspectionApr = new Inspection("2-Jan-2014", "Not Well Maintained");
		facilityOffshore.addInspection(inspectionApr);

		System.out.println("\n<<< Adding facilities to DB >>>");
		// This saves the facility to database
		facilityUsage.addFacility(facilitySkyScraper);
		facilityUsage.addFacility(facilityDownTown);
		facilityUsage.addFacility(facilityVillage);
		facilityUsage.addFacility(facilityOffshore);

		System.out.println("\n<<< Listing facilities: >>>");

		System.out.println("\n----");
		System.out.println("Facility Info: \t" + facilitySkyScraper.getFacilityInfo());
		System.out.println("Facility Units: \t");
		facilitySkyScraper.listUnits();
		System.out.println("Facility Capacity: \t" + facilitySkyScraper.getAvailableCapacity());
		System.out.println("Facility Inspections: \t" + facilitySkyScraper.getInspections());
		System.out.println("Facility Usage: \t" + facilitySkyScraper.getUsage());
		System.out.println("----\n");

		System.out.println("\n----");
		System.out.println("Facility Info: \t" + facilityDownTown.getFacilityInfo());
		System.out.println("Facility Units: \t");
		facilityDownTown.listUnits();
		System.out.println("Facility Capacity: \t" + facilityDownTown.getAvailableCapacity());
		System.out.println("Facility Inspections: \t" + facilityDownTown.getInspections());
		System.out.println("Facility Usage: \t" + facilityDownTown.getUsage());
		System.out.println("----\n");

		System.out.println("\n----");
		System.out.println("Facility Info: \t" + facilityVillage.getFacilityInfo());
		System.out.println("Facility Units: \t");
		facilityVillage.listUnits();
		System.out.println("Facility Capacity: \t" + facilityVillage.getAvailableCapacity());
		System.out.println("Facility Inspections: \t" + facilityVillage.getInspections());
		System.out.println("Facility Usage: \t" + facilityVillage.getUsage());
		System.out.println("----\n");

		System.out.println("\n----");
		System.out.println("Facility Info: \t" + facilityOffshore.getFacilityInfo());
		System.out.println("Facility Units: \t");
		facilityOffshore.listUnits();
		System.out.println("Facility Capacity: \t" + facilityOffshore.getAvailableCapacity());
		System.out.println("Facility Inspections: \t" + facilityOffshore.getInspections());
		System.out.println("Facility Usage: \t" + facilityOffshore.getUsage());
		System.out.println("----\n");

		System.out.println("\n<<< Creating persons: >>>");

		// Add a person to the facility
        Person person1 = DataCreator.createPerson("Irfan", "Raziuddin", "iraziud@gmail.com", "01-01-1985", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
        Person person2 = DataCreator.createPerson("Zain", "Maqsood", "zmaqsood@gmail.com", "01-01-1986", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
        Person person3 = DataCreator.createPerson("Frank", "Thumb", "frank@gmail.com", "01-01-1987", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
        Person person4 = DataCreator.createPerson("Chris", "Cross", "chris@gmail.com", "01-01-1988", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
        Person person5 = DataCreator.createPerson("Julia", "Stiles", "julia@gmail.com", "01-01-1989", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
        Person person6 = DataCreator.createPerson("Robert", "Redford", "robert@gmail.com", "01-01-1990", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
        Person person7 = DataCreator.createPerson("Hugh", "Grant", "hugh@gmail.com", "01-01-1991", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");

        System.out.println("\n<<< Assigning facility to person >>>");
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

		System.out.println("\n<<< Persons in the facility: >>>");
		// Check if facility is used by person
		person1.listPersonFacilities();
		person2.listPersonFacilities();
		person3.listPersonFacilities();
		person4.listPersonFacilities();
		person5.listPersonFacilities();
		person6.listPersonFacilities();
		person7.listPersonFacilities();

		System.out.println("\n<<< Vacating person from the facility: >>>");
		// Vacate
		facilityUsage.vacateFacility(person1, facilitySkyScraper);
		facilityUsage.vacateFacility(person2, facilityDownTown);
		facilityUsage.vacateFacility(person3, facilityVillage);
		facilityUsage.vacateFacility(person4, facilitySkyScraper);
		facilityUsage.vacateFacility(person6, facilityVillage);
		facilityUsage.vacateFacility(person7, facilitySkyScraper);

		System.out.println("\n<<< Persons from the facility: >>>");
		person1.listPersonFacilities();
		person2.listPersonFacilities();
		person3.listPersonFacilities();
		person4.listPersonFacilities();
		person5.listPersonFacilities();
		person6.listPersonFacilities();
		person7.listPersonFacilities();

		System.out.println("\n<<< Adding a new inspection to facility: >>>");
		// Add an inspection
		Inspection inspectionPreviousYear = new Inspection("31-Dec-2013", "Well Maintained");
		facilityUsage.addInspection(facilitySkyScraper, inspectionPreviousYear);

		System.out.println("\n<<< Total inspections so far to facility: >>>");
		// Should be four inspections so far
		facilityUsage.listInspection(facilitySkyScraper);

		System.out.println("\n<<< Update facility: >>>");
		// Update tests
		facilitySkyScraper.setCapacity(5000);
		facilitySkyScraper.setDetail("SkyScraper New");
		facilityUsage.updateFacility(facilitySkyScraper);

		System.out.println("\n<<< Remove unit: >>>");
		facilitySkyScraper.removeUnit(facilitySkyScraper.getUnits().get(0));

		System.out.println("\n<<< Add new unit: >>>");
		Unit newunit = new Unit();
		newunit.setWidth(200);
		newunit.setLength(200);
		newunit.setHeight(2000);
		newunit.setType("US");

		facilitySkyScraper.addNewUnit(newunit);

		System.out.println("\n<<< Update facility: >>>");
		facilityUsage.updateFacility(facilitySkyScraper);

		System.out.println("\n<<< Facility from DB: >>>");
		facilityUsage.getFacility(facilitySkyScraper.getId());
		System.out.println(facilitySkyScraper);

		System.out.println("\n<<< Remove facility: >>>");
		// Before removing, vacate all persons
		facilityUsage.vacateFacility(person5, facilityOffshore);
		facilityUsage.removeFacility(facilityOffshore);

		System.out.println("\n<<< Removing all data from DB: >>>");

		personService.removePerson(person1);
		personService.removePerson(person2);
		personService.removePerson(person3);
		personService.removePerson(person4);
		personService.removePerson(person5);
		personService.removePerson(person6);
		personService.removePerson(person7);
		
		System.out.println("\n<<< Removing facilities: >>>");
		facilityUsage.removeFacility(facilitySkyScraper);
		facilityUsage.removeFacility(facilityDownTown);
		facilityUsage.removeFacility(facilityVillage);

		// Close the spring application context
		context.close();

		System.out.println("\n*******************************************************");
		System.out.println("***************** End Facility Usage ******************");
		System.out.println("*******************************************************");
	}

	

}
