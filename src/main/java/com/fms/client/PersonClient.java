package com.fms.client;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.service.IPersonService;
import com.fms.model.Address;
import com.fms.model.Person;
import com.fms.model.Phone;

public class PersonClient {
	public static void main(String[] args) {
		System.out.println("******************************************************");
		System.out.println("***************** Demo Person Usage ******************");
		System.out.println("********************************************************\n");

		System.out.println("Initializing Spring...");

		// Create Spring Application Context
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		System.out.println("Spring initialized...");

		IPersonService personService = context.getBean(IPersonService.class);

		/**
		 * Person usage
		 */
		System.out.println("\n<<< Create new person >>>");

		// Create a new person
        Person person1 = DataCreator.createPerson("Irfan", "Raziuddin", "iraziud@gmail.com", "01-01-1985", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person2 = DataCreator.createPerson("Zain", "Maqsood", "zmaqsood@gmail.com", "01-01-1986", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person3 = DataCreator.createPerson("Frank", "Thumb", "frank@gmail.com", "01-01-1987", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person4 = DataCreator.createPerson("Chris", "Cross", "chris@gmail.com", "01-01-1988", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person5 = DataCreator.createPerson("Julia", "Stiles", "julia@gmail.com", "01-01-1989", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person6 = DataCreator.createPerson("Robert", "Redford", "robert@gmail.com", "01-01-1990", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");
        Person person7 = DataCreator.createPerson("Hugh", "Grant", "hugh@gmail.com", "01-01-1991", "800 Michigan Ave", "Chicago", "IL", "60611", "USA", "312","6661236");

        System.out.println("\n<<< Save person to database >>>");
		// Save the person to the database
		personService.addPerson(person1);
		personService.addPerson(person2);
		personService.addPerson(person3);
		personService.addPerson(person4);
		personService.addPerson(person5);
		personService.addPerson(person6);
		personService.addPerson(person7);

		System.out.println("\n<<< Remove address >>>");
		Address address = person1.getAddress().get(0);
		person1.removeAddress(address);

		System.out.println("\n<<< Update phone >>>");
		Phone phone = person1.getPhone().get(0);
        phone.setArea("917");
		phone.setNumber("4281234");

		// Add new phone
		Phone workphone = new Phone();
		workphone.setArea("312");
        workphone.setNumber("6661234");
		workphone.setType("Work");
		workphone.setCountry("+1");

		person1.addPhone(workphone);

		System.out.println("\n<<< Update in DB >>>");
		// Update person details:
		person1.setEmail("iraziud@outlook.com");
		personService.updatePerson(person1);

		System.out.println("\n<<< Retrieved from database >>>");
		System.out.println(personService.getPerson(person1.getId()));
		System.out.println(personService.getPerson(person2.getId()));

		// Remove person itself
		System.out.println("\n<<< Remove person >>>");
		personService.removePerson(person1);
		personService.removePerson(person2);
		personService.removePerson(person3);
		personService.removePerson(person4);
		personService.removePerson(person5);
		personService.removePerson(person6);
		personService.removePerson(person7);

		// Close Spring Application Context
		context.close();

		System.out.println("\n*****************************************************");
		System.out.println("***************** End Person Usage ******************");
		System.out.println("*****************************************************");
	}
}
