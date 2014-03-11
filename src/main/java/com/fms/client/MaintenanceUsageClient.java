package com.fms.client;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.service.IFacilityUsage;
import com.fms.service.IMaintenanceUsage;
import com.fms.model.Facility;
import com.fms.model.Maintenance;

public class MaintenanceUsageClient {
	public static void main(String[] args) {
		System.out.println("***********************************************************");
		System.out.println("***************** Demo Maintenance Usage ******************");
		System.out.println("********************************************************\n");

		System.out.println("Initializing Spring...");

		// Create Spring Application Context
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println("Spring initialized...");

		/**
		 * Facility Maintenance usage
		 */
		IMaintenanceUsage maintenanceUsage = context.getBean(IMaintenanceUsage.class);
		IFacilityUsage facilityUsage = context.getBean(IFacilityUsage.class);

		System.out.println("\n<<< Creating Facility: >>>");
		// Create a facility with a new address
		Facility facility = DataCreator.createFacility("Skyscraper", "400x400", 1000, "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "3125561234", 400, 400, 400);
		Facility facilityDownTown = DataCreator.createFacility("Downtown", "200x200", 1000, "1 Downtown Ave", "New York", "NY", "10018", "USA", "2125562234", 200, 200, 200);

		System.out.println("\n<<< Adding Facility to DB: >>>");
		// Add this facility to database
		facilityUsage.addFacility(facility);
		facilityUsage.addFacility(facilityDownTown);

		// Print facility Info
		System.out.println("\n<<< Facility: >>>");
		System.out.println(facility);
		System.out.println(facilityDownTown);

		Maintenance watermaintenance = new Maintenance("water", 100);
		Maintenance electricmaintenance = new Maintenance("electric", 1000);

		System.out.println("\n<<< Adding water and electric scheduled maintenance: >>>");
		maintenanceUsage.scheduleMaintenance(facility, watermaintenance);
		maintenanceUsage.scheduleMaintenance(facility, electricmaintenance);
		
		System.out.println("\n<<< Adding scheduled maintenance to Downtown: >>>");

		Maintenance maintenance2 = new Maintenance("electric", 1000);
		Maintenance maintenance3 = new Maintenance("water", 1000);
		Maintenance maintenance4 = new Maintenance("gas", 1000);

		maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance2);
		maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance3);
		maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance4);

		System.out.println("\n<<< List scheduled maintenance for all facilities: >>>");
		maintenanceUsage.listScheduledMaintenance();


		System.out.println("\n<<< Adding gas maintenance request: >>>");
		Maintenance gasmaintenance = new Maintenance("gas", 250);
		maintenanceUsage.makeFacilityMaintRequest(gasmaintenance, facility);

		Maintenance gasmaintenance2 = new Maintenance("gas", 250);
		Maintenance watermaintenance2 = new Maintenance("gas", 250);
		maintenanceUsage.makeFacilityMaintRequest(gasmaintenance2, facilityDownTown);
		maintenanceUsage.makeFacilityMaintRequest(watermaintenance2, facilityDownTown);

		System.out.println("\n<<< List Maintenance requests: >>>");
		maintenanceUsage.listMaintenanceRequests();

		System.out.println("\n<<< Calculate Down Time: >>>");
		maintenanceUsage.calcDownTime(facility);

		System.out.println("\n<<< Calculate Maintenance cost: >>>");
		maintenanceUsage.calcMaintenanceCost(facility);

		System.out.println("\n<<< Calculate Problem Rate: >>>");
		maintenanceUsage.calcProblemRate(facility);

		System.out.println("\n<<< List Facility problems: >>>");
		maintenanceUsage.listFacilityProblems(facility);

		System.out.println("\n<<< Facility/Maintenance Update: >>>");

		System.out.println("\n<<< Revoke maintenance: >>>");
		maintenanceUsage.revokeMaintenanceRequest(gasmaintenance, facility);

		System.out.println("\n<<< Facility from DB: >>>");
		System.out.println(facilityUsage.getFacility(facility.getId()));

		System.out.println("\n<<< Removing all facility and related maintenance: >>>");
		facilityUsage.removeFacility(facility);
		facilityUsage.removeFacility(facilityDownTown);

		System.out.println("\n<<< End Facility/Maintenance Usage >>>");

		// Close the Spring Context
		context.close();

		System.out.println("\n**********************************************************");
		System.out.println("***************** End Maintenance Usage ******************");
		System.out.println("**********************************************************");
	}
}
