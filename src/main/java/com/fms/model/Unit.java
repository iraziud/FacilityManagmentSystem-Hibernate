/**
 * 
 */
package com.fms.model;


/**
 *
 * 
 */

public class Unit {

	public void setId(Long id) {
		this.id = id;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}


	private Long id;

	private double length, width, height;
	private String Type;


	private Facility facility;

	public Unit() {
	}

	public Unit(long id, double length, double width, double height, String type) {
		this.id = id;
		this.length = length;
		this.width = width;
		this.height = height;
		this.Type = type;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Unit [id=" + id + ", length=" + length + ", width=" + width + ", height=" + height
				+ ", Type=" + Type + "]";
	}

}// end class
