package com.fms.client;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.service.IFacilityUsage;
import com.fms.service.IMaintenanceUsage;
import com.fms.util.FMSLogger;
import com.fms.model.Facility;
import com.fms.model.Maintenance;

public class MaintenanceUsageClient {
    public static void main(String[] args) {
        FMSLogger.log.debug("***********************************************************");
        FMSLogger.log.debug("***************** Demo Maintenance Usage ******************");
        FMSLogger.log.debug("********************************************************\n");

        FMSLogger.log.debug("Initializing Spring...");

        // Create Spring Application Context
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        FMSLogger.log.debug("Spring initialized...");

        /**
         * Facility Maintenance usage
         */
        IMaintenanceUsage maintenanceUsage = context.getBean(IMaintenanceUsage.class);
        IFacilityUsage facilityUsage = context.getBean(IFacilityUsage.class);

        FMSLogger.log.debug("\n<<< Creating Facility: >>>");
        // Create a facility with a new address
        Facility facility = DataCreator.createFacility("SkyScraper", "400x400", 1000, "620 8th Ave", "New York", "NY", "07094", "USA", "2125561234", 400, 400, 400);
        Facility facilityDownTown = DataCreator.createFacility("DownTown", "200x200", 1000, "1 Downtown Ave", "New York", "NY", "10018", "USA", "2125562234", 200, 200, 200);

        FMSLogger.log.debug("\n<<< Adding Facility to DB: >>>");
        // Add this facility to database
        facilityUsage.addFacility(facility);
        facilityUsage.addFacility(facilityDownTown);

        // Print facility Info
        FMSLogger.log.debug("\n<<< Facility: >>>");
        FMSLogger.log.debug(facility);
        FMSLogger.log.debug(facilityDownTown);

        Maintenance watermaintenance = new Maintenance("water", 100);
        Maintenance electricmaintenance = new Maintenance("electric", 1000);

        FMSLogger.log.debug("\n<<< Adding water and electric scheduled maintenance: >>>");
        maintenanceUsage.scheduleMaintenance(facility, watermaintenance);
        maintenanceUsage.scheduleMaintenance(facility, electricmaintenance);

        FMSLogger.log.debug("\n<<< Adding scheduled maintenance to Downtown: >>>");

        Maintenance maintenance2 = new Maintenance("electric", 1000);
        Maintenance maintenance3 = new Maintenance("water", 1000);
        Maintenance maintenance4 = new Maintenance("gas", 1000);

        maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance2);
        maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance3);
        maintenanceUsage.scheduleMaintenance(facilityDownTown, maintenance4);

        FMSLogger.log.debug("\n<<< List scheduled maintenance for all facilities: >>>");
        maintenanceUsage.listScheduledMaintenance();


        FMSLogger.log.debug("\n<<< Adding gas maintenance request: >>>");
        Maintenance gasmaintenance = new Maintenance("gas", 250);
        maintenanceUsage.makeFacilityMaintRequest(gasmaintenance, facility);

        Maintenance gasmaintenance2 = new Maintenance("gas", 250);
        Maintenance watermaintenance2 = new Maintenance("gas", 250);
        maintenanceUsage.makeFacilityMaintRequest(gasmaintenance2, facilityDownTown);
        maintenanceUsage.makeFacilityMaintRequest(watermaintenance2, facilityDownTown);

        FMSLogger.log.debug("\n<<< List Maintenance requests: >>>");
        maintenanceUsage.listMaintenanceRequests();

        FMSLogger.log.debug("\n<<< Calculate Down Time: >>>");
        maintenanceUsage.calcDownTime(facility);

        FMSLogger.log.debug("\n<<< Calculate Maintenance cost: >>>");
        maintenanceUsage.calcMaintenanceCost(facility);

        FMSLogger.log.debug("\n<<< Calculate Problem Rate: >>>");
        maintenanceUsage.calcProblemRate(facility);

        FMSLogger.log.debug("\n<<< List Facility problems: >>>");
        maintenanceUsage.listFacilityProblems(facility);

        FMSLogger.log.debug("\n<<< Facility/Maintenance Update: >>>");

        FMSLogger.log.debug("\n<<< Revoke maintenance: >>>");
        maintenanceUsage.revokeMaintenanceRequest(gasmaintenance, facility);

        FMSLogger.log.debug("\n<<< Facility from DB: >>>");
        FMSLogger.log.debug(facilityUsage.getFacility(facility.getId()));

        FMSLogger.log.debug("\n<<< Removing all facility and related maintenance: >>>");
        facilityUsage.removeFacility(facility);
        facilityUsage.removeFacility(facilityDownTown);

        FMSLogger.log.debug("\n<<< End Facility/Maintenance Usage >>>");

        // Close the Spring Context
        context.close();

        FMSLogger.log.debug("\n**********************************************************");
        FMSLogger.log.debug("***************** End Maintenance Usage ******************");
        FMSLogger.log.debug("**********************************************************");
    }
}
