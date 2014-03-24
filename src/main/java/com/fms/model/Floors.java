/**
 * 
 */
package com.fms.model;




public class Floors implements IFloors {


	private Long id;
	private int personCapacity, numRooms;
	private double length, width, height;

	public void setId(Long id) {
		this.id = id;
	}


	private Facility facility;

	public Floors() {
	}

	public Floors(long id, int pCapacity, int nRooms, double l, double w, double h) {
		this.id = id;
		this.personCapacity = pCapacity;
		this.numRooms = nRooms;
		this.length = l;
		this.width = w;
		this.height = h;
	}

	public int getPersonCapacity() {
		return personCapacity;
	}

	public void setPersonCapacity(int capacity) {
		this.personCapacity = capacity;
	}

	public Long getId() {
		return id;
	}

	public int getRooms() {
		return numRooms;
	}

	public int getNumRooms() {
		return numRooms;
	}

	public void setNumRooms(int numRooms) {
		this.numRooms = numRooms;
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

	public void setId(long id) {
		this.id = id;
	}

	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public int getCapacity() {
		return personCapacity;
	}

	public void updateCapacity(int capacity) {
		this.personCapacity = capacity;
	}

	public String getDimension() {
		return "[" + "Length=" + length + ", Width=" + width + ", Height=" + height + "]";
	}

	@Override
	public String toString() {
		return "Floors [id=" + id + ", personCapacity=" + personCapacity + ", numRooms=" + numRooms
				+ ", length=" + length + ", width=" + width + ", height=" + height + "]";
	}

}// end class
