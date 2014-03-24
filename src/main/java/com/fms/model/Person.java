package com.fms.model;

import java.util.ArrayList;
import java.util.List;


import com.fms.util.FMSLogger;


public class Person {


	private Long id;
	private String fname;
	private String middlename;
	private String lname;
	private String DOB;

	private List<Phone> phone = new ArrayList<Phone>();

	private List<Address> address = new ArrayList<Address>();
	private String email;

	public List<Facility> usesFacility = new ArrayList<Facility>();

	public Person() {
	}

	public Person(long id, String Fname, String Lname, String dob) {
		this.id = id;
		this.fname = Fname;
		this.lname = Lname;
		this.DOB = dob;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.fname;
	}

	public void setFirstName(String Fname) {
		this.fname = Fname;
	}

	public String getLastName() {
		return this.lname;
	}

	public void setLastName(String Lname) {
		this.lname = Lname;
	}

	public String getMiddleName() {
		return this.middlename;
	}

	public void setMiddleName(String mname) {
		this.middlename = mname;
	}

	public String getFullName() {
		if (this.middlename != null)
			return this.fname + " " + this.middlename + " " + this.lname;
		else
			return this.fname + " " + this.lname;
	}

	public List<Address> getAddress() {
		return this.address;
	}

	public void addAddress(Address address) {
		this.address.add(address);
	}

	public boolean removeAddress(Address address) {
		if (this.address.contains(address)) {
			this.address.remove(address);
		}
		return !this.address.contains(address);
	}

	public List<Phone> getPhone() {
		return this.phone;
	}

	public void addPhone(Phone phone) {
		this.phone.add(phone);
	}

	public boolean removePhone(Phone phone) {
		if (this.phone.contains(phone)) {
			this.phone.remove(phone);
		}
		return !this.phone.contains(phone);
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDOB() {
		return this.DOB;
	}

	public void setDOB(String dob) {
		this.DOB = dob;
	}

	public void listPersonFacilities() {
		FMSLogger.log.debug("Person: " + this.getId() + ": " + this.getName() + " users the following facilities: ");
		for (IFacility f : usesFacility)
			FMSLogger.log.debug(f.getFacilityInfo());
		FMSLogger.log.debug("");
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", fname=" + fname + ", middlename=" + middlename + ", lname=" + lname
				+ ", DOB=" + DOB + ", phone=" + phone + ", address=" + address + ", email=" + email
				+ ", usesFacility=" + usesFacility + "]";
	}

	public String getName() {
		return fname + " " + lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public List<Facility> getUsesFacility() {
		return usesFacility;
	}

	public void setUsesFacility(List<Facility> usesFacility) {
		this.usesFacility = usesFacility;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
}// end class

