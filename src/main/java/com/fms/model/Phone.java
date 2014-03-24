/**
 * 
 */
package com.fms.model;


public class Phone {

	private Long id;

	private String type = null;
	private String country = null;

	public void setId(Long id) {
		this.id = id;
	}

	private String area = null;
	private String number = null;

	public Phone() {
	}

	public Phone(String Type, String Country, String Area, String Number) {
		this.type = Type;
		this.country = Country;
		this.area = Area;
		this.number = Number;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String Type) {
		this.type = Type;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String Country) {
		this.country = Country;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String Area) {
		this.area = Area;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String Number) {
		this.number = Number;
	}

	@Override
	public String toString() {
		return "Phone [id=" + id + ", type=" + type + ", country=" + country + ", area=" + area + ", number="
				+ number + "]";
	}

}// end class
