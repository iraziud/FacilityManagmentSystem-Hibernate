package com.fms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Maintenance {

	@Id
	@GeneratedValue
	private Long id;

	private String Type;
	private double Cost;

	public Maintenance() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Maintenance(String type, double cost) {
		this.Type = type;
		this.Cost = cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public void setCost(double cost) {
		Cost = cost;
	}

	public double getCost() {
		return Cost;
	}

	@Override
	public String toString() {
		return "Maintenance [id=" + id + ", Type=" + Type + ", Cost=" + Cost + "]";
	}

}
