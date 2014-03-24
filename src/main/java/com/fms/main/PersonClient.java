package com.fms.main;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.service.IPersonService;
import com.fms.util.FMSLogger;
import com.fms.model.*;

public class PersonClient {
	public static void main(String[] args) {
		FMSLogger.log.debug("******************************************************");
		FMSLogger.log.debug("***************** Demo Person Usage ******************");
		FMSLogger.log.debug("********************************************************\n");

		FMSLogger.log.debug("Initializing Spring...");

		// Create Spring Application Context
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		FMSLogger.log.debug("Spring initialized...");

		IPersonService personService = context.getBean(IPersonService.class);
		DataCreator dataCreator = context.getBean(DataCreator.class);

		/**
		 * Person usage
		 */
		FMSLogger.log.debug("\n<<< Create new person >>>");

		// Create a new person
        Person person1 = DataCreator.createPerson("Irfan", "Raziuddin", "iraziud@gmail.com", "01-01-1985", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person2 = DataCreator.createPerson("Zain", "Maqsood", "zmaqsood@gmail.com", "01-01-1986", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661237");
        Person person3 = DataCreator.createPerson("Frank", "Thumb", "frank@gmail.com", "01-01-1987", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661238");
        Person person4 = DataCreator.createPerson("Chris", "Cross", "chris@gmail.com", "01-01-1988", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661239");
        Person person5 = DataCreator.createPerson("Julia", "Stiles", "julia@gmail.com", "01-01-1989", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661240");
        Person person6 = DataCreator.createPerson("Robert", "Redford", "robert@gmail.com", "01-01-1990", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661241");
        Person person7 = DataCreator.createPerson("Hugh", "Grant", "hugh@gmail.com", "01-01-1991", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661242");

        FMSLogger.log.debug("\n<<< Save person to database >>>");
		// Save the person to the database
		personService.addPerson(person1);
		personService.addPerson(person2);
		personService.addPerson(person3);
		personService.addPerson(person4);
		personService.addPerson(person5);
		personService.addPerson(person6);
		personService.addPerson(person7);

		FMSLogger.log.debug("\n<<< Remove address >>>");
		Address address = person1.getAddress().get(0);
		person1.removeAddress(address);

		FMSLogger.log.debug("\n<<< Update phone >>>");
		Phone phone = person1.getPhone().get(0);
        phone.setArea("917");
		phone.setNumber("4281234");

		// Add new phone
		Phone workphone = context.getBean(Phone.class);
		workphone.setNumber("6661234");
		workphone.setArea("212");
        workphone.setType("Work");
		workphone.setCountry("+1");

		person1.addPhone(workphone);

		FMSLogger.log.debug("\n<<< Update in DB >>>");
		// Update person details:
		person1.setEmail("iraziud@outlook.com");
		personService.updatePerson(person1);

		FMSLogger.log.debug("\n<<< Retrieved from database >>>");
		FMSLogger.log.debug(personService.getPerson(person1.getId()));

		// Remove person itself
		FMSLogger.log.debug("\n<<< Remove person >>>");
		personService.removePerson(person1);
		personService.removePerson(person2);
		personService.removePerson(person3);
		personService.removePerson(person4);
		personService.removePerson(person5);
		personService.removePerson(person6);
		personService.removePerson(person7);

		// Close Spring Application Context
		context.close();

		FMSLogger.log.debug("\n*****************************************************");
		FMSLogger.log.debug("***************** End Person Usage ******************");
		FMSLogger.log.debug("*****************************************************");
	}
}
