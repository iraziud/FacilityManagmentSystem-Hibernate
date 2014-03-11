package com.fms.client;

import com.fms.model.*;
import com.fms.service.IFacilityUsage;
import com.fms.service.IMaintenanceUsage;
import com.fms.service.IPersonService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HerokuMain extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		PrintWriter out = resp.getWriter();
		out.write("Please wait.. running spring apps... ");
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		{
			out.println("******************************************************");
			out.println("***************** Demo Person Usage ******************");
			out.println("********************************************************\n");

			out.println("Initializing Spring...");

			// Create Spring Application Context
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

			out.println("Spring initialized...");

			IPersonService personService = context.getBean(IPersonService.class);

			/**
			 * Person usage
			 */
			out.println("\n<<< Create new person >>>");

			// Create a new person
			Person person1 = DataCreator.createPerson("Irfan", "Raziuddin", "iraziud@gmail.com", "01-01-1985", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
			Person person2 = DataCreator.createPerson("Zain", "Maqsood", "zmaqsood@gmail.com", "01-01-1986", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
			Person person3 = DataCreator.createPerson("Frank", "Thumb", "frank@gmail.com", "01-01-1987", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
			Person person4 = DataCreator.createPerson("Chris", "Cross", "chris@gmail.com", "01-01-1988", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
			Person person5 = DataCreator.createPerson("Julia", "Stiles", "julia@gmail.com", "01-01-1989", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
			Person person6 = DataCreator.createPerson("Robert", "Redford", "robert@gmail.com", "01-01-1990", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
			Person person7 = DataCreator.createPerson("Hugh", "Grant", "hugh@gmail.com", "01-01-1991", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");

			out.println("\n<<< Save person to database >>>");
			// Save the person to the database
			personService.addPerson(person1);
			personService.addPerson(person2);
			personService.addPerson(person3);
			personService.addPerson(person4);
			personService.addPerson(person5);
			personService.addPerson(person6);
			personService.addPerson(person7);

			out.println("\n<<< Remove address >>>");
			Address address = person1.getAddress().get(0);
			person1.removeAddress(address);

			out.println("\n<<< Update phone >>>");
			Phone phone = person1.getPhone().get(0);
			phone.setNumber("9174281234");

			// Add new phone
			Phone workphone = new Phone();
			workphone.setNumber("2126661234");
			workphone.setType("Work");
			workphone.setCountry("+1");

			person1.addPhone(workphone);

			out.println("\n<<< Update in DB >>>");
			// Update person details:
			person1.setEmail("iraziud@outlook.com");
			personService.updatePerson(person1);

			out.println("\n<<< Retrieved from database >>>");
			out.println(personService.getPerson(person1.getId()));

			// Remove person itself
			out.println("\n<<< Remove person >>>");
			personService.removePerson(person1);
			personService.removePerson(person2);
			personService.removePerson(person3);
			personService.removePerson(person4);
			personService.removePerson(person5);
			personService.removePerson(person6);
			personService.removePerson(person7);

			// Close Spring Application Context
			context.close();

			out.println("\n*****************************************************");
			out.println("***************** End Person Usage ******************");
			out.println("*****************************************************");
		}
		out.flush();
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		{
			out.println("********************************************************");
			out.println("***************** Demo Facility Usage ******************");
			out.println("********************************************************\n");

			out.println("Initializing Spring...");

			// Create Spring Application Context
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

			out.println("Spring initialized...");

			/**
			 * Test the facility Usage
			 */
			IFacilityUsage facilityUsage = context.getBean(IFacilityUsage.class);
			IPersonService personService = context.getBean(IPersonService.class);

			out.println("\n<<< Creating facilities >>>");

			// Create a new facility with address
			Facility facilitySkyScraper = DataCreator.createFacility("Skyscraper", "400x400", 1000, "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3125561234", 400, 400, 400);
			Facility facilityDownTown = DataCreator.createFacility("Downtown", "200x200", 1000, "1 Downtown Ave", "New York", "NY", "10018", "USA", "3125562234", 200, 200, 200);
			Facility facilityVillage = DataCreator.createFacility("Village", "600x600", 1000, "1 Village Ave", "New York", "NY", "07094", "USA", "2125563234", 600, 600, 600);
			Facility facilityOffshore = DataCreator.createFacility("Offshore", "300x300", 1000, "1 Offshore Ave", "Offshore", "NA", "10018", "India", "9195564234", 300, 300, 300);

			out.println("\n<<< Creating Inspections >>>");
			// Add some sample inspections
			Inspection inspectionMar = new Inspection("5-Mar-2014", "Well Maintained");
			facilitySkyScraper.addInspection(inspectionMar);

			Inspection inspectionFeb = new Inspection("1-Feb-2014", "Not Well Maintained");
			facilityDownTown.addInspection(inspectionFeb);
			
			Inspection inspectionJan = new Inspection("2-Jan-2014", "Well Maintained");
			facilityVillage.addInspection(inspectionJan);
			
			Inspection inspectionApr = new Inspection("2-Jan-2014", "Not Well Maintained");
			facilityOffshore.addInspection(inspectionApr);

			out.println("\n<<< Adding facilities to DB >>>");
			// This saves the facility to database
			facilityUsage.addFacility(facilitySkyScraper);
			facilityUsage.addFacility(facilityDownTown);
			facilityUsage.addFacility(facilityVillage);
			facilityUsage.addFacility(facilityOffshore);

			out.println("\n<<< Listing facilities: >>>");

			out.println("\n----");
			out.println("Facility Info: \t" + facilitySkyScraper.getFacilityInfo());
			out.println("Facility Units: \t");
			facilitySkyScraper.listUnits();
			out.println("Facility Capacity: \t" + facilitySkyScraper.getAvailableCapacity());
			out.println("Facility Inspections: \t" + facilitySkyScraper.getInspections());
			out.println("Facility Usage: \t" + facilitySkyScraper.getUsage());
			out.println("----\n");

			out.println("\n----");
			out.println("Facility Info: \t" + facilityDownTown.getFacilityInfo());
			out.println("Facility Units: \t");
			facilityDownTown.listUnits();
			out.println("Facility Capacity: \t" + facilityDownTown.getAvailableCapacity());
			out.println("Facility Inspections: \t" + facilityDownTown.getInspections());
			out.println("Facility Usage: \t" + facilityDownTown.getUsage());
			out.println("----\n");

			out.println("\n----");
			out.println("Facility Info: \t" + facilityVillage.getFacilityInfo());
			out.println("Facility Units: \t");
			facilityVillage.listUnits();
			out.println("Facility Capacity: \t" + facilityVillage.getAvailableCapacity());
			out.println("Facility Inspections: \t" + facilityVillage.getInspections());
			out.println("Facility Usage: \t" + facilityVillage.getUsage());
			out.println("----\n");

			out.println("\n----");
			out.println("Facility Info: \t" + facilityOffshore.getFacilityInfo());
			out.println("Facility Units: \t");
			facilityOffshore.listUnits();
			out.println("Facility Capacity: \t" + facilityOffshore.getAvailableCapacity());
			out.println("Facility Inspections: \t" + facilityOffshore.getInspections());
			out.println("Facility Usage: \t" + facilityOffshore.getUsage());
			out.println("----\n");

			out.println("\n<<< Creating persons: >>>");

			// Add a person to the facility
            Person person1 = DataCreator.createPerson("Irfan", "Raziuddin", "iraziud@gmail.com", "01-01-1985", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
            Person person2 = DataCreator.createPerson("Zain", "Maqsood", "zmaqsood@gmail.com", "01-01-1986", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
            Person person3 = DataCreator.createPerson("Frank", "Thumb", "frank@gmail.com", "01-01-1987", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
            Person person4 = DataCreator.createPerson("Chris", "Cross", "chris@gmail.com", "01-01-1988", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
            Person person5 = DataCreator.createPerson("Julia", "Stiles", "julia@gmail.com", "01-01-1989", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
            Person person6 = DataCreator.createPerson("Robert", "Redford", "robert@gmail.com", "01-01-1990", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");
            Person person7 = DataCreator.createPerson("Hugh", "Grant", "hugh@gmail.com", "01-01-1991", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3126661236");

            out.println("\n<<< Assigning facility to person >>>");
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

			out.println("\n<<< Persons in the facility: >>>");
			// Check if facility is used by person
			person1.listPersonFacilities();
			person2.listPersonFacilities();
			person3.listPersonFacilities();
			person4.listPersonFacilities();
			person5.listPersonFacilities();
			person6.listPersonFacilities();
			person7.listPersonFacilities();

			out.println("\n<<< Vacating person from the facility: >>>");
			// Vacate
			facilityUsage.vacateFacility(person1, facilitySkyScraper);
			facilityUsage.vacateFacility(person2, facilityDownTown);
			facilityUsage.vacateFacility(person3, facilityVillage);
			facilityUsage.vacateFacility(person4, facilitySkyScraper);
			facilityUsage.vacateFacility(person6, facilityVillage);
			facilityUsage.vacateFacility(person7, facilitySkyScraper);

			out.println("\n<<< Persons from the facility: >>>");
			person1.listPersonFacilities();
			person2.listPersonFacilities();
			person3.listPersonFacilities();
			person4.listPersonFacilities();
			person5.listPersonFacilities();
			person6.listPersonFacilities();
			person7.listPersonFacilities();

			out.println("\n<<< Adding a new inspection to facility: >>>");
			// Add an inspection
			Inspection inspectionPreviousYear = new Inspection("31-Dec-2013", "Well Maintained");
			facilityUsage.addInspection(facilitySkyScraper, inspectionPreviousYear);

			out.println("\n<<< Total inspections so far to facility: >>>");
			// Should be four inspections so far
			facilityUsage.listInspection(facilitySkyScraper);

			out.println("\n<<< Update facility: >>>");
			// Update tests
			facilitySkyScraper.setCapacity(5000);
			facilitySkyScraper.setDetail("SkyScraper New");
			facilityUsage.updateFacility(facilitySkyScraper);

			out.println("\n<<< Remove unit: >>>");
			facilitySkyScraper.removeUnit(facilitySkyScraper.getUnits().get(0));

			out.println("\n<<< Add new unit: >>>");
			Unit newunit = new Unit();
			newunit.setWidth(200);
			newunit.setLength(200);
			newunit.setHeight(2000);
			newunit.setType("US");

			facilitySkyScraper.addNewUnit(newunit);

			out.println("\n<<< Update facility: >>>");
			facilityUsage.updateFacility(facilitySkyScraper);

			out.println("\n<<< Facility from DB: >>>");
			facilityUsage.getFacility(facilitySkyScraper.getId());
			out.println(facilitySkyScraper);

			out.println("\n<<< Remove facility: >>>");
			// Before removing, vacate all persons
			facilityUsage.vacateFacility(person5, facilityOffshore);
			facilityUsage.removeFacility(facilityOffshore);

			out.println("\n<<< Removing all data from DB: >>>");

			personService.removePerson(person1);
			personService.removePerson(person2);
			personService.removePerson(person3);
			personService.removePerson(person4);
			personService.removePerson(person5);
			personService.removePerson(person6);
			personService.removePerson(person7);
			
			out.println("\n<<< Removing facilities: >>>");
			facilityUsage.removeFacility(facilitySkyScraper);
			facilityUsage.removeFacility(facilityDownTown);
			facilityUsage.removeFacility(facilityVillage);

			// Close the spring application context
			context.close();

			out.println("\n*******************************************************");
			out.println("***************** End Facility Usage ******************");
			out.println("*******************************************************");
		}
		out.flush();
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		{
			out.println("***********************************************************");
			out.println("***************** Demo Maintenance Usage ******************");
			out.println("********************************************************\n");

			out.println("Initializing Spring...");

			// Create Spring Application Context
			ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

			out.println("Spring initialized...");

			/**
			 * Facility Maintenance usage
			 */
			IMaintenanceUsage maintenanceUsage = context.getBean(IMaintenanceUsage.class);
			IFacilityUsage facilityUsage = context.getBean(IFacilityUsage.class);

			out.println("\n<<< Creating Facility: >>>");
			// Create a facility with a new address
			Facility facility = DataCreator.createFacility("Skyscraper", "400x400", 1000, "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3125561234", 400, 400, 400);
			Facility facilityDownTown = DataCreator.createFacility("Downtown", "200x200", 1000, "1 Downtown Ave", "New York", "NY", "10018", "USA", "2125562234", 200, 200, 200);

			out.println("\n<<< Adding Facility to DB: >>>");
			// Add this facility to database
			facilityUsage.addFacility(facility);
			facilityUsage.addFacility(facilityDownTown);

			// Print facility Info
			out.println("\n<<< Facility: >>>");
			out.println(facility);
			out.println(facilityDownTown);

			Maintenance watermaintenance = new Maintenance("water", 100);
			Maintenance electricmaintenance = new Maintenance("electric", 1000);

			out.println("\n<<< Adding water and electric scheduled maintenance: >>>");
			maintenanceUsage.scheduleMaintenance(facility, watermaintenance);
			maintenanceUsage.scheduleMaintenance(facility, electricmaintenance);
			
			out.println("\n<<< Adding scheduled maintenance to Downtown: >>>");

			Maintenance maintenance2 = new Maintenance("electric", 1000);
			Maintenance maintenance3 = new Maintenance("water", 1000);
			Maintenance maintenance4 = new Maintenance("gas", 1000);

			maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance2);
			maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance3);
			maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance4);

			out.println("\n<<< List scheduled maintenance for all facilities: >>>");
			maintenanceUsage.listScheduledMaintenance();


			out.println("\n<<< Adding gas maintenance request: >>>");
			Maintenance gasmaintenance = new Maintenance("gas", 250);
			maintenanceUsage.makeFacilityMaintRequest(gasmaintenance, facility);

			Maintenance gasmaintenance2 = new Maintenance("gas", 250);
			Maintenance watermaintenance2 = new Maintenance("gas", 250);
			maintenanceUsage.makeFacilityMaintRequest(gasmaintenance2, facilityDownTown);
			maintenanceUsage.makeFacilityMaintRequest(watermaintenance2, facilityDownTown);

			out.println("\n<<< List Maintenance requests: >>>");
			maintenanceUsage.listMaintenanceRequests();

			out.println("\n<<< Calculate Down Time: >>>");
			maintenanceUsage.calcDownTime(facility);

			out.println("\n<<< Calculate Maintenance cost: >>>");
			maintenanceUsage.calcMaintenanceCost(facility);

			out.println("\n<<< Calculate Problem Rate: >>>");
			maintenanceUsage.calcProblemRate(facility);

			out.println("\n<<< List Facility problems: >>>");
			maintenanceUsage.listFacilityProblems(facility);

			out.println("\n<<< Facility/Maintenance Update: >>>");

			out.println("\n<<< Revoke maintenance: >>>");
			maintenanceUsage.revokeMaintenanceRequest(gasmaintenance, facility);

			out.println("\n<<< Facility from DB: >>>");
			out.println(facilityUsage.getFacility(facility.getId()));

			out.println("\n<<< Removing all facility and related maintenance: >>>");
			facilityUsage.removeFacility(facility);
			facilityUsage.removeFacility(facilityDownTown);

			out.println("\n<<< End Facility/Maintenance Usage >>>");

			// Close the Spring Context
			context.close();

			out.println("\n**********************************************************");
			out.println("***************** End Maintenance Usage ******************");
			out.println("**********************************************************");
		}
		out.println("\n\n\n");
		out.println("---------------------------------------------------------");
		out.println("\n\n\n");
		out.flush();
	}

	public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(new ServletHolder(new HerokuMain()), "/*");
		server.start();
		server.join();
	}
}
